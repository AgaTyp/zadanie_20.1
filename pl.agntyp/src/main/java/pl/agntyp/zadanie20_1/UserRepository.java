package pl.agntyp.zadanie20_1;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }
}
