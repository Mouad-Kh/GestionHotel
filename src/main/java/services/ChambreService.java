package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.Connexion;
import entities.Chambre;



public class ChambreService {
	public List<Chambre> search(Date start,Date end,int price, String type) {
        List<Chambre> rooms = new ArrayList<Chambre>();
        String sql = "SELECT * FROM chambre " 
                + "LEFT JOIN booking ON chambre.id = booking.roomId "
                + "WHERE chambre.id NOT IN ( SELECT roomId FROM booking where booking.endDate > ?  OR booking.startDate = ? ) "
                + "AND chambre.prix < ? "
                + "AND chambre.type = ?";

        try {

            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(start.getTime()));
            ps.setDate(2, new java.sql.Date(start.getTime()));
            ps.setInt(3, price);
            ps.setString(4, type);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rooms.add(new Chambre(rs.getInt("id"), rs.getInt("num"), rs.getString("type"), rs.getInt("prix")));
            }

        } catch (SQLException e) {
            System.out.println(" Room search " + e.getMessage());
        }
        return rooms;
    }
public boolean create(Chambre c) {
        
        
        try {
            String req = "insert into chambre values (null, ?, ?,?)";
            PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
            pr.setInt(1, c.getNum());
            pr.setString(2, c.getType());
            pr.setInt(3, c.getPrix());
          
            
            
            if (pr.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
public boolean update(Chambre c) {
    try {
        String req = "update chambre set num = ? ,type = ? ,prix= ?  where id = ?";
        PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
        pr.setInt(1, c.getNum());
        pr.setString(2, c.getType());
        pr.setInt(3, c.getPrix());
       

        pr.setInt(4, c.getId());
        if (pr.executeUpdate() != 0) {
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
public boolean delete(Chambre c) {
    try {
        String req = "delete from chambre where id = ?";
        PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
        pr.setInt(1, c.getId());
        if (pr.executeUpdate() != 0) {
            return true;
        }
    } catch (SQLException e) {

    }
    return false;
}
public Chambre findById(int id) {
    try {
        String req = "select * from chambre where id = ?";
        PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            return new Chambre (rs.getInt("id"), rs.getInt("num"),rs.getString("type"), rs.getInt("prix"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public List<Chambre> findAll() {
    List<Chambre> chambres = new ArrayList<Chambre>();
    try {
        String req = "select * from chambre";
        PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            chambres.add(new Chambre(rs.getInt("id"), rs.getInt("num"), rs.getString("type"), rs.getInt("prix")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return chambres;
}


}
