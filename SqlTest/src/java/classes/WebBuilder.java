/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public class WebBuilder {
    private PrintWriter out;
    
    public WebBuilder(PrintWriter out) {
        this.out = out;
    }
    
    /**
     * Writes the start of the html page
     * @param title title of the page, visible on browser tab
     * @param bodyId id of body (entire page) for full page customisation
     */
    public void writeHead (String title, String bodyId) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");     
        
        //links the css document, important
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"");
        
        out.println("</head>");
        out.println("<body class=\"" + bodyId + "\">");
    }
    
    
    //ArrayList objects should consist of PrintObject
    public void writeDivs (ArrayList<PrintObject> objects, String objClass, String attClass) {
        
        for (int i = 0; i < objects.size(); i++) {
            //gets the attributes of every PrintObject in the ArrayList
            ArrayList attributes = objects.get(i).getAttributes();
            
            //Container div class is printed here
            out.println("<div class=\"" + objClass + "\">");
            
            //prints a div for each attribute
            for (int j = 0; j < attributes.size(); j++) {
                out.println("<div class=\"" + attClass + "\">" + attributes.get(j) + "</div>");
            }
            //closes div
            out.println("</div>");
        }
    }
    
    public void writePageHeader (String header, String headerClass) {
        out.println("<h1 class=\"" + headerClass + "\">" + header + "</h1>");
    }

    /**
     * Writes the end of the html page (might not be strictly necessary)
     */
    public void writeEndOfDoc() {
        out.println("</body>");
        out.println("</html>");
    }
}
