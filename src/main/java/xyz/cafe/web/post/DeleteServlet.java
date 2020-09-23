package xyz.cafe.web.post;

import io.github.mcroteau.Parakeet;
import xyz.cafe.dao.PostDao;
import xyz.cafe.common.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Parakeet parakeet = (Parakeet) context.getAttribute(Constants.PARAKEET_LOOKUP);
        PostDao postDao = (PostDao) context.getAttribute(Constants.POSTS_DAO_LOOKUP);

        if(parakeet.isAuthenticated() &&
                parakeet.hasRole(Constants.ROLE_OWNER)){

            long id = Long.parseLong(req.getParameter("id"));
            postDao.delete(id);
            resp.sendRedirect(context.getContextPath() + "/");

        }else{
            req.setAttribute("message", "You don't have privileges to do this...");
            req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, resp);
        }
    }
}
