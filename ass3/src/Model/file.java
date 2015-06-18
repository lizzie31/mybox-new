package Model;

public class file extends SystemItem{
	private String fileName;
	private String Direction;
	private int Permission;
	private String FileOwner;
	private byte[] fileContent;

	public file(String name)
	{
		fileName=name;
	
	}


	public file(String name,String Dir, int perm, String fileOwner)
	{
		fileName=name;
		Direction=Dir;
		this.Permission=perm;
		this.FileOwner=fileOwner;
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
    
}

