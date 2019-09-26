package app.servlet;

import app.service.UserServiceImpl;
import app.model.User;
import app.service.UserServiceImplHb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("name"));

//        new UserServiceImpl().create(user);
        UserServiceImplHb.getInstance().create(user);

        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }
}