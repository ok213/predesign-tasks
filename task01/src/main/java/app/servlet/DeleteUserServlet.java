package app.servlet;

import app.service.UserServiceImpl;
import app.service.UserServiceImplHb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        new UserServiceImpl().delete(Long.parseLong(req.getParameter("id")));
        UserServiceImplHb.getInstance().delete(Long.parseLong(req.getParameter("id")));

        resp.sendRedirect("/");
    }

}
