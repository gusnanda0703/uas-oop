import java.util.Scanner;

import menu.MainMenu;
import models.User;
import repositories.BukuRepository;
import repositories.ReadListRepository;
import repositories.UserRepository;
import utils.ScreenHelper;

public class ReadLater {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserRepository users = new UserRepository(Seeder.seedUser());
        BukuRepository books = new BukuRepository(Seeder.seedBuku());
        ReadListRepository readList = new ReadListRepository(Seeder.seedReadList());

        boolean isRunning = true;
        User auth = null;

        while (isRunning) {
            while (auth == null) {
                auth = login(users);
            }
            new MainMenu(users, books, readList, auth);

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
        scanner.close();
    }

    private static User login(UserRepository users) {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|                    LOGIN                    |");
        System.out.println("+=============================================+");

        System.out.print("email     : ");
        String email = scanner.nextLine();

        System.out.print("password  : ");
        String password = scanner.nextLine();

        var user = users.findFirstByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            System.out.println("email atau password tidak ditemukan");
            scanner.nextLine();
            return null;
        }
    }

}