import java.util.Scanner;

import menu.MainMenu;
import models.User;
import repositories.BookRepository;
import repositories.ReadListRepository;
import repositories.UserRepository;

public class ReadLater {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;
    private static User auth;

    private static UserRepository users = new UserRepository(Seeder.seedUser());
    private static BookRepository books = new BookRepository(Seeder.seedBuku());
    private static ReadListRepository readList = new ReadListRepository(Seeder.seedReadList());

    public static void main(String[] args) {

        while (isRunning) {
            showMenu();
        }

    }

    public static void clearConsole() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        while (auth == null) {
            loginMenu();
        }
        MainMenu menuPerpustakaan = new MainMenu(users, books, readList, auth);
        menuPerpustakaan.tampilMenu();

        // logout
        auth = null;

        // close program
        System.out.print("Apakah anda ingin menutup program? (y|N) : ");
        String jawaban = scanner.nextLine();
        if (jawaban.equalsIgnoreCase("y")) {
            System.out.print("Terima kasih telah menggunakan program ini.");
            isRunning = false;
        }
    }

    private static void loginMenu() {
        clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|                    LOGIN                    |");
        System.out.println("+=============================================+");

        System.out.print("email     : ");
        String email = scanner.nextLine();

        System.out.print("password  : ");
        String password = scanner.nextLine();

        var user = users.findFirstByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            auth = user;
        } else {
            System.out.println("email atau password tidak ditemukan");
            scanner.nextLine();
        }
    }

}