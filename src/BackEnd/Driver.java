package BackEnd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public Driver(){
		
	}
	
	public Connection getCon(){
		return connection;
	}
	
	public Statement getState(){
		return statement;
	}
	
	public ResultSet getResult(){
		return resultSet;
	}
	
	public void setCon(Connection connection){
		this.connection = connection;
	}
	
	public void setState(Statement statement){
		this.statement = statement;
	}
	
	public void setResult(ResultSet resultSet){
		this.resultSet = resultSet;
	}
}
