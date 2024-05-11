package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/createUser")
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/createUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameParam = request.getParameter("userName");
        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");

        if(nameParam != null && emailParam != null && passwordParam != null){
            UserDao userDao = new UserDao();
            User user = new User(nameParam,emailParam,passwordParam);
            User createdUser = userDao.create(user);
            if (createdUser != null) {
                response.sendRedirect(request.getContextPath() + "/user/list");
                return;
            }

    }else {
            response.getWriter().append("Wystąpił błąd podczas tworzenia uzytkownika.");
        }
    }
}