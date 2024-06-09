package menu;

import java.util.ArrayList;
import java.util.Scanner;
import models.Buku;
import utils.ScreenHelper;
import models.Menu;
import repositories.BookRepository;

public class MenuBuku extends Menu {
    BookRepository data;

    public MenuBuku(BookRepository data) {
        this.data = data;
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                  DATA BUKU                  |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Buku                             |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Buku                             |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Buku                               |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Buku                              |");
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
                // case 2:
                // tambah();
                // break;
                // case 3:
                // edit();
                // break;
                // case 4:
                // hapus();
                // break;
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

    @Override
    public void tampilData() {
        ScreenHelper.clearConsole();
        if (data.size() > 0) {
            System.out.println("+=============================================+");
            System.out.println("|               TAMPIL DATA BUKU              |");
            System.out.println("+=============================================+");
            for (Buku tempBuku : data.getAll()) {
                System.out.println("Judul Buku    : " + tempBuku.getJudul());
                System.out.println("Penulis Buku  : " + tempBuku.getPenulis().getName());
                System.out.println("Penerbit Buku : " + tempBuku.getPenerbit().getName());
                System.out.println("Tahun Terbit  : " + tempBuku.getTahunTerbit());
                System.out.println("+=============================================+");
            }
            input.nextLine();
        } else {
            System.out.println("Data buku kosong, silakan tambahkan data.");
            input.nextLine();
        }
    }

    @Override
    public int pilih() {
        ScreenHelper.clearConsole();
        String judulBuku = "";
        int bukuDipilih = -1;

        if (data.size() > 0) {
            do {
                System.out.println("+=============================================+");
                System.out.println("|                  PILIH BUKU                 |");
                System.out.println("+=============================================+");
                for (Buku tempBuku : data.getAll()) {
                    System.out.println("Judul Buku    : " + tempBuku.getJudul());
                    System.out.println("Penulis Buku  : " + tempBuku.getPenulis());
                    System.out.println("Penerbit Buku : " + tempBuku.getPenerbit());
                    System.out.println("Tahun Terbit  : " + tempBuku.getTahunTerbit());
                    System.out.println("+=============================================+");
                }

                System.out.print("Silakan pilih kode buku : ");
                judulBuku = input.nextLine();
                for (int i = 0; i < data.size(); i++) {
                    if (data.getById(i).getJudul().equals(judulBuku)) {
                        bukuDipilih = i;
                        break;
                    }
                }
            } while (bukuDipilih == -1);
        } else {
            System.out.println("Data buku kosong, silakan tambahkan data.");
            input.nextLine();
        }
        return bukuDipilih;
    }

    // @Override
    // public void tambah() {
    // ScreenHelper.clearConsole();
    // System.out.println("+=============================================+");
    // System.out.println("| TAMBAH DATA BUKU |");
    // System.out.println("+=============================================+");
    // Buku tempBuku = new Buku();
    // System.out.print("Judul Buku : ");
    // tempBuku.setJudul(input.nextLine());
    // System.out.print("Penulis Buku : ");
    // tempBuku.setPenulis(input.nextLine());
    // System.out.print("Penerbit Buku : ");
    // tempBuku.setPenerbit(input.nextLine());
    // System.out.print("Tahun Terbit : ");
    // tempBuku.setTahunTerbit(input.nextLine());
    // System.out.print("Jumlah Buku : ");
    // tempBuku.setJumlah(input.nextInt());
    // input.nextLine();
    // data.add(tempBuku);
    // System.out.println("+=============================================+");
    // System.out.println("| DATA BUKU TERSIMPAN |");
    // System.out.println("+=============================================+");
    // input.nextLine();
    // }

    // @Override
    // public void edit() {
    // ScreenHelper.clearConsole();
    // int indexBuku = pilih();
    // if (indexBuku != -1) {
    // Buku editBuku = data.get(indexBuku);
    // System.out.println("+=============================================+");
    // System.out.println("| EDIT DATA BUKU |");
    // System.out.println("+=============================================+");
    // System.out.print("Kode Buku : ");
    // editBuku.setKode(input.nextLine());
    // System.out.print("Judul Buku : ");
    // editBuku.setJudul(input.nextLine());
    // System.out.print("Penulis Buku : ");
    // editBuku.setPenulis(input.nextLine());
    // System.out.print("Penerbit Buku : ");
    // editBuku.setPenerbit(input.nextLine());
    // System.out.print("Tahun Terbit : ");
    // editBuku.setTahunTerbit(input.nextLine());
    // System.out.print("Jumlah Buku : ");
    // editBuku.setJumlah(input.nextInt());
    // data.set(indexBuku, editBuku);
    // System.out.println("+=============================================+");
    // System.out.println("| DATA BUKU TERSIMPAN |");
    // System.out.println("+=============================================+");
    // input.nextLine();
    // input.nextLine();
    // }
    // }

    // public void hapus() {
    // ScreenHelper.clearConsole();
    // int indexBuku = pilih();
    // if (indexBuku != -1) {
    // data.remove(indexBuku);
    // System.out.println("+=============================================+");
    // System.out.println("| DATA BUKU DIHAPUS |");
    // System.out.println("+=============================================+");
    // input.nextLine();
    // }
    // }

}