package Model;

public class GroupsRequests extends AbstractModel{
	/** groupname- the requested group name*/
	private String GroupName;
	/**username- the user who sent the request*/
	private String UserName;
	/**requesttype- join\leave*/
	private String RequestType;
/** constructor
 * 	
 * @param Gname
 * @param Uname
 * @param REtype
 */
	public GroupsRequests (String Gname,String Uname,String REtype)
	{
		this.GroupName=Gname;
		this.UserName=Uname;
		this.RequestType=REtype;
	}
	
/**************************************************getters and setters*****************************************************************/
public String getGroupName() {
	return GroupName;
}
public void setGroupName(String groupName) {
	GroupName = groupName;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public String getRequestType() {
	return RequestType;
}
public void setRequestType(String requestType) {
	RequestType = requestType;
}

	
	
}
