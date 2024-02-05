package Models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class UserAccount implements Serializable {
//	private static final long serialVersionUID = 123456789L; // Example value, change it accordingly
	
	private String userFirstName;
	private String userLastName;
	private String userName;
	private String userEmailAddress;
	private String userPassword;
	private String userTypes;
	private boolean isAdminUser;
	
	private static UserAccount user;
	
	public static UserAccount getUserAccount() {
		if(user == null) {
			user = new UserAccount();
		}
		return user;
	}
	
	public UserAccount registerUser(UserAccount registerUser, String userFirstName, String userLastName, String userName, String userEmailAddress, String userPassword, int choseOption) {
		registerUser.userFirstName = userFirstName;
		registerUser.userLastName = userLastName;
		registerUser.userName = userName; 
		registerUser.userEmailAddress = userEmailAddress; 
		registerUser.userPassword = userPassword;
		
		
		if(choseOption == 1) {
			registerUser.userTypes = "admin";
			registerUser.isAdminUser = true;
		}
		else {
			registerUser.userTypes = "user";
			registerUser.isAdminUser = false;
		}
		
		return registerUser;
	}
	

//	Getters methods
	public String getUserFirstName() {
		return userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmailAddress() {
		return userEmailAddress;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserTypes() {
		return userTypes;
	}

	public boolean isAdminUser() {
		return isAdminUser;
	}

//	Setter Methods
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

	public void setAdminUser(boolean isAdminUser) {
		this.isAdminUser = isAdminUser;
	}
	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return userEmailAddress.equals(that.userEmailAddress) &&
                userTypes.equals(that.userTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmailAddress, userTypes);
    }
    
//    search
    public static UserAccount searchAUserByEmail(Set<UserAccount> userAccountSet, String searchingEmail) {
        for (UserAccount user : userAccountSet) {
            if (user.getUserEmailAddress().equals(searchingEmail)) {
            	
                return user; // Found the user with the email that this function received by parameter. 
            }
        }
        return null; // If the user is not found by searching by the Email this method is getting by parameter. 
    }
	
	
}
