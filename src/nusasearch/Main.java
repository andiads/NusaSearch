/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nusasearch;

import java.util.ArrayList;
import nusasearch.DBConnection;
import nusasearch.ModelNasabah;
import nusasearch.NusaSearch;
import java.util.Scanner;

/**
 *
 * @author ANDI DWI SAPUTRO
 */
public class Main {
    public static void main(String[] args) {
        NusaSearch nsc = new NusaSearch();
        
        Scanner input = new Scanner(System.in);
        String key = "";
        
        ArrayList<ModelNasabah> dataNasabah = new ArrayList<ModelNasabah>();
        
        ArrayList<ModelNasabah> linearSearch = new ArrayList<ModelNasabah>();
        
        dataNasabah = nsc.getDbList();
        
        // tampilkan data table nasabah
        System.out.println("--------Data Table Nasabah-------");
        System.out.println("+ID\t|\tNAME\t\t|\tNIK\t\t|\tUSERNAME\t\t");
        for (int i=0; i<dataNasabah.size();i++){
            System.out.println("+"+dataNasabah.get(i).getId()
                    +"\t|"+dataNasabah.get(i).getNama()
                    +"\t\t\t|"+dataNasabah.get(i).getNik()
                    +"\t\t|"+dataNasabah.get(i).getUsername());
        }
        
        // Input keyword pencarian
        System.out.println("--------------SEARCH DATA-----------------");
        System.out.print("Inputkan nama nasabah yg dicari: ");
        key = input.nextLine();
        
        // result utk linear search
        System.out.println("==================result=================");
        System.out.println("+ID\t|\tNAME\t\t|\tNIK\t\t|\tUSERNAME\t\t");
        linearSearch = nsc.doLinearSearch(dataNasabah, "name", key);
        for (int i=0; i<linearSearch.size();i++){
            System.out.println("+"+linearSearch.get(i).getId()
                    +"\t|"+linearSearch.get(i).getNama()
                    +"\t\t\t|"+linearSearch.get(i).getNik()
                    +"\t\t|"+linearSearch.get(i).getUsername());
        }
    }
}