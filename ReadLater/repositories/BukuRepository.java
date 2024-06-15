package repositories;

import java.util.List;

import models.Buku;

public class BukuRepository extends CrudRepository<Buku> {

    public BukuRepository(List<Buku> repo) {
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

    public List<Buku> listBukuTidakDalamIds(List<Integer> ids) {
        return super.repo.stream().filter(buku -> !ids.contains(buku.getId())).toList();
    }
}