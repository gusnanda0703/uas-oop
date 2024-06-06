import models.Pengguna;
import repositories.BookRepository;
import repositories.ReadListRepository;
import repositories.UserRepository;

public class ReadLater {

    public static void main(String[] args) {
        UserRepository userService = new UserRepository(Seeder.seedUser());
        BookRepository bookService = new BookRepository(Seeder.seedBuku());
        ReadListRepository readListService = new ReadListRepository(Seeder.seedReadList());

        Pengguna pengguna = (Pengguna) userService.findFirstByEmail("agus@gmail.com");

        for (var readList : readListService.listBukuByPengguna(pengguna)) {
            System.out.println(readList.getJudul());
        }

        for (var user : userService.getAll()) {
            System.out.println(user.getEmail());
        }

        for (var book : bookService.getAll()) {
            System.out.println(book.getJudul());
        }
    }
}