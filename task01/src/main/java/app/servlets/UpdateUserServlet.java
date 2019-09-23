package app.servlets;

import app.service.UserService;
import app.models.User;

import javax.servlet.RequestDispatcher;
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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"),
                             req.getParameter("password"),
                             "");
        UserService userService = new UserService();
        if (!userService.validateUser(user)) {
            String newlogin = req.getParameter("newlogin");
            String newpassword = req.getParameter("newpassword");
            String newname = req.getParameter("newname");
            userService.updateUser(user, newlogin, newpassword, newname);
        }

        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }
}
