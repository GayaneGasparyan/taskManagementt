package servlet;

import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/managerHomeServlet")
public class ManagerHomeServlet extends HttpServlet {
UserManager userManager=new UserManager();

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<User> users=userManager.getAllUsers();
      req.setAttribute("users",users);
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rePassword = req.getParameter("re-password");
            String userType=req.getParameter("userType");
            if (!password.equals(rePassword)) {
                req.getSession().setAttribute("msg", "passwords are not match");
                resp.sendRedirect("/WEB-INF/managerHomeServlet.jsp");
            }else {
                User user = User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .userType(UserType.valueOf(userType))
                        .build();
                userManager.addUser(user);

                req.getSession().setAttribute("msg", "user was register successfully");
                resp.sendRedirect("/WEB-INF/managerHomeServlet.jsp");
            }
        }
}
