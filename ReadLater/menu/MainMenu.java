package menu;

import java.util.Scanner;

import enums.Role;
import models.Pengguna;
import models.User;
import repositories.BukuRepository;
import repositories.ReadListRepository;
import repositories.UserRepository;
import utils.ScreenHelper;

public class MainMenu {
    private Scanner input;

    private MenuBuku menuBuku;
    private MenuPenulis menuPenulis;
    private MenuPenerbit menuPenerbit;
    private MenuReadList readListMenu;
    private MenuPengguna menuPengguna;

    public MainMenu(UserRepository users, BukuRepository books, ReadListRepository readList, User auth) {
        this.input = new Scanner(System.in);
        switch (auth.getRole()) {
            case Role.ADMIN -> {
                this.menuPenulis = new MenuPenulis(users);
                this.menuPenerbit = new MenuPenerbit(users);
                this.menuBuku = new MenuBuku(books, menuPenerbit, menuPenulis);
                this.menuPengguna = new MenuPengguna(users);

                this.tampilMenuAdmin();
            }
            case Role.PENGGUNA -> {
                this.menuBuku = new MenuBuku(books, menuPenerbit, menuPenulis);
                this.readListMenu = new MenuReadList(readList, books, (Pengguna) auth);

                this.tampilMenuPengguna();
            }
            // TODO: tambah case untuk role baru
            default -> {
                System.out.println("Role tidak ditemukan");
            }
        }
    }

    public void tampilMenuAdmin() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|                MAIN MENU ADMIN              |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Data Buku                               |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Data Penulist                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Data Penerbit                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Data Pengguna                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Logout                                  |");
            System.out.println("+=============================================+");

            System.out.print("\nSilakan masukan pilihan anda (0, 1, 2) : ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> menuBuku.tampilMenu();
                case 2 -> menuPenulis.tampilMenu();
                case 3 -> menuPenerbit.tampilMenu();
                case 4 -> menuPengguna.tampilMenu();
                case 0 -> {
                    ScreenHelper.clearConsole();
                    System.out.println("+=============================================+");
                    System.out.println("|             KELUAR DARI PROGRAM             |");
                    System.out.println("+=============================================+\n");
                }
                default -> {
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
                }
            }
        } while (pilihan != 0);
    }

    public void tampilMenuPengguna() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              MAIN MENU PENGGUNA             |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Data Read List                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Logout                                  |");
            System.out.println("+=============================================+");

            System.out.print("\nSilakan masukan pilihan anda (0, 1) : ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> readListMenu.tampilMenu();
                case 0 -> {
                    ScreenHelper.clearConsole();
                    System.out.println("+=============================================+");
                    System.out.println("|             KELUAR DARI PROGRAM             |");
                    System.out.println("+=============================================+\n");
                }
                default -> {
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
                }
            }
        } while (pilihan != 0);
    }

}
