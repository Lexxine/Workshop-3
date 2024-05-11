package pl.coderslab.users;

import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("id");
        int userId = Integer.parseInt(userIdParam);
        UserDao userDao = new UserDao();
        userDao.delete(userId);
        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}