package models;

import enums.Role;
import enums.Status;

public class Penerbit extends User {

    public Penerbit(String name, String email, String password) {
        super(name, email, password, Role.PENERBIT);
    }

    public Buku terbitkanBuku(Buku buku) {
        buku.setPenerbit(this);
        buku.setTahunTerbit(2024);
        buku.setStatus(Status.PUBLISHED);
        return buku;
    }

}
