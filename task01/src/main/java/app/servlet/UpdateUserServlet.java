package app.servlet;

import app.model.User;
import app.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("user", new UserServiceImpl().getById(id));

        getServletContext().getRequestDispatcher("/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(Long.parseLong(req.getParameter("id")), req.getParameter("login"),
                             req.getParameter("password"), req.getParameter("name"));

        new UserServiceImpl().updateUser(user);

        resp.sendRedirect("/");
    }
}
