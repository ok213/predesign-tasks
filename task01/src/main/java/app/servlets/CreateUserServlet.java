package app.servlets;

import app.DAO.UserDAO;
import app.models.Model;
import app.models.User;
import app.util.DBHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {

//    private Model model = Model.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"),
                             req.getParameter("password"),
                             req.getParameter("name"));
//        model.create(user);

        Connection connection = DBHelper.getConnection();
        UserDAO userDAO = new UserDAO(DBHelper.getConnection());
        if (!userDAO.validateUser(user)) {
            userDAO.createUser(user);
        }

        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }
}
