
package server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;






import Model.User;
//import model.Employee;
import ocsf.server.*;
import server.*;


public class ServerController {
    
    private serverGui ServerView;
    private ServerLogGui serverLogView;
    private ServerController temp;
    private Connection conn; 
    private  ArrayList<User> userLog;
    private String userName = "root";
    private String password1 = "Nofar1990";
    private String Defport = "5555";
    private int port = 0;
    private String Scheam = "jdbc:mysql://localhost/test";
    private EchoServer sv;
    
    
    public ServerController(serverGui SerGui,ServerLogGui servLog){
        ServerView = SerGui;
        serverLogView = servLog;
        temp = this;
        ServerView.setTextFieldPass(password1);
        ServerView.setTextFieldUser(userName);
        ServerView.setTextFieldPort(Defport);
        ServerView.setTextFieldscheam(Scheam);
        userLog = new ArrayList<User>();
        ServerView.addLoginActionListener(new LoginListener());
        serverLogView.addDisconnectedBottonActionListener(new DisconnectedListener());
        
    }
    
    class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            
            userName = ServerView.getTextUserName();
            password1 = ServerView.getTextPassword();
            Defport = ServerView.getTextPort();
            port = Integer.parseInt(Defport);
            Scheam = ServerView.getTextScheam();
            
            if(openConnectionDB()){
                 sv = new EchoServer(port);
                 sv.setConn(conn);
                 
                   try 
                    {
                      sv.listen(); //Start listening for connections
                      sv.setController(temp);
                      ServerView.dispose();
                      ServerView.setVisible(false);
                      serverLogView.setVisible(true);
                      
                    } 
                    catch (Exception ex) 
                    {
                         ServerView.setWarningMessageVisibleTrue("ERROR - Could not listen for clients!");
                    }
            }    
        }
    }
    /**
     * openConnectionDB is method that check if the open Connection to DB
     * @return boolean
     */
      public boolean openConnectionDB(){
            try 
            {
              Class.forName("com.mysql.jdbc.Driver").newInstance();
          } catch (Exception ex) {/* handle the error*/}
          
          try 
          {
              conn = DriverManager.getConnection(Scheam,userName,password1);
              //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
              System.out.println("SQL connection succeed");
              return true;
              
           } catch (SQLException ex) 
               {/* handle any errors*/
              ServerView.setWarningMessageVisibleTrue("SQLException: " + ex.getMessage());
              ServerView.setWarningMessageVisibleTrue("SQLState: " + ex.getSQLState());
              ServerView.setWarningMessageVisibleTrue("VendorError: " + ex.getErrorCode());
              return false;
              }
          
    }
      /**
       * Inner class that handles when Button Logout Pressed, implements ActiontListener
       * @author jacob
       *
       */
    class DisconnectedListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
        System.exit(0);
            
        }
    
    }
    /**
     * set the new log of Employee that connect or disconnect to server
     * @param e1
     * @param Task
     */
    public void SetLog(User e1, String Task){

        if(Task.equals("login")){
            userLog.add(e1);
            serverLogView.getTextArea().setForeground(Color.blue);
            serverLogView.getTextArea().append( "User name:  " + e1.getUserName() +  ",  is connected\n");    
        }
       if(Task.equals("logout")){
            serverLogView.getTextArea().setForeground(Color.red);
            serverLogView.getTextArea().append(  "   User name:  " + e1.getUserName() +  ",  Disconnected\n");    
            userLog.remove(e1);
        
        }  
         
        
    }
    
    public serverGui getServerView() {
        return ServerView;
    }

    public void setServerView(serverGui serverView) {
        ServerView = serverView;
    }

    public ServerLogGui getServerLogView() {
        return serverLogView;
    }

    public void setServerLogView(ServerLogGui serverLogView) {
        this.serverLogView = serverLogView;
    }
    
     public void setPassword1(String password1) {
            this.password1 = password1;
        }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public static void updateTableFile(Connection con1,String fname, String fdir,ConnectionToClient client){
        int i=0;
        String sql = "UPDATE file SET fileDir = ? WHERE fileName = ?";
        int exist = 0;
        try 
        {
            Statement st = con1.createStatement();
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = st.executeQuery("SELECT fileName FROM file");
            int count = countRS(con1, "file");
            String[] fName = new String[count];
            
            while(rs.next()) //fill array (numbers and prices)
            {
                fName[i] = rs.getString(1);
                if(fName[i].equals(fname))
                    exist = 1;
            }
            
            if(exist == 1)
            {
                ps.setString(1, fdir);
                ps.setString(2, fname);
                ps.executeUpdate();

                try 
                {
                    client.sendToClient("Writing to the DB succeeded!\n");
                } 
              catch (IOException e) 
                  {e.printStackTrace();}
            }
    
            else
            {
                try 
                  {
                    client.sendToClient("The file '" + fname + "' doesn't exist!");
                  } 
                  catch (IOException e) {}
            }
            ps.close();
            st.close();
        } catch (SQLException e) {    e.printStackTrace();}    
    }
    
    public static void insertNewField(Connection con1,String fname, String fdir,ConnectionToClient client)
    {
        int exist = 0;
        String query = " insert into file (fileName, fileDir)"
                + " values (?, ?)";
        int  i = 0;
        try 
        {
            Statement st = con1.createStatement();
            PreparedStatement ps = con1.prepareStatement(query);
            ResultSet rs = st.executeQuery("SELECT fileName FROM file");
            int count = countRS(con1, "file");
            String[] fName = new String[count];
            
            while(rs.next()) //fill array (numbers and prices)
            {
                fName[i] = rs.getString(1);
                if(fName[i].equals(fname))
                    exist = 1;
            }
        
            if(exist == 0)
            {
                ps.setString(1, fname);
                ps.setString(2, fdir);
                ps.execute();

                try 
                {
                    client.sendToClient("Writing to the DB succeeded!\n");
                } 
              catch (IOException e) 
                  {e.printStackTrace();}
            }
            
            else 
            {
                try 
                  {
                    client.sendToClient("Duplicate entry '" + fname + "' for key PRIMARY");
                  }  catch (IOException e) {}
            }
            ps.close();
        } catch (SQLException e) {    e.printStackTrace();}    
    }

    //createNewField

    public static int countRS(Connection con1, String str)
    {
        PreparedStatement ps;
        ResultSet rs;
        String sqlCount = "select count(*) from " + str;
        try
        {
            ps =  con1.prepareStatement(sqlCount);

            rs = ps.executeQuery();
            int rowCount = 0;
            while(rs.next()) {
                rowCount = Integer.parseInt(rs.getString("count(*)"));
                Integer.parseInt(rs.getString("count(*)"));
            }
            return rowCount;
        } catch (SQLException e) {    e.printStackTrace();}
        return 0;
    }

 /*   public boolean checkUserExistance(String name, String pass, String filename) {
        String str  = "select * from users where fileName=\"" + this.fileName + "\"";
          arr = Select(getConn(),str, "file", this.fileName,client);
          whereFlag = 0;
          write = 1;
          if(arr.isEmpty())
          {
              try 
              {
                  client.sendToClient("\tNo files with a such name, please try again");
              } 
              catch (IOException e) 
                  {e.printStackTrace();}
          }
        return false;
        // TODO Auto-generated method stub
        
    }*/
    
} // end mysqlConnection


