import java.util.ArrayList;
import java.util.List;

import enums.Role;
import models.Buku;
import models.Penerbit;
import models.Pengguna;
import models.Penulis;
import models.ReadList;
import models.User;

public class Seeder {

    private static User admin = new User("chandra", "chandra@gmail.com", "220010010", Role.ADMIN);
    private static Penulis penulis = new Penulis("bagus", "bagus@gmail.com", "220010031");
    private static Penerbit penerbit = new Penerbit("ardayana", "ardayana@gmail.com", "220010086");
    private static Pengguna pengguna = new Pengguna("agus", "agus@gmail.com", "220010137");
    private static Pengguna pengguna2 = new Pengguna("agus2", "agus2@gmail.com", "220010137");

    private static Buku chandraGupta = new Buku("Chandra Gupta", penulis, penerbit, 1990);
    private static Buku bukuBagus = new Buku("Buku Bagus", penulis, penerbit, 1998);

    public static List<User> seedUser() {
        List<User> users = new ArrayList<>();
        users.add(admin);
        users.add(penulis);
        users.add(penerbit);
        users.add(pengguna);
        return users;
    }

    public static List<Buku> seedBuku() {
        List<Buku> books = new ArrayList<>();
        books.add(chandraGupta);
        books.add(bukuBagus);
        return books;
    }

    public static List<ReadList> seedReadList() {
        List<ReadList> readLists = new ArrayList<>();
        readLists.add(new ReadList(pengguna, chandraGupta));
        readLists.add(new ReadList(pengguna2, bukuBagus));
        return readLists;
    }

}
