package Models;

import java.io.Serializable;

public class Website extends PasswordManagement implements Serializable{
	   private String websiteName;
	   private String websiteLink;
	   
	   private static Website web; 

	    // Constructor for WebsitePassword
	    private Website(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type,
	                           String websiteName, String websiteLink) {
	        super(name, password, dateOfCreate, dateOfUpdate, user, type);
	        this.websiteName = websiteName;
	        this.websiteLink = websiteLink;
	    }
	    
	    public static Website getwebsite(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user,
	    		String type, String websiteName, String websiteLink) {
	    	if(web == null) {
	    		web = new Website(name, password, dateOfCreate, dateOfUpdate, user, type, websiteName, websiteLink);
	    	}
	    	return web;
	    }
	    

	    public void displayWebsiteInfo() {
	        System.out.println("Website Information: " + websiteName + ", Link: " + websiteLink);
	    }

		public String getWebsiteName() {
			return websiteName;
		}

		public String getWebsiteLink() {
			return websiteLink;
		}

		public void setWebsiteName(String websiteName) {
			this.websiteName = websiteName;
		}

		public void setWebsiteLink(String websiteLink) {
			this.websiteLink = websiteLink;
		}
	    
	    
	    
}
