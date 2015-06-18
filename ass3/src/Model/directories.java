package Model;

import java.util.ArrayList;

/** this Class saves the files in users Directories, any directory has its own */
public class directories extends SystemItem{
	
	/**files in user directories*/
	private ArrayList<SystemItem> ItemsInDirectory=null;
	/**directory name*/
	private String DirectoryName;
	
	/**@param filesInDirectory*/
	/**@pram name*/
	public directories(ArrayList<SystemItem> files,String name)
	{
	  this.ItemsInDirectory=files;
		this.DirectoryName=name;
	}
	
	
	public directories(String name)
	{
		this.DirectoryName=name;
	}
	
	/****************************************getters and setters*******************************************************/
	
	public ArrayList<SystemItem> getfiles()
	{
		return this.ItemsInDirectory;
	}
	

	public void setfiles(ArrayList<SystemItem> files)
	{
		this.ItemsInDirectory=files;
	}
	
	public String getDirectoryName()
	{
		return this.DirectoryName;
	}
	
	public void setDirectoryName(String name)
	{
		this.DirectoryName=name;
	}
	

}
