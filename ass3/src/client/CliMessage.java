package client;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class CliMessage.
 * a message sent by observable  to observers

 */
public class CliMessage 
{
	
	/** The message from server. */
	private String messageFromServer;		
	
	/** The serialized result set. */
	private ArrayList<HashMap<String,String>> serializedResultSet;
	
	/** The serialized photo. */
	private String serializedPhoto;
	
	/**
	 * Instantiates a new cli message.
	 *
	 * @param messageFromServer the message from server
	 */
	public CliMessage(String messageFromServer)
	{
		this.messageFromServer = messageFromServer;
	}
	
	/**
	 * Instantiates a new cli message.
	 *
	 * @param messageFromServer the message from server
	 * @param serializedResultSet the serialized result set
	 */
	public CliMessage(String messageFromServer , ArrayList<HashMap<String,String>> serializedResultSet)
	{
		this(messageFromServer);
		this.serializedResultSet = serializedResultSet;
	}

	/**
	 * Instantiates a new cli message.
	 *
	 * @param messageFromServer the message from server
	 * @param serializedPhoto the serialized photo
	 */
	public CliMessage(String messageFromServer, String serializedPhoto)
	{
		this.messageFromServer = messageFromServer;
		this.serializedPhoto = serializedPhoto;
	}

	/**
	 * Gets the message from server.
	 *
	 * @return the message from server
	 */
	public String getMessageFromServer()
	{
		return messageFromServer;
	}

	/**
	 * Gets the srs.
	 *
	 * @return the srs
	 */
	public ArrayList<HashMap<String,String>> getSrs()
	{
		return serializedResultSet;
	}

	/**
	 * Gets the serialized photo.
	 *
	 * @return the serialized photo
	 */
	public String getSerializedPhoto() {
		return serializedPhoto;
	}

	/**
	 * Sets the serialized photo.
	 *
	 * @param serializedPhoto the new serialized photo
	 */
	public void setSerializedPhoto(String serializedPhoto) {
		this.serializedPhoto = serializedPhoto;
	}
	
}
