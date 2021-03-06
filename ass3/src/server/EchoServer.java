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

import controllers.fileMenuCon;
import controllers.forgetPassCon;
import controllers.logInCon;
import Model.Envelope;
import Model.GroupsRequests;
import Model.SystemItem;
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
    ResultSet rs2;
    int flag = 0;
    int insertFlag = 0;
    int whereFlag = 0;
    private Desktop desktop=null;
    String fileName = null;
    String fileDir = null;
    ConnectionToClient ct;
    boolean updateFlag = false;
    private fileMenuCon fileCon;
    
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
		  String username;
		  String pass;
		  String mail;
		  int status;
		  ArrayList<SystemItem> userItems=new ArrayList<>();
		  directories Items=new directories(""+showfiles.getUserName()+"'s files");
		  String re = "SELECT * FROM users WHERE users.username= '"+(showfiles.getUserName()+"' AND users.password='"+showfiles.getPassword()+"'");
		  rs = stmt.executeQuery(re);

		  
		    if(rs.next()==true)
		    {  
			    username=rs.getString(1);
			    pass=rs.getString(2);
			    mail=rs.getString(3);
			    status=rs.getInt(4);
			    ArrayList<String> Itemname=new ArrayList<>();
			    re = "SELECT DISTINCT directory FROM test.user_and_dir WHERE user_and_dir.username='"+(showfiles.getUserName()+"'");
				rs1 = stmt.executeQuery(re);
				while(rs1.next())
				 {
					Itemname.add(rs1.getString(1));
				 }
				
				for(int i=0;i<Itemname.size();i++)
				{
					directories d=null;
					d=setthetree(Itemname.get(i),username,stmt);
					userItems.add(d);

				}
				Items.setfiles(userItems);
				interestGroups s= null;
		    	ArrayList<interestGroups> interestGroup=new ArrayList<>();
		    	re="SELECT * FROM test.userinterestgroups WHERE userinterestgroups.username= '"+username +"'";
		    	rs = stmt.executeQuery(re);
		    	while(rs.next()==true)
		    	{
		    	  s=new interestGroups(rs.getString(2));
		    	  interestGroup.add(s);
		    	}
		    	ArrayList<String> allmessage=new ArrayList<>();
		    	String messege;
		    	re="SELECT * FROM test.meaagegtousers WHERE meaagegtousers.username= '"+username +"'";
		    	rs = stmt.executeQuery(re);
		    	while(rs.next()==true)
		    	{
		    	messege=rs.getString(2);
		    	  allmessage.add(messege);
		    	}
				
	       	 user = new User(username,pass,mail,status,Items,interestGroup,allmessage);
	    	 en=new Envelope(user,"log in handle");
	    	 client.sendToClient(en);
		  }
		
		  else 
			 client.sendToClient("Not found User");
	  }
	 if(en.getTask().equals("refresh data"))
	 {
		  User userrefresh=(User)en.getObject();
		  ArrayList<String> Itemname=new ArrayList<>();
		  ArrayList<SystemItem> userItems=new ArrayList<>();
		  directories Items=new directories(""+userrefresh.getUserName()+"'s files");
		  String re = "SELECT DISTINCT directory FROM test.user_and_dir WHERE user_and_dir.username='"+(userrefresh.getUserName()+"'");
			rs1 = stmt.executeQuery(re);
			while(rs1.next())
			 {
				Itemname.add(rs1.getString(1));
			 }
			
			for(int i=0;i<Itemname.size();i++)
			{
				directories d=null;
				d=setthetree(Itemname.get(i),userrefresh.getUserName(),stmt);
				userItems.add(d);
				

			}
			Items.setfiles(userItems);
			interestGroups s= null;
	    	ArrayList<interestGroups> interestGroup=new ArrayList<>();
	    	re="SELECT * FROM test.userinterestgroups WHERE userinterestgroups.username= '"+userrefresh.getUserName() +"'";
	    	rs = stmt.executeQuery(re);
	    	while(rs.next()==true)
	    	{
	    	  s=new interestGroups(rs.getString(2));
	    	  interestGroup.add(s);
	    	}
	    	userrefresh.setuserItems(Items);
	    	userrefresh.setInterestGroupInDB(interestGroup);
	    	Envelope e=new Envelope(userrefresh,"refresh data");
	    	client.sendToClient(e);
			
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

    
        if(en.getTask().equals("add file for read"))
    {
    	  interestGroups groupforedit=(interestGroups)en.getObject();
    	  int i=(groupforedit.getFilesForRead().size())-1;
    	  stmt.executeUpdate("INSERT INTO test.file_read_groups VALUES('"+groupforedit.getFilesForRead().get(i).getFileName()+"','"+groupforedit.getGroupName()+"');");
    	      client.sendToClient("the file added to the group");
    	
    }
    if(en.getTask().equals("add file for update"))
    {
    	 interestGroups groupforedit2=(interestGroups)en.getObject();
   	  int k=(groupforedit2.getFilesForRead().size())-1;
   	 int j=(groupforedit2.getFilesForUpdate().size())-1;
    	  stmt.executeUpdate("INSERT INTO test.file_read_groups VALUES('"+ groupforedit2.getFilesForRead().get(k).getFileName()+"','"+ groupforedit2.getGroupName()+"');");
    	  stmt.executeUpdate("INSERT INTO test.file_update_groups VALUES('"+ groupforedit2.getFilesForUpdate().get(j).getFileName()+"','"+ groupforedit2.getGroupName()+"');");
    	      client.sendToClient("the file added to the group");
    	
    }

    
    
    
    if(en.getTask().equals("show interest group to user"))
    {
    	interestGroups IG=(interestGroups)en.getObject();
    	file f=null;
    	Envelope e=null;
    	ArrayList<file> readfiles=new ArrayList<>();
    	ArrayList<file> updatefiles=new ArrayList<>();
    	String re="SELECT f.filename,f.direction,f.permission,f.fileowner,f.description,f.AbandonedFlag,f.UpdatedFlag,f.Parent From file_read_groups as fr,files as f WHERE f.filename=fr.file_name AND fr.interest_group='"+IG.getGroupName()+"'";
    	rs=stmt.executeQuery(re);
    	while(rs.next())
    	{
    		f=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
    		readfiles.add(f);
    	}
    	re="SELECT f.filename,f.direction,f.permission,f.fileowner,f.description,f.AbandonedFlag,f.UpdatedFlag,f.Parent From file_update_groups as fu,files as f WHERE f.filename=fu.file_name AND fu.interest_group='"+IG.getGroupName()+"'";
    	rs1=stmt.executeQuery(re);
    	while(rs1.next())
    	{
    		f=new file(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(4),rs1.getString(5),rs1.getInt(6),rs1.getInt(7),rs1.getString(8));
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
    

    if(en.getTask().equals("send request to system administrator"))
    {
    	  GroupsRequests request=(GroupsRequests)en.getObject();
    	  rs=stmt.executeQuery("SELECT * FROM test.requests WHERE groupname='"+request.getGroupName()+"'AND username='"+request.getUserName()+"'AND requesttype='"+request.getRequestType()+"'");
    	  if(rs.next()==true) 
    	      client.sendToClient("request allready exist");
    	  else
          {
    	  String re="INSERT INTO test.requests VALUES('"+request.getGroupName()+"','"+request.getUserName()+"','"+request.getRequestType()+"');";
    	  stmt.executeUpdate(re);
    	  client.sendToClient("request was send");
          }
    }
   if(en.getTask().equals("Show fils in group"))
    {
	   interestGroups i=(interestGroups) en.getObject();
	   file f=null;
    	ArrayList<file> filesREADgroup=new ArrayList();
    	ArrayList<file> filesUPDATEgroup=new ArrayList();
    	Envelope e=null;
    	
    	String re="SELECT * FROM test.file_read_groups WHERE interest_group='"+i.getGroupName()+"'";
    	rs=stmt.executeQuery(re);
    	while(rs.next())
    	{
    		f=new file(rs.getString(1));
    		filesREADgroup.add(f);
    	}
    	re="SELECT * FROM test.file_update_groups WHERE interest_group='"+i.getGroupName()+"'";
    	rs=stmt.executeQuery(re);
    	while(rs.next())
    	{
    	f=new file(rs.getString(1));
    	filesUPDATEgroup.add(f);
    	}
    	i.setFilesForRead(filesREADgroup);
    	i.setFilesForUpdate(filesUPDATEgroup);
    	e=new Envelope(i,"files in group");
    	client.sendToClient(e);
    	
    }
   
   if(en.getTask().equals("check if someone updating"))
 	{
     	Statement stmt1 = this.getConn().createStatement();
     	ResultSet rs2;
     	
 	   file fileUpdated=(file)en.getObject();
 	   String upd = "SELECT * FROM test.files as f WHERE f.filename= '"+fileUpdated.getFileName()+"'";
 	   rs = stmt1.executeQuery(upd);
 	   if(rs.next())
 	   {
 		   fileUpdated.setUpFlag(rs.getInt(7));
 		   en = new Envelope(fileUpdated,"updating flag result");
 		   client.sendToClient(en);
 	   }
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
    
        if(en.getTask().equals("delete file from interest group"))
    {
    	Statement stmt1 = this.getConn().createStatement();
    	String file1=(String)en.getObject();

    	stmt1.executeUpdate("DELETE FROM test.file_read_groups WHERE file_name='"+(file1+"'"));
    	String re="SELECT * FROM file_update_groups WHERE file_name='"+file1+"'";
     	 rs = stmt.executeQuery(re);
     	 if(rs.next()==true)
     	stmt1.executeUpdate("DELETE FROM file_update_groups WHERE file_name='"+file1+"'");
        
      	 client.sendToClient("the file delete secsefuly"); 
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

   if(en.getTask().equals("add file to user"))
   {
     file file=(file)en.getObject();
     String re="SELECT * FROM test.userdirectories WHERE username='"+file.getCurrAddingUser()+"' AND directory='"+file.getParent().getDirectoryName()+"' AND Itemname='"+file.getFileName()+"'";
     rs=stmt.executeQuery(re);
     if(rs.next())	
     {
    	 client.sendToClient("this file exists in your files");
     }
     else{
     re=("INSERT INTO test.userdirectories VALUES('"+file.getCurrAddingUser()+"','"+file.getParent().getDirectoryName()+"','"+file.getFileName()+"')");
	 stmt.executeUpdate(re);
	 client.sendToClient("file added succesfully");
     }
   }
   if(en.getTask().contains("this file is for"))
   {
	  interestGroups grouptoedit=(interestGroups)en.getObject();
	  String re;
	  if(en.getTask().contains("UPDATE")) 
	  {
		  stmt.executeUpdate("DELETE FROM test.file_update_groups WHERE file_name='" + grouptoedit.getFiletoChangP() + "' ;");
	  }
	  else stmt.executeUpdate("INSERT INTO test.file_update_groups VALUES('"+ grouptoedit.getFiletoChangP()+ "','"+grouptoedit.getGroupName()+"')" );
     
   }

   if(en.getTask().equals("add directory"))
   {
     directories dir=(directories)en.getObject();
     if(dir.getParent().getRootFlag()==true)
     {
     String re=("INSERT INTO test.user_and_dir VALUES('"+dir.getUsername()+"','"+dir.getDirectoryName()+"');");
     stmt.executeUpdate(re);
     }
     else
     {
    	 String re=("INSERT INTO test.userdirectories VALUES('"+dir.getUsername()+"','"+dir.getParent().getDirectoryName()+"','"+dir.getDirectoryName()+"')");
    	 stmt.executeUpdate(re);
     }
     
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
    		f=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
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
    if(en.getTask().contains("Change permission"))
    {
    	Statement stmt1 = this.getConn().createStatement();
    	int oldPermission, newPermission;
    	file upFile = (file)(en.getObject());
    	oldPermission = ((file)(en.getObject())).getFilepermission();
    	if(en.getTask().contains("1"))
    		newPermission = 1;
    	if(en.getTask().contains("2"))
    		newPermission = 2;
    	else
    		newPermission = 3;
    	String str = "UPDATE test.files SET permission= " + newPermission + " WHERE files.filename= '"+(((file)(en.getObject())).getFileName()+"'");
    	stmt1.executeUpdate(str);
    	if(oldPermission == 2)
    	{
    			stmt1.executeUpdate("DELETE FROM test.file_read_groups WHERE file_name='" + ((file)(en.getObject())).getFileName() + "' ;");
    			stmt1.executeUpdate("DELETE FROM test.file_update_groups WHERE file_name='" + ((file)(en.getObject())).getFileName() + "' ;");  		
    	}
    	else if(oldPermission == 3)
    	{
    		stmt1.executeUpdate("DELETE FROM test.file_read_groups WHERE file_name='" + ((file)(en.getObject())).getFileName() + "' ;");
    	}
    	
    	int readCount = upFile.getGroupsForRead().size();
   		int updateCount = upFile.getGroupsForUpdate().size();
   		if(readCount > 0)
   			for(int i = 0; i < readCount; i++)
   			{
   				String read = "INSERT INTO test.file_read_groups VALUES('"+upFile.getFileName()+"','"+upFile.getGroupsForRead().get(i).getGroupName()+"');";
   				stmt.executeUpdate(read);
   			}
   		if (updateCount > 0)
   			for(int i = 0; i < updateCount; i++)
   			{
   				String update = "INSERT INTO test.file_update_groups VALUES('"+upFile.getFileName()+"','"+upFile.getGroupsForUpdate().get(i).getGroupName()+"');";
   				stmt.executeUpdate(update);
   			}
   		client.sendToClient("permission updated successfully");
    }
    
    
    if(en.getTask().equals("change description"))
    {
    	file file=(file)en.getObject();
    	File f=new File(file.getDirection());
    	byte[] content = Files.readAllBytes(f.toPath());
    	byte[] newfilecontent=content;
    	f.delete();
    	String[] type = file.getDirection().split("\\.",2);
		String name=file.getnewfilename();
		String temp ="D:/mybox/"+ name+ "." + type[1];
    	file.setDirection(temp);
		f=new File(file.getDirection());
		BufferedWriter writer=new BufferedWriter(new FileWriter(f));
		FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
		fos.write(newfilecontent);
		fos.flush();
		fos.close();
	 	String re = "UPDATE test.files SET description= '"+(((file)(en.getObject())).getDescription()+"' WHERE files.filename= '"+(((file)(en.getObject())).getFileName()+"'"));
    	stmt.executeUpdate(re);
    	String re2 = "UPDATE test.files SET filename= '"+(((file)(en.getObject())).getnewfilename()+"' WHERE files.filename= '"+(((file)(en.getObject())).getFileName()+"'"));
    	stmt.executeUpdate(re2);
    	re="UPDATE test.files SET direction= '"+file.getDirection()+"' WHERE files.filename= '"+file.getnewfilename()+"'";
    	stmt.executeUpdate(re);
    	re="UPDATE test.userdirectories SET Itemname='"+file.getnewfilename()+"'WHERE userdirectories.Itemname='"+file.getFileName()+"'";
    	stmt.executeUpdate(re);
    	re="SELECT * FROM test.file_read_groups as FRG WHERE  FRG.file_name='"+file.getFileName()+"'";
    	rs=stmt.executeQuery(re);
    	re="UPDATE test.file_read_groups SET file_name='"+file.getnewfilename()+"' WHERE file_name='"+file.getFileName()+"'";
    	while(rs.next())
    	{
    		stmt.executeUpdate(re);
    	}
    	re="SELECT * FROM test.file_update_groups as FRG WHERE  FRG.file_name='"+file.getFileName()+"'";
    	rs=stmt.executeQuery(re);
    	re="UPDATE test.file_update_groups SET file_name='"+file.getnewfilename()+"' WHERE file_name='"+file.getFileName()+"'";
    	while(rs.next())
    	{
    		stmt.executeUpdate(re);
    	}
    }
    
    if(en.getTask().equals("Save file in server"))
    {
    	Statement stmt1 = this.getConn().createStatement();
    	file f = (file)(en.getObject());
    	int abandoned = 0;
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
    		String re = "INSERT INTO test.files VALUES ('"+f.getFileName()+"','"+f.getDirection()+"','"+f.getFilepermission()+"','"+f.getFileOwner()+"','"+f.getDescription()+"','"+f.getAbandonedFlag()+"','0' , '"+f.getParent().getDirectoryName()+"')";
       		stmt1.executeUpdate(re);


       		int readCount = f.getGroupsForRead().size();
       		int updateCount = f.getGroupsForUpdate().size();
       		if (readCount > 0)
       			for(int i = 0; i < readCount; i++)
       			{
       				String read = "INSERT INTO test.file_read_groups VALUES('"+f.getFileName()+"','"+f.getGroupsForRead().get(i).getGroupName()+"');";
       				stmt.executeUpdate(read);
       			}
       		if (updateCount > 0)
       			for(int i = 0; i < updateCount; i++)
       			{
       				String update = "INSERT INTO test.file_update_groups VALUES('"+f.getFileName()+"','"+f.getGroupsForUpdate().get(i).getGroupName()+"');";
       				stmt.executeUpdate(update);
       			}
       	    re=("INSERT INTO test.userdirectories VALUES('"+f.getFileOwner()+"','"+f.getParent().getDirectoryName()+"','"+f.getFileName()+"')");
    	    stmt.executeUpdate(re); 		
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
   	    	stmt.executeUpdate("INSERT INTO test.meaagegtousers VALUES('"+r.getUserName()+"','the admin aprrove your request to join " +r.getGroupName()+"');");
   	    	stmt.executeUpdate("DELETE FROM test.requests WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");	
   	  
   	        client.sendToClient("the user was added secssfuly to this group" );
   	     
    	}
    	else{
    		stmt.executeUpdate("INSERT INTO test.meaagegtousers VALUES('"+r.getUserName()+"','the admin aprrove your request to leave " +r.getGroupName()+"');");
    		stmt.executeUpdate("DELETE FROM test.userinterestgroups WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");
    		stmt.executeUpdate("DELETE FROM test.requests WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");
    		client.sendToClient("the user was deleted secssfuly from this group" );
    	}

    	  }
    if(en.getTask().equals("DenyRequest"))
    {
    	GroupsRequests r=(GroupsRequests)en.getObject();
    	if(r.getRequestType().equals("join"))
    	{	
    	stmt.executeUpdate("INSERT INTO test.meaagegtousers VALUES('"+r.getUserName()+"','the admin reject your request to join " +r.getGroupName()+"');");
    	stmt.executeUpdate("DELETE FROM test.requests WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");
    	}
    	else
    	{
    	stmt.executeUpdate("INSERT INTO test.meaagegtousers VALUES('"+r.getUserName()+"','the admin reject your request to leave " +r.getGroupName()+"');");
    	stmt.executeUpdate("DELETE FROM test.requests WHERE groupname='"+r.getGroupName()+"'AND username='"+r.getUserName()+"'");
    	}
    }
    
    if(en.getTask().equals("delete file not owner"))
    {
    	file file=(file)en.getObject();
    	String re="DELETE from test.userdirectories WHERE userdirectories.username='"+file.getuserNotOwnerDeleteFile()+"' AND userdirectories.Itemname='"+file.getFileName()+"'";
    	stmt.executeUpdate(re);
    }
   
   if(en.getTask().equals("delete file owner"))
   {
	file file=(file)en.getObject();
   	String re="DELETE from test.userdirectories WHERE userdirectories.username='"+file.getFileOwner()+"' AND userdirectories.Itemname='"+file.getFileName()+"'";
   	stmt.executeUpdate(re);
   	re="UPDATE test.files SET AbandonedFlag='1' WHERE filename='"+file.getFileName()+"'";
   	stmt.executeUpdate(re);
   }
   
   if(en.getTask().equals("delete file permanantly"))
   {
	   file file=(file)en.getObject();
	   String re2;
	   String nameDeleted=""+file.getFileName()+"(deleted)";
	   String re="DELETE FROM test.userdirectories WHERE userdirectories.username='"+file.getFileOwner()+"' AND userdirectories.Itemname='"+file.getFileName()+"'";
	   stmt.executeUpdate(re);
	   re="UPDATE test.files SET filename='"+nameDeleted+"' WHERE filename='"+file.getFileName()+"'";
	   stmt.executeUpdate(re);
	   re="SELECT * FROM test.userdirectories WHERE Itemname='"+file.getFileName()+"'";
	   rs=stmt.executeQuery(re);
	   while(rs.next())
	   {
	     re2="UPDATE test.userdirectories SET Itemname='"+nameDeleted+"' WHERE Itemname='"+file.getFileName()+"'";
	     stmt.executeUpdate(re2);
	   }
	   re="SELECT * FROM test.file_read_groups WHERE file_name='"+file.getFileName()+"'";
	   rs1=stmt.executeQuery(re);
	   while(rs1.next())
	   {
		 re2="DELETE FROM test.file_read_groups WHERE file_name='"+ file.getFileName()+"'";
		 stmt.executeUpdate(re2);
	   }
	   
	   re="SELECT * FROM test.file_update_groups WHERE file_name='"+file.getFileName()+"'";
	   rs2=stmt.executeQuery(re);
	   while(rs2.next())
	   {
		 re2="DELETE FROM test.file_update_groups WHERE file_name='"+ file.getFileName()+"'";
		 stmt.executeUpdate(re2);
	   }
	   
	   File f=new File(file.getDirection());
	   f.delete();
	   
   }
   
   if(en.getTask().equals("files to restore"))
   {
      User curruser=(User)en.getObject();
      ArrayList<file> filestorestore=new ArrayList<>();
      String re="SELECT * FROM test.files as f  WHERE f.fileOwner='"+curruser.getUserName()+"' AND f.AbandonedFlag='1'";
      rs=stmt.executeQuery(re);
      while(rs.next())
      {
    	  file file=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
    	  filestorestore.add(file);
    	  
      }
      Envelope e=new Envelope(filestorestore,"restore files");
      client.sendToClient(e);
   }
   
   if(en.getTask().equals( "restore file"))
   {
	   file file=(file)en.getObject();
	   String re="INSERT INTO test.userdirectories VALUES('"+file.getFileOwner()+"' , '"+file.getParentName()+"' ,'"+file.getFileName()+"')";
	   stmt.executeUpdate(re);
	   re="UPDATE test.files SET files.AbandonedFlag='0' WHERE files.filename='"+file.getFileName()+"'";
	   stmt.executeUpdate(re);
	   
   }
   
  }
   if(msg instanceof String)
   {
	   String str=(String)msg;
	   Envelope e;
   	   User user1;
   	   interestGroups interestgroup1;
   	   
   	      	   
   	  if(str.equals("all interest groups to admin"))
   	  {
   		 {
   			 ArrayList<interestGroups> AllGroups2=new ArrayList<>();
   		     String re="SELECT * FROM test.interestgroups";
   		     rs = stmt.executeQuery(re);
   		     while(rs.next()==true)
   	    	 {

   		    	interestgroup1=new interestGroups(rs.getString(1));	
   	    		AllGroups2.add( interestgroup1);
   	    	
   	    	 }
   			 e=new Envelope(AllGroups2,"all groups to admin");
   	 		 client.sendToClient(e);
   	     }
   		     
   	  }
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
	   
	  if(str.equals("show all files"))
	 {
		 ArrayList<file> Allfiles=new ArrayList<>();
		 file file1;
	     String re="SELECT * FROM test.files";
	     rs = stmt.executeQuery(re);
	     while(rs.next()==true)
    	 {

	    	file1=new file(rs.getString(1));	
	    	Allfiles.add( file1);
    	
    	 }
		 e=new Envelope(Allfiles,"all files");
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

 private directories setthetree(String ItemName, String username,Statement stmt) throws SQLException
 {
	    String Itemname=ItemName;
	    String UserName=username; 
	    file f;
	    directories d=null;
		ArrayList<file> files=new ArrayList<>();
		ArrayList<directories> dir=new ArrayList<>();
		ArrayList<SystemItem> items = new ArrayList<>();
		String re=("SELECT f.filename,f.direction,f.permission,f.fileowner,f.description,f.AbandonedFlag,f.UpdatedFlag,f.Parent FROM userdirectories as u,files as f WHERE f.filename=u.Itemname AND u.directory= '"+Itemname+"' AND u.username='"+UserName+"'");
		rs=stmt.executeQuery(re);
		while(rs.next()==true)
		 {
			 f=new file(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
			 file f2=setGroupsPermission(f);
			 files.add(f2);
		 }
		re=("SELECT u.Itemname FROM userdirectories as u,files as f WHERE f.filename=u.Itemname AND u.directory= '"+Itemname+"' AND u.username='"+UserName+"'");
		String re2=("select Itemname from userdirectories AS u Where u.directory= '"+Itemname+"' AND u.username='"+username+"' AND Itemname NOT IN ("+re+")" );
		rs1=stmt.executeQuery(re2);
		while(rs1.next())
		{
			String str=rs1.getString(1);
			directories dr=setthetree(str,UserName,stmt);
			dir.add(dr);
					
		}
		if(files.size()!=0)	
		{
			SystemItem si;
			for(int i=0;i<files.size();i++)
			{
				si=(SystemItem)files.get(i);
				items.add(si);
			}
				
		}
		
		if(dir.size()!=0)
		{
			SystemItem si;
			for(int i=0;i<dir.size();i++)
			{
				si=(SystemItem)dir.get(i);
				items.add(si);
			}
				
		}
			
		
		if(files.size()==0&&dir.size()==0)
			d=new directories(Itemname);
		else d=new directories(items,Itemname);
		
			return d;
			
			
 }
 
 
 public file setGroupsPermission(file f) throws SQLException
 {
	 Statement stmt1 = conn.createStatement();
	 ArrayList<interestGroups> groupsnamesupdate=new ArrayList<>();
	 ArrayList<interestGroups> groupsnamesread=new ArrayList<>();
	 String re="SELECT * from file_update_groups WHERE file_name='"+f.getFileName()+"'";
	 rs1=stmt1.executeQuery(re);
	 while(rs1.next())
	 {
		interestGroups groupupdate=new interestGroups(rs1.getString(2));
		groupsnamesupdate.add(groupupdate);
	 }
	 f.setGroupsForUpdate(groupsnamesupdate);
	 re="SELECT * from file_read_groups WHERE file_name='"+f.getFileName()+"'";
	 rs2=stmt1.executeQuery(re);
	 while(rs2.next())
	 {
		interestGroups groupread=new interestGroups(rs2.getString(2));
		groupsnamesread.add(groupread);
	 }
	 f.setGroupsForRead(groupsnamesread);
	 
	 
	return f;
	 
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
