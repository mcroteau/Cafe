package xyz.cafe.dao;

import xyz.cafe.factory.DbFactory;
import xyz.cafe.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class UserDao {

    Connection connection;

    public UserDao(){
        connection = DbFactory.getConnection();
    }

    public User getUser(String username){
        try {
            Statement stmt = connection.createStatement();
            String sql = "select * from accounts where username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String getPassword(String username){
        try {
            Statement stmt = connection.createStatement();
            String sql = "select password from accounts where username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("password");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public Set<String> getUserRoles(String username){
        try {
            User user = getUser(username);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select r.name from user_roles ur, roles r where ur.role_id = r.id and ur.account_id = " + user.getId());

            Set<String> roles = new HashSet<String>();
            while(rs.next()){
                String role = rs.getString("name");
                roles.add(role);
            }
            return roles;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> getUserPermissions(String username){
        try {
            User user = getUser(username);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select permission from user_permissions where account_id = " + user.getId());
            Set<String> permissions = new HashSet<String>();
            while(rs.next()){
                String permission = rs.getString("permission");
                permissions.add(permission);
            }
            return permissions;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveUserPermission(String permission, long id){
        try {
            String sql = "insert into user_permissions values ( ?, ? );";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.setString(2, permission);

            int result = stmt.executeUpdate();
            if (result == 1) return true;

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return false;
    }
}
