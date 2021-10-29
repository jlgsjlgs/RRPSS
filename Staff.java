public class Staff {

	private String name;
	private String gender;
	private int employeeID;
	private StaffRole jobTitle;

	public String getName() {
		return this.name;
	}

	public String getGender() {
		return this.gender;
	}

	public int getEmployeeID() {
		return this.employeeID;
	}

	public StaffRole getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * 
	 * @param jobTitle
	 */
	public void setJobTitle(StaffRole jobTitle) {
		this.jobTitle = jobTitle;
	}

}