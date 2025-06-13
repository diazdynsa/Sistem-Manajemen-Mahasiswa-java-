package sistemmanajemenmahasiswa;

public class MahasiswaBeasiswa extends Mahasiswa {
    private double bonusNilai;

    public MahasiswaBeasiswa(String nama, String nim, String prodi, double bonusNilai) {
        super(nama, nim, prodi);
        this.bonusNilai = bonusNilai;
    }

    @Override
    public double hitungIPK() {
        double totalNilai = 0;
        int totalSks = 0;
        for (MataKuliah mk : getDaftarMataKuliah()) {
            double nilai = Math.min(4.0, mk.getNilai() + bonusNilai);
            totalNilai += nilai * mk.getSks();
            totalSks += mk.getSks();
        }
        return totalSks == 0 ? 0 : totalNilai / totalSks;
    }
}