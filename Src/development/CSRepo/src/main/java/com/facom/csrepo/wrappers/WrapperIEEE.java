package com.facom.csrepo.wrappers;

import com.google.gson.Gson;
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

/**
 *
 * @author Renato Gouvea
 */
public class WrapperIEEE {

    public static void main(String[] args) {
        String text1 = "http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title=";
        String text2 = "format=json&apikey=xsgdpynaaxuxtwummncmbbxs";
        
        String text = getText("http://ieeexploreapi.ieee.org/api/v1/search/articles?publication_title=ICSE&max_records=5000&format=json&apikey=xsgdpynaaxuxtwummncmbbxs");
        //Converter para Gson
        //Gson g = new Gson();
        //text = g.toJson(text);
        System.out.println(text);
    }
    
    public static String getText(String uriStr) {
        final StringBuffer buf = new StringBuffer(1000);    
        try {
            HTMLDocument doc = new HTMLDocument() {
                public HTMLEditorKit.ParserCallback getReader(int pos) {
                    return new HTMLEditorKit.ParserCallback() {
                        public void handleText(char[] data, int pos) {
                            buf.append(data);
                            buf.append('\n');
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
        String result = buf.toString();
        Gson g = new Gson();
        result = g.toJson(result);
        //g = result;
        return result;
    }

}
