package com.facom.csrepo.wrappers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
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
        // search a conference in the IEE API and returns a string contents in Json format
        String searchConference = getPapers("http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title=ICSE-SEET&max_records=5000&format=json&apikey=xsgdpynaaxuxtwummncmbbxs");
        
        // converting "getPapers" from String to Json object
        JSONObject resultSearch = new JSONObject(searchConference);
        
        // getting the pappers through JSONArray
        JSONArray papers = resultSearch.getJSONArray("articles");
        //List<String> list = new ArrayList<String>();
        
        // total of papers returned from IEEE API
        int totalPapers = resultSearch.getInt("total_records");
        System.out.println(totalPapers);
        
        return papers;
    }
    
    public static void buildMetadata(JSONArray papers){
        //declarando atributos dos metadados de um paper
        String paperTitle, nameConference, paperAuthor;
        int yearPublication;
        
        for (int i=0; i<papers.length(); i++) {
            System.out.println("PAPER" + (i+1) + ": " + papers.getJSONObject(i).getString("title"));
        }
        //for (int i = 0; i < totalPapers; i++) {
            //resultSearch = articles.toJSONObject(articles);
                    //resultSearch.getString("title");
            //System.out.println(resultSearch);
        //}
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
