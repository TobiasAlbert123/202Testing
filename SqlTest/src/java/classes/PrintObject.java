/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public class PrintObject {
    private ArrayList attributes;
    
    
    public PrintObject() {
        attributes = new ArrayList();
    }
    
    public void addAttribute(String attribute) {
        attributes.add(attribute);
    }
    
    public ArrayList getAttributes() {
        return attributes;
    }
}
