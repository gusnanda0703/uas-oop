package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Buku;
import models.Pengguna;
import models.ReadList;

public class ReadListRepository extends CrudRepository<ReadList> {

    public ReadListRepository(List<ReadList> repo) {
        super(repo);
    }

    public List<Buku> listBukuByPengguna(Pengguna pengguna) {
        List<Buku> books = new ArrayList<>();

        for (var readList : super.repo) {
            if (readList.getPengguna().equals(pengguna)) {
                books.add(readList.getBuku());
            }
        }
        return books;
    }

}
