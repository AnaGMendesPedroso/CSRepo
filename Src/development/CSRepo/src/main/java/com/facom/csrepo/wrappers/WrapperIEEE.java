package com.facom.csrepo.wrappers;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.Publisher;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.EditionDao;
import com.facom.csrepo.model.dao.PaperDao;
import com.facom.csrepo.model.dao.PublisherDao;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
    PublisherDao publisherDao;

    List<Conference> listConferences;
    
    Publisher publisher;
    Edition edition;

//    public WrapperIEEE(){
//        authorDao = new AuthorDao();
//        authorDao.openCurrentSessionWithTransaction();
//        paperDao = new PaperDao();
//        start();
//    }
    
    public WrapperIEEE(List<Conference> conferences){
        scheduleWrapper(conferences);
        //start(conferences);
    }
    
    private void initDao(){
        authorDao = new AuthorDao();
        paperDao = new PaperDao();
        editionDao = new EditionDao();
        publisherDao = new PublisherDao();
        
        authorDao.openCurrentSessionWithTransaction();
        paperDao.openCurrentSession();
        editionDao.openCurrentSessionWithTransaction();
        
        publisherDao.openCurrentSession();
        publisher = publisherDao.findByAcronym("IEEE").get(0);
        publisherDao.closeCurrentSession();
    }
    
    private void closeDao(){
        editionDao.closeCurrentSessionWithTransaction();
        paperDao.closeCurrentSession();
        authorDao.closeCurrentSessionWithTransaction();
    }
    
    private void scheduleWrapper(List<Conference> conferences){
        Timer timer = new Timer();
        int first, last;
        first = 0;
        last = 185;
        
        int days = 1;
        
        start(conferences.subList(first, last));
        
        for(first = last; first < conferences.size(); first += 185){
            if((conferences.size() - first) >= 185)
                last += 185; 
            else
                last = conferences.size();
            
            timer.schedule(new DailyTask(conferences.subList(first, last)), getDate(days));
            days++;
        }
    }
    
    private Date getDate(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        
        return calendar.getTime();
    }
    
    private void start(List<Conference> conferences){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
        
        int year = calendar.get(Calendar.YEAR);
        boolean conferenceAdded;
        
        initDao();
        
        for(Conference conference : conferences){
            System.out.println(conference.getId() + " blablabla" + conference.getName());
        }
        
        for(Conference conference : conferences){
            List<Edition> editions = editionDao.findByConferenceId(conference.getId());
            conferenceAdded = false;
            
            // Check if the conference was already added this year
            if(!editions.isEmpty()){
                for(Edition edition : editions)
                    if(edition.getYear() >= year){
                        conferenceAdded = true;
                        break;
                     }
                if(conferenceAdded)
                    continue;
            }
            
            // method that returns papers from a searched conference
            NodeList papers = searchConference(conference, year);
            
            if(papers != null){
                System.out.println("Achou papers da conferencia");
                // method that builds metadata for each paper
                buildMetadata(papers, conference);
            }
        }
        
        closeDao();
    }

    private NodeList searchConference(Conference conference, int year) {
        String search;
        String name, acronym, apiKey;
        
        search = "http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title="
                + "NAMEANDACRONYM&publication_year=YEAR&max_records=1000&format=xml&apikey=APIKEY";
        name = "\"" + conference.getName() + "\"";
        acronym = "\"" + conference.getAcronym() + "\"";
        apiKey = "xsgdpynaaxuxtwummncmbbxs";

        search = search.replaceAll("NAME", name);
        search = search.replaceAll("ACRONYM", acronym);
        search = search.replaceAll("YEAR", Integer.toString(year));
        search = search.replaceAll("APIKEY", apiKey);
        search = search.replaceAll(" ", "%20");
        System.out.println(search);

        // search a conference in the IEE API and returns a string contents in Json format
        Document searchConference = getPapers(search);
        
        //Check if the request failed
        if(searchConference == null){
            return null;
        }
            
        String total = ((Element)searchConference.getElementsByTagName("articles").item(0)).getElementsByTagName("totalfound").item(0).getTextContent();
        System.out.println(total);
        
        // Check if the request return a conference
        if(Integer.parseInt(total) <= 0)
            return null;
        
        NodeList papers = searchConference.getElementsByTagName("article");
        
        // Create a new edition
        edition = new Edition(year);
        edition.setConference(conference);
        edition.setPublisher(publisher);
        editionDao.insert(edition);

        return papers;
    }

    private void buildMetadata(NodeList papers, Conference conference) {
        //declarando atributos dos metadados de um paper
        Paper paper;
        String paperTitle;
        NodeList authors;
        List<Author> listAuthor;
        
        int publicationYear, firstPage, lastPage, pages;

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
                paper.setConference(conference);
                paper.setPublisher(publisher);
                paper.setEdition(edition);

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
    }
    
    private Document getPapers(String uriStr) {
        HttpURLConnection connection;
        
        try {
            URL url = new URL(uriStr);

            connection = (HttpURLConnection) url.openConnection();
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

    private void insertAuthorDB(List<Author> authors, Paper paper) {
        Author author;
        for (int i = 0; i < authors.size(); i++) {
            List<Author> tmpAuthors = authorDao.findByName(authors.get(i).getName());
            
            if(tmpAuthors.isEmpty()){
                author = authors.get(i);
                author.addPaper(paper);
                authorDao.insert(author);
            }else{
                author = tmpAuthors.get(0);
                author.addPaper(paper);
                authorDao.update(author);
            }
        }
    }
    
    private boolean existPaper(String paperTitle){
        boolean exist;
        
        //Check if dao found a paper in database
        exist = !paperDao.findByName(paperTitle).isEmpty();
        
        return exist;
    }
    
    class DailyTask extends TimerTask{
        
        List<Conference> conferences;
        
        public DailyTask(List<Conference> conferences){
            this.conferences = conferences;
        }

        @Override
        public void run() {
            start(conferences);
        }
        
    }
}