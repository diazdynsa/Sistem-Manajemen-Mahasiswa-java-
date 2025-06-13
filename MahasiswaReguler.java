package sistemmanajemenmahasiswa;

public class MahasiswaReguler extends Mahasiswa {
    public MahasiswaReguler(String nama, String nim, String prodi) {
        super(nama, nim, prodi);
    }

    @Override
    public double hitungIPK() {
        double totalNilai = 0;
        int totalSks = 0;
        for (MataKuliah mk : getDaftarMataKuliah()) {
            totalNilai += mk.getNilai() * mk.getSks();
            totalSks += mk.getSks();
        }
        return totalSks == 0 ? 0 : totalNilai / totalSks;
    }
}