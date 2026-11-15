package lw05.prelab;

import java.util.*;

public class Main {
    static Scanner baca = new Scanner(Main.class.getResourceAsStream("campus.txt"));

    static int[][] graph;
    static int jumlah;
    static String[] nama;

    static void addEdge(int from, int to, int minutes) {
        graph[from][to] = minutes;
        graph[to][from] = minutes;
    }

    static List<Integer> bfs(int awal) {
        List<Integer> urutan = new ArrayList<>();
        Set<Integer> dikunjungi = new HashSet<>();
        Queue<Integer> antrean = new LinkedList<>();
        antrean.add(awal);
        dikunjungi.add(awal);
        while (!antrean.isEmpty()) {
            int sekarang = antrean.poll();
            urutan.add(sekarang);
            for (int tetangga = 0; tetangga < jumlah; tetangga++) {
                if (graph[sekarang][tetangga] > 0 && !dikunjungi.contains(tetangga)) { 
                    dikunjungi.add(tetangga);
                    antrean.offer(tetangga); 
                }
            }
        }
        return urutan;
    }

    static Set<Integer> dfsVisited = new HashSet<>(); 
    static List<Integer> dfsOrder = new ArrayList<>(); 

    static void dfs(int sekarang) {
        dfsVisited.add(sekarang); 
        dfsOrder.add(sekarang);
        for (int tetangga = 0; tetangga < jumlah; tetangga++) {
            if (graph[sekarang][tetangga] > 0 && !dfsVisited.contains(tetangga)) {
                dfs(tetangga);
            }
        }
    }

    static void dijkstraAlg(int awal, int tujuan) {
        int[] jarak = new int[jumlah];
        for (int i = 0; i < jumlah; i++) {
            jarak[i] = 9999;
        }
        jarak[awal] = 0;
        boolean[] dikunjungi = new boolean[jumlah];
        int[] asal = new int[jumlah];

        for (int i = 0; i < jumlah; i++) {
            int sekarang = findTheNextNode(dikunjungi, jarak);
            dikunjungi[sekarang] = true;
            for (int tetangga = 0; tetangga < jumlah; tetangga++) {
                int jarakBaru = jarak[sekarang] + graph[sekarang][tetangga];
                if (graph[sekarang][tetangga] > 0 && jarakBaru < jarak[tetangga]) { 
                    jarak[tetangga] = jarakBaru;
                    asal[tetangga] = sekarang; 
                }
            }
        }

        System.out.println("=== Shortest Time from " + nama[awal] + " ===");
        for (int i = 0; i < jumlah; i++) {
            System.out.println(nama[i] + " : " + jarak[i] + " min");
        }
        System.out.println("=== Shortest Route from " + nama[awal] + " to " + nama[tujuan] + " ===");
        route(tujuan, awal, asal);
        System.out.println();
        System.out.println("Total time: " + jarak[tujuan] + " min");
    }

    static int findTheNextNode(boolean[] dikunjungi, int[] jarak) {
        int terkecil = 9999;
        int terdekat = -1;
        for (int i = 0; i < jumlah; i++) {
            if (!dikunjungi[i] && jarak[i] < terkecil) { 
                terkecil = jarak[i];
                terdekat = i;
            }
        }
        return terdekat;
    }

    static void route(int sekarang, int awal, int[] asal) {
        if (sekarang == awal) {
            System.out.print(nama[sekarang]);
        } else {
            route(asal[sekarang], awal, asal); 
            System.out.print(" -> " + nama[sekarang]);
        }
    }

    static void printPath(List<Integer> daftar) {
        for (int i = 0; i < daftar.size(); i++) {
            System.out.print(nama[daftar.get(i)]);
            if (i < daftar.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        jumlah = baca.nextInt();
        nama = new String[jumlah];
        for (int i = 0; i < jumlah; i++) {
            nama[i] = baca.next();
        }
        graph = new int[jumlah][jumlah];

        int jumlahJalan = baca.nextInt();
        for (int i = 0; i < jumlahJalan; i++) {
            int from = baca.nextInt();
            int to = baca.nextInt();
            int minutes = baca.nextInt();
            addEdge(from, to, minutes);
        }

        int awal = baca.nextInt();
        int tujuan = baca.nextInt();
        baca.close();

        System.out.println("=== BFS from " + nama[awal] + " ===");
        printPath(bfs(awal));

        System.out.println("=== DFS from " + nama[awal] + " ===");
        dfs(awal);
        printPath(dfsOrder);

        dijkstraAlg(awal, tujuan);
    }
}