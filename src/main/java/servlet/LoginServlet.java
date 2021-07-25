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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getUsersByEmailAndPassword(email, password);

        req.setAttribute("user", user);
        if (user.getUserType() == UserType.USER) {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/UserHome");
        }else {

        if (user.getUserType() == UserType.MANAGER) {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/managerHomeServlet.jsp").forward(req, resp);
        }


    }
}}
//        } else {
//            req.setAttribute("massage", "Email or password invalid");
//            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
//     }}}
//@Override
//protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    String email = req.getParameter("email");
//    String password = req.getParameter("password");
//
//    User user = userManager.getUsersByEmailAndPassword(email, password);
//    if(user == null){
//        req.getSession().setAttribute("msg", "Wrong username or password");
//        resp.sendRedirect("/WEB-INF/login.jsp");
//    } else {
//        req.getSession().setAttribute("user", user);
//        resp.sendRedirect("/WEB-INF/managerHomeServlet.jsp");
//    }
//
//}
//
//    }
