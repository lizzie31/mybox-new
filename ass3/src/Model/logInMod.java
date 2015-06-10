package Model;

public class logInMod extends AbstractModel {
	/**user Name of employee*/
	private String userName;
	/**password of employee */
	private String Password;
	/**
	 * constructor
	 */
	public logInMod(){
		
		userName = null;
		Password = null;
	}
	
/**
 * get User Name
 * @return String
 */
	public String getUserName() {
		return userName;
	}
/**
 * set new User Name
 * @param userName
 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
/**
 * get Password
 * @return String
 */
	public String getPassword() {
		return Password;
	}
/**
 * set new Password
 * @param password
 */
	public void setPassword(String password) {
		Password = password;
	}

}


