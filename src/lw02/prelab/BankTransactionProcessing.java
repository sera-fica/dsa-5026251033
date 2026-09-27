package lw02.prelab;

import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class BankTransactionProcessing {
    public static void main(String[] args) throws Exception {

        LinkedList<String[]> daftarTransaksi = new LinkedList<>();
        Scanner baca = new Scanner(BankTransactionProcessing.class.getResourceAsStream("/lw02/prelab/transactions.txt"));
        while (baca.hasNextLine()) {
            String baris = baca.nextLine();
            String[] bagian = baris.split(" ");
            String name = bagian[0];
            String type = bagian[1];
            String amount = bagian[2];

            String[] transaksi = new String[]{name, type, amount};
            daftarTransaksi.add(transaksi);
        }
        baca.close();

        LinkedList<String[]> daftarCustomer = new LinkedList<>();
        for (int i = 0; i < daftarTransaksi.size(); i++) {
            String[] transaksi = daftarTransaksi.get(i);
            String namaCustomer = transaksi[0];

            boolean found = false;
            for (int j = 0; j < daftarCustomer.size(); j++) {
                String[] customer = daftarCustomer.get(j);
                if (customer[0].equals(namaCustomer)) {
                    found = true;
                }
            }

            if (found == false) {
                String[] customer = new String[]{namaCustomer, "0"};
                daftarCustomer.add(customer);
            }
        } 

        Queue<String[]> antrianTransaksi = new LinkedList<>();
        for (int j = 0; j < daftarTransaksi.size(); j++) {
            antrianTransaksi.add(daftarTransaksi.get(j));
        }

        Stack<String[]> transaksiGagal = new Stack<>();

        while (antrianTransaksi.isEmpty() == false) {
            String[] transaksi = antrianTransaksi.poll();
            String namaCustomer = transaksi[0];
            String type = transaksi[1];
            int amount = Integer.parseInt(transaksi[2]);

            for (int j = 0; j < daftarCustomer.size(); j++) {
                String[] customer = daftarCustomer.get(j);
                if (customer[0].equals(namaCustomer)) {
                    int saldo = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        int saldoBaru = saldo + amount;
                        customer[1] = String.valueOf(saldoBaru);

                    } else if (type.equals("WITHDRAW")) {
                        if (amount > saldo) {
                            transaksiGagal.push(transaksi);
                        } else {
                            int saldoBaru = saldo - amount;
                            customer[1] = String.valueOf(saldoBaru);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (int i = 0; i < daftarCustomer.size(); i++) {
            String[] customer = daftarCustomer.get(i);
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (transaksiGagal.isEmpty() == false) {
            String[] transaksi = transaksiGagal.pop(); 
            System.out.println(transaksi[0] + " " + transaksi[1] + " " + transaksi[2]);
        }
    }
}