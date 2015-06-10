package server;

public class startServer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  ServerLogGui servLog = new ServerLogGui();
	    servLog.dispose();
	    serverGui serv = new serverGui();
	    ServerController servCon = new ServerController(serv,servLog);
	}

}
