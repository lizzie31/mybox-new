package Model;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;

import Model.AbstractModel;

/**
 * this class is User Model  which saves the user Model fields data ,which extends AbstractModel
 * @author jacob
 *
 */
public class User extends AbstractModel {
	/**user name  */
	private String userName;
	/**password */
	private String upassword;
	/**status */
	private int status;
	private  ArrayList<file> filesInDB=null;
	private  ArrayList<interestGroups> interestGroupInDB=null;
	private  ArrayList<interestGroups> allGroupInDB=null;
	private String email = null;

/**
 * Constructor
 * @param email
 * @param usreName
 * @param upassword
 * @param Position
 * @param status*
 */
	public User(String usreName, String upassword,String email,int status,ArrayList<file> files)
	{
		this.userName = usreName;
    	this.upassword =upassword;
    	this.status = status;
		this.email = email;
		this.filesInDB=files;
	}
	
	public User(String usreName, String upassword,String email,int status)
	{
		this.userName = usreName;
    	this.upassword =upassword;
    	this.status = status;
		this.email = email;
	}

public ArrayList<file> getFilesInDB() {
	return filesInDB;
}

public ArrayList<interestGroups> getInterestGroupInDB() {
	return interestGroupInDB;
}

public void setInterestGroupInDB(ArrayList<interestGroups> interestGroupInDB) {
	this.interestGroupInDB = interestGroupInDB;
}

public void setFilesInDB(ArrayList<file> filesInDB) {
	this.filesInDB = filesInDB;
}

/**
 * get the email of user
 * @return String
 */
	public String getEmail() {
		return email;
	}
/**
 * set new Email 
 * @param email
 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * get User Name
	 * @return String
	 */
		public String getUserName() {
			return userName;
		}
	/**
	 * get Usre Name
	 * @returnString
	 */
		public String getUsreName() {
			return userName;
		}
	/**
	 * set new User Name
	 * @param usreName
	 */
		public void setUserName(String usreName) {
			this.userName = usreName;
		}
	/**
	 * get employee password
	 * @return String
	 */
		public String getUpassword() {
			return upassword;
		}
	/**
	 * set employee password
	 * @param upassword
	 */
		public void setUpassword(String upassword) {
			this.upassword = upassword;
		}
	/**
	 * get Status
	 * @return int
	 */
		public int getStatus() {
			return status;
		}
	/**
	 * set new Status
	 * @param status
	 */
		public void setStatus(int status) {
			this.status = status;
		}

	public void setAllGroupInDB(ArrayList<interestGroups> allGroupInDB) {
		this.allGroupInDB = allGroupInDB;
	}

	public ArrayList<interestGroups> getAllGroupInDB() {
		return allGroupInDB;
	}
		

	
}
