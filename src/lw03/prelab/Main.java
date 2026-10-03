package lw03.prelab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> daftarLagu = new ArrayList<>();

        while (input.hasNext()) {
            String perintah = input.next();

            if (perintah.equals("ADD")) {
                String lagu = input.nextLine().substring(1);
                daftarLagu.add(lagu);
            } else if (perintah.equals("INSERT")) {
                int indeks = input.nextInt();
                String lagu = input.nextLine().substring(1);
                daftarLagu.add(indeks, lagu);
            } else if (perintah.equals("REMOVE")) {
                String lagu = input.nextLine().substring(1);
                daftarLagu.remove(lagu);
            }
        }
        input.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + daftarLagu.size());
        for (int i = 0; i < daftarLagu.size(); i++) {
            System.out.println((i + 1) + ": " + daftarLagu.get(i));
        }

        Scanner input2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> daftarPeserta = new HashSet<>();   
        List<String> urutanPeserta = new ArrayList<>(); 
        int jumlahDuplikat = 0;

        while (input2.hasNext()) {
            String nama = input2.next();
            if (daftarPeserta.contains(nama)) {
                jumlahDuplikat++;
            } else {
                daftarPeserta.add(nama);
                urutanPeserta.add(nama);
            }
        }
        input2.close();

        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + daftarPeserta.size());
        for (int i = 0; i < urutanPeserta.size(); i++) {
            System.out.println((i + 1) + ". " + urutanPeserta.get(i));
        }
        System.out.println("Duplicate registrations: " + jumlahDuplikat);

        Scanner input3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stokBarang = new HashMap<>(); 
        List<String> urutanBarang = new ArrayList<>();      
        int penjualanGagal = 0;

        while (input3.hasNext()) {
            String jenis = input3.next();
            String barang = input3.next();
            int jumlah = input3.nextInt();

            if (jenis.equals("ADD")) {
                if (stokBarang.containsKey(barang)) {
                    stokBarang.put(barang, stokBarang.get(barang) + jumlah);
                } else {
                    stokBarang.put(barang, jumlah);
                    urutanBarang.add(barang);
                }
            } else if (jenis.equals("SELL")) {
                if (stokBarang.containsKey(barang) && stokBarang.get(barang) >= jumlah) {
                    stokBarang.put(barang, stokBarang.get(barang) - jumlah);
                } else {
                    penjualanGagal++;
                }
            }
        }
        input3.close();

        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (int i = 0; i < urutanBarang.size(); i++) {
            String barang = urutanBarang.get(i);
            System.out.println(barang + ": " + stokBarang.get(barang));
        }
        System.out.println("Failed sales: " + penjualanGagal);
    }
}