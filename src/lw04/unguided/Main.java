package lw04.unguided;

import java.util.Scanner;

public class Main {
    static void bubbleSort(int[] beratPaket, int jumlahPutaran) {
        int panjang = beratPaket.length;

        for (int i = 0; i < jumlahPutaran && i < panjang - 1; i++) {
            for (int j = 0; j < panjang - 1 - i; j++) {
                if (beratPaket[j] < beratPaket[j + 1]) {
                    int sementara = beratPaket[j];
                    beratPaket[j] = beratPaket[j + 1];
                    beratPaket[j + 1] = sementara;
                }
            }
        }
    }

    static void binarySearch(int[] beratPaket, int beratTarget) {
        int kiri = 0;
        int kanan = beratPaket.length - 1;
        int jumlahLangkah = 0;

        while (kiri <= kanan) {
            int tengah = (kiri + kanan) / 2;
            jumlahLangkah++;

            if (beratPaket[tengah] == beratTarget) {
                System.out.println(beratTarget + ": FOUND at index " + tengah + " (steps: " + jumlahLangkah + ")");
                return;
            } else if (beratPaket[tengah] < beratTarget) {
                kanan = tengah - 1;
            } else {
                kiri = tengah + 1;
            }
        }

        System.out.println(beratTarget + ": NOT FOUND (steps: " + jumlahLangkah + ")");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int jumlahPaket = input.nextInt();
        int[] paket = new int[jumlahPaket];
        for (int i = 0; i < jumlahPaket; i++) {
            paket[i] = input.nextInt();
        }

        int jumlahTarget = input.nextInt();
        int[] target = new int[jumlahTarget];
        for (int i = 0; i < jumlahTarget; i++) {
            target[i] = input.nextInt();
        }

        input.close();

        bubbleSort(paket, 2);

        int[] setelahDuaPass = new int[jumlahPaket];
        for (int i = 0; i < jumlahPaket; i++) {
            setelahDuaPass[i] = paket[i];
        }

        bubbleSort(paket, jumlahPaket - 1);

        System.out.println("==== After 2 Passes ====");
        tampilkanArray(setelahDuaPass);
        
        System.out.println();
        System.out.println("==== Fully Sorted ====");
        tampilkanArray(paket);

        System.out.println();
        System.out.println("==== Search Results ====");
        for (int i = 0; i < jumlahTarget; i++) {
            binarySearch(paket, target[i]);
        }
    }
}