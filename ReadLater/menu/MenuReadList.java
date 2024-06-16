package menu;

import java.util.Optional;
import java.util.stream.Collectors;

import models.Buku;
import models.Menu;
import models.Pengguna;
import models.ReadList;
import repositories.BukuRepository;
import repositories.ReadListRepository;
import utils.ScreenHelper;

public class MenuReadList extends Menu<ReadList> {
    private ReadListRepository readListRepository;
    private BukuRepository bukuRepository;
    private Pengguna pengguna;

    public MenuReadList(ReadListRepository readListRepository, BukuRepository bukuRepository, Pengguna pengguna) {
        this.readListRepository = readListRepository;
        this.bukuRepository = bukuRepository;
        this.pengguna = pengguna;
    }

    @Override
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
            System.out.println("| 3 | Detail ReadList                         |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus ReadList                          |");
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
        System.out.println("|              TAMBAH DATA ReadList           |");
        System.out.println("+=============================================+");

        var listIdBukuPengguna = readListRepository
                .listReadListByPengguna(pengguna)
                .stream()
                .map(readList -> readList.getBuku().getId())
                .collect(Collectors.toList());
        var bukuTersedia = bukuRepository.listBukuTidakDalamIds(listIdBukuPengguna);
        if (bukuTersedia.size() > 0) {
            Optional<Buku> bukuDipilih = Optional.empty();
            do {
                System.out.println("Buku yang tersedia : ");
                for (var buku : bukuTersedia) {
                    System.out.printf("%d) %s\n", buku.getId(), buku.getJudul());
                }
                System.out.print("Pilih id buku : ");
                var idBuku = input.nextInt();
                bukuDipilih = bukuTersedia.stream().filter(buku -> buku.getId() == idBuku).findFirst();
            } while (bukuDipilih.isEmpty());

            if (bukuDipilih.isPresent()) {
                readListRepository.add(new ReadList(pengguna, bukuDipilih.get()));
                System.out.println("Data berhasil ditambahkan.");
            }
        } else {
            System.out.println("Buku kosong.");
        }
        input.nextLine();
    }

    @Override
    public Optional<ReadList> pilih() {
        return this.pilih(true);
    }

    @Override
    public Optional<ReadList> pilih(boolean required) {
        var listReadList = readListRepository.listReadListByPengguna(pengguna);
        Optional<ReadList> readListDipilih = Optional.empty();
        if (listReadList.size() > 0) {
            do {
                System.out.printf("\nReadList yang tersedia %d: \n", listReadList.size());
                for (var read : listReadList) {
                    System.out.printf("%d) %s\n", read.getId(), read.getBuku().getJudul());
                }

                System.out.print("Silakan pilih id read list : ");
                var idReadList = input.nextInt();
                readListDipilih = listReadList.stream().filter(read -> read.getId() == idReadList).findFirst();
            } while (required && readListDipilih.isEmpty());
        } else {
            System.out.println("ReadList kosong.");
        }
        input.nextLine();
        return readListDipilih;
    }

    @Override
    public void tampilData() {
        var listReadList = readListRepository.listReadListByPengguna(pengguna);
        if (listReadList.size() > 0) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              TAMPIL DATA ReadList           |");
            System.out.println("+=============================================+");

            for (var readList : listReadList) {
                System.out.println("ID ReadList     : " + readList.getId());
                System.out.println("Judul Buku      : " + readList.getBuku().getJudul());
                System.out.println("Nama Pengarang  : " + readList.getBuku().getPenulis().getName());
                System.out.println("Nama Penerbit   : " + readList.getBuku().getPenerbit().getName());
                System.out.println("Nama Pengguna   : " + readList.getPengguna().getName());
                System.out.println("+=============================================+");
            }
        } else {
            System.out.println("ReadList kosong.");
        }
        input.nextLine();
    }

    @Override
    public void edit() {
        System.out.println("Fitur belum tersedia.");
        input.nextLine();
    }

    @Override
    public void hapus() {
        var indexPeminjaman = pilih();
        if (indexPeminjaman.isPresent()) {
            readListRepository.remove(indexPeminjaman.get());
            System.out.println("ReadList berhasil dihapus.");
        } else {
            System.out.println("ReadList tidak ditemukan.");
        }
        input.nextLine();
    }
}
