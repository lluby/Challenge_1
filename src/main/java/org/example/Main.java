package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    private static int userInput;
    private static final Scanner input = new Scanner(System.in);
    private static final String[][] MENU = {        //menginput nilai dari array menu
            { "1. Nasi Goreng  | 15.000", "15000" },
            { "2. Mie Goreng   | 13.000", "13000" },
            { "3. Nasi + Ayam  | 18.000", "18000" },
            { "4. Es Teh Manis | 3.000", "3000" },
            { "5. Es Jeruk     | 5.000", "5000" },
            { "99. Pesan dan Bayar" },
            { "0. Keluar Aplikasi" }
    };
    private static final int[] pesanan = new int[MENU.length]; // Array untuk menyimpan pesanan

    public static void main(String[] args) {
        menu(); //memanggil method menu
    }

    public static void menu() {         //membuat method dengan nama menu tanpa parameter
        System.out.println("=================================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("=================================");
        System.out.println();

        for (String[] menu : MENU) {        //memanggil array menu
            String menuItem = menu[0];
            System.out.println(menuItem);
        }

        pilihan(); // memanggil method pilihan
        kondisi(); // memanggil method kondisi
    }

    public static void pilihan() {  // method pilihan tanpa parameter
        System.out.print("qrt => ");
        userInput = input.nextInt();    // sebuah inputan dari user
    }

    public static void kondisi() {   // method kondisi tanpa parameter
        if (userInput >= 1 && userInput <= 5) {         // pengecekan kondisi inputan dari user
            userInput--;

            System.out.println("\n\n\n================================");
            System.out.println("Berapa pesanan Anda");
            System.out.println("================================");
            System.out.println();

            String slicedText = MENU[userInput][0].substring(3);

            System.out.println(slicedText);
            System.out.println("\n(input 0 untuk kembali)");
            masukanJumlah();
        } else if (userInput == 99) {
            // Memproses pembayaran
            pesanDanBayar();
        } else if (userInput == 0) {
            // Keluar dari aplikasi
            System.out.println("Terima kasih telah menggunakan BinarFud.\n");
            System.exit(0);
        } else {
            System.out.println("Pilihan tidak valid.\n");
            menu();
        }
    }

    public static void masukanJumlah() {    // method masukanJumlah tanpa parameter
        System.out.print("Masukkan jumlah pesanan: ");
        int jumlah = input.nextInt();   // inputan user
        System.out.print("\n\n");
        if (jumlah > 0) {
            pesanan[userInput] += jumlah;
        }
        menu(); //memanggil method menu
    }

    public static void pesanDanBayar() {        //method pesandanBayar tanpa parameter
        int totalHarga = 0;
        int totalPembelian = 0;
        for (int i = 0; i < pesanan.length; i++) {  // memanggil jumlah pesanan
            if (pesanan[i] > 0) {
                totalPembelian += pesanan[i];
            }
        }

        if (totalPembelian == 0) {
            System.out.println("\n\nAnda belum memesan apapun.\n");  // kondisi ini akan keluar jika user belum pesan apapun
            menu();
        } else {
            System.out.println("\n\n================================");
            System.out.println("Konfirmasi dan Pembayaran");
            System.out.println("================================\n");

            for (int i = 0; i < pesanan.length; i++) {          // memanggil inputan pesanan yang telah user inputkan
                if (pesanan[i] > 0) {
                    String slicedText = MENU[i][0].substring(3);
                    String[] parts = slicedText.split("\\|");
                    System.out.println(parts[0] + "\t" + pesanan[i] + "   \t" + parts[1]);

                    int harga = Integer.parseInt(MENU[i][1]);
                    totalHarga += harga * pesanan[i];       // menghitung total harga dari inputan user

                }
            }
            System.out.println("-------------------------------");

            System.out.println("Total Harga: " + "\t" + totalPembelian + "\t" +   totalHarga);  // mengembalikan nilai total harga
            System.out.println("\n1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke menu utama");
            System.out.println("0. Keluar aplikasi");
            System.out.print("Pilihan => ");
            int kembali = input.nextInt();

            switch (kembali) {
                case 1:
                    write("struk.txt");     // memanggil method write
                    for (int i = 0; i < pesanan.length; i++) { // memanggil jumlah pesanan user
                        pesanan[i] = 0;
                    }
                    break;
                case 2:
                    System.out.print("\n");
                    menu(); // memanggil method menu
                    break;
                case 0:
                    System.exit(0); // keluar dari program
                    break;
                default:
                    menu(); // memanggil method menu
                    break;
            }
        }
    }

    public static void write(String txtFile) {      // membuat method write
        try {
            // java akan membuka file, klo tdk ada java akan create
            File file = new File(txtFile);
            if(file.createNewFile()) {
                System.out.println("file has been created");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("======================================\n");
            bw.write("BinarFud\n");
            bw.write("======================================\n\n\n");
            bw.write("======================================\n");
            bw.write("Terima kasih sudah memesan \ndi BinarFud\n");
            bw.write("======================================\n");
            bw.newLine();
            bw.write("Dibawah ini adalah pesanan Anda \n");
            // disini pake for yang mana yaa biar pesanannya muncul hehe

            int totalHarga = 0;
            int totalPembelian = 0;

            for (int i = 0; i < pesanan.length; i++) {      // memanggil pesanan user dan memasukkannya ke dalam file .txt
                if (pesanan[i] > 0) {
                    String slicedText = MENU[i][0].substring(3);
                    String[] parts = slicedText.split("\\|");
                    int harga = Integer.parseInt(MENU[i][1]);
                    totalHarga += harga * pesanan[i];

                    totalPembelian += pesanan[i];

                    bw.write(parts[0] + "\t" + pesanan[i] + "\t" + parts[1]);

                    bw.newLine();
                    bw.write("--------------------------------\n");
                    bw.write("Total Harga  \t\t"   +totalHarga);
                }
            }

            bw.newLine();
            bw.newLine();
            bw.write("Pembayaran : BinarCash \n");
            bw.newLine();
            bw.newLine();
            bw.write("=================================\n");
            bw.write("Simpan Struk ini sebagai \nbukti pembayaran\n");
            bw.write("=================================\n");
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}