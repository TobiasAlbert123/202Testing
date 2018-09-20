/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.PrintWriter;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;


/**
 *
 * @author Tobias
 */
public class Tool {
    Connection conn;
    Statement stmt;
    
    public void writeModules(PrintWriter out) {
        String strSelect = "select * from module;";
        
        try {
            ResultSet rset = stmt.executeQuery(strSelect);
            
            WebBuilder site = new WebBuilder(out);
            ArrayList printObjects = new ArrayList();
            
            int rowCount = 0;
            while (rset.next()) {
                String moduleNr = rset.getString("mnr");
                String moduleName = rset.getString("moduleName");
                String moduleDescription = rset.getString("moduleDescription");
                String modulePoints = rset.getString("module_points");
                
                PrintObject po = new PrintObject();
                
                //adds the attributes to the newly created object
                po.addAttribute("Module nr: " + moduleNr);
                po.addAttribute("Name: " + moduleName);
                po.addAttribute("Description: " + moduleDescription);
                po.addAttribute("Points: " + modulePoints);
                
                //Prints each attribute
                printObjects.add(po);
                
                rowCount++;
            }
            site.writeDivs(printObjects, "module-container", "modules");
            out.println("Total number of records = " + rowCount);
        }
        catch (SQLException ex) {
            out.println("Ikke hentet fra DB " + ex);
        }
    }
    
    public void writeStudents (PrintWriter out) {
        String strSelect = "SELECT * FROM student";
        
        try {
            ResultSet rset = stmt.executeQuery(strSelect);
            
            WebBuilder site = new WebBuilder(out);
            ArrayList printObjects = new ArrayList();
            
            int rowCount = 0;
            while (rset.next()) {
                String stId = rset.getString("st_id");
                String stName = rset.getString("st_name");
                String stLanguage = rset.getString("st_language");
                
                PrintObject po = new PrintObject();
                po.addAttribute("Student ID: " + stId);
                po.addAttribute("Name: " + stName);
                po.addAttribute("Languages spoken: " + stLanguage);
                
                printObjects.add(po);
                rowCount++;
            }
            
            site.writeDivs(printObjects, "student-container", "student");
            
            out.println("Total number of records: " + rowCount);
        }
        catch (SQLException ex) {
            out.println("Ikke hentet fra DB + ex");
        }
    }
    
    public void addNewModule (PrintWriter out, HttpServletRequest request) {
        
        int mNumber = Integer.valueOf(request.getParameter("mNumber"));
        String mName = request.getParameter("mName");
        String mDescription = request.getParameter("mDescription");

        String insertData = "INSERT INTO module VALUES (?,?,?);";
        
        try {
            PreparedStatement newMod = conn.prepareStatement(insertData);
            newMod.setInt(1, mNumber);
            newMod.setString(2, mName);
            newMod.setString(3, mDescription);
            
            newMod.executeUpdate();
        }
        catch (SQLException ex) {
            out.println("Ikke lagt til i DB " + ex);
        }
    }
    
    public void logIn (PrintWriter out) {
        try  {
            Context cont = new InitialContext();
            DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS"); 
            //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
            conn = ds.getConnection();
            
            stmt = conn.createStatement();
        }
        catch (SQLException ex) {
            out.println("Not connected to database " + ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
    }
}

