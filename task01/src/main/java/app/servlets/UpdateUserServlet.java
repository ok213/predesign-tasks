package app.servlets;

import app.models.Model;
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

    private Model model = Model.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"),
                             req.getParameter("password"),
                             "anonymous");
        if (model.validate(user)) {
            String newlogin = req.getParameter("newlogin");
            String newpassword = req.getParameter("newpassword");
            String newname = req.getParameter("newname");
            model.update(user, newlogin, newpassword, newname);
        }

        getServletContext().getRequestDispatcher("/").forward(req, resp);
    }
}
