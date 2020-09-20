package xyz.ioc.web.post;

import io.github.mcroteau.Parakeet;
import xyz.cafe.dao.PostDao;
import xyz.cafe.dao.UserDao;
import xyz.cafe.model.Post;
import xyz.cafe.model.User;
import xyz.cafe.common.Constants;
import xyz.cafe.common.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Parakeet parakeet = (Parakeet) context.getAttribute(Constants.PARAKEET_LOOKUP);
        PostDao postDao = (PostDao) context.getAttribute(Constants.POSTS_DAO_LOOKUP);
        UserDao userDao = (UserDao) context.getAttribute(Constants.USER_DAO_LOOKUP);

        if(parakeet.isAuthenticated()){
            User user = userDao.getUser(parakeet.getUser());
            Post post = new Post();
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            post.setTitle(title);
            post.setContent(content);
            post.setDateCreated(Utils.getDate());
            post.setUserId(user.getId());

            Post savedPost = postDao.save(post);
            String permission = Constants.POST_PREFIX + savedPost.getId();
            userDao.saveUserPermission(permission, user.getId());

            req.setAttribute("message", "Successfully saved post!");
            req.setAttribute("post", savedPost);
            req.getRequestDispatcher("/jsp/post/edit.jsp").forward(req, resp);
        }else{
            resp.sendRedirect(context.getContextPath() + "/signin");
        }
    }
}
