package Model;

import java.util.ArrayList;

	public class interestGroups extends AbstractModel {
	
		/**groupNumber */
		private String groupName;
		/** files for read ArrayLIST*/
		ArrayList<file> filesForRead=new ArrayList<>();
		/** files for read ArrayLIST*/
		ArrayList<file> filesForUpdate=new ArrayList<>();
		
	/**constructor
	 * @param groupName
	 */
		public interestGroups(String groupName) {
			this.groupName = groupName;
		}


		public String getGroupName() {
			return groupName;
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
		
		
		
}
