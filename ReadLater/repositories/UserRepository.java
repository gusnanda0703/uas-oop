package repositories;

import java.util.ArrayList;
import java.util.List;

import enums.Role;
import models.Penerbit;
import models.Pengguna;
import models.Penulis;
import models.User;

public class UserRepository extends CrudRepository<User> {

    public UserRepository(List<User> repo) {
        super(repo);
    }

    public User findFirstByEmail(String email) {
        for (var user : super.repo) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public List<Penulis> getPenulis() {
        List<Penulis> penulis = new ArrayList<>();
        for (var user : super.repo) {
            if (user.getRole().equals(Role.PENULIS)) {
                penulis.add((Penulis) user);
            }
        }
        return penulis;
    }

    public List<Pengguna> getPenggunas() {
        List<Pengguna> penggunas = new ArrayList<>();
        for (var user : super.repo) {
            if (user.getRole().equals(Role.PENGGUNA)) {
                penggunas.add((Pengguna) user);
            }
        }
        return penggunas;
    }

    public List<User> getAdmins() {
        List<User> admins = new ArrayList<>();
        for (var user : super.repo) {
            if (user.getRole().equals(Role.ADMIN)) {
                admins.add(user);
            }
        }
        return admins;
    }

    public List<Penerbit> getPenerbits() {
        List<Penerbit> penerbits = new ArrayList<>();
        for (var user : super.repo) {
            if (user.getRole().equals(Role.PENERBIT)) {
                penerbits.add((Penerbit) user);
            }
        }
        return penerbits;
    }

}
