package xyz.cafe.startup;

import org.h2.tools.RunScript;
import xyz.cafe.factory.DbFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileReader;
import java.sql.Connection;

public class AppStartup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {

            System.out.println("Starting up!");
            Connection conn = DbFactory.getConnection();
            RunScript.execute(conn, new FileReader("exec/create-db.sql"));

        }catch(Exception ex){
            ex.printStackTrace();
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
