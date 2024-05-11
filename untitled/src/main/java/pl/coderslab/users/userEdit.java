package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class userEdit extends HttpServlet {
   private String idParam;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/editUser.jsp").forward(request, response);
        String userIdParam = request.getParameter("id");
        System.out.println("ID użytkownika: " + userIdParam);
        idParam = request.getParameter("id");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameParam = request.getParameter("userName");
        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");
        String userIdParam = idParam;


        if (nameParam != null && emailParam != null && passwordParam != null && userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);

                UserDao userDao = new UserDao();
                User user = userDao.read(userId);

                if (user != null) {
                    user.setUserName(nameParam);
                    user.setEmail(emailParam);
                    user.setPassword(passwordParam);

                    userDao.update(user);

                    request.setAttribute("user", user);

                  //  request.getRequestDispatcher("/user/list").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/user/list");
                } else {
                    response.getWriter().append("Użytkownik o podanym identyfikatorze nie istnieje.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().append("Nieprawidłowy identyfikator użytkownika.");
            }
        } else {
            response.getWriter().append("Wystąpił błąd podczas zmiany danych użytkownika.");
        }
    }
}