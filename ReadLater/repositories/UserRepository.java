package repositories;

import java.util.List;

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

}
