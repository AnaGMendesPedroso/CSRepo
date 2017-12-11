package com.facom.csrepo.wrappers;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.PaperDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
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

    public WrapperIEEE() {
    }

    public static void main(String[] args) {
        // method that returns papers from a searched conference
        NodeList papers = searchConference();

        // method that builds metadata for each paper
        buildMetadata(papers);
    }

    public static NodeList searchConference() {
        String search = "http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title="
                + "$name$AND$acronym$&publication_year=$year$&max_records=1000&format=json&apikey=$apiKey$";
        String name, acronym, year, apiKey;
        name = "International Conference on Software Engineering";
        acronym = "ICSE";
        year = "2017";
        apiKey = "xsgdpynaaxuxtwummncmbbxs";
        
        search = search.replaceAll("\\$name\\$", name);
        search = search.replaceAll("\\$acronym\\$", acronym);
        search = search.replaceAll("\\$year\\$", year);
        search = search.replaceAll("\\$apiKey\\$", apiKey);
        System.out.println(search);
        
        // search a conference in the IEE API and returns a string contents in Json format
        //String searchConference = getPapers(search);
        Document document = getPapers("http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title=ICSE-SEET&max_records=1000&format=xml&apikey=xsgdpynaaxuxtwummncmbbxs");
        //Document document = getPapers(search);

        // converting "getPapers" from String to Json object
//        JSONObject resultSearch = new JSONObject(searchConference);

        // getting the pappers through JSONArray
//        JSONArray papers = resultSearch.getJSONArray("articles");
        Element total = (Element)document.getElementsByTagName("articles").item(0);
        System.out.println(total.getElementsByTagName("totalfound").item(0).getTextContent());
        NodeList papers = document.getElementsByTagName("article");

        // total of records returned from IEEE API
//        int totalRecords = resultSearch.getInt("total_records");
//        System.out.println("Total records: " + totalRecords + "\n");

        return papers;
    }

    public static void buildMetadata(NodeList papers) {
        //declarando atributos dos metadados de um paper
        NodeList authors;
        String paperTitle, year;
        List<Author> nameAuthor;
        int yearPublication, firstPage, lastPage, pages;
        int cont = 0;
        
        for(int i = 0; i < papers.getLength(); i++){
            try{
                Node node = papers.item(i);

                //if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element)node;
                authors = element.getElementsByTagName("authors");
                authors = ((Element)authors.item(0)).getElementsByTagName("author");
                
                System.out.println(authors.getLength());
                Integer rank = Integer.parseInt(element.getElementsByTagName("rank").item(0).getTextContent());
                String title = element.getElementsByTagName("title").item(0).getTextContent();
                title = title.replaceAll("&#x0022;", "\"");
                System.out.println(rank + " Title: " + title);
                //}
            } catch(Exception e){}
        }

//        for (int i = 0; i < papers.length(); i++) {
//            try {
//                authors = papers.getJSONObject(i).getJSONObject("authors").getJSONArray("authors");
//                ++cont;
//
//                paperTitle = papers.getJSONObject(i).getString("title");
//                firstPage = papers.getJSONObject(i).getInt("start_page");
//                lastPage = papers.getJSONObject(i).getInt("end_page");
//                pages = lastPage - firstPage + 1;
//                
//                year = papers.getJSONObject(i).getString("publication_title");
//                //pega no título de publicação apenas o ano
//                yearPublication = Integer.parseInt(year.split(" ")[0]);
//
//                Paper paper = new Paper(paperTitle, pages, yearPublication, firstPage, lastPage);
//                //insertPaperDB(paper);
//                
//                System.out.println("PAPER " + cont + ": " + paperTitle);
//                System.out.println("YEAR: " + yearPublication);
//                System.out.println("Pages: " + pages + " | firstPage: " + firstPage + " | lastPage: " + lastPage);
//                
//                
//                nameAuthor = new ArrayList<>();
//                for (int j = 0; j < authors.length(); j++) {
//                    nameAuthor.add(new Author(authors.getJSONObject(j).getString("full_name")));
//                    System.out.println("Authors: " + nameAuthor.get(j).getName());
//            
//                }
//                //insertAuthorDB(nameAuthor);
//            } catch (Exception e) {
//            }
//        }
    }

    public static void insertPaperDB(Paper paper) {
            PaperDao paperDao = new PaperDao();
            paperDao.insert(paper);
    }
    
    public static void insertAuthorDB(List<Author> authors) {
        AuthorDao dao = new AuthorDao();
        for (int i = 0; i < authors.size(); i++) {
            dao.insert(authors.get(i));
        }
    }

    public static Document getPapers(String uriStr){
        
        try {
            URL url = new URL(uriStr);
            
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            
            InputStream in = connection.getInputStream();
            
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(in);
            document.getDocumentElement().normalize();
            
            return document;
//    public static String getPapers(String uriStr) {
//        final StringBuffer buf = new StringBuffer(1000);
//        try {
//            HTMLDocument doc = new HTMLDocument() {
//                public HTMLEditorKit.ParserCallback getReader(int pos) {
//                    return new HTMLEditorKit.ParserCallback() {
//                        public void handleText(char[] data, int pos) {
//                            buf.append(data);
//                        }
//                    };
//                }
//            };
//            URL url = new URI(uriStr).toURL();
//            URLConnection conn = url.openConnection();
//            Reader rd = new InputStreamReader(conn.getInputStream());
//            EditorKit kit = new HTMLEditorKit();
//            kit.read(rd, doc, 0);
//        } catch (MalformedURLException e) {
//        } catch (URISyntaxException e) {
//        } catch (BadLocationException e) {
//        } catch (IOException e) {
//        }
//        // Return the text found
//        return buf.toString();
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
}
