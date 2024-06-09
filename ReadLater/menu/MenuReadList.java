package menu;

import java.util.Scanner;

import models.Pengguna;
import models.ReadList;
import repositories.BookRepository;
import repositories.ReadListRepository;
import utils.ScreenHelper;

public class MenuReadList {
    private Scanner input = new Scanner(System.in);
    private BookRepository books;
    private ReadListRepository readList;
    private Pengguna auth;

    public MenuReadList(BookRepository books, ReadListRepository readList, Pengguna auth) {
        this.books = books;
        this.readList = readList;
        this.auth = auth;
    }

    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                 DATA ReadList               |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil ReadList                         |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah ReadList                         |");
            System.out.println("+---+-----------------------------------------+");
            // System.out.println("| 3 | Detail ReadList |");
            // System.out.println("+---+-----------------------------------------+");
            // System.out.println("| 4 | Hapus ReadList |");
            // System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Kembali                                 |");
            System.out.println("+=============================================+");
            System.out.print("\nSilakan masukan pilihan anda (0...4) : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1:
                    tampilData();
                    break;
                case 2:
                    tambah();
                    break;
                case 3:
                    // edit();
                    break;
                case 4:
                    // hapus();
                    break;
                case 0:
                    System.out.println("+=============================================+");
                    System.out.println("|            KEMBALI KE MENU UTAMA            |");
                    System.out.println("+=============================================+\n");
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
            }
        } while (pilihan != 0);
    }

    public void tampilData() {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|              TAMPIL DATA ReadList           |");
        System.out.println("+=============================================+");
        for (var readList : readList.listBukuByPengguna(auth)) {
            System.out.println("ID          : " + readList.getId());
            System.out.println("Judul Buku  : " + readList.getBuku().getJudul());
            System.out.println("Nama User   : " + readList.getPengguna().getName());
            System.out.println("+=============================================+");

        }
        input.nextLine();
    }

    public void tambah() {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|             TAMBAH DATA ReadLater           |");
        System.out.println("+=============================================+");

        System.out.println("Silahkah pilih buku yang akan dipeminjaman!");
        books.getAll().forEach(book -> {
            System.out.println("ID          : " + book.getId());
            System.out.println("Judul Buku  : " + book.getJudul());
            System.out.println("+=============================================+");
        });
        System.out.print("Masukan ID Buku : ");

        int idBuku = input.nextInt();
        var book = books.getById(idBuku);
        if (book == null) {
            System.out.println("Buku tidak ditemukan.");
            input.nextLine();
            return;
        }

        readList.listBukuByPengguna(auth).forEach(read -> {
            if (read.getBuku().equals(book)) {
                System.out.println("Buku sudah ada di ReadList.");
                input.nextLine();
                return;
            }
        });

        readList.add(new ReadList(auth, book));
        input.nextLine();

    }

    // // public void hapus() {
    // int indexPeminjaman = pilih();
    // if (indexPeminjaman != -1) {
    // data.remove(indexPeminjaman);
    // System.out.println("+=============================================+");
    // System.out.println("| DATA PEMINJAMAN DIHAPUS |");
    // System.out.println("+=============================================+");
    // input.nextLine();
    // }
    // }

    // // public int pilih() {
    // ScreenHelper.clearConsole();
    // int peminjamanDipilih = -1;

    // if (data.size() > 0) {
    // do {
    // System.out.println("+=============================================+");
    // System.out.println("| PILIH PEMINJAMAN |");
    // System.out.println("+=============================================+");
    // for (int index = 0; index < data.size(); index++) {
    // Peminjaman tempPeminjaman = data.get(index);
    // System.out.println("INDEX : " + index);

    // System.out.println("ID Peminjaman : " + tempPeminjaman.getIdPeminjaman());
    // System.out.println("Nama Peminjaman : " +
    // tempPeminjaman.getPeminjam().getNama());
    // System.out.println("Petugas Peminjaman : " +
    // tempPeminjaman.getPetugasPeminjaman().getNama());
    // System.out.println("Tanggal Peminjaman : " +
    // tempPeminjaman.getTanggalPeminjaman());
    // System.out.println("Batas Pengembalian : " +
    // tempPeminjaman.getBatasPengembalian());
    // System.out
    // .println("Tanggal Pengembalian : " + (tempPeminjaman.getTanggalPengembalian()
    // == null ? "-"
    // : tempPeminjaman.getTanggalPengembalian()));
    // System.out
    // .println("Petugas Pengembalian : " + (tempPeminjaman.getPetugasPengembalian()
    // == null ? "-"
    // : tempPeminjaman.getPetugasPengembalian().getNama()));
    // System.out.println("Denda : Rp " + tempPeminjaman.getDenda());
    // System.out.println("+=============================================+");
    // }

    // System.out.print("Silakan pilih INDEX Peminjaman : ");
    // peminjamanDipilih = input.nextInt();
    // input.nextLine();
    // } while (peminjamanDipilih == -1);
    // } else {
    // System.out.println("Data Peminjaman kosong, silakan tambahkan data.");
    // input.nextLine();
    // }
    // return peminjamanDipilih;

    // }
}
