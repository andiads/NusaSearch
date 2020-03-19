package nusasearch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANDI DWI SAPUTRO
 */
import java.util.ArrayList;
import nusasearch.DBConnection;
import nusasearch.ModelNasabah;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NusaSearch {

    private ArrayList<ModelNasabah> listNasabah;
    private ArrayList<ModelNasabah> resultSearch;
    private ModelNasabah nsb;
    
    private boolean found;
    private int charLength;
    
    public NusaSearch() {
        
    }
    
    // INITIALIZE TABLE NASABAH 
    public ArrayList<ModelNasabah> getDbList() {
        listNasabah = new ArrayList<ModelNasabah>();
        try {
            PreparedStatement ps = DBConnection.getConnection()
                    .prepareStatement("select*from nasabah");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nsb = new ModelNasabah();
                nsb.setId(rs.getInt("id_nasabah"));
                nsb.setNama(rs.getString("nama"));
                nsb.setNik(rs.getInt("nik"));
                nsb.setUsername(rs.getString("username_nasabah"));
                listNasabah.add(nsb);
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNasabah;
    }
    
    // LINEAR SEARCH - SEQUENTIAL SEARCH
    public ArrayList<ModelNasabah> doLinearSearch(ArrayList<ModelNasabah> obj, String category, String key) {
        listNasabah = obj;
        resultSearch = new ArrayList<ModelNasabah>();
        
        if (category.equalsIgnoreCase("id")){
            
        } else if (category.equalsIgnoreCase("name")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                if (listNasabah.get(i).getNama().equalsIgnoreCase(key)){
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        } else if (category.equalsIgnoreCase("nik")) {
            
        } else if (category.equalsIgnoreCase("username")) {
            
        }
        
        if (!found) { 
            System.out.println("---data not found----");
        } else {
            System.out.println("----Data Found----");
            return resultSearch;
        }
        return resultSearch;
    }
    
    // MERGE SEARCH
    public ArrayList<ModelNasabah> doMergeSearch(ArrayList<ModelNasabah> obj, String category, String key) {
        return listNasabah;
    }
    
   // public boolean startWith(int charLength, String keyword){
   //     this.charLength = charLength;

        //if(keyword.substring(0,charLength-1);
        
       // return startWith;
    //}
}
