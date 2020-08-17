package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class BBDD {

		private static String user = "postgres";
		private static String pass= "masterkey";
		private static Statement stmt = null;
		private static PreparedStatement psmst ;
		private static ResultSet rs;
		private static Connection conn = null;
		private int tf;
		
		
		public BBDD() {
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Base_Crud",user,pass);

			} catch (SQLException|ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		public  Connection getConnection() { 
			return conn;
		}

		  public void desconectar(){
		      conn = null;
		   }
		  
		   public int totalFilas() {
			   return tf;
		   }
		   
		   
		   
		   public boolean ejecutarCambio(BBDD cnn, String sql) {
				try {
					Statement st = cnn.getConnection().createStatement();
					st.executeUpdate(sql);
					st.close();
					return true;
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		   }
		   
		   public ResultSet abrirConsulta(BBDD cnn, String sql) {
				tf = 0;
				
				try {
				
					stmt = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery(sql);
				    if (rs.last()) { 
				    	tf = rs.getRow();
				        rs.first();
				        return rs;
				    }
				    return null;
				} 
				catch (SQLException e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
					return null;
				}
		   }

		   public void cerrarConsulta() {
			   try {
				   rs.close();
				   stmt.close();
			   }
			   catch (SQLException e) {
				   System.out.println(e.getMessage());
				   JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
			   }
		   }
		/*
		public static void sacardatos() {
			try {
				psmst = conn.prepareStatement("SELECT * FROM prueba");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		public static void ingresoDatos()  {
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("insert into prueba (columna) values('hola amigo')");
			} catch (SQLException e) {
				
				
			}
		}*/
		
	}
