package menu;

import java.util.ArrayList;
import java.util.List;

import enums.Role;
import models.Menu;
import models.Penulis;
import repositories.UserRepository;
import utils.ScreenHelper;

public class MenuPenulis extends Menu {
    UserRepository data;
    List<Penulis> listPenulis = new ArrayList<>();

    public MenuPenulis(UserRepository data) {
        this.data = data;

        // ambil data penulis
        data.getAll().forEach(it -> {
            if (it.getRole().equals(Role.PENULIS)) {
                listPenulis.add((Penulis) it);
            }
        });
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                DATA PENULIS                 |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Penulis                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Penulis                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Penulis                            |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Penulis                           |");
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
        if (listPenulis.size() > 0) {
            System.out.println("+=============================================+");
            System.out.println("|              TAMPIL DATA PENULIS            |");
            System.out.println("+=============================================+");

            for (Penulis tempPenulis : listPenulis) {
                System.out.println("Nama Penulis  : " + tempPenulis.getName());
                System.out.println("Biografi      : " + tempPenulis.getEmail());
                System.out.println("+=============================================+");
            }
            input.nextLine();
        } else {
            System.out.println("Data penulis kosong, silakan tambahkan data.");
            input.nextLine();
        }
    }

    @Override
    public int pilih() {
        ScreenHelper.clearConsole();
        String namaPenulis = "";
        int penulisDipilih = -1;

        if (listPenulis.size() > 0) {
            do {
                System.out.println("+=============================================+");
                System.out.println("|                 PILIH PENULIS               |");
                System.out.println("+=============================================+");
                for (Penulis tempPenulis : listPenulis) {
                    System.out.printf("%d) %s", tempPenulis.getId(), tempPenulis.getName());
                }

                System.out.print("Silakan pilih nama penulis : ");
                namaPenulis = input.nextLine();
                for (int i = 0; i < listPenulis.size(); i++) {
                    if (listPenulis.get(i).getName().equals(namaPenulis)) {
                        penulisDipilih = listPenulis.get(i).getId();
                        break;
                    }
                }
            } while (penulisDipilih == -1);
        } else {
            System.out.println("Data penulis kosong, silakan tambahkan data.");
            input.nextLine();
        }
        return penulisDipilih;
    }
}
