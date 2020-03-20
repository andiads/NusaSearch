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
        Scanner inputKey = new Scanner(System.in);
        Scanner inputCategory = new Scanner(System.in);

        int option = 0;
        String key = "";
        String category = "";

        ArrayList<ModelNasabah> dataNasabah = new ArrayList<ModelNasabah>();

        ArrayList<ModelNasabah> linearSearch = new ArrayList<ModelNasabah>();
        ArrayList<ModelNasabah> mergeSearch = new ArrayList<ModelNasabah>();

        dataNasabah = nsc.getDbList();

        // tampilkan data table nasabah
        System.out.println("--------Data Table Nasabah-------");
        System.out.println("+ID\t|\tNAME\t\t|\tNIK\t\t|\tUSERNAME\t\t");
        for (int i = 0; i < dataNasabah.size(); i++) {
            System.out.println("+" + dataNasabah.get(i).getId()
                    + "\t|" + dataNasabah.get(i).getNama()
                    + "\t\t\t|" + dataNasabah.get(i).getNik()
                    + "\t\t|" + dataNasabah.get(i).getUsername());
        }

        // Input keyword pencarian
        System.out.println("--------------SEARCH DATA-----------------");
        System.out.println("1. Linear/Sequential Search");
        System.out.println("2. Binary Search");
        System.out.println("------------------------------------------");
        System.out.print("Masukkan pilihan (1/2) algoritma yg ingin digunakan: ");
        option = input.nextInt();

        // opsi pencarian
        if (option == 1) {

            System.out.println("==============Linear Search==============");
            System.out.print("Inputkan NIK (endsWith) nasabah yg dicari: ");
            key = inputKey.nextLine();

            // result utk linear search
            System.out.println("==================result=================");
            System.out.println("+ID\t|\tNAME\t\t|\tNIK\t\t|\tUSERNAME\t\t");
            linearSearch = nsc.doLinearSearch(dataNasabah, "nik", key);
            for (int i = 0; i < linearSearch.size(); i++) {
                System.out.println("+" + linearSearch.get(i).getId()
                        + "\t|" + linearSearch.get(i).getNama()
                        + "\t\t\t|" + linearSearch.get(i).getNik()
                        + "\t\t|" + linearSearch.get(i).getUsername());
            }

        } else if (option == 2) {

            System.out.println("==============Merge/Binary Search==============");
            System.out.print("Inputkan NAMA (startsWith) nasabah yg dicari: ");
            key = inputKey.nextLine();

            // result utk merge search
            //nsc.binarySearch(dataNasabah, key);
            mergeSearch = nsc.getBinarySearch(dataNasabah, key);
            System.out.println("==================result=================");
            System.out.println("+ID\t|\tNAME\t\t|\tNIK\t\t|\tUSERNAME\t\t");

            for (int i = 0; i < mergeSearch.size();i++) {
                System.out.println("+" + mergeSearch.get(i).getId()
                        + "\t|" + mergeSearch.get(i).getNama()
                        + "\t\t\t|" + mergeSearch.get(i).getNik()
                        + "\t\t|" + mergeSearch.get(i).getUsername());
            }
            
            //mergeSearch = nsc.searchNama(dataNasabah, key);
            //nsc.showBinarySearch(mergeSearch);
        } else {
            System.out.println("UNKNOWN OPTION");
        }

    }
}
