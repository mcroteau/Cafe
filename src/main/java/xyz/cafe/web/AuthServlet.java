package xyz.cafe.web;

import io.github.mcroteau.Parakeet;
import xyz.cafe.common.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Parakeet parakeet = (Parakeet) context.getAttribute(Constants.PARAKEET_LOOKUP);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(parakeet.login(username, password)){
            resp.sendRedirect(req.getContextPath() + "/");
        }else{
            req.setAttribute("message", "Username and password are incorrect! Please try again!");
            req.getRequestDispatcher("/jsp/signin.jsp").forward(req, resp);
        }

    }

}
