package models;

import enums.Role;

public class Penulis extends User {

    public Penulis(String name, String email, String password) {
        super(name, email, password, Role.PENULIS);
    }

    public Buku tulisBuku(String judul) {
        return new Buku(judul, this);
    }

}
