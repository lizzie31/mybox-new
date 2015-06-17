package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
import view.InterestGroupGui;

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
    private Desktop desktop=null;
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
			    re = "SELECT DISTINCT directory FROM test.user_and_dir WHERE user_and_dir.username='"+(showfiles.getUserName()+"'");
				rs1 = stmt.executeQuery(re);
				while(rs1.next())
				 {
					dirname.add(rs1.getString(1));
				 }
				
				for(int i=0;i<dirname.size();i++)
				{
					re=("SELECT f.filename,f.direction,f.permission,f.fileowner FROM userdirectories as u,files as f WHERE f.filename=u.filename AND u.directory= '"+dirname.get(i)+"' AND u.username='"+username+"'");
					rs1=stmt.executeQuery(re);
					files=new ArrayList<>();
					while(rs1.next()==true)
					 {
						 f=new file(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(4));
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
    
    if(en.getTask().equals("show interest group to user"))
    {
    	interestGroups IG=(interestGroups)en.getObject();
    	file f=null;
    	Envelope e=null;
    	ArrayList<file> readfiles=new ArrayList<>();
    	ArrayList<file> updatefiles=new ArrayList<>();
    	String re="SELECT f.filename,f.direction,f.permission,f.fileowner From file_read_groups as fr,files as f WHERE f.filename=fr.file_name AND fr.interest_group='"+IG.getGroupName()+"'";
    	rs=stmt.executeQuery(re);
    	while(rs.next())
    	{
    		f=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
    		readfiles.add(f);
    	}
    	re="SELECT f.filename,f.direction,f.permission,f.fileowner From file_update_groups as fu,files as f WHERE f.filename=fu.file_name AND fu.interest_group='"+IG.getGroupName()+"'";
    	rs1=stmt.executeQuery(re);
    	while(rs1.next())
    	{
    		f=new file(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(4));
    		updatefiles.add(f);
    	}
    	IG.setFilesForRead(readfiles);
    	IG.setFilesForUpdate(updatefiles);
    	e=new Envelope(IG,"show interest group");
    	client.sendToClient(e);
    }
    if(en.getTask().equals("open file"))
    {
    	String path=(String)en.getObject();
    	File f=new File(path);
    	byte[] content = Files.readAllBytes(f.toPath());
    	Envelope e=new Envelope(content,"open file");
    	client.sendToClient(e);

    }
    if(en.getTask().equals("update file"))
    {
      file f=(file)en.getObject();
      byte[] bs=f.getFileContent();
      File curfile=new File(f.getDirection());
      curfile.delete();
      curfile=new File(f.getDirection());
      BufferedWriter writer=new BufferedWriter(new FileWriter(curfile));
	  FileOutputStream fos = new FileOutputStream(curfile.getAbsolutePath());
	  fos.write(bs);
	  fos.flush();
	  fos.close();
	  client.sendToClient("updated");
      
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
    if(en.getTask().equals("Delete this Group"))
    {
    	Statement stmt1 = this.getConn().createStatement();
    	interestGroups interestGroup1=(interestGroups)en.getObject();

    	stmt1.executeUpdate("DELETE FROM test.interestgroups WHERE groupname='"+(interestGroup1.getGroupName()+"'"));
      	 stmt1.executeUpdate("DELETE FROM test.userinterestgroups WHERE groupname='"+(interestGroup1.getGroupName()+"'"));
      	 client.sendToClient("the group delete secsefuly"); 
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
   	    for(int i=0;i<s.getUsers().size();i++)
   	    		{
   	    	String re=("INSERT INTO test.userinterestgroups VALUES('"+s.getUsers().get(i).getUsreName()+"','"+s.getGroupName()+"');");
   	    	stmt1.executeUpdate(re);
   	    		}
   		client.sendToClient("the group was added sucssesfuly");
   		}
   }
   if(en.getTask().equals("add directory"))
   {
     User user1=(User)en.getObject();
     directories dir=user1.getuserDirectories().get(user1.getuserDirectories().size()-1);
     String re=("INSERT INTO test.user_and_dir VALUES('"+user1.getUsreName()+"','"+dir.getDirectoryName()+"');");
     stmt.executeUpdate(re);
     
   }
    if(en.getTask().contains("search files"))
    {
    	boolean isadmin=false;
    	if(en.getTask().equals("search files 2"))
    		isadmin=true;
    	Envelope e;
    	file f;
    	 String textField=(String)en.getObject();
    	 ArrayList<file> FinalFiles=null;
    	String re="SELECT * FROM test.files";
    	 rs = stmt.executeQuery(re);
    	 FinalFiles=new ArrayList<>();
    	 while(rs.next()==true)
    	 {
    		f=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
    		if(f.getFileName().contains(textField))	
    			FinalFiles.add(f);
    	 }
    	 if(FinalFiles.size()==0 && isadmin==false)
    	 {
    		 e=new Envelope(null,"search file");
 		     client.sendToClient(e); 
    	 }
    	 if(FinalFiles.size()==0 && isadmin==true)
    	 {
    		 e=new Envelope(null,"search file 2");
 		     client.sendToClient(e); 
    	 }
    	 if(FinalFiles.size()!=0 && isadmin==true)
 		 {
    		 e=new Envelope(FinalFiles,"search file 2");
    		 client.sendToClient(e);
    	 }
    	 if(FinalFiles.size()!=0 && isadmin==false)
 		 {
    		 e=new Envelope(FinalFiles,"search file");
    		 client.sendToClient(e);
    	 }
    }
       
    
    
    if(en.getTask().equals("Save file in server"))
    {
    	Statement stmt1 = this.getConn().createStatement();
    	file f = (file)(en.getObject());
       	 rs = stmt1.executeQuery("SELECT filename FROM test.files WHERE files.filename= '"+(f.getFileName()+"'"));
       	if (rs.next()==true) 
       	   {
       		client.sendToClient("file already exist");
       	   }
       	else 
       		{

    		byte[] filecontent=f.getFileContent();
    		String name = f.getFileName();
    		File file=new File(f.getDirection());
    		BufferedWriter writer=new BufferedWriter(new FileWriter(file));
    		FileOutputStream fos= new FileOutputStream(file.getAbsolutePath());
    		fos.write(filecontent);
    		fos.flush();
    		fos.close();
    		String re = "INSERT INTO test.files VALUES('"+f.getFileName()+"','"+f.getDirection()+"','"+f.getFilepermission()+"','"+f.getFileOwner()+"');";
       		stmt1.executeUpdate(re);
       		client.sendToClient("file saved successfully");
       		} 	
    }
   
    
  
  
    if(en.getTask().equals("answer request"))
    {
    	GroupsRequests r=(GroupsRequests)en.getObject();
    	
    	if(r.getRequestType().equals("join"))
    	{
    		String re=("INSERT INTO test.userinterestgroups VALUES('"+r.getUserName()+"','"+r.getGroupName()+"');");
   	    	stmt.executeUpdate(re);	
   	    	//DELETE FROM `test`.`requests` WHERE `groupname`='animals';
   	    	stmt.executeUpdate("DELETE FROM test.requests WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");	
   	     client.sendToClient("the user was added secssfuly to this group" );
    	}
    	else{
    		stmt.executeUpdate("DELETE FROM test.userinterestgroups WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");
    		stmt.executeUpdate("DELETE FROM test.requests WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");
    		 client.sendToClient("the user was deleted secssfuly from this group" );
    	}
    		
 		
    	  }
   }
  
   if(msg instanceof String)
   {
	   String str=(String)msg;
	   Envelope e;
   	   User user1;
   	   interestGroups interestgroup1;
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
	   if(str.equals("ShowAllrequets"))
		 {
		     GroupsRequests Request1;
			 ArrayList<GroupsRequests> AllRequests=new ArrayList<>();
		     String re="SELECT * FROM test.requests";
		     rs = stmt.executeQuery(re);  
		     while(rs.next()==true)
	    	 {

		    	Request1=new GroupsRequests(rs.getString(1),rs.getString(2),rs.getString(3));
		    	AllRequests.add(Request1);
	    	
	    	 }
		     if(AllRequests.size()==0) client.sendToClient("no requests");
		     else{
			 e=new Envelope(AllRequests,"all requests");
	 		 client.sendToClient(e);
		     }
	     }
	   if(str.equals("ShowAllGroups"))
	 {
		 ArrayList<interestGroups> AllGroups=new ArrayList<>();
	     String re="SELECT * FROM test.interestgroups";
	     rs = stmt.executeQuery(re);
	     while(rs.next()==true)
    	 {

	    	interestgroup1=new interestGroups(rs.getString(1));	
    		AllGroups.add( interestgroup1);
    	
    	 }
		 e=new Envelope(AllGroups,"all groups");
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
