package app.servlet;

import app.service.UserService;
import app.model.User;

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
        User user = new UserService().getById(req.getParameter("id"));

        req.setAttribute("user", user);
        req.setAttribute("link", req.getServletPath());

        getServletContext().getRequestDispatcher("/views/updateAndDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"),
                             req.getParameter("password"),"");

        String[] params = {req.getParameter("iden"),
                           req.getParameter("newlogin"),
                           req.getParameter("newpassword"),
                           req.getParameter("newname")};

        new UserService().updateUser(user, params);

        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }
}
