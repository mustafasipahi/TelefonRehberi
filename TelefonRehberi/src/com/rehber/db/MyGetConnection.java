package com.rehber.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rehber.dao.TelefonDomain;

public class MyGetConnection extends Baglanti {

	public void insertTablo(TelefonDomain telefonDomain) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO kisiler (Adi,Soyadi,Telefonu) VALUES "
									+ "('"+telefonDomain.getAdi()+"','"+telefonDomain.getSoyadi()+"','"+telefonDomain.getTelefonNo()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TelefonDomain bul(int id) {
		
		TelefonDomain bulunacakkisi = new TelefonDomain();
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kisiler");
			
			while(resultSet.next()) {
				if (id == resultSet.getInt("Id")) {
					
					bulunacakkisi.setId(resultSet.getInt("Id"));
					bulunacakkisi.setAdi(resultSet.getString("Adi"));
					bulunacakkisi.setSoyadi(resultSet.getString("Soyadi"));
					bulunacakkisi.setTelefonNo(resultSet.getString("Telefonu"));
				}
			}
			
			statement.close();
			connection.close();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bulunacakkisi;
	}
	
	public void sil(TelefonDomain telefonDomain) {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM kisiler WHERE Adi = '"+telefonDomain.getAdi()+"' ");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void duzenle(TelefonDomain yenitelefonDomain,TelefonDomain eskitelefonDomain) {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE kisiler SET Id = "+yenitelefonDomain.getId()+" WHERE Id = "+eskitelefonDomain.getId()+" ");
			statement.executeUpdate("UPDATE kisiler SET Adi = '"+yenitelefonDomain.getAdi()+"' WHERE Adi = '"+eskitelefonDomain.getAdi()+"' ");
			statement.executeUpdate("UPDATE kisiler SET Soyadi = '"+yenitelefonDomain.getSoyadi()+"' WHERE Soyadi = '"+eskitelefonDomain.getSoyadi()+"' ");
			statement.executeUpdate("UPDATE kisiler SET Telefonu = '"+yenitelefonDomain.getTelefonNo()+"' WHERE Telefonu = '"+eskitelefonDomain.getTelefonNo()+"' ");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<TelefonDomain> rehberiListele(){
		List<TelefonDomain> list = new ArrayList<TelefonDomain>();
		TelefonDomain telefonDomain = null;
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kisiler");
			
			while(resultSet.next()) {
				telefonDomain = new TelefonDomain();
				telefonDomain.setId(resultSet.getInt("Id"));
				telefonDomain.setAdi(resultSet.getString("Adi"));
				telefonDomain.setSoyadi(resultSet.getString("Soyadi"));
				telefonDomain.setTelefonNo(resultSet.getString("Telefonu"));
				
				list.add(telefonDomain);
			}
			statement.close();
			connection.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void getAlter() {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute("ALTER TABLE kisiler AUTO_INCREMENT = 1");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(getUrl(),getKullaniciadi(),getSifre());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
