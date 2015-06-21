package Model;

import java.util.ArrayList;

	public class interestGroups extends AbstractModel {
	
		/**groupNumber */
		private String groupName;
		/** files for read ArrayLIST*/
		ArrayList<file> filesForRead=new ArrayList<>();
		/** files for read ArrayLIST*/
		ArrayList<file> filesForUpdate=new ArrayList<>();
		/** the users in the group*/
		ArrayList<User> users=new ArrayList<>();
	/**constructor
	 * @param groupName
	 */
	


		public interestGroups(String groupName, ArrayList<User> users) {
		
		this.groupName = groupName;
		this.users = users;
	}

		public void setGroupName(String groupNumber) {
			this.groupName = groupNumber;
		}
		
		public ArrayList<file> getFilesForRead() {
			return filesForRead;
		}


		public void setFilesForRead(ArrayList<file> filesForRead) {
			this.filesForRead = filesForRead;
		}


		public ArrayList<file> getFilesForUpdate() {
			return filesForUpdate;
		}


		public void setFilesForUpdate(ArrayList<file> filesForUpdate) {
			this.filesForUpdate = filesForUpdate;
		}
		public ArrayList<User> getUsers() {
			return users;
		}
		public void setUsers(ArrayList<User> users) {
			this.users = users;
		}
		
		public String getGroupName() {
			return groupName;
		}
		public interestGroups(String groupName){
			
		this.groupName = groupName;
	}
		
		
		
		
}
