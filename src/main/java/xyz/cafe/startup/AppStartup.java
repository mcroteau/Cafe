package xyz.cafe.startup;

import io.github.mcroteau.Parakeet;
import org.h2.tools.RunScript;
import xyz.cafe.common.Constants;
import xyz.cafe.dao.PostDao;
import xyz.cafe.dao.UserDao;
import xyz.cafe.factory.DbFactory;
import xyz.cafe.factory.ParakeetFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileReader;
import java.sql.Connection;

public class AppStartup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {

            System.out.println("Starting up!");
            PostDao postDao = new PostDao();
            UserDao userDao = new UserDao();

            ParakeetFactory parakeetFactory = new ParakeetFactory();
            Parakeet parakeet = parakeetFactory.getParakeet();

            Connection conn = DbFactory.getConnection();
            ServletContext context = sce.getServletContext();
            context.setAttribute(Constants.PARAKEET_LOOKUP, parakeet);
            context.setAttribute(Constants.POSTS_DAO_LOOKUP, postDao);
            context.setAttribute(Constants.USER_DAO_LOOKUP, userDao);

            RunScript.execute(conn, new FileReader("exec/create-db.sql"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {

            Connection conn = DbFactory.getConnection();
            RunScript.execute(conn, new FileReader("exec/drop-db.sql"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
