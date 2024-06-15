package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Buku;
import models.Pengguna;
import models.ReadList;

public class ReadListRepository extends CrudRepository<ReadList> {

    public ReadListRepository(List<ReadList> repo) {
        super(repo);
    }

    public List<ReadList> listReadListByPengguna(Pengguna pengguna) {
        List<ReadList> read = new ArrayList<>();

        for (var readList : super.repo) {
            if (readList.getPengguna().equals(pengguna)) {
                read.add(readList);
            }
        }
        return read;
    }

    public boolean isExist(Pengguna pengguna, Buku buku) {
        for (var readList : super.repo) {
            if (readList.getPengguna().equals(pengguna) && readList.getBuku().equals(buku)) {
                return true;
            }
        }
        return false;
    }

}
