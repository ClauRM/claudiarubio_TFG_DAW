package controladorDB;

import java.sql.*;

import modelo.Usuario;

public class GestorDB {

	String RUTA = "jdbc:mysql://localhost:3306/cronomedbd";
	String USUARIO ="root";
	String PASSWORD = "";
	
	Connection conection; // objeto de la clase Connection
	PreparedStatement prepareStatement; // variable de tipo prepareStatement
	ResultSet resultSet; // variable de tipo resultSet

	public Connection abrirConexion() {
		try {
			// indicar de forma dinamica, donde esta la libreria
			Class.forName("com.mysql.cj.jdbc.Driver");
			// establecer la conexion
			conection = DriverManager.getConnection(RUTA,USUARIO,PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conection;

	}
	
	// CERRAR CONEXION A LA BD
	public void cerrarConexion() {
		try {
			conection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// REALIZA UNA CONEXION A LA BD, EJECUTA UNA CONSULTA SIN PARAMETROS Y RETORNA EL RESULTADO
	public ResultSet getResult(String consultaSql) {
		
		conection = abrirConexion(); // establecer la conexion, la variable conetion va a ser igual a mi objeto
		
			try {
				prepareStatement = conection.prepareStatement(consultaSql);
				// prepare statement es igual a la conexion con parametro la consulta
				// resulSet ejecuto la consulta
				resultSet = prepareStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//cerrarConexion();
		
		return resultSet;
	}
	
	//INSERT NUEVO USUARIO
	public int insertarUsuarioDB(Usuario usuario) {
				
		String consulta = "INSERT INTO usuarios(nombre, email, password) VALUES (?,?,?)"; //consultaSql
		int resultado = 0; // resultado de ejecutar la consulta de insercion: 1 ok - 2 nada
		
		try {
			conection = abrirConexion(); // establecer la conexion, la variable conetion va a ser igual a mi objeto
			prepareStatement = conection.prepareStatement(consulta);
			//indico cuales son los parametos de la consulta
			prepareStatement.setString(1, usuario.getNombre());
			prepareStatement.setString(2, usuario.getEmail());
			prepareStatement.setString(3, usuario.getPassword());
			//ejecuto query
			resultado = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		cerrarConexion();

		return resultado;
	}
}