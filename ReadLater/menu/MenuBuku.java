package menu;

import java.util.Optional;

import models.Buku;
import models.Menu;
import repositories.BukuRepository;
import utils.ScreenHelper;

public class MenuBuku extends Menu<Buku> {
    BukuRepository bukuRepository;
    MenuPenerbit menuPenerbit;
    MenuPenulis menuPenulis;

    public MenuBuku(BukuRepository bukuRepository, MenuPenerbit menuPenerbit, MenuPenulis menuPenulis) {
        this.bukuRepository = bukuRepository;
        this.menuPenerbit = menuPenerbit;
        this.menuPenulis = menuPenulis;
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
                case 1 -> tampilData();
                case 2 -> tambah();
                case 3 -> edit();
                case 4 -> hapus();
                case 0 -> {
                    System.out.println("+=============================================+");
                    System.out.println("|            KEMBALI KE MENU UTAMA            |");
                    System.out.println("+=============================================+\n");
                }
                default -> {
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
                }
            }
        } while (pilihan != 0);
    }

    @Override
    public void tambah() {
        ScreenHelper.clearConsole();

        System.out.println("+=============================================+");
        System.out.println("| TAMBAH DATA BUKU                            |");
        System.out.println("+=============================================+");

        System.out.print("Judul Buku : ");
        var judul = input.nextLine();
        System.out.print("Penulis Buku : ");
        var penulis = menuPenulis.pilih();
        System.out.print("Penerbit Buku : ");
        var penerbit = menuPenerbit.pilih();
        System.out.print("Tahun Terbit : ");
        int tahunTerbit = input.nextInt();
        input.nextLine();

        bukuRepository.add(new Buku(judul, penulis.get(), penerbit.get(), tahunTerbit));
        System.out.println("Buku berhasil ditambahkan.");
        input.nextLine();
    }

    @Override
    public Optional<Buku> pilih() {
        return pilih(true);
    }

    @Override
    public Optional<Buku> pilih(boolean required) {
        Optional<Buku> bukuDipilih = Optional.empty();
        if (bukuRepository.size() > 0) {
            do {
                System.out.printf("\nBuku yang tersedia %d:\n", bukuRepository.size());
                for (var buku : bukuRepository.getAll()) {
                    System.out.printf("%d) %s\n", buku.getId(), buku.getJudul());
                }
                System.out.print("\nSilakan pilih ID buku : ");
                var idBuku = input.nextInt();
                bukuDipilih = Optional.ofNullable(bukuRepository.getById(idBuku));
            } while (required && bukuDipilih.isEmpty());
        } else {
            System.out.println("Buku kosong.");
        }
        input.nextLine();
        return bukuDipilih;
    }

    @Override
    public void tampilData() {
        if (bukuRepository.size() > 0) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|               TAMPIL DATA BUKU              |");
            System.out.println("+=============================================+");

            for (Buku tempBuku : bukuRepository.getAll()) {
                System.out.println("ID Buku       : " + tempBuku.getId());
                System.out.println("Judul Buku    : " + tempBuku.getJudul());
                System.out.println("Penulis Buku  : " + tempBuku.getPenulis().getName());
                System.out.println("Penerbit Buku : " + tempBuku.getPenerbit().getName());
                System.out.println("Tahun Terbit  : " + tempBuku.getTahunTerbit());
                System.out.println("+=============================================+");
            }
        } else {
            System.out.println("Buku kosong.");
        }
        input.nextLine();
    }

    @Override
    public void edit() {
        var bukuDipilih = pilih();
        if (bukuDipilih.isPresent()) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              EDIT DATA BUKU                 |");
            System.out.println("+=============================================+");

            System.out.println("Biarkan kosong jika tidak ingin mengubah data.");
            var buku = bukuDipilih.get();
            System.out.printf("Judul Buku    (%s) : ", buku.getJudul());
            var judul = input.nextLine();
            System.out.printf("Penulis Buku  (%s) : ", buku.getPenulis().getName());
            var penulis = menuPenulis.pilih(false);
            System.out.printf("Penerbit Buku (%s) : ", buku.getPenerbit().getName());
            var penerbit = menuPenerbit.pilih(false);
            System.out.printf("Tahun Terbit  (%d) : ", buku.getTahunTerbit());
            var tahunTerbit = input.nextLine();

            buku.setJudul(judul.isBlank() ? buku.getJudul() : judul);
            buku.setPenulis(penulis.orElse(buku.getPenulis()));
            buku.setPenerbit(penerbit.orElse(buku.getPenerbit()));
            buku.setTahunTerbit(tahunTerbit.isBlank() ? buku.getTahunTerbit() : Integer.parseInt(tahunTerbit));
            System.out.println("Buku berhasil diubah.");
        } else {
            System.out.println("Buku tidak ditemukan");
        }
        input.nextLine();
    }

    @Override
    public void hapus() {
        var bukuDipilih = pilih();
        if (bukuDipilih.isPresent()) {
            var buku = bukuDipilih.get();
            bukuRepository.remove(buku);
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
        input.nextLine();
    }

}