package xyz.cafe.web.post;

import io.github.mcroteau.Parakeet;
import xyz.cafe.dao.PostDao;
import xyz.cafe.model.Post;
import xyz.cafe.common.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Parakeet parakeet = (Parakeet) context.getAttribute(Constants.PARAKEET_LOOKUP);
        PostDao postDao = (PostDao) context.getAttribute(Constants.POSTS_DAO_LOOKUP);

        long id = Long.parseLong(req.getParameter("id"));
        String permission = Constants.POST_PREFIX + id;
        if(parakeet.hasPermission(permission) ||
                parakeet.hasRole(Constants.ROLE_OWNER)){
            Post post = postDao.getById(id);
            req.setAttribute("post", post);
            req.getRequestDispatcher("/jsp/post/edit.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, resp);
        }
    }
}