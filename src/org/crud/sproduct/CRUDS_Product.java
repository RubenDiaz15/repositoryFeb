package org.crud.sproduct;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDS_Product {
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
  
  public static void agregarS_Product(int id, String name,String shortDesc,int longTextId,int imagenId, 
		  int suggestedWhlslPrice,String whlslUnits) throws IOException,SQLException{
	  
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql = "INSERT INTO S_PRODUCT(ID, NAME, SHORT_DESC, LONGTEXT_ID, IMAGE_ID, SUGGESTED_WHLSL_PRICE, WHLSL_UNITS) "
				+ "VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1,id);
		ps.setString(2, name);
		ps.setString(3, shortDesc);
		ps.setInt(4, longTextId);
		ps.setInt(5, imagenId);
		ps.setInt(6, suggestedWhlslPrice);
		ps.setString(7, whlslUnits);
		ps.executeQuery();
		ps.close();
		connection.close();
		System.out.print("INSERTO: " + id +","+ name + "," + shortDesc + "," + longTextId + "," 
		+ imagenId + "," + suggestedWhlslPrice + "," + whlslUnits);
	    }catch (Exception e) {
		System.out.println("Exception: " + e.getMessage());
	   }
    }
  
  public static void actualizarS_Product(int id, String name,String shortDesc,int longTextId,int imagenId, 
		  int suggestedWhlslPrice,String whlslUnits) throws IOException,SQLException{
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql = "UPDATE S_PRODUCT SET  NAME = ?, SHORT_DESC = ?, LONGTEXT_ID =?, IMAGE_ID = ?,"
				+ " SUGGESTED_WHLSL_PRICE = ?, WHLSL_UNITS = ? WHERE ID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, shortDesc);
		ps.setInt(3, longTextId);
		ps.setInt(4, imagenId);
		ps.setInt(5, suggestedWhlslPrice);
		ps.setString(6, whlslUnits);
		ps.setInt(7,id);
		ps.executeQuery();
		ps.close();
		connection.close();
		System.out.print("INSERTO: " + id +","+ name + "," + shortDesc + "," + longTextId + "," 
		+ imagenId + "," + suggestedWhlslPrice + "," + whlslUnits);
	    }catch (Exception e) {
		System.out.println("Exception: " + e.getMessage());
	   }
    }
  
  public static void eliminarS_Product(int id) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "DELETE FROM S_PRODUCT WHERE ID = ?";
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

  public static void consultaIndividualS_Producto(int id) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "SELECT * FROM S_PRODUCT WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rSet = ps.executeQuery();
			
			if(rSet.next()) {
				System.out.println(rSet.getInt("id") + ", " + rSet.getString("name") + ", " + rSet.getString("short_desc")+ ", " 
						+ rSet.getInt("longtext_id")+ ", "  +  rSet.getInt("image_id")+ ", "  + rSet.getFloat("suggested_whlsl_price")+ ", " 
						+ rSet.getString("whlsl_units"));
			}
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
  
  public static void consultaGeneralS_Product() throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "SELECT * FROM S_PRODUCT";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				System.out.println(rSet.getInt("id") + ", " + rSet.getString("name") + ", " + rSet.getString("short_desc")+ ", " 
						+ rSet.getInt("longtext_id")+ ", "  +  rSet.getInt("image_id")+ ", "  + rSet.getFloat("suggested_whlsl_price")+ ", " 
						+ rSet.getString("whlsl_units"));
			}
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
  public static void invocaProcedimientoAlmacenado(int id, String name,String shortDesc,int longTextId,
		  int imagenId, int suggestedWhlslPrice,String whlslUnits) throws IOException,SQLException{
		try {
			connectionOracleDataBase();
			//CONSULTA
		    CallableStatement cs = connection.prepareCall("CALL proc(?,?,?,?,?,?,?)");
			
			cs.setInt(1,id);
			cs.setString(2, name);
			cs.setString(3, shortDesc);
			cs.setInt(4, longTextId);
			cs.setInt(5, imagenId);
			cs.setInt(6, suggestedWhlslPrice);
			cs.setString(7, whlslUnits);
			cs.execute();
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
		
	}
      public static void main(String[] args)throws IOException,SQLException {
	     //agregarS_Product(3, "HP", "begenir boot",1 ,4, 150, "bott");
    	 //actualizarS_Product(1, "boot2", "begenir boot",1 ,4, 150 , "bott");
    	 //eliminarS_Product(2);
    	 //consultaIndividualS_Producto(1);
	     consultaGeneralS_Product();
	
    }
}
