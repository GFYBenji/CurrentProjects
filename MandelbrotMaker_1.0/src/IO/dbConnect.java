package IO;

import Windows.mainMenu;

import java.sql.*;

public class dbConnect {

	private final int tableColumns = 8;
	private String dbURL, msAccDB, path;
	
	public dbConnect() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load JDBC driver");
			e.printStackTrace();
		}
		
		dbURL = "jdbc:ucanaccess://" + mainMenu.dbPath;
	}
	
	public void newEntry(Object[] data, String name) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			boolean success = statement.execute("INSERT INTO FRACTALS(Fractal, XStart, YStart, XEnd, Iterations, Width, Height)"+
							" VALUES('"+name+"',"+data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+")");
			if(success) {
				System.out.println("Successfully added row...");
			}
		
	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(connection != null) {
					statement.close();
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Object[] getEntry(int rowIndex) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Object[] rowItems = new Object[tableColumns -2];
		
		try {
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM FRACTALS WHERE ID = " + rowIndex);
			resultSet.next();
			
			for (int i = 0; i < 6; i++) {
				rowItems[i] = resultSet.getObject(i + 3);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					resultSet.close();
					statement.close();
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowItems;
	}
	
	public Object[][] fillTableRows() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Object[][] data = null;
		try {
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(1) FROM FRACTALS");
			int numOfRows = 0;
			while(resultSet.next()) {
				numOfRows = resultSet.getInt(1);
			}
			resultSet = statement.executeQuery("SELECT * FROM FRACTALS");
			data = new Object[numOfRows][tableColumns];
			int currentRow = 0;
			while(resultSet.next()) {
				
				for(int i = 0; i < tableColumns; i++){
					data[currentRow][i] = resultSet.getObject(i+1);
				}
				currentRow++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					resultSet.close();
					statement.close();
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}
