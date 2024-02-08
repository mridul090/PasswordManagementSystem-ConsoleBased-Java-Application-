package Models;

import java.io.Serializable;

public class PasswordManagement implements Serializable{
    protected String name;
    protected String password;
    protected String dateOfCreate;
    protected String dateOfUpdate;
    protected UserAccount user;
    protected String type;

    // Constructor for Password class
    protected PasswordManagement(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type) {
        this.name = name;
        this.password = password;
        this.dateOfCreate = dateOfCreate;
        this.dateOfUpdate = dateOfUpdate;
        this.user = user;
        this.type = type;
    }
    
    // Common methods for Password class
    public void displayInfo() {
        System.out.println("Password Information: " + name +" Password: ***...." +", Created: " + dateOfCreate + ", Updated: " + dateOfUpdate);
    }
   

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getDateOfCreate() {
		return dateOfCreate;
	}

	public String getDateOfUpdate() {
		return dateOfUpdate;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDateOfCreate(String dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}

	public void setDateOfUpdate(String dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
