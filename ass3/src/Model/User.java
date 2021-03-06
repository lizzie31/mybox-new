package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Model.AbstractModel;

/**
 * this class is User Model  which saves the user Model fields data ,which extends AbstractModel
 *
 */
public class User extends AbstractModel {
	/**user name  */
	private String userName;
	/**password */
	private String upassword;
	/**status */
	private int status;
	/**interest groups in user DB*/
	private  ArrayList<interestGroups> interestGroupInDB=null;
	/** user directories*/
	private directories userItems;
	private String email = null;
	private ArrayList<String> allmessages;

/**
 * Constructor
 * @param email
 * @param usreName
 * @param upassword
 * @param interestGroup 
 * @param Position
 * @param status
 * @param dir
 * 
 */
	public User(String usreName, String upassword,String email,int status,directories Item, ArrayList<interestGroups> interestGroup,ArrayList<String> Allmessage)
	{
		this.userName = usreName;
    	this.upassword =upassword;
    	this.status = status;
		this.email = email;
		this.userItems=Item;
		this.interestGroupInDB=interestGroup;
		this.allmessages=Allmessage;
	}
	
	public User(String usreName, String upassword,String email,int status)
	{
		this.userName = usreName;
    	this.upassword =upassword;
    	this.status = status;
		this.email = email;
	}


public ArrayList<interestGroups> getInterestGroupInDB() {
	return interestGroupInDB;
}

public void setInterestGroupInDB(ArrayList<interestGroups> interestGroupInDB) {
	this.interestGroupInDB = interestGroupInDB;
}


public void setuserItems(directories Items) {
	this.userItems =Items ;
}

public directories getuserItems() {
	return userItems;
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

	public ArrayList<String> getAllmessages() {
		return allmessages;
	}

	public void setAllmessages(ArrayList<String> allmessages) {
		this.allmessages = allmessages;
	}
		


		

	
}
