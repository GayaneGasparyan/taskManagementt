package servlet;

import manager.TaskManager;
import model.Status;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {

    TaskManager taskManager=new TaskManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> tasks = taskManager.getAllTasks();
        req.setAttribute("tasks", tasks);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        Task task = Task.builder()
                .name(name)
                .description(description)
                .status(Status.valueOf(status))
                .deadline(new Date())
                .build();
        taskManager.addTask(task);

        req.getSession().setAttribute("msg", "tasks was added");
        resp.sendRedirect("/WEB-INF/managerHomeServlet.jsp");
    }

}

