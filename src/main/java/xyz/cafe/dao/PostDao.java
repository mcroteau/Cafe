package xyz.cafe.dao;

import xyz.cafe.factory.DbFactory;
import xyz.cafe.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    Connection connection;

    public PostDao(){
        connection = DbFactory.getConnection();
    }

    public Post getById(long id) {
        try {
            String sql = "select * from posts where id = " + id;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                Post post = extractResultSetPost(rs);
                return post;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Post getLast(){
        try {
            String sql = "select * from posts order by date_created desc limit 1 ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                Post post = extractResultSetPost(rs);
                return post;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Post save(Post post){
        try {
            String sql = "insert into posts values ( null, ?, ?, ?, ? );";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getContent());
            stmt.setLong(4, post.getDateCreated());

            int result = stmt.executeUpdate();
            if(result == 1) {
                Post savedPost = getLast();
                return savedPost;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public boolean update(Post post){
        try {
            String sql = "update posts set title = ?, content = ? where id = " + post.getId();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());

            int result = stmt.executeUpdate();
            if(result == 1) {
                return true;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return false;
    }


    public boolean delete(long id){
        try {
            String sql = "delete from posts where id = " + id;

            PreparedStatement stmt = connection.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if(result == 1) {
                return true;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }

    public List<Post> getList(){
        try {
            Statement stmt = connection.createStatement();
            String sql = "select * from posts";
            ResultSet rs = stmt.executeQuery(sql);

            List<Post> posts = new ArrayList<Post>();
            while(rs.next()){
                Post post = extractResultSetPost(rs);
                posts.add(post);
            }

            return posts;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Post extractResultSetPost(ResultSet rs) throws Exception{
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setDateCreated(rs.getLong("date_created"));
        return post;
    }

}
