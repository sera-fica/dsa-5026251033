package lw01.prelab;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        PrintJob[] jobs = new PrintJob[100];
        int jumlahJobs = 0;

        Scanner baca = new Scanner(new File("src/lw01/prelab/jobs.txt"));
        while (baca.hasNext()) {
            String type = baca.next();
            String id = baca.next();
            int pages = baca.nextInt();

            if (type.equals("COLOUR")) {
                jobs[jumlahJobs] = new ColourPrint(id, pages);
            } else if (type.equals("MONO")) {
                jobs[jumlahJobs] = new MonoPrint(id, pages);
            }
            jumlahJobs++;
        }

        for (int i = 0; i < jumlahJobs; i++) {
            System.out.println(jobs[i].summary());
        }

        baca.close();
    }
}