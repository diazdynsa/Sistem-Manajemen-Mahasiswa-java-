package sistemmanajemenmahasiswa;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainFrame extends JFrame {
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private JLabel lblOutput;

    public MainFrame() {
        daftarMahasiswa = new ArrayList<>();

        // Atur jendela
        setTitle("Sistem Manajemen Mahasiswa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Jendela di tengah layar
        setLayout(null); // Layout manual untuk pemula

        // Judul
        JLabel lblJudul = new JLabel("Sistem Manajemen Mahasiswa");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));
        lblJudul.setBounds(50, 10, 300, 30);
        add(lblJudul);

        // Tombol Tambah Mahasiswa
        JButton btnTambahMahasiswa = new JButton("Tambah Mahasiswa");
        btnTambahMahasiswa.setBounds(100, 50, 200, 30);
        btnTambahMahasiswa.addActionListener(e -> tambahMahasiswa());
        add(btnTambahMahasiswa);

        // Tombol Tambah Mata Kuliah
        JButton btnTambahMataKuliah = new JButton("Tambah Mata Kuliah");
        btnTambahMataKuliah.setBounds(100, 90, 200, 30);
        btnTambahMataKuliah.addActionListener(e -> tambahMataKuliah());
        add(btnTambahMataKuliah);

        // Tombol Hitung IPK
        JButton btnHitungIPK = new JButton("Hitung IPK");
        btnHitungIPK.setBounds(100, 130, 200, 30);
        btnHitungIPK.addActionListener(e -> hitungIPK());
        add(btnHitungIPK);

        // Label untuk hasil
        lblOutput = new JLabel(" ");
        lblOutput.setBounds(20, 170, 350, 30);
        add(lblOutput);
    }

    private void tambahMahasiswa() {
        String nama = JOptionPane.showInputDialog(this, "Masukkan Nama:");
        if (nama == null || nama.trim().isEmpty()) return;
        String nim = JOptionPane.showInputDialog(this, "Masukkan NIM:");
        if (nim == null || nim.trim().isEmpty()) return;
        String prodi = JOptionPane.showInputDialog(this, "Masukkan Program Studi:");
        if (prodi == null || prodi.trim().isEmpty()) return;
        String[] jenis = {"Reguler", "Beasiswa"};
        String pilihan = (String) JOptionPane.showInputDialog(this, "Pilih Jenis Mahasiswa:",
                "Jenis Mahasiswa", JOptionPane.QUESTION_MESSAGE, null, jenis, jenis[0]);

        Mahasiswa mhs;
        if (pilihan.equals("Beasiswa")) {
            String bonusStr = JOptionPane.showInputDialog(this, "Masukkan Bonus Nilai (misal 0.5):");
            try {
                double bonus = Double.parseDouble(bonusStr);
                mhs = new MahasiswaBeasiswa(nama, nim, prodi, bonus);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Bonus nilai harus angka!");
                return;
            }
        } else {
            mhs = new MahasiswaReguler(nama, nim, prodi);
        }
        daftarMahasiswa.add(mhs);
        lblOutput.setText("Mahasiswa " + nama + " berhasil ditambahkan!");
    }

    private void tambahMataKuliah() {
        String nim = JOptionPane.showInputDialog(this, "Masukkan NIM Mahasiswa:");
        if (nim == null || nim.trim().isEmpty()) return;
        Mahasiswa mhs = null;
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(nim)) {
                mhs = m;
                break;
            }
        }
        if (mhs == null) {
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak ditemukan!");
            return;
        }

        String namaMk = JOptionPane.showInputDialog(this, "Masukkan Nama Mata Kuliah:");
        if (namaMk == null || namaMk.trim().isEmpty()) return;
        String sksStr = JOptionPane.showInputDialog(this, "Masukkan SKS:");
        String nilaiStr = JOptionPane.showInputDialog(this, "Masukkan Nilai (0-4):");
        try {
            int sks = Integer.parseInt(sksStr);
            double nilai = Double.parseDouble(nilaiStr);
            if (nilai < 0 || nilai > 4) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0-4!");
                return;
            }
            mhs.tambahMataKuliah(new MataKuliah(namaMk, sks, nilai));
            lblOutput.setText("Mata kuliah " + namaMk + " ditambahkan untuk " + mhs.getNama());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "SKS atau Nilai harus angka!");
        }
    }

    private void hitungIPK() {
        String nim = JOptionPane.showInputDialog(this, "Masukkan NIM Mahasiswa:");
        if (nim == null || nim.trim().isEmpty()) return;
        Mahasiswa mhs = null;
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(nim)) {
                mhs = m;
                break;
            }
        }
        if (mhs == null) {
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak ditemukan!");
            return;
        }
        double ipk = mhs.hitungIPK();
        lblOutput.setText("IPK " + mhs.getNama() + ": " + String.format("%.2f", ipk));
    }
}