package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final AtomicLong AUTO_ID = new AtomicLong(1);
    private static Map<Long, User> users = new HashMap<>();

    static {
        User user1 = new User(AUTO_ID.getAndIncrement(), "a", "a", "a");
        User user2 = new User(AUTO_ID.getAndIncrement(), "b", "b", "b");
        User user3 = new User(AUTO_ID.getAndIncrement(), "c", "c", "c");
        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);
    }

    @Override
    public void create(User user) {
        user.setId(AUTO_ID.getAndIncrement());
        users.put(user.getId(), user);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(long id) {
        users.remove(id);
    }

    @Override
    public User getById(long id) {
        return users.get(id);
    }
}
