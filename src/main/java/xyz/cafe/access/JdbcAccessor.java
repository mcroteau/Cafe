package xyz.cafe.access;

import io.github.mcroteau.resources.access.Accessor;
import xyz.cafe.dao.UserDao;

import java.util.Set;

public class JdbcAccessor implements Accessor {

    UserDao userDao;

    public JdbcAccessor(){
        this.userDao = new UserDao();
    }

    @Override
    public String getPassword(String username) {
        return userDao.getPassword(username);
    }

    @Override
    public Set<String> getRoles(String username) {
        return userDao.getUserRoles(username);
    }

    @Override
    public Set<String> getPermissions(String username) {
        return userDao.getUserPermissions(username);
    }

}
