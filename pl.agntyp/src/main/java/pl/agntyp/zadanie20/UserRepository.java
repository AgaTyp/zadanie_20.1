package pl.agntyp.zadanie20;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        User user = new User("Marek", "Wilk", 30);
        User user1 = new User("Zofia", "Kowalska", 27);
        User user2 = new User("Wojciech", "Mak", 36);
        users.add(user);
        users.add(user1);
        users.add(user2);
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }
}
