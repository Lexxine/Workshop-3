package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/showUser")
public class ShowUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("id");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            UserDao userDao = new UserDao();

            if (userDao.read(userId) != null) {
                request.setAttribute("user", userDao.read(userId));

                getServletContext().getRequestDispatcher("/users/userDetails.jsp").forward(request, response);
            } else {
                response.getWriter().append("Użytkownik o podanym ID nie istnieje.");
            }
        } else {
            response.getWriter().append("Brak parametru 'id' w żądaniu");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}