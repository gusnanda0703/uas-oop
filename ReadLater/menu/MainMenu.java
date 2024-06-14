package menu;

import java.util.ArrayList;
import java.util.Scanner;

import enums.Role;
import utils.ScreenHelper;
import repositories.UserRepository;
import repositories.BookRepository;
import repositories.ReadListRepository;
import models.User;
import models.Pengguna;

public class MainMenu {
    private UserRepository users;
    private BookRepository books;
    private ReadListRepository readList;

    private Scanner input = new Scanner(System.in);
    private MenuBuku menuBuku;
    private MenuPenulis menuPenulis;
    private MenuPenerbit menuPenerbit;
    private MenuReadList readListMenu;

    public MainMenu(UserRepository users, BookRepository books, ReadListRepository readList, User auth) {
        if (auth.getRole().equals(Role.ADMIN)) {
            this.menuPenulis = new MenuPenulis(users);
            this.menuPenerbit = new MenuPenerbit(users);
            this.menuBuku = new MenuBuku(books);
        }
        if (auth.getRole().equals(Role.PENGGUNA)) {
            this.readListMenu = new MenuReadList(books, readList, (Pengguna) auth);
        }
        this.users = users;
        this.books = books;
        this.readList = readList;

    }

    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                  MAIN MENU                  |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Data Buku                               |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Read Penulist                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Read List                               |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Logout                                  |");
            System.out.println("+=============================================+");
            System.out.print("\nSilakan masukan pilihan anda (0, 1, 2) : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1:
                    menuBuku.tampilMenu();
                    System.out.println("print menu buku");
                    break;
                case 2:
                    menuPenulis.tampilMenu();
                    System.out.println("print menu readlist");
                    break;
                case 3:
                    readListMenu.tampilMenu();
                    System.out.println("print menu readlist");
                    break;
                case 0:
                    ScreenHelper.clearConsole();
                    System.out.println("+=============================================+");
                    System.out.println("|             KELUAR DARI PROGRAM             |");
                    System.out.println("+=============================================+\n");
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
            }
        } while (pilihan != 0);
    }

}
