package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WashService[] services = new WashService[30];
        int jumlahServices = 0;

        Scanner baca = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        int i = baca.nextInt();
        for (int j = 0; j < i; j++) {
            String type = baca.next();
            String id = baca.next();
            int days = baca.nextInt();
            
            if (type.equals("CAR")) {
                services[jumlahServices] = new CarWash(id, days);
            } else if (type.equals("MOTORCYCLE")) {
                services[jumlahServices] = new MotorcycleWash(id, days);
            }

            System.out.println(services[jumlahServices].getId() + " | " + services[jumlahServices].label() + " | " + services[jumlahServices].calculateCharge());
            jumlahServices++;
        }

        baca.close();
    }
}


        