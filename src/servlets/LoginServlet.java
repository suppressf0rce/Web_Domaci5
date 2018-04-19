package servlets;


import model.HibUtils;
import model.UsersEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    List<UsersEntity> users;
    public LoginServlet(){
        super();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if(session.getAttribute("userId") !=null && !session.getAttribute("userId").equals("")){
            resp.sendRedirect("checkout.jsp");
        }else {
            resp.sendRedirect("register.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if(session.getAttribute("userId") !=null){
            resp.sendRedirect("checkout.jsp");
        }

        String username = req.getParameter("username");
        String password  = req.getParameter("password");

        users = HibUtils.getAllUsers();
        boolean loginStatus = false;
        Integer userId = null;
        for(UsersEntity user:users){
            if(user.getUsername().equals(username)  && user.getPassword().equals(password)) {
                loginStatus = true;
                userId = user.getId();
            }
        }

        if(loginStatus){
            session.setAttribute("userId",userId);
            resp.sendRedirect("checkout.jsp");
        }else{
            resp.sendRedirect("registerFailed.jsp");
        }
    }
}