package lw02.unguided;

import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> daftarMakanan = new LinkedList<>(); 
        LinkedList<String[]> daftarMinuman = new LinkedList<>();
        LinkedList<String[]> ordersBerhasil = new LinkedList<>();

        Queue<String[]> antrianOrders = new LinkedList<>();
        Stack<String[]> ordersGagal = new Stack<>();

        Scanner input = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        
        while (input.hasNextLine()) {
            String baris = input.nextLine();
            String[] bagian = baris.split(" ");
            String name = bagian[0];
            String sideDish = bagian[1];
            String drink = bagian[2];
            String table = bagian[3];

            String[] originalOrder = new String[]{name, sideDish, drink, table};
            orders.add(originalOrder);
        }
        input.close();

        daftarMakanan.add(new String[]{"Bakso", "2"});
        daftarMakanan.add(new String[]{"Sate", "1"});
        daftarMakanan.add(new String[]{"Soto", "2"});

        daftarMinuman.add(new String[]{"EsTeh", "4"});
        daftarMinuman.add(new String[]{"EsJeruk", "2"});
        
        for (int j = 0; j < orders.size(); j++) {
            antrianOrders.add(orders.get(j));
        }

        while (antrianOrders.isEmpty() == false) {
            String[] order = antrianOrders.poll();
            String name = order[0];
            String sideDish = order[1];
            String drink = order[2];

            String[] dataMakanan = null;
            boolean foundMakanan = true;
            if (sideDish.equals("-") == false) {
                foundMakanan = false;
                for (int j = 0; j < daftarMakanan.size(); j++) {
                    String[] makanan = daftarMakanan.get(j);
                    if (makanan[0].equals(sideDish)) {
                        dataMakanan = makanan;
                        if (Integer.parseInt(makanan[1]) > 0) {
                            foundMakanan = true;
                        }
                        break;
                }
            }
            }

            String[] dataMinuman = null;
            boolean foundMinuman = true;
            if (drink.equals("-") == false) {
                foundMinuman = false;
                for (int j = 0; j < daftarMinuman.size(); j++) {
                    String[] minuman = daftarMinuman.get(j);
                    if (minuman[0].equals(drink)) {
                        dataMinuman = minuman;
                        if (Integer.parseInt(minuman[1]) > 0) {
                            foundMinuman = true;
                        }
                        break;
                    }
                }
            }

            if (foundMakanan && foundMinuman) {
                if (dataMakanan != null) {
                    int stokMakanan = Integer.parseInt(dataMakanan[1]) -1;
                    dataMakanan[1] = String.valueOf(stokMakanan);
                }

                if (dataMinuman != null) {
                    int stokMinuman = Integer.parseInt(dataMinuman[1]) -1;
                    dataMinuman[1] = String.valueOf(stokMinuman);
                }
                ordersBerhasil.add(order);
            } else {
                ordersGagal.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (int i = 0; i < ordersBerhasil.size(); i++) {
            String[] order = ordersBerhasil.get(i);
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (int i = 0; i < daftarMakanan.size(); i++) {
            String[] makanan = daftarMakanan.get(i);
            System.out.println(makanan[0] + " : " + makanan[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (int i = 0; i < daftarMinuman.size(); i++) {
            String[] minuman = daftarMinuman.get(i);
            System.out.println(minuman[0] + " : " + minuman[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while (ordersGagal.isEmpty() == false) {
            String[] order = ordersGagal.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}
