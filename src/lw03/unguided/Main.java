package lw03.unguided;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        
        Scanner read = new Scanner(Main.class.getResourceAsStream("registration.txt"));
        Set<String> studentRegistered = new HashSet<>();

        while (read.hasNext()) {
            String id = read.next();
            studentRegistered.add(id); 
        }
        read.close();

        Scanner readAll = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        Set<String> alreadyCheckedIn = new HashSet<>();
        int ditolak = 0;
        
        System.out.println("===== Event Check-In Results =====");
        while (readAll.hasNext()) {
            String id = readAll.next();

            if (terdaftar.contains(id) == false) {
                System.out.println(id + ": Rejected (not registered)");
                ditolak++;
            } else if (alreadyCheckedIn.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                ditolak++;
            } else {
                alreadyCheckedIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        readAll.close();

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + terdaftar.size());
        System.out.println("Successful check-ins: " + alreadyCheckedIn.size());
        System.out.println("Absent students: " + (terdaftar.size() - alreadyCheckedIn.size()));
        System.out.println("Rejected attempts: " + ditolak);
    }
}