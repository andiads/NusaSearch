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
                //listNasabah.get(i).getNama().startsWith(key)
                if (startsWithIgnoreCase(listNasabah.get(i).getNama(),key)){
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
            //System.out.println("----Data Found----");
            return resultSearch;
        }
        return resultSearch;
    }
    
    // MERGE SEARCH
    public ArrayList<ModelNasabah> doMergeSearch(ArrayList<ModelNasabah> obj, String category, String key) {
        listNasabah = obj;
        resultSearch = new ArrayList<ModelNasabah>();
        int nasabahIndex = -1;
        
        int low = 0;
        int high = listNasabah.size();
        int mid = (low + high)/2;
        
        while((low<high) && 
              (!startsWithIgnoreCase(listNasabah.get(mid).getNama(), key)))
        {
            if (startsWithIgnoreCase(listNasabah.get(mid).getNama(), key)) {
                low = mid + 1;
                //resultSearch.add(listNasabah.get(low));
            } else {
                high = mid - 1;
                //resultSearch.add(listNasabah.get(high));
            }
            
            mid = (low + high) / 2;
            
            if (low > high) {
                nasabahIndex = mid;
                resultSearch.add(listNasabah.get(nasabahIndex));
            }
        }
        
        return listNasabah;
        
    }
    
    // trying to create a startswith & endsWith check by ignoring the case sensitive 
    // based on javaString class startsWith() and equalIgnoeCase() methods
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }
    
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        int suffixLength = suffix.length();
        return str.regionMatches(true, str.length() - suffixLength, suffix, 0, suffixLength);
    }
}
