package Models;

import java.io.Serializable;

public class DesktopApplication extends PasswordManagement implements Serializable{
	private String applicationName;
	private static DesktopApplication deskApp;

    // Constructor for DesktopApplicationPassword
    private DesktopApplication(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type,
                                      String applicationName) {
        super(name, password, dateOfCreate, dateOfUpdate, user, type);
        this.applicationName = applicationName;
        
    }
    
    public static DesktopApplication getDeskApp(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user,
    		String type, String applicationName) {
    	if(deskApp == null) {
    		deskApp = new DesktopApplication(name, password, dateOfCreate, dateOfUpdate, user, type, applicationName);
    	}
    	return deskApp;
    }
    

    public void displayApplicationInfo() {
        System.out.println("Application Information: " + applicationName);
    }

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
    
}
