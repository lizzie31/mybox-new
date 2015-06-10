package Model;

public class file extends AbstractModel{
	private String fileName;
	private String Direction;
	private String FileType;
	private String FileOwner;
	
	public file(String name,String Dir, String type, String fileOwner)
	{
		fileName=name;
		Direction=Dir;
		FileType=type;
		this.FileOwner=fileOwner;
	}
	
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
	
	public String getFileType()
	{
	return FileType;
    }
	
	public void setFileType(String type)
	{
	this.FileType=type;
    }
	
	public String getFileOwner()
	{
	return FileOwner;
    }
	
	public void setFileOwner(String Owner)
	{
	this.FileType=Owner;
    }
    
}

