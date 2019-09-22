package app.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void create(User user) {
        model.add(user);
    }

    public void update(User user, String login, String password, String name) {
        Iterator<User> modelIterator = model.iterator();
        while(modelIterator.hasNext()) {
            User nextUser = modelIterator.next();
            if (nextUser.getLogin().equals(user.getLogin())
                && nextUser.getPassword().equals(user.getPassword())) {
                nextUser.setLogin(login);
                nextUser.setPassword(password);
                nextUser.setName(name);
                break;
            }
        }
    }

    public List<User> list() {
        return model;
    }

    public boolean validate(User user) {
        Iterator<User> modelIterator = model.iterator();
        while(modelIterator.hasNext()) {
            User nextUser = modelIterator.next();
            if (nextUser.getLogin().equals(user.getLogin())
                    && nextUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void delete(User user) {
        Iterator<User> modelIterator = model.iterator();
        while(modelIterator.hasNext()) {
            User nextUser = modelIterator.next();
            if (validate(user) && nextUser.getName().equals(user.getName())) {
                modelIterator.remove();
                break;
            }
        }
    }
}
