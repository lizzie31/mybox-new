package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controllers.forgetPassCon;
import controllers.logInCon;
import Model.Envelope;
import Model.User;
import Model.directories;
import Model.file;
import Model.interestGroups;
import Model.logInMod;
import ocsf.server.*;
import server.EchoServer;
import server.serverGui;
import server.ServerLogGui;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 */
public class EchoServer extends AbstractServer 
{
    private Connection conn;
    private ServerController controller;
     


    ResultSet rs;
    ResultSet rs1;
    int flag = 0;
    int insertFlag = 0;
    int whereFlag = 0;
    String fileName = null;
    String fileDir = null;
    ConnectionToClient ct;
    boolean updateFlag = false;
 
  final public static int DEFAULT_PORT = 5555; //The default port to listen on.
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */


    public void createTableUser(){
        Statement stmt;
        try {
            stmt = this.getConn().createStatement();
            ///////////////*********remember to retrieve*************/////////////
            //stmt.executeUpdate("create table user(username VARCHAR(40),email VARCHAR(40), password int);");
            //stmt.executeUpdate("INSERT INTO user VALUES('A','pit@gmail.com',12344);");
            //stmt.executeUpdate("INSERT INTO user VALUES('CC','pit234@gmail.com',3231);");
        } catch (SQLException e) {    e.printStackTrace();}
    }
  public EchoServer(int port) 
  {
    super(port);
  }

  //Instance methods ***********************************************
  
//This method handles any messages received from the client. 
 public void handleMessageFromClient (Object msg, ConnectionToClient client)     
  {  
	  Envelope en=(Envelope)msg;
	  User user=null;
	  int write=0;
   try{
	  Statement stmt = conn.createStatement();
   if(msg instanceof Envelope)
   {
	 if((en.getTask()).equals("login"))  //search Login
	  {
		  logInMod showfiles=(logInMod)en.getObject();
		  file f;
		  String username;
		  String pass;
		  String mail;
		  int status;
		  ArrayList<directories> userDirectories=new ArrayList<>();
		  ArrayList<file> files=new ArrayList<>();
		  directories directory;
		  String re = "SELECT * FROM users WHERE users.username= '"+(showfiles.getUserName()+"' AND users.password='"+showfiles.getPassword()+"'");
		  rs = stmt.executeQuery(re);
		  
		  if(rs.next()==true)
		  {  
			    username=rs.getString(1);
			    pass=rs.getString(2);
			    mail=rs.getString(3);
			    status=rs.getInt(4);
			    ArrayList<String> dirname=new ArrayList<>();
			    re = "SELECT directory FROM test.userdirectories WHERE userdirectories.username='"+(showfiles.getUserName()+"'");
				rs1 = stmt.executeQuery(re);
				while(rs1.next())
				 {
					dirname.add(rs1.getString(1));
				 }
				
				for(int i=0;i<dirname.size();i++)
				{
					re=("SELECT * FROM userdirectories WHERE userdirectories.directory= '"+dirname+"' AND userdirectories.username='"+username+"'");
					rs1=stmt.executeQuery(re);
					if(rs1.next()==true)
					 {
						 f=new file(rs1.getString(3),rs1.getString(4),rs1.getInt(5),rs1.getString(6));
						 files.add(f);
					 }
					 
					 directory=new directories(files,dirname.get(i));
					 userDirectories.add(directory);
				}
				
	       	 user = new User(username,pass,mail,status,userDirectories);
	    	 en=new Envelope(user,"log in handle");
	    	 client.sendToClient(en);
		  }
		
		  else 
			 client.sendToClient("Not found User");
	  }
	
	 if(en.getTask().equals("forgotPass"))
	  {
		  forgetPassCon forgot=(forgetPassCon)en.getObject();
		  String re = "SELECT * FROM users WHERE users.email= '"+(forgot.getUserMail()+"'");
		  rs = stmt.executeQuery(re);
		  if(rs.next()==true)
		  {
			  client.sendToClient("we found the mail!");
			  
		  }
		  
		  else
			  client.sendToClient("mail doesn't exists");
	  }
	 
	if(en.getTask().equals("log in status"))
	{
	   User userloged=(User)en.getObject();
	   String upd = "UPDATE users SET status= '1' WHERE users.username = '"+(userloged.getUsreName()+"' AND users.password='"+userloged.getUpassword())+"'";
	   stmt.executeUpdate(upd);
       controller.SetLog(userloged,"login"); //update the login serverLogGui
	}
    if(en.getTask().equals("log out status"))
	{
	   User userloged=(User)en.getObject();
	   String upd = "UPDATE users SET status= '0' WHERE users.username = '"+(userloged.getUsreName()+"' AND users.password='"+userloged.getUpassword())+"'";
	   stmt.executeUpdate(upd);
	   controller.SetLog(userloged,"logout");  //update the logout serverLogGui
    }
    if(en.getTask().equals("show user interest groups"))
    {
    	interestGroups s= null;
    	user=(User)en.getObject();
    	ArrayList<interestGroups> interestGroup=new ArrayList<>();

    	String re="SELECT * FROM test.userinterestgroups WHERE userinterestgroups.username= '"+user.getUserName() +"'";

    	 rs = stmt.executeQuery(re);
    	 while(rs.next()==true)
    	 {
    		s=new interestGroups(rs.getString(2));
    		interestGroup.add(s);
    	
    	 }
    	 user.setInterestGroupInDB(interestGroup);
 
 		 client.sendToClient(en);	 
    }
    if(en.getTask().equals("show all interest groups"))
    {
    	interestGroups s= null;
    	ArrayList<interestGroups> allGroups=new ArrayList<>();
    	String re="SELECT * FROM test.interestgroups";
      	 rs = stmt.executeQuery(re);
      	 while(rs.next()==true)
    	 {
    		 s=new interestGroups(rs.getString(1));
    		 allGroups.add(s);
    	 }
      	en=new Envelope(allGroups,"show all interest groups");
      	 client.sendToClient(en);
	}
  }
   
    
    
    if(en.getTask().equals("search files"))
    {
 
    	String str1=(String)msg;
    	/*if(str1.equals("search files")){
    	file f= null;
    	file f;
    	String temp;
    	ArrayList<file> files=new ArrayList<>();
    	String re="SELECT filename FROM test.files";
    	 rs = stmt.executeQuery(re);
    	 while(rs.next()==true)
    	 {

    		f=new file(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
    		files.add(f);
    	
    	 }
    	 
    
    	 en=new Envelope(files,"search files");
 		 client.sendToClient(en);
    	}
    	if(str1.equals("show all interest groups"))
    	{
    		interestGroups s= null;
        	ArrayList<interestGroups> allGroups=new ArrayList<>();
        	String re="SELECT * FROM test.interestgroups";
          	 rs = stmt.executeQuery(re);
          	 while(rs.next()==true)
        	 {
        		 s=new interestGroups(rs.getString(1));
        		 allGroups.add(s);
        	 }
          	en=new Envelope(allGroups,"show all interest groups");
          	 client.sendToClient(en);
    	}
    	*/

    }
    
  }
	   
	       
      catch (SQLException e) {
        	e.printStackTrace();
      } 
     catch (IOException e) {
      e.printStackTrace();}
 }
  
   private void sendToClient(String string) {
	// TODO Auto-generated method stub
	
}

  

  
  public static ArrayList<String> Select(Connection con1, String str, String table,String where,ConnectionToClient client)
    {
        ArrayList<String> arr= new ArrayList<String>();
        String sql = str;
                arr=selectFromFile(con1,sql,client);
             if (table.equals("file"))
                arr=selectFromFile(con1,sql,client);
        return arr;
        
    }
  
  public static ArrayList<String> selectFromFile(Connection con1, String sql,ConnectionToClient client)
  {
      int i = 0;
      ArrayList<String> arr= new ArrayList<String>();
      try 
      {
          Statement st = con1.createStatement();
          ResultSet rs = st.executeQuery(sql);
          int count = ServerController.countRS(con1 , "file");
          String[] fName = new String[count];
          String[] fDir = new String[count];
          
              while(rs.next()) //fill array (numbers and prices)
              {
                  fName[i] = rs.getString(1);
                  fDir[i] = rs.getString(2);
              }         
              rs = st.executeQuery(sql);
              while(rs.next())
              {
                  String from = rs.getString("fileName");
                  String to = rs.getString("fileDir");
                  arr.add(from + "\t");
                  arr.add(to + "\n");
              }
          
          rs.close();    
      } catch (SQLException e) {    e.printStackTrace();}

      return arr;
  }
  public Connection getConn() {
        return conn;
    }
      
     
     public void setConn(Connection conn) {
        this.conn = conn;
    }
     public void setController(ServerController controller) {
            this.controller = controller;
        }

	 
      
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
}
//End of EchoServer class
