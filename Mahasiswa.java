package sistemmanajemenmahasiswa;

import java.util.ArrayList;

public abstract class Mahasiswa {
    private String nama;
    private String nim;
    private String prodi;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public Mahasiswa(String nama, String nim, String prodi) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.daftarMataKuliah = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void tambahMataKuliah(MataKuliah mk) {
        daftarMataKuliah.add(mk);
    }

    public ArrayList<MataKuliah> getDaftarMataKuliah() {
        return daftarMataKuliah;
    }

    public abstract double hitungIPK();
}