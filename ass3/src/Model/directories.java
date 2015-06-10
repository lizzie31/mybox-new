package Model;

import java.util.ArrayList;

/** this Class saves the files in users Directories, any directory has its own */
public class directories extends AbstractModel{
	
	/**files in user directories*/
	private ArrayList<file> filesInDirectory;
	/**directory name*/
	private String DirectoryName;
	
	/**@param filesInDirectory*/
	/**@pram name*/
	public directories(ArrayList<file> filesInDir,String name)
	{
		this.filesInDirectory=filesInDir;
		this.DirectoryName=name;
	}
	
	
	/****************************************getters and setters*******************************************************/
	
	public ArrayList<file> getfiles()
	{
		return this.filesInDirectory;
	}
	

	public void setfiles(ArrayList<file> files)
	{
		this.filesInDirectory=files;
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
