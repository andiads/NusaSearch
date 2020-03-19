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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NusaSearch {

    private ArrayList<ModelNasabah> listNasabah;
    private ArrayList<ModelNasabah> resultSearch;
    private ArrayList<Integer> listIndex;

    private ModelNasabah nsb;

    private boolean found;

    private final static int NOT_FOUND = -1;

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

        if (category.equalsIgnoreCase("id")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                //listNasabah.get(i).getNama().startsWith(key)
                if (listNasabah.get(i).getId() == Integer.parseInt(key)) {
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        } else if (category.equalsIgnoreCase("name")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                if (endsWithIgnoreCase(listNasabah.get(i).getNama(), key)) {
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        } else if (category.equalsIgnoreCase("nik")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                if (endsWithIgnoreCase(
                        String.valueOf(listNasabah.get(i).getNik()), key)) {
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        } else if (category.equalsIgnoreCase("username")) {
            for (int i = 0; i < listNasabah.size(); i++) {
                if (endsWithIgnoreCase(listNasabah.get(i).getUsername(), key)) {
                    found = true;
                    resultSearch.add(listNasabah.get(i));
                }
            }
        }

        if (!found) {
            System.out.println("---data not found----");
        } else {
            //System.out.println("----Data Found----");
            return resultSearch;
        }
        return resultSearch;
    }

    // BINARY/MERGE SEARCH
    public void binarySearch(ArrayList<ModelNasabah> obj, String key){
        listNasabah = doMergeSort(obj); // sort the list first
        int index = -1;
        int low = 0;
        int high = listNasabah.size()-1;
        int mid = (low + high) / 2;
        
        while(low <= high && (!startsWithIgnoreCase(listNasabah.get(mid).getNama(), key))){
            if (listNasabah.get(mid).getNama().compareTo(key) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            mid = (low + high) / 2;

            if (low > high) {
                mid = NOT_FOUND;
            }
            index = mid;
        }
        
        // create list indexes
        final ArrayList<Integer> indexes = new ArrayList<Integer>();
        // add one we already found
        indexes.add(index);
        
        // iterate upwards until we hit the end or a different value
        int current = index + 1;
        while (current < listNasabah.size() && startsWithIgnoreCase(listNasabah.get(current).getNama(),key)){
            indexes.add(current);
            current++;
        }
        
        // iterate downwards until we hit the start or a different value
        current = index - 1;
        while(current >=0 && startsWithIgnoreCase(listNasabah.get(current).getNama(),key)) {
            indexes.add(current);
            current--;
        }
        
        // sort the indexes 
        Collections.sort(indexes);
        
        for (int idx : indexes) {
            System.out.println("+" + listNasabah.get((idx+1)).getId()
                + "\t|" + listNasabah.get((idx+1)).getNama()
                + "\t\t\t|" + listNasabah.get((idx+1)).getNik()
                + "\t\t|" + listNasabah.get((idx+1)).getUsername());
        }
    }

    // trying to create startswith & endsWith check by ignoring the case sensitive 
    // based on javaString class startsWith() and equalIgnoeCase() methods
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static boolean endsWithIgnoreCase(String str, String suffix) {
        // int suffixLength = suffix.length();
        // return str.regionMatches(true, str.length() - suffixLength, suffix, 0, suffixLength);
        return endsWith(str, suffix, true);
    }

    // got the refence from java2s.com 
    // http://www.java2s.com/Tutorial/Java/0040__Data-Type/CaseinsensitivecheckifaStringendswithaspecifiedsuffix.htm
    private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str == null || suffix == null) {
            return (str == null && suffix == null);
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        int strOffset = str.length() - suffix.length();
        return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
    }
    
     // MERGE SORT (cc: https://www.codexpedia.com/java/java-merge-sort-implementation/)
    public ArrayList<ModelNasabah> doMergeSort(ArrayList<ModelNasabah> whole) {
        //listNasabah = whole;
        ArrayList<ModelNasabah> left = new ArrayList<ModelNasabah>();
        ArrayList<ModelNasabah> right = new ArrayList<ModelNasabah>();
        int center;
 
        if (whole.size() == 1) {    
            return whole;
        } else {
            center = whole.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                    left.add(whole.get(i));
            }
 
            //copy the right half of whole into the new arraylist.
            for (int i=center; i<whole.size(); i++) {
                    right.add(whole.get(i));
            }
 
            // Sort the left and right halves of the arraylist.
            left  = doMergeSort(left);
            right = doMergeSort(right);
 
            // Merge the results back together.
            merge(left, right, whole);
        }
        return whole;
    }
 
    private void merge(ArrayList<ModelNasabah> left, ArrayList<ModelNasabah> right, ArrayList<ModelNasabah> whole) {
        //listNasabah = whole;
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
 
        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).getNama().compareTo(right.get(rightIndex).getNama())) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
 
        ArrayList<ModelNasabah> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }
 
        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }
    
}
