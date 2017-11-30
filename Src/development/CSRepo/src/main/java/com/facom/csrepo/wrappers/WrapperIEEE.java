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

    public static void main(String[] args) {
        //String text1 = "http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title=";
        //String text2 = "format=json&apikey=xsgdpynaaxuxtwummncmbbxs";
        
        String text = getText("http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title=ICSE-SEET&max_records=30&format=json&apikey=xsgdpynaaxuxtwummncmbbxs");
        //System.out.println("TEXTO:" + text);

        //konvertierung in ein Json-Objekt
        JSONObject resultSearch = new JSONObject(text);
        String test = resultSearch.getString("articles");
               
        System.out.println("SUAHDSUAHUDAUSHD");
        System.out.println(resultSearch);
                
        //JSONArray articles = resultSearch.getJSONArray("articles");
        
        //getMetadata(resultSearch, articles);
    }
    
    public static void getMetadata(JSONObject resultSearch, JSONArray articles){
        //System.out.println("RESULTADO"+ text);
        //text = json.toString();
        int totalRecords = resultSearch.getInt("total_records");
        //String title = articles.ge
        System.out.println(totalRecords);
        
        //declarando atributos dos metadados de um paper
        String blabla, paperTitle, nameConference, paperAuthor;
        int yearPublication;
        //for (int i = 0; i < totalRecords; i++) {
            //resultSearch = articles.toJSONObject(articles);
                    //resultSearch.getString("title");
            //System.out.println(resultSearch);
        //}
        
    }
    
    public static String getText(String uriStr) {
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
