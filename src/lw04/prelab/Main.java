package lw04.prelab;

import java.util.*;

public class Main {

    public static void insertionSort(int[] data, int jumlahPass) {
        int panjang = data.length;

        for (int i = 1; i <= jumlahPass && i < panjang; i++) {
            int kunci = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] < kunci) {
                data[j + 1] = data[j];
                j--;
            }

            data[j + 1] = kunci;
        }
    }

    public static int linearSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void cetakArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(data[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int jumlahCabang = baca.nextInt();
        int[] stabilitas = new int[jumlahCabang];
        for (int i = 0; i < jumlahCabang; i++) {
            stabilitas[i] = baca.nextInt();
        }

        int jumlahTarget = baca.nextInt();
        int[] daftarTarget = new int[jumlahTarget];
        for (int i = 0; i < jumlahTarget; i++) {
            daftarTarget[i] = baca.nextInt();
        }
        
        baca.close();

        insertionSort(stabilitas, 3);
        cetakArray(stabilitas);

        for (int i = 0; i < jumlahTarget; i++) {
            int posisi = linearSearch(stabilitas, daftarTarget[i]);

            if (posisi != -1) {
                System.out.println(daftarTarget[i] + ": FOUND at index " + posisi);
            } else {
                System.out.println(daftarTarget[i] + ": NOT FOUND");
            }
        }
    }
}