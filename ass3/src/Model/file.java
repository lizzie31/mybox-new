package Model;

import java.util.ArrayList;

public class file extends SystemItem{
	private String fileName;
	private String Direction;
	private int Permission;
	private String FileOwner;
	private String Description;
	/**renaming file*/
	private String newfilename;
	/**if some one wants to delete a file from personal db*/
	private String userNotOwnerDeleteFile;
	private byte[] fileContent;
    private ArrayList<interestGroups> groupsForRead = new ArrayList<>();
	private ArrayList<interestGroups> groupsForUpdate = new ArrayList<>();
    private directories parent;
    private String CurrAddingUser=null;

    public file(String name)
	{
	
		fileName=name;
		
	}
	public file(String name,String Dir, int perm, String fileOwner,String Description)
	{
		this.Description=Description;
		fileName=name;
		Direction=Dir;
		this.Permission=perm;
		this.FileOwner=fileOwner;
	}
	public file(String name,String Dir, int perm, String fileOwner)
	{
		this.Description=Description;
		fileName=name;
		Direction=Dir;
		this.Permission=perm;
		this.FileOwner=fileOwner;
	}
	
	public file(ArrayList<interestGroups> groupsRead, ArrayList<interestGroups> groupsUpdate) {
		this.groupsForRead = groupsRead;
		this.groupsForUpdate = groupsUpdate;
	}
	/********************************************getters and setters**********************************************************/
	public String getFileName()
	{
	return fileName;
    }
	
	public void setFileName(String name)
	{
	this.fileName=name;
    }
	
	public String getDirection()
	{
	return Direction;
    }
	
	public void setDirection(String dir)
	{
	this.Direction=dir;
    }
	
	public int getFilepermission()
	{
	return Permission;
    }
	
	public void setFilePermission(int perm)
	{
	this.Permission=perm;
    }
	
	public String getFileOwner()
	{
	return FileOwner;
    }
	
	public void setFileOwner(String Owner)
	{
	this.FileOwner=Owner;
    }
	
	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	

	public ArrayList<interestGroups> getGroupsForRead() {
		return groupsForRead;
	}


	public void setGroupsForRead(ArrayList<interestGroups> groupsForRead) {
		this.groupsForRead = groupsForRead;
	}


	public ArrayList<interestGroups> getGroupsForUpdate() {
		return groupsForUpdate;
	}


	public void setGroupsForUpdate(ArrayList<interestGroups> groupsForUpdate) {
		this.groupsForUpdate = groupsForUpdate;
	}
	public String getDescription()
	{
	return this.Description;
    }
	public void setDescription(String d) {
		this.Description = d;
	}
	public String getnewfilename()
	{
	return this.newfilename;
    }
	public void setnewfilename(String d) {
		this.newfilename = d;
	}
	
	
	public directories getParent() {
		return parent;
	}

	public void setParent(directories parent) {
		this.parent = parent;
	}
	
	public String getuserNotOwnerDeleteFile() {
		return userNotOwnerDeleteFile;
	}

	public void setuserNotOwnerDeleteFile(String userName) {
		this.userNotOwnerDeleteFile = userName;
	}
	public String getCurrAddingUser() {
		return CurrAddingUser;
	}
	public void setCurrAddingUser(String currAddingUser) {
		CurrAddingUser = currAddingUser;
	}
	
	
	
	
	
}

