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

    private static Penulis rowling = new Penulis("J.K. Rowling", "rowling@gamil.com", "rowling");
    private static Penerbit bloomsbury = new Penerbit("Bloomsbury Publishing", "bloomsbury@gmail.com", "bloomsbury");
    private static Buku harryPotter = new Buku("Harry Potter and the Philosopher's Stone", rowling, bloomsbury, 1997);

    private static Penulis tolkien = new Penulis("J.R.R. Tolkien", "tolkien@gmail.com", "tolkien");
    private static Penerbit allenUnwin = new Penerbit("Allen & Unwin", "allenUnwin@gmail.com", "allenUnwin");
    private static Buku lordOfTheRings = new Buku("The Lord of the Rings", tolkien, allenUnwin, 1954);

    private static Penulis orwell = new Penulis("George Orwell", "orwell@gmail.com", "orwell");
    private static Penerbit seckerWarburg = new Penerbit("Secker & Warburg", "seckerWarburg@gamil.com","seckerWarburg");
    private static Buku nineteenEightyFour = new Buku("Nineteen Eighty Four", orwell, seckerWarburg, 1949);

    private static Pengguna agus = new Pengguna("agus", "agus@gmail.com", "220010137");
    private static Pengguna ardayana = new Pengguna("ardayana", "ardayana@gmail.com", "220010086");
    private static Pengguna bagus = new Pengguna("bagus", "bagus@gmail.com", "220010031");

    public static List<User> seedUser() {
        List<User> users = new ArrayList<>();
        users.add(admin);
        users.add(rowling);
        users.add(tolkien);
        users.add(orwell);
        users.add(bloomsbury);
        users.add(allenUnwin);
        users.add(seckerWarburg);
        users.add(agus);
        users.add(ardayana);
        users.add(bagus);
        return users;
    }

    public static List<Buku> seedBuku() {
        List<Buku> books = new ArrayList<>();
        books.add(harryPotter);
        books.add(lordOfTheRings);
        books.add(nineteenEightyFour);
        return books;
    }

    public static List<ReadList> seedReadList() {
        List<ReadList> readLists = new ArrayList<>();
        readLists.add(new ReadList(agus, harryPotter));
        readLists.add(new ReadList(ardayana, lordOfTheRings));
        readLists.add(new ReadList(bagus, nineteenEightyFour));
        return readLists;
    }

}
