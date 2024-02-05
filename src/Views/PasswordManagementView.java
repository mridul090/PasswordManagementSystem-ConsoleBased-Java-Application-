package Views;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Models.DesktopApplication;
import Models.Game;
import Models.PasswordManagement;
import Models.UserAccount;
import Models.Website;

import java.util.ArrayList;
import java.util.List;

public class PasswordManagementView {
	public String name = "";
	public String password = "";
	public String dateOfCreate = "";
	public String dateOfUpdate = "";
	public String type = "";
	public String websiteName = ""; 
	public String websiteLink = "";
	public String desktopApplication = ""; 
	public String gameName = ""; 
	public String gameDeveloper = "";
	public UserAccount user = null; 
	public boolean wellstop = false;
	
	//For update
	public String updatesearchByName = "";

	
	public int chosenOpption;
	public int passwordTypeOpption;
	
	Scanner insert = new Scanner(System.in);
	
	 LocalDateTime currDateTime = LocalDateTime.now();
     DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public PasswordManagementView(UserAccount user, List<PasswordManagement> passwordList) {
		if(user.getUserTypes().toLowerCase().equals("admin")) {
			System.out.println(" Please chose an opption's that you want to do for password Managing");
			System.out.println(" 1 . Add");
			System.out.println(" 2 . Update");
			System.out.println(" 3 . Delete");
			System.out.println(" 4 . Display All");
			System.out.println(" 5 . Search");
			System.out.println(" 6 . Stop");
			this.chosenOpption = insert.nextInt();
			insert.nextLine();
			
			switch (this.chosenOpption) {
		    	case 1:
		    		// Code to be executed if expression matches value1
		    		System.out.println(" Please chose an opption's to add a specific types of password");
		    		System.out.println(" 1. Website");
		    		System.out.println(" 2. Desktop Application");
		    		System.out.println(" 3. Game");
		    		this.passwordTypeOpption = insert.nextInt();
					insert.nextLine();
					switch (this.passwordTypeOpption) {
			    		case 1:
			    			System.out.println(" Please write down name, passwrod,website name, website link");
			    			this.name = insert.nextLine();
			    			this.password = insert.nextLine();
			    			this.dateOfCreate = currDateTime.format(formate);
			    			this.dateOfUpdate = currDateTime.format(formate);
			    			this.user = user;
			    			this.type = "website";
			    			this.websiteName = insert.nextLine();
			    			this.websiteLink = insert.nextLine();
			    			break;
			    		case 2:
			    			System.out.println(" Please write down name, passwrod, Application");
			    			this.name = insert.nextLine();
			    			this.password = insert.nextLine();
			    			this.dateOfCreate = currDateTime.format(formate);
			    			this.dateOfUpdate = currDateTime.format(formate);
			    			this.user = user;
			    			this.type = "desktop";
			    			this.desktopApplication = insert.nextLine();
				    		break;
			    		case 3:
			    			System.out.println(" Please write down name, passwrod, Game Name, Game Developer");
			    			this.name = insert.nextLine();
			    			this.password = insert.nextLine();
			    			this.dateOfCreate = currDateTime.format(formate);
			    			this.dateOfUpdate = currDateTime.format(formate);
			    			this.user = user;
			    			this.type = "game";
			    			this.gameName = insert.nextLine();
			    			this.gameDeveloper = insert.nextLine();
				    		break;
				    	default:
				    		System.out.println(" you have chosen an invalid opptions");
					}
		    		
		    		break;
		    	
		    	case 2:
		    		// Code to be executed if expression matches value1
		    		System.out.println(" Please chose an opption's to Update a specific types of password");
		    		System.out.println(" 1. Website");
		    		System.out.println(" 2. Desktop Application");
		    		System.out.println(" 3. Game");
		    		this.passwordTypeOpption = insert.nextInt();
					insert.nextLine();
					
					int opption;
					PasswordManagement searchAPassword;
					
					switch (this.passwordTypeOpption) {
		    		case 1:
		    			System.out.println(" Please write down website name");
		    			this.updatesearchByName = insert.nextLine();
		    			searchAPassword = search(this.updatesearchByName, passwordList);
		    			displaySinglePasswordDetails(searchAPassword);
		    					    			    			
		    			System.out.println(" Want to change name! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if( opption == 1) {
		    				
		    				this.name = insert.nextLine();
		    			}
		    			else {
		    				this.name = searchAPassword.getName();
		    			}
		    				    				 
		    			System.out.println(" Want to change password! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if(opption == 1) {
		    				
		    				this.password = insert.nextLine();
		    			}
		    			else {
		    				this.password = searchAPassword.getPassword();
		    			}
		    			
		    			this.dateOfCreate = searchAPassword.getDateOfCreate();
		    			this.dateOfUpdate = currDateTime.format(formate);
		    			this.user = searchAPassword.getUser();
		    			this.type = searchAPassword.getType();
		    			
		    			System.out.println(" Want to change website name! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if(opption == 1) {
		    				
		    				this.websiteName = insert.nextLine();
		    			}
		    			else {
		    				this.websiteName = ((Website) searchAPassword).getWebsiteName();
		    			}
		    					    			
		    			System.out.println(" Want to change website link ! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();	
		    			if(opption == 1) {
		    			
		    				this.websiteLink = insert.nextLine();
		    			}
		    			else {
		    				this.websiteLink = ((Website) searchAPassword).getWebsiteLink();
		    			}
		    			
		    			
		    			break;
		    		case 2:
		    			System.out.println(" Please write down Desktop Application name");
		    			this.updatesearchByName = insert.nextLine();
		    			searchAPassword = search(this.updatesearchByName, passwordList);
		    			displaySinglePasswordDetails(searchAPassword);
		    					    			    			
		    			System.out.println(" Want to change name! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if( opption == 1) {
		    				
		    				this.name = insert.nextLine();
		    			}
		    			else {
		    				this.name = searchAPassword.getName();
		    			}
		    				    				 
		    			System.out.println(" Want to change password! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if(opption == 1) {
		    				
		    				this.password = insert.nextLine();
		    			}
		    			else {
		    				this.password = searchAPassword.getPassword();
		    			}
		    			
		    			this.dateOfCreate = searchAPassword.getDateOfCreate();
		    			this.dateOfUpdate = currDateTime.format(formate);
		    			this.user = searchAPassword.getUser();
		    			this.type = searchAPassword.getType();
		    			
		    			System.out.println(" Want to change Desktop Application name! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if(opption == 1) {
		    				
		    				this.desktopApplication = insert.nextLine();
		    			}
		    			else {
		    				this.desktopApplication = ((DesktopApplication) searchAPassword).getApplicationName();
		    			}
		    		   		    		 	    			
			    		break;
		    		case 3:
		    			System.out.println(" Please write down game name");
		    			this.updatesearchByName = insert.nextLine();
		    			searchAPassword = search(this.updatesearchByName, passwordList);
		    			displaySinglePasswordDetails(searchAPassword);
		    					    			    			
		    			System.out.println(" Want to change name! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if( opption == 1) {
		    				
		    				this.name = insert.nextLine();
		    			}
		    			else {
		    				this.name = searchAPassword.getName();
		    			}
		    				    				 
		    			System.out.println(" Want to change password! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if(opption == 1) {
		    				
		    				this.password = insert.nextLine();
		    			}
		    			else {
		    				this.password = searchAPassword.getPassword();
		    			}
		    			
		    			this.dateOfCreate = searchAPassword.getDateOfCreate();
		    			this.dateOfUpdate = currDateTime.format(formate);
		    			this.user = searchAPassword.getUser();
		    			this.type = searchAPassword.getType();
		    			
		    			System.out.println(" Want to change game name! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();
		    			if(opption == 1) {
		    				
		    				this.gameName = insert.nextLine();
		    			}
		    			else {
		    				this.gameName = ((Game) searchAPassword).getGameName();
		    			}
		    					    			
		    			System.out.println(" Want to change Game Developer ! 1. yes 2. no");
		    			opption = insert.nextInt();
		    			insert.nextLine();	
		    			if(opption == 1) {
		    			
		    				this.gameDeveloper = insert.nextLine();
		    			}
		    			else {
		    				this.gameDeveloper = ((Game) searchAPassword).getGameDeveloper();
		    			}
		    			
		    			

			    		break;
			    	default:
			    		System.out.println(" you have chosen an invalid opptions");
					}
					
		    		break;
		    		
		    	case 3:
		    		// Code to be executed if expression matches value2
		    		break;
		    		
		    	case 4:
		    		displayAllPasswordDetails(passwordList,user);
		    		break;
		    	case 5:
		    		
		    		break;
		    	case 6:
		    		this.wellstop = true;
		    		break;
		    	default:
		    		
			}
			
		}
	}
	   private PasswordManagement search(String searchCriteria, List<PasswordManagement> passwordList) {
	       
	        for (PasswordManagement password : passwordList) {
	        	// Check the type of password and call specific methods if needed
	            if (password instanceof Website) {
	            	
	                if( ((Website) password).getWebsiteName().equalsIgnoreCase(searchCriteria)){
	                	
	                	return password;
	                }
	            
	            } else if (password instanceof DesktopApplication) {
	                if( ((DesktopApplication) password).getApplicationName().equalsIgnoreCase(searchCriteria)){
	                	
	                	return password;
	                }
	            } else if (password instanceof Game) {
	                if( ((Game) password).getGameName().equalsIgnoreCase(searchCriteria)){
	                	
	                	return password;
	                }
	            }
	        }
	        return null; // Password not found
	    }
	   private void displaySinglePasswordDetails(PasswordManagement password) {
		   try {
			 if (password.getType().toLowerCase().equals("website")) {
				password.displayInfo();
				((Website) password).displayWebsiteInfo();
			 }
			 else if(password.getType().toLowerCase().equals("desktop")) {
				 password.displayInfo();
				 ((DesktopApplication) password).displayApplicationInfo();;
			 }
			 else {
				 password.displayInfo();
				 ((Game) password).displayGameInfo();;
			 }
		   }catch(Exception e) {
			   System.out.println(e.getStackTrace());
		   }
		   
	   }
	   
	   private void displayAllPasswordDetails(List<PasswordManagement> passwordList, UserAccount user) {
		   try {
			 for(PasswordManagement password: passwordList) {
				 if( password.getUser().getUserEmailAddress().toLowerCase().equals(user.getUserEmailAddress().toLowerCase()) || user.getUserTypes().toLowerCase().equals("admin")) {
					 password.displayInfo();

					// Check the type of password and call specific methods if needed
	    	        if (password instanceof Website) {
	    	             ((Website) password).displayWebsiteInfo();
	    	        } else if (password instanceof DesktopApplication) {
	    	             ((DesktopApplication) password).displayApplicationInfo();
	    	        } else if (password instanceof Game) {
	    	             ((Game) password).displayGameInfo();
	    	        }					 
				 }
			 }
			
		   }catch(Exception e) {
			   System.out.println(e.getStackTrace());
		   }
		   
	   }
}
