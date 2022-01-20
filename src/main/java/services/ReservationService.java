package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

import entities.Reservation;

public class ReservationService {
	UserService us;
	ChambreService cs;

	public ReservationService() {
		this.us = new UserService();
		this.cs = new ChambreService();
	}

	public boolean create(Reservation r) {

		try {
			String req = "insert into booking values (null, ?, ?,?,?,?)";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, r.getChambre().getId());
			pr.setInt(2, r.getClient().getId());
			pr.setDate(3, new java.sql.Date(r.getDated().getTime()));
			pr.setDate(4, new java.sql.Date(r.getDatef().getTime()));
			pr.setInt(5, r.getPrix());

			if (pr.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean update(Reservation r) {
		try {
			String req = "update booking set roomId= ?, clientId= ?, startDate = ? ,endDate = ? ,price= ?  where id = ?";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, r.getChambre().getId());
			pr.setInt(2, r.getClient().getId());
			pr.setDate(3, new java.sql.Date(r.getDated().getTime()));
			pr.setDate(4, new java.sql.Date(r.getDatef().getTime()));
			pr.setInt(5, r.getPrix());

			pr.setInt(6, r.getId());
			if (pr.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Reservation r) {
		try {
			String req = "delete from booking where id = ?";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, r.getId());
			if (pr.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {

		}
		return false;
	}

	public Reservation findById(int id) {
		try {
			String req = "select * from booking where id = ?";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return new Reservation(rs.getInt("id"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getInt("price"),
						us.findById(rs.getInt("clientId")), cs.findById(rs.getInt("roomId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findAll() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			String req = "select * from booking";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {

				reservations.add(new Reservation(rs.getInt("id"), rs.getDate("startDate"), rs.getDate("endDate"),
						rs.getInt("price"), us.findById(rs.getInt("clientId")), cs.findById(rs.getInt("roomId"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}

}
