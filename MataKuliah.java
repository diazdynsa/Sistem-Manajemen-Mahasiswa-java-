package sistemmanajemenmahasiswa;

public class MataKuliah {
    private String nama;
    private int sks;
    private double nilai;

    public MataKuliah(String nama, int sks, double nilai) {
        this.nama = nama;
        this.sks = sks;
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public int getSks() {
        return sks;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
}