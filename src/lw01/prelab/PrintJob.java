package lw01.prelab;

public abstract class PrintJob implements Chargeable { 
    //field id dan pages digunakan untuk menyimpan informasi tentang print job
    private String id; //private karena supaya hanya bisa diakses di dalam class ini saja
    private int pages;

    public PrintJob(String id, int pages) { //constructor untuk menginisialisasi id dan pages
        this.id = id;
        this.pages = pages;
    }

    public String getId() { //getter
        return id;
    }

    public int getPages() { //getter
        return pages;
    }

    @Override
    public abstract int calculateCharge(); //method abstract karena setiap jenis print job akan memiliki cara perhitungan biaya yang berbeda

    public String label() { //method untuk menampilkan informasi tentang print job
        return "Print"; //induk dari semua jenis print job adalah "Print"
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}