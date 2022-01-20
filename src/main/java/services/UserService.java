package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;
import entities.User;

public class UserService {
	public boolean create(User o) {
        
        
        try {
            String req = "insert into user values (null, ?, ?,?,?,?,?)";
            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
            pr.setString(1, o.getNom());
            pr.setString(2, o.getPrenom());
            pr.setString(3, o.getEmail());
            pr.setString(4, o.getPassword());
            pr.setString(5, o.getEtat());
            pr.setString(6, o.getRole());
            
            
            if (pr.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
	 public boolean update(User o) {
	        try {
	            String req = "update user set nom = ? ,prenom = ? ,email= ?, password= ?, etat= ?, role= ? where id = ?";
	            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
	            pr.setString(1, o.getNom());
	            pr.setString(2, o.getPrenom());
	            pr.setString(3, o.getEmail());
	            pr.setString(4, o.getPassword());
	            pr.setString(5, o.getEtat());
	            pr.setString(6, o.getRole());


	            pr.setInt(7, o.getId());
	            if (pr.executeUpdate() != 0) {
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 public boolean delete(User o) {
	        try {
	            String req = "delete from user where id = ?";
	            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
	            pr.setInt(1, o.getId());
	            if (pr.executeUpdate() != 0) {
	                return true;
	            }
	        } catch (SQLException e) {

	        }
	        return false;
	    }
	  public User findById(int id) {
	        try {
	            String req = "select * from user where id = ?";
	            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
	            pr.setInt(1, id);
	            ResultSet rs = pr.executeQuery();
	            if (rs.next()) {
	                return new User(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("etat"),rs.getString("role"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	  public User findByEmail(String email) {
	        try {
	            String req = "select * from user where email = ?";
	            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
	            pr.setString(1, email);
	            ResultSet rs = pr.executeQuery();
	            if (rs.next()) {
	                return new User(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("etat"),rs.getString("role"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	  
	  public List<User> findAll() {
	        List<User> users = new ArrayList<User>();
	        try {
	            String req = "select * from user";
	            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
	            ResultSet rs = pr.executeQuery();
	            while (rs.next()) {
	                users.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("etat"),rs.getString("role")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }
	  public User login(String email, String password) {
	        try {
	            String req = "select * from user where email = ? and password = ?";
	            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
	            pr.setString(1, email);
	            pr.setString(2, password);
	            ResultSet rs = pr.executeQuery();
	            if (rs.next()) {
	                return new User(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("etat"),rs.getString("role"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
		

	
	
}
