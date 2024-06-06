package models;

import enums.Role;

public class Pengguna extends User {

    public Pengguna(String name, String email, String password) {
        super(name, email, password, Role.USER);
    }
}
