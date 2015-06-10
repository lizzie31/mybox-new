package Model;

import java.util.ArrayList;

/** this Class saves the files in users Directories, any directory has its own */
public class directories extends AbstractModel{
	
	/**files in user directories*/
	private ArrayList<file> filesInDirectory;
	
	/**@param filesInDirectory*/
	public directories(ArrayList<file> filesInDir)
	{
		this.filesInDirectory=filesInDir;
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
	

}
