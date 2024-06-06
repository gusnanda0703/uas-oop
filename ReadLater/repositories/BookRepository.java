package repositories;

import java.util.List;

import models.Buku;

public class BookRepository extends CrudRepository<Buku> {

    public BookRepository(List<Buku> repo) {
        super(repo);
    }

    public Buku findFirstByJudul(String judul) {
        for (var buku : super.repo) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                return buku;
            }
        }
        return null;
    }

}