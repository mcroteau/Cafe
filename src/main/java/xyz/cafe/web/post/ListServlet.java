package xyz.cafe.web.post;

import io.github.mcroteau.Parakeet;
import xyz.cafe.dao.PostDao;
import xyz.cafe.model.Post;
import xyz.cafe.common.Constants;
import xyz.cafe.common.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Parakeet parakeet = (Parakeet) context.getAttribute(Constants.PARAKEET_LOOKUP);
        PostDao postDao = (PostDao) context.getAttribute(Constants.POSTS_DAO_LOOKUP);

        if(parakeet.isAuthenticated()) {
            List<Post> posts = postDao.getList();
            for (Post post : posts) {
                post.setDate(Utils.getDate(post.getDateCreated()));
            }
            req.setAttribute("posts", posts);
        }
        req.getRequestDispatcher("/jsp/post/list.jsp").forward(req, resp);
    }
}
