package client;

import java.text.ParseException;
import java.util.Observer;


/**
 * This is an interface that extends the observer interface.<br>
 * It is used to define methods that handle different answers received from ccrmserver, and sent to observers by . 
 * @author 
 */
public interface IObserve extends Observer
{
	/**
	 * Handle a request failure message.<br>
	 * this message is usually sent by server after an SQL Exception at server side.
	 */
	void handleDBRequestFail();
	
	/**
	 * Handle a success message sent from server in a response to an insert/update/delete query sent.
	 */
	void handleDBRequestSuccess();
	
	/**
	 * Handle a connection failure.
	 */
	void handleConnectionFail();
	
	/**
	 * Handle a null search result.
	 */
	void handleNullSearchResult();
	
	/**
	 * Handle database search result sent by server.
	 *
	 * @param dbResult the message containing the serialized result set.
	 * @throws ParseException 
	 */
	void handleDBSearchResult(CliMessage dbResult);
	
	/**
	 * Handle a photo sent by server.
	 *
	 * @param msgWithSerializedImage the  class containing a Serialized image.
	 */
	void handlePhoto(CliMessage msgWithSerializedImage);
}
