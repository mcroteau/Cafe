package xyz.cafe.web.post;

import io.github.mcroteau.Parakeet;
import xyz.cafe.common.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Parakeet parakeet = (Parakeet) context.getAttribute(Constants.PARAKEET_LOOKUP);

        if(parakeet.isAuthenticated()){
            req.getRequestDispatcher("/jsp/post/create.jsp").forward(req, resp);
        }else{
            resp.sendRedirect(context.getContextPath() + "/signin");
        }
    }

}
