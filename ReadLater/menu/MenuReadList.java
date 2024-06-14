package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Buku;
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

    private List<Buku> bukuNotInReadList;

    public MenuReadList(BookRepository books, ReadListRepository readList, Pengguna auth) {
        this.bukuNotInReadList = books.getAll().stream().filter(it -> !readList.isExist(auth, it)).toList();
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
            System.out.println("| 4 | Hapus ReadList                          |");
            System.out.println("+---+-----------------------------------------+");
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
            System.out.println("ID              : " + readList.getId());
            System.out.println("Judul Buku      : " + readList.getBuku().getJudul());
            System.out.println("Nama Pengarang  : " + readList.getBuku().getPenulis().getName());
            System.out.println("Nama Penerbit   : " + readList.getBuku().getPenerbit().getName());
            System.out.println("Nama Pengguna   : " + readList.getPengguna().getName());
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
        bukuNotInReadList.forEach(book -> {
            System.out.println("ID          : " + book.getId());
            System.out.println("Judul Buku  : " + book.getJudul());
            System.out.println("+=============================================+");
        });
        System.out.print("Masukan ID Buku : ");

        // priksa apakah buku ada di list buku yang bisa dipinjam
        int idBuku = input.nextInt();
        var book = bukuNotInReadList.stream().filter(it -> it.getId() == idBuku).findFirst().orElse(null);
        if (book == null) {
            System.out.println("Buku tidak ditemukan.");
            input.nextLine();
            return;
        }

        // tambahkan buku ke list peminjaman
        readList.add(new ReadList(auth, book));
        input.nextLine();

    }

    public void hapus() {
        int indexPeminjaman = pilih();
        if (indexPeminjaman != -1) {
            readList.delete(readList.getById(indexPeminjaman));
            System.out.println("+=============================================+");
            System.out.println("| DATA PEMINJAMAN DIHAPUS |");
            System.out.println("+=============================================+");
            input.nextLine();
        }
    }

    public int pilih() {
        ScreenHelper.clearConsole();
        int peminjamanDipilih = -1;

        if (readList.size() > 0) {
            do {
                System.out.println("+=============================================+");
                System.out.println("| PILIH PEMINJAMAN |");
                System.out.println("+=============================================+");
                for (var read : readList.listBukuByPengguna(auth)) {
                    System.out.println("ID              : " + read.getId());
                    System.out.println("Judul Buku      : " + read.getBuku().getJudul());
                    System.out.println("Nama Pengarang  : " + read.getBuku().getPenulis().getName());
                    System.out.println("Nama Penerbit   : " + read.getBuku().getPenerbit().getName());
                    System.out.println("Nama Pengguna   : " + read.getPengguna().getName());
                    System.out.println("+=============================================+");
                }

                System.out.print("Silakan pilih INDEX ReadLIst : ");
                peminjamanDipilih = input.nextInt();
                input.nextLine();
            } while (peminjamanDipilih == -1);
        } else {
            System.out.println("Data ReadLIst kosong, silakan tambahkan data.");
            input.nextLine();
        }
        return peminjamanDipilih;

    }
}
