package test;



import java.sql.SQLException;
import java.sql.Statement;

import connection.Connexion;

public class Test {

	public static void main(String[] args) {
		
		  String TUser = "create table user ("
					+ "id int primary key auto_increment,"
					+ "nom varchar(20) NOT NULL UNIQUE,"
					+ "prenom varchar(20) NOT NULL,"
					+ "email varchar(20) NOT NULL UNIQUE,"
					+ "password varchar(20) NOT NULL,"
					+ "etat varchar(20) NOT NULL,"
					+ "role varchar(20) NOT NULL ); ";
		  
		  String TChambre = "create table chambre ("
				  + "id int primary key auto_increment,"
				  + "num int NOT NULL UNIQUE,"
				  + "type varchar(20) NOT NULL ,"
				  + "prix int NOT NULL );";
		  
		  String TBooking = "create table booking ("
		          + "id int primary key auto_increment,"
                  + "roomId int NOT NULL," + "clientId int NOT NULL ,"+"startDate date NOT NULL ,"+"endDate date NOT NULL ,"
                  + "price int NOT NULL );" ;
		  
		  String fk = "alter table booking add "
                  + "constraint fk foreign key (roomId) "
                  + "references chambre(id)"
                  + "ON DELETE CASCADE;";
          String fk2 = "alter table booking add "
                  + "constraint fk2 foreign key (clientId) "
                  + "references user(id)"
                  + "ON DELETE CASCADE;";
		  
		  Statement st;
		try {
			st = Connexion.getInstance().getConnection().createStatement();
			 // st.executeUpdate(TUser);
			//st.executeUpdate(TChambre);
			st.executeUpdate(TBooking);
			st.executeUpdate(fk);
            st.executeUpdate(fk2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
