package Model;

	public class interestGroups extends AbstractModel {
	
		/**groupNumber */
		private String groupName;
		
	
		public interestGroups(String groupName) {
			this.groupName = groupName;
		}


		public String getGroupNumber() {
			return groupName;
		}

		public void setGroupNumber(String groupNumber) {
			this.groupName = groupNumber;
		}
		
		
		
}
