package org.crud.sregion;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDS_Region {

	static Connection connection;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectionOracleDataBase()throws IOException,SQLException {
		
		try{
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER CORRECTAMENTE");
		}catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
		try {
			connection = DriverManager.getConnection(URL,"System","Diazmelo15");
			System.out.println("CONEXION EXITOSA A ORACLE DATA BASE");
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		}
	
	public static void agregarS_Region(int id, String name) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES(?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setString(2, name);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.print("INSERTO: " + id +","+ name);
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void actualizarS_Region(String name, int id) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "UPDATE S_REGION SET NAME= ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2,id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.print("ACTUALIZO: " + id +","+ name);
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
	
	public static void eliminarS_Region(int id) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("ELIMINO:" + id);
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	public static void consultaIndividualS_Region(int id) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rSet = ps.executeQuery();
			
			if(rSet.next()) {
				System.out.println(rSet.getInt("id") + "," + rSet.getString("name"));
			}
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
	
	public static void consultaGeneralS_Region() throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				System.out.println(rSet.getInt("id") + "," + rSet.getString("name"));
			}
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
	
	
	public static void invocaProcedimientoAlmacenado(int id, String name) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
		    CallableStatement cs = connection.prepareCall("CALL proc(?,?)");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
	public static void main(String[] args)throws IOException,SQLException  {
		
		//agregarS_Region(15, "COAHUILA");
		//actualizarS_Region("Coahuila", 15);
		//eliminarS_Region(15);
		//consultaIndividualS_Region(1);
		//invocaProcedimientoAlmacenado(16, "TIJUANA");
	    consultaGeneralS_Region();
	}
}
