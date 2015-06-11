package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.awt.Desktop;
import java.io.File;
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
import Model.GroupsRequests;
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
    }
  public EchoServer(int port) 
  {
    super(port);
  }

  //Instance methods ***********************************************
  
//This method handles any messages received from the client. 
 public void handleMessageFromClient (Object msg, ConnectionToClient client)     
  {  
	  User user=null;
	  int write=0;
   try{
	  Statement stmt = conn.createStatement();
      if(msg instanceof Envelope)
      {
	     Envelope en=(Envelope)msg;
	   if((en.getTask()).equals("login"))  //search Login
	     {
		    logInMod showfiles=(logInMod)en.getObject();
		    file f;
		    String username;
		    String pass;
		    String mail;
		    int status;
		    ArrayList<directories> userDirectories=new ArrayList<>();
		    ArrayList<file> files;
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
					re=("SELECT * FROM userdirectories WHERE userdirectories.directory= '"+dirname.get(i)+"' AND userdirectories.username='"+username+"'");
					rs1=stmt.executeQuery(re);
					files=new ArrayList<>();
					if(rs1.next()==true)
					 {
						 f=new file(rs1.getString(3),rs1.getString(4),rs1.getInt(5),rs1.getString(6));
						 files.add(f);
					 }
					 
					 directory=new directories(files,dirname.get(i));
					 userDirectories.add(directory);
				}
				interestGroups s= null;
		    	ArrayList<interestGroups> interestGroup=new ArrayList<>();
		    	re="SELECT * FROM test.userinterestgroups WHERE userinterestgroups.username= '"+username +"'";
		    	rs = stmt.executeQuery(re);
		    	while(rs.next()==true)
		    	{
		    	  s=new interestGroups(rs.getString(2));
		    	  interestGroup.add(s);
		    	}
				
	       	 user = new User(username,pass,mail,status,userDirectories,interestGroup);
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
    if(en.getTask().equals("send request to system administrator"))
    {
    	  GroupsRequests request=(GroupsRequests)en.getObject();
    	  String re="INSERT INTO test.requests VALUES('"+request.getGroupName()+"','"+request.getUserName()+"','"+request.getRequestType()+"');";
    	  stmt.executeUpdate(re);
    }
    if(en.getTask().equals("open file"))
    {
    	String path=(String)en.getObject();
    	Desktop desktop;
    	desktop= Desktop.getDesktop();
			try {
				desktop.open(new File(path));
			} catch (IOException e1) {
                e1.printStackTrace();
			}
    }
    if(en.getTask().equals("show all interest groups"))
    {
    	User user1=(User)en.getObject();
    	interestGroups s= null;
    	ArrayList<interestGroups> allGroups=new ArrayList<>();
    	String re="SELECT * FROM test.interestgroups";
      	 rs = stmt.executeQuery(re);
      	 while(rs.next()==true)
    	 {
    		 s=new interestGroups(rs.getString(1));
    		 allGroups.add(s);
    	 }
      	 for(int i=0;i<allGroups.size();i++)
      	 {
      		 for(int j=0;j<user1.getInterestGroupInDB().size();j++)
      		 {
      			 String groupname=user1.getInterestGroupInDB().get(j).getGroupName();
      			 if((allGroups.get(i).getGroupName()).equals(groupname))
      			 {
      				 allGroups.remove(i);
      			 }
      		 }
      	 }
      	en=new Envelope(allGroups,"show all interest groups");
      	 client.sendToClient(en);
	}
 
   if(en.getTask().equals("add new group to DB"))
   {
   	Statement stmt1 = this.getConn().createStatement();
   	interestGroups s= (interestGroups)en.getObject();
   	 rs = stmt1.executeQuery("SELECT groupname FROM test.interestgroups WHERE interestgroups.groupname= '"+(s.getGroupName()+"'"));
   	if (rs.next()==true) 
   	   {
   		client.sendToClient("this name is allready exist");
   	   }
   	else 
   		{
   		stmt1.executeUpdate("INSERT INTO test.interestgroups VALUES('"+s.getGroupName()+"');");
   	
   		client.sendToClient("the group was added sucssesfuly");
   		}
   }
    
    if(en.getTask().equals("search files"))
    {
 
    	Envelope e;
    	file f;
    	 String textField=(String)en.getObject();
    	 ArrayList<file> FinalFiles=new ArrayList<>();
    	String re="SELECT * FROM test.files";
    	 rs = stmt.executeQuery(re);
    	
    	 while(rs.next()==true)
    	 {

    		f=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
    		if(f.getFileName().contains(textField))
    		//if(f.getFileName().indexOf(textField)!=-1)	
    			FinalFiles.add(f);
    	
    	 }
    	 e=new Envelope(FinalFiles,"search file");
 		 client.sendToClient(e);
    	}
    
  }
   if(msg instanceof String)
   {
	   String str=(String)msg;
	   Envelope e;
   	   User user1;
	   if(str.equals("ShowAllUsers"))
	 {
		 ArrayList<User> AllUsers=new ArrayList<>();
	     String re="SELECT * FROM test.users";
	     rs = stmt.executeQuery(re);
	     while(rs.next()==true)
    	 {

    		user1=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));	
    		AllUsers.add(user1);
    	
    	 }
		 e=new Envelope(AllUsers,"all users");
 		 client.sendToClient(e);
     }
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
