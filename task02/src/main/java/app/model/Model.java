package app.model;

import app.service.UserService;
import app.service.UserServiceImpl;

public class Model {

    private static Model instance;
    private static UserService userService;

    private Model() {
        userService = new UserServiceImpl();
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

}
