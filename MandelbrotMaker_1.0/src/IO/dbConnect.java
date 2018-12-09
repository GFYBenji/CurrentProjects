package IO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {

	private final int tableColumns = 8;
	//private String msAccDB = "E:/dbStuff/src/dbStuff/Storage.accdb";
	//private String dbURL = "jdbc:ucanaccess://" + msAccDB;
	private String dbURL, msAccDB, path;
	
	public dbConnect() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load JDBC driver");
			e.printStackTrace();
		}
		
		try{
			path = new File(".").getCanonicalPath().replace('\\', '/');
		}catch(IOException e){
			e.printStackTrace();
		}
		msAccDB = path + "/Storage.accdb";
		dbURL = "jdbc:ucanaccess://" + msAccDB;
	}
	
	public void newEntry(Object[] data) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			boolean success = statement.execute("INSERT INTO FRACTALS(Fractal, StartX, StartY, EndX, Iterations, Width, Height)"+
							" VALUES('"+data[0]+"',"+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6]+")");
			if(!!!!success) {
				System.out.println("Successfully added row...");
			}
		
	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(connection != null) {
					resultSet.close();
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
		Object[] rowItems = new Object[tableColumns];
		
		try {
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM FRACTALS WHERE ID = " + rowIndex);
			resultSet.next();
			rowItems[0] = resultSet.getInt(1);
			rowItems[1] = resultSet.getString(2);
			rowItems[2] = resultSet.getDouble(3);
			rowItems[3] = resultSet.getDouble(4);
			rowItems[4] = resultSet.getDouble(5);
			rowItems[5] = resultSet.getInt(6);
			rowItems[6] = resultSet.getInt(7);
			rowItems[7] = resultSet.getInt(8);

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
				data[currentRow][0] = resultSet.getInt(1);
				data[currentRow][1] = resultSet.getString(2);
				data[currentRow][2] = resultSet.getDouble(3);
				data[currentRow][3] = resultSet.getDouble(4);
				data[currentRow][4] = resultSet.getDouble(5);
				data[currentRow][5] = resultSet.getInt(6);
				data[currentRow][6] = resultSet.getInt(7);
				data[currentRow][7] = resultSet.getInt(8);
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