package com.facom.csrepo.wrappers;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.PaperDao;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Renato Gouvea
 */
public class WrapperIEEE {

    public WrapperIEEE() {
    }

    public static void main(String[] args) {
        // method that returns papers from a searched conference
        JSONArray papers = searchConference();

        // method that builds metadata for each paper
        buildMetadata(papers);
    }

    public static JSONArray searchConference() {
        String search = "http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title="
                + "$name$AND$acronym$&publication_year=$year$&max_records=1000&format=json&apikey=$apiKey$";
        String name, acronym, year, apiKey;
        name = "International Conference on Software Engineering";
        acronym = "ICSE";
        year = "2017";
        apiKey = "xsgdpynaaxuxtwummncmbbxs";
        
        search.replaceAll("\\$name\\$", name);
        search.replaceAll("\\$acronym\\$", acronym);
        search.replaceAll("\\$year\\$", year);
        search.replaceAll("\\$apiKey\\$", apiKey);
        System.out.println(search);
        System.out.println("KKKKKKKKKKK");
        
        // search a conference in the IEE API and returns a string contents in Json format
        String searchConference = getPapers(search);

        // converting "getPapers" from String to Json object
        JSONObject resultSearch = new JSONObject(searchConference);

        // getting the pappers through JSONArray
        JSONArray papers = resultSearch.getJSONArray("articles");

        // total of records returned from IEEE API
        int totalRecords = resultSearch.getInt("total_records");
        System.out.println("Total records: " + totalRecords + "\n");

        return papers;
    }

    public static void buildMetadata(JSONArray papers) {
        //declarando atributos dos metadados de um paper
        JSONArray authors = new JSONArray();
        String paperTitle, year;
        List<Author> nameAuthor;
        int yearPublication, firstPage, lastPage, pages;
        int cont = 0;

        for (int i = 0; i < papers.length(); i++) {
            try {
                authors = papers.getJSONObject(i).getJSONObject("authors").getJSONArray("authors");
                ++cont;

                paperTitle = papers.getJSONObject(i).getString("title");
                firstPage = papers.getJSONObject(i).getInt("start_page");
                lastPage = papers.getJSONObject(i).getInt("end_page");
                pages = lastPage - firstPage + 1;
                
                year = papers.getJSONObject(i).getString("publication_title");
                //pega no título de publicação apenas o ano
                yearPublication = Integer.parseInt(year.split(" ")[0]);

                Paper paper = new Paper(paperTitle, pages, yearPublication, firstPage, lastPage);
                //insertPaperDB(paper);
                
                System.out.println("PAPER " + cont + ": " + paperTitle);
                System.out.println("YEAR: " + yearPublication);
                System.out.println("Pages: " + pages + " | firstPage: " + firstPage + " | lastPage: " + lastPage);
                
                
                nameAuthor = new ArrayList<>();
                for (int j = 0; j < authors.length(); j++) {
                    nameAuthor.add(new Author(authors.getJSONObject(j).getString("full_name")));
                    System.out.println("Authors: " + nameAuthor.get(j).getName());
            
                }
                //insertAuthorDB(nameAuthor);
            } catch (Exception e) {
            }
        }
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

    public static String getPapers(String uriStr) {
        final StringBuffer buf = new StringBuffer(1000);
        try {
            HTMLDocument doc = new HTMLDocument() {
                public HTMLEditorKit.ParserCallback getReader(int pos) {
                    return new HTMLEditorKit.ParserCallback() {
                        public void handleText(char[] data, int pos) {
                            buf.append(data);
                        }
                    };
                }
            };
            URL url = new URI(uriStr).toURL();
            URLConnection conn = url.openConnection();
            Reader rd = new InputStreamReader(conn.getInputStream());
            EditorKit kit = new HTMLEditorKit();
            kit.read(rd, doc, 0);
        } catch (MalformedURLException e) {
        } catch (URISyntaxException e) {
        } catch (BadLocationException e) {
        } catch (IOException e) {
        }
        // Return the text found
        return buf.toString();
    }
}
