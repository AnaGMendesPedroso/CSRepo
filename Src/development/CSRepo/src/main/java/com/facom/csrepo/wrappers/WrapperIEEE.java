package com.facom.csrepo.wrappers;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.EditionDao;
import com.facom.csrepo.model.dao.PaperDao;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Renato Gouvea
 */
public class WrapperIEEE {
    
    AuthorDao authorDao;
    PaperDao paperDao;
    EditionDao editionDao;
    List<Conference> listConferences;

//    public WrapperIEEE(){
//        authorDao = new AuthorDao();
//        authorDao.openCurrentSessionWithTransaction();
//        paperDao = new PaperDao();
//        start();
//    }
    
    public WrapperIEEE(List<Conference> conferences){
        authorDao = new AuthorDao();
        paperDao = new PaperDao();
        editionDao = new EditionDao();
        authorDao.openCurrentSessionWithTransaction();
        paperDao.openCurrentSession();
        editionDao.openCurrentSession();
        start(conferences);
    }
    
    public void start(List<Conference> conferences){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        
        for(Conference conference : conferences){
            // TODO: Verificar se a conferencia ja ocorreu esse ano
            List<Edition> editions = editionDao.findByConferenceId(conference.getId());
            // Verificar se a conferenceia deste ano ja esta adicionada
            
            // method that returns papers from a searched conference
            NodeList papers = searchConference(conference, year);
            // method that builds metadata for each paper
            buildMetadata(papers);
        }
    }

    public NodeList searchConference(Conference conference, int year) {
        String search;
        String name, acronym, apiKey;
        
        search = "http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title="
                + "NAMEANDACRONYM&publication_year=YEAR&max_records=1000&format=xml&apikey=APIKEY";
        name = "\"" + conference.getName() + "\"";
//        name = "\"International Conference on Software Engineering\"";
        acronym = "\"" + conference.getAcronym() + "\"";
//        acronym = "\"ICSE\"";
//        year = "2017";
        apiKey = "xsgdpynaaxuxtwummncmbbxs";

        search = search.replaceAll("NAME", name);
        search = search.replaceAll("ACRONYM", acronym);
        search = search.replaceAll("YEAR", Integer.toString(year));
        search = search.replaceAll("APIKEY", apiKey);
        search = search.replaceAll(" ", "%20");
        System.out.println(search);

        // search a conference in the IEE API and returns a string contents in Json format
        Document searchConference = getPapers(search);
        Element total = (Element) searchConference.getElementsByTagName("articles").item(0);
        // total of records returned from IEEE API
        System.out.println(total.getElementsByTagName("totalfound").item(0).getTextContent());
        NodeList papers = searchConference.getElementsByTagName("article");

        return papers;
    }

    public void buildMetadata(NodeList papers) {
        //declarando atributos dos metadados de um paper
        Paper paper;
        String paperTitle;
        NodeList authors;
        List<Author> listAuthor;
        
        int publicationYear, firstPage, lastPage, pages;
        int cont = 0;

        // each article returned from API
        for (int i = 0; i < papers.getLength(); i++) {
            try {
                Node node = papers.item(i);
                Element element = (Element) node;
                
                //get title
                paperTitle = element.getElementsByTagName("title").item(0).getTextContent();
                paperTitle = paperTitle.replaceAll("&#x0022;", "\"");
                
                //Check if the paper is already exist in the database
                if(existPaper(paperTitle)){
                    System.out.println("Paper already exist");
                    continue;
                }
                
                //get pages
                firstPage = Integer.parseInt(element.getElementsByTagName("start_page").item(0).getTextContent());
                lastPage = Integer.parseInt(element.getElementsByTagName("end_page").item(0).getTextContent());
                pages = lastPage - firstPage + 1;
                //publication year
                String publicationTitle = (element.getElementsByTagName("publication_title").item(0).getTextContent());
                publicationYear = Integer.parseInt(publicationTitle.split(" ")[0]);

                paper = new Paper(paperTitle, pages, publicationYear, firstPage, lastPage);
//                ConferenceDao conference = new ConferenceDao();
//              paper.setConference(conference.findById(200));

                //get authors
                authors = element.getElementsByTagName("authors");
                authors = ((Element) authors.item(0)).getElementsByTagName("author");
                
                listAuthor = new ArrayList<>();
                Author author;
                for (int j = 0; j < authors.getLength(); j++) {
                    String name = ((Element) authors.item(j)).getElementsByTagName("full_name").item(0).getTextContent();
                    author = new Author(name);
                    listAuthor.add(author);
                    System.out.println("Authors: " + listAuthor.get(j).getName());
                }
                insertAuthorDB(listAuthor, paper);
                
            } catch (Exception e) {}
        }
        paperDao.closeCurrentSession();
        authorDao.closeCurrentSessionWithTransaction();
    }
    
    public Document getPapers(String uriStr) {
        try {
            URL url = new URL(uriStr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");

            InputStream in = connection.getInputStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(in);
            document.getDocumentElement().normalize();

            return document;
        } catch (MalformedURLException ex) {
            Logger.getLogger(WrapperIEEE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WrapperIEEE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WrapperIEEE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(WrapperIEEE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAuthorDB(List<Author> authors, Paper paper) {
        Author author;
        for (int i = 0; i < authors.size(); i++) {
            List<Author> tmpAuthors = authorDao.findByName(authors.get(i).getName());
            //boolean newAuthor = authorDao.findByName(authors.get(i).getName()).isEmpty();
            
            if(tmpAuthors.isEmpty()){
                author = authors.get(i);
                author.addPaper(paper);
                authorDao.insert(author);
            }else{
                author = tmpAuthors.get(0);
                author.addPaper(paper);
                authorDao.update(author);
//                authorDao.update(authors.get(i));
            }
        }
    }
    
    private boolean existPaper(String paperTitle){
        boolean exist;
        
        //Check if dao found a paper in database
        exist = !paperDao.findByName(paperTitle).isEmpty();
        
        return exist;
    }
}