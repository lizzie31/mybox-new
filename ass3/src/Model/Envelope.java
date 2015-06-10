package Model;

import java.io.Serializable;

import Model.AbstractModel;
/**
 * this class is Envelope Model which saves the Object ,task and message fields to send to server , extends AbstractModel
 * @author jacob
 *
 */
public class Envelope  extends AbstractModel{
	    /**the model that we send to server*/
		private Object obj;
		/**the task that need to do in server*/
		private String task;
		/** the message that return from server*/
		private String mess;

/**
 * 
 * @param obj1 model to send to server
 * @param Task1 that need to do in server
 */
		public Envelope(Object obj1, String Task1){
			obj = obj1;
			task = Task1;
			mess = "";
		}
		
		/*****************************************Getters and Setters********************************/
		
		public String getMess() {
			return mess;
		}


		public void setMess(String mess) {
			this.mess = mess;
		}
		public String getTask() {
			return task;
		}

		public void setTask(String task) {
			this.task = task;
		}

		public Object getObject() 
		{
			return obj;
		}
		
		public void setObject(Object obj1) 
		{
			this.obj = obj1; 
		}
}
