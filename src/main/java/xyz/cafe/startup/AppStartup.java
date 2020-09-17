package xyz.cafe.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppStartup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application Startup!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application Shutdown!");
    }

}
