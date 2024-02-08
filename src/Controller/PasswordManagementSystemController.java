package Controller;

import Models.PasswordManagement;
import Models.Website;
import Models.DesktopApplication;
import Models.Game;
import Models.UserAccount;
import Models.EncryptionClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Views.PasswordManagementView;

//Factory interface
interface PasswordManagementFactory {
	 // As all types of password we will add and this funtion need to be implemented by all classes that implements it, 
	//  that's way I add all types of parameters for this methods 
	PasswordManagement createPassword(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type, String websiteName, String websiteLink, String desktopApplication, 
			 String gameName, String gameDeveloper);
}

//Concrete factory for Website passwords
class WebsiteFactory implements PasswordManagementFactory {
 
	public PasswordManagement createPassword(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type, String websiteName, String websiteLink, String desktopApplication, 
			 String gameName, String gameDeveloper) {
		return Website.getwebsite(name, password, dateOfCreate, dateOfUpdate, user, type, websiteName, websiteLink);
	}
}

//Concrete factory for DesktopApplication passwords
class DesktopApplicationPasswordFactory implements PasswordManagementFactory {
 public PasswordManagement createPassword(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type, String websiteName, String websiteLink, String desktopApplication, 
		 String gameName, String gameDeveloper) {
     return DesktopApplication.getDeskApp(name, password, dateOfCreate, dateOfUpdate, user, type, desktopApplication);
 }
}

//Concrete factory for Game passwords
class GamePasswordFactory implements PasswordManagementFactory {
 public PasswordManagement createPassword(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user, String type, String websiteName, String websiteLink, String desktopApplication, 
		 String gameName, String gameDeveloper) {
     return Game.getgame(name, password, dateOfCreate, dateOfUpdate, user, type, gameName, gameDeveloper);
 }
}

public class PasswordManagementSystemController {
	// Using a single ArrayList to store different types of passwords
    List<PasswordManagement> passwordList = new ArrayList<>();
    List<EncryptionClass> encryptionPasswordList = new ArrayList<>();
    
    boolean wantToStop = false;
    boolean isPasswordSave = false;
    boolean isEncryptedPasswordSave = false;
    EncryptionClass encryptPassword;
    String encryptedPasswordCode;
    
    public PasswordManagementSystemController(UserAccount user){
    	System.out.println("The use is now "+user.getUserName());
    	passwordList = loadAllPasswords(passwordList);
    	
    	while(!wantToStop){
    		
    		PasswordManagementView passView = new PasswordManagementView(user,passwordList);
//    		System.out.println("passView.chosenOpption "+passView.chosenOpption+" passView.passwordTypeOpption "+passView.passwordTypeOpption);
    		switch (passView.chosenOpption) {
    		case 1:
    			// Add the password details
    			switch (passView.passwordTypeOpption) {
        			case 1:
        				passwordList = loadAllPasswords(passwordList);
        				PasswordManagementFactory websiteFactory = new WebsiteFactory();
        				
        				try {
        					//Encrypt the password
        					encryptionPasswordList = loadAllEncryptedPassword(encryptionPasswordList);
        					encryptedPasswordCode = encryptPassword(passView.password);
        					System.out.println(" Original Password "+ passView.password +" , Encrypted Password: "+encryptedPasswordCode);
        					encryptPassword = EncryptionClass.getEncryption(passView.password, encryptedPasswordCode, passView.websiteName);
        					System.out.println(" Encrypted Object store Password: "+encryptPassword.getOriginalPassword()+" Encrypted: "+encryptPassword.getEncryptedPassword()+" Type Name: "+encryptPassword.getPasswordTypeName());
        					encryptionPasswordList.add(encryptPassword);
        					isEncryptedPasswordSave = SaveAllEncrypetedPasswords(encryptionPasswordList);
        					if(!isEncryptedPasswordSave) {
        						throw new Exception("Password Can't Encrypted");
        					}
        				        						
        					PasswordManagement websitePassword = websiteFactory.createPassword(passView.name, encryptedPasswordCode, passView.dateOfCreate, passView.dateOfUpdate, passView.user, passView.type, passView.websiteName, passView.websiteLink, passView.desktopApplication, passView.gameName, passView.gameDeveloper);
        					passwordList.add(websitePassword);
        					isPasswordSave = SaveAllPasswords(passwordList);
        					if(isPasswordSave) {
        						System.out.println("All data has been saved to data file");	
        					}
        					else {
        						System.out.println("Unable to save!!");
        					}
        				}
        				catch(Exception e) {
        					System.out.println(e.getStackTrace());
        				}
        				break;
        			case 2:        				
        				passwordList = loadAllPasswords(passwordList);
        				PasswordManagementFactory deskApplicationFactory = new DesktopApplicationPasswordFactory();
        				try {
        					//Encrypt the password
        					encryptionPasswordList = loadAllEncryptedPassword(encryptionPasswordList);
        					encryptedPasswordCode = encryptPassword(passView.password);
        					encryptPassword = EncryptionClass.getEncryption(passView.password, encryptedPasswordCode, passView.desktopApplication);
        					encryptionPasswordList.add(encryptPassword);
        					isEncryptedPasswordSave = SaveAllEncrypetedPasswords(encryptionPasswordList);
        					if(!isEncryptedPasswordSave) {
        						throw new Exception("Password Can't Encrypted");
        					}
        					
        					PasswordManagement deskApplicationPassword = deskApplicationFactory.createPassword(passView.name, passView.password, passView.dateOfCreate, passView.dateOfUpdate, passView.user, passView.type, passView.websiteName, passView.websiteLink, passView.desktopApplication, passView.gameName, passView.gameDeveloper);
        					passwordList.add(deskApplicationPassword);
        					isPasswordSave = SaveAllPasswords(passwordList);
        					if(isPasswordSave) {
        						System.out.println("All data has been saved to data file");	
        					}
        					else {
        						System.out.println("Unable to save!!");
        					}
        				}
        				catch(Exception e) {
        					System.out.println(e.getStackTrace());
        				}	
        				break;
        			case 3:
        				passwordList = loadAllPasswords(passwordList);
        				PasswordManagementFactory gameFactory = new GamePasswordFactory();
        				try {
        					//Encrypt the password
        					encryptionPasswordList = loadAllEncryptedPassword(encryptionPasswordList);
        					encryptedPasswordCode = encryptPassword(passView.password);
        					encryptPassword = EncryptionClass.getEncryption(passView.password, encryptedPasswordCode, passView.gameName);
        					encryptionPasswordList.add(encryptPassword);
        					isEncryptedPasswordSave = SaveAllEncrypetedPasswords(encryptionPasswordList);
        					if(!isEncryptedPasswordSave) {
        						throw new Exception("Password Can't Encrypted");
        					}        					        				
        				
        					PasswordManagement gamePassword = gameFactory.createPassword(passView.name, passView.password, passView.dateOfCreate, passView.dateOfUpdate, passView.user, passView.type, passView.websiteName, passView.websiteLink, passView.desktopApplication, passView.gameName, passView.gameDeveloper);
        					passwordList.add(gamePassword);
        					isPasswordSave = SaveAllPasswords(passwordList);
        					if(isPasswordSave) {
        						System.out.println("All data has been saved to data file");	
        					}
        					else {
        						System.out.println("Unable to save!!");
        					}
        				}
        				catch(Exception e) {
        					System.out.println(e.getStackTrace());
        				}	
        				break;
        			default:
        				System.out.println(" ");
    			}
        	
    			break;
    		case 2:
    			
    			switch (passView.passwordTypeOpption) {
    				case 1:
    				passwordList = loadAllPasswords(passwordList);
    				for (int i = 0; i < passwordList.size(); i++) {
    					if(passwordList.get(i).getType().toLowerCase().equals(passView.type)){
    			    		    			    		
    						if( ((Website)passwordList.get(i)).getWebsiteName().toLowerCase().equals(passView.updatesearchByName)) {
    							System.out.println("The website is need to update is "+ ((Website)passwordList.get(i)).getWebsiteName());
    							PasswordManagement passManager = passwordList.get(i);
        						passManager.setName(passView.name);;
        						
        						//Encrypt the password
        						try {
        							if(passView.isPasswordUpdated) {
        	        					encryptionPasswordList = loadAllEncryptedPassword(encryptionPasswordList);
        	        					encryptedPasswordCode = encryptPassword(passView.password);
        	        					encryptionPasswordList = updateEncryptionList(encryptionPasswordList, passView.updatesearchByName, passView.password, encryptedPasswordCode, passView.websiteName );
        	        					isEncryptedPasswordSave = SaveAllEncrypetedPasswords(encryptionPasswordList);
        	        					if(isEncryptedPasswordSave != true) {
        	        						throw new Exception("Password Can't Encrypted");
        	        					}
        	        					else {
        	        						passManager.setPassword(encryptedPasswordCode);
        	        					}
        	        					
        							}
        							else {
        								passManager.setPassword(passView.password);
        							}
        							
        						}catch(Exception e) {
        							System.out.println(e.getStackTrace());
        						}
        						        						
        						passManager.setDateOfCreate(passView.dateOfCreate);
        						passManager.setDateOfUpdate(passView.dateOfUpdate);
        						passManager.setUser(passView.user);
        						passManager.setType(passView.type);
        						((Website)passManager).setWebsiteName(passView.websiteName);
        						((Website)passManager).setWebsiteLink(passView.websiteLink);
        						
        						passwordList.set(i, passManager);
        						
        						isPasswordSave = SaveAllPasswords(passwordList);
                				if(isPasswordSave) {
                					System.out.println("Password details has been updated");	
                				}
                				else {
                					System.out.println("Unable to update!!");
                				}
    						}
    					    						
    					}
    				}
    				break;
    				
    				case 2:
    				passwordList = loadAllPasswords(passwordList);	
    				for (int i = 0; i < passwordList.size(); i++) {
    					if(passwordList.get(i).getType().toLowerCase().equals(passView.type)) {
    			    		    			    		
    						if( ((DesktopApplication)passwordList.get(i)).getApplicationName().toLowerCase().equals(passView.updatesearchByName)) {
    							System.out.println("The Appilcaiton is need to update is "+ ((DesktopApplication)passwordList.get(i)).getApplicationName());
    							PasswordManagement passManager = passwordList.get(i);
        						passManager.setName(passView.name);
        						
        						//Encrypt the password
        						try {
        							if(passView.isPasswordUpdated) {
        	        					encryptionPasswordList = loadAllEncryptedPassword(encryptionPasswordList);
        	        					encryptedPasswordCode = encryptPassword(passView.password);
        	        					encryptionPasswordList = updateEncryptionList(encryptionPasswordList, passView.updatesearchByName, passView.password, encryptedPasswordCode, passView.desktopApplication );
        	        					isEncryptedPasswordSave = SaveAllEncrypetedPasswords(encryptionPasswordList);
        	        					if(isEncryptedPasswordSave != true) {
        	        						throw new Exception("Password Can't Encrypted");
        	        					}
        	        					else {
        	        						passManager.setPassword(encryptedPasswordCode);
        	        					}
        	        					
        							}
        							else {
        								passManager.setPassword(passView.password);
        							}
        							
        						}catch(Exception e) {
        							System.out.println(e.getStackTrace());
        						}
        						
        						passManager.setDateOfCreate(passView.dateOfCreate);
        						passManager.setDateOfUpdate(passView.dateOfUpdate);
        						passManager.setUser(passView.user);
        						passManager.setType(passView.type);
        						((DesktopApplication)passManager).setApplicationName(passView.desktopApplication);;
        					
        						passwordList.set(i, passManager);
        						
        						isPasswordSave = SaveAllPasswords(passwordList);
                				if(isPasswordSave) {
                					System.out.println("Password details has been updated");	
                				}
                				else {
                					System.out.println("Unable to update!!");
                				}
    						}
    					    						
    					}
    				}
    				break;
    				
    				case 3:
    				passwordList = loadAllPasswords(passwordList);
    				
    				for (int i = 0; i < passwordList.size(); i++) {
    					if(passwordList.get(i).getType().toLowerCase().equals(passView.type)){
    			    		    			    		
    						if( ((Game)passwordList.get(i)).getGameName().toLowerCase().equals(passView.updatesearchByName)) {
    							System.out.println("The Game is need to update is "+ ((Game)passwordList.get(i)).getGameName());
    							PasswordManagement passManager = passwordList.get(i);
        						passManager.setName(passView.name);
        						
        						//Encrypt the password
        						try {
        							if(passView.isPasswordUpdated) {
        	        					encryptionPasswordList = loadAllEncryptedPassword(encryptionPasswordList);
        	        					encryptedPasswordCode = encryptPassword(passView.password);
        	        					encryptionPasswordList = updateEncryptionList(encryptionPasswordList, passView.updatesearchByName, passView.password, encryptedPasswordCode, passView.gameName );
        	        					isEncryptedPasswordSave = SaveAllEncrypetedPasswords(encryptionPasswordList);
        	        					if(isEncryptedPasswordSave != true) {
        	        						throw new Exception("Password Can't Encrypted");
        	        					}
        	        					else {
        	        						passManager.setPassword(encryptedPasswordCode);
        	        					}
        	        					
        							}
        							else {
        								passManager.setPassword(passView.password);
        							}
        							
        						}catch(Exception e) {
        							System.out.println(e.getStackTrace());
        						}
        						
        						passManager.setDateOfCreate(passView.dateOfCreate);
        						passManager.setDateOfUpdate(passView.dateOfUpdate);
        						passManager.setUser(passView.user);
        						passManager.setType(passView.type);
        						((Game)passManager).setGameName(passView.gameName);
        						((Game)passManager).setGameDeveloper(passView.gameDeveloper);
        						
        						passwordList.set(i, passManager);
        						
        						isPasswordSave = SaveAllPasswords(passwordList);
                				if(isPasswordSave) {
                					System.out.println("Password details has been updated");	
                				}
                				else {
                					System.out.println("Unable to update!!");
                				}
    						}
    					    						
    					}
    				}
    				break;
    				
    				default:
    					System.out.println(" ");
    			}
    			
    			break;
    			
    		case 3:
    			switch(passView.passwordTypeOpption) {
    			case 1:
    				passwordList = loadAllPasswords(passwordList);
    				System.out.println("The "+passView.type+" passwords details that will be delete form system is "+passView.deleteSearchByName);
    				for (int i = 0; i < passwordList.size(); i++) {
    					if(passwordList.get(i).getType().toLowerCase().equals(passView.type)){
    			    		
    						PasswordManagement passManager = passwordList.get(i);
    						String targetName = ((Website)passManager).getWebsiteName().toLowerCase();
    						String sourceName = passView.deleteSearchByName.toLowerCase();
    						
    						if( ((Website)passwordList.get(i)).getWebsiteName().toLowerCase().equals(passView.deleteSearchByName)) {
    							
    							passwordList.remove(i);       						
    								       						
        						isPasswordSave = SaveAllPasswords(passwordList);
                				if(isPasswordSave) {
                					System.out.println("Password details has been Deleted");	
                				}
                				else {
                					System.out.println("Unable to update!!");
                				}
    						}
    					    						
    					}
    				}
    				break;
    			case 2:
    				passwordList = loadAllPasswords(passwordList);
    				System.out.println("The "+passView.type+" application passwords details that will be delete form system is "+passView.deleteSearchByName);
    				for (int i = 0; i < passwordList.size(); i++) {
    					if(passwordList.get(i).getType().toLowerCase().equals(passView.type)){
    						
    						PasswordManagement passManager = passwordList.get(i);
    						String targetName = ((DesktopApplication)passManager).getApplicationName().toLowerCase();
    						String sourceName = passView.deleteSearchByName.toLowerCase();
    						
    						if(targetName.equals(sourceName)) {
    							
    							passwordList.remove(i);       						
    											
        						isPasswordSave = SaveAllPasswords(passwordList);
                				if(isPasswordSave) {
                					System.out.println("Password details has been Deleted");	
                				}
                				else {
                					System.out.println("Unable to update!!");
                				}
    						}
    					    						
    					}
    				}
    				break;
    			case 3:
    				passwordList = loadAllPasswords(passwordList);
    				System.out.println("The "+passView.type+" passwords details that will be delete form system is "+passView.deleteSearchByName);
    				for (int i = 0; i < passwordList.size(); i++) {
    					if(passwordList.get(i).getType().toLowerCase().equals(passView.type)){
    			    		
    						PasswordManagement passManager = passwordList.get(i);
    						String targetName = ((Game)passManager).getGameName().toLowerCase();
    						String sourceName = passView.deleteSearchByName.toLowerCase();
    						
    						if( ((Game)passwordList.get(i)).getGameName().toLowerCase().equals(passView.deleteSearchByName)) {
    							
    							passwordList.remove(i);       						
    											
        						isPasswordSave = SaveAllPasswords(passwordList);
                				if(isPasswordSave) {
                					System.out.println("Password details has been Deleted");	
                				}
                				else {
                					System.out.println("Unable to update!!");
                				}
    						}
    					    						
    					}
    				}
    				break;
    			default:
    				System.out.println("");
    			}
    			break;
    		case 4:
    			break;
    		
    		case 5:
    			//For search
    			break;
    			
    		case 6:
    			//For Sorting
//    			// Sort the passwordList based on the last update date
//    	        Collections.sort(passwordList, (password1, password2) -> 
//    	                password2.getDateOfUpdate().compareTo(password1.getDateOfUpdate()));
    			
    			// Sort the passwordList based on the last update date in descending order
    	        Collections.sort(passwordList, Comparator.comparing(PasswordManagement::getDateOfUpdate).reversed());
    			break;
    			
    		case 7:
    			if(passView.wellstop) {
    				//before close this application save it to file.
    			
    				wantToStop = true;
    				
    			}
//    	        // Displaying information for all passwords in the list
//    	        for (PasswordManagement password : passwordList) {
//    	            password.displayInfo();
//
//    	            // Check the type of password and call specific methods if needed
//    	            if (password instanceof Website) {
//    	                ((Website) password).displayWebsiteInfo();
//    	            } else if (password instanceof DesktopApplication) {
//    	                ((DesktopApplication) password).displayApplicationInfo();
//    	            } else if (password instanceof Game) {
//    	                ((Game) password).displayGameInfo();
//    	            }
//
//    	            System.out.println(); // Separator between passwords
//    	        }
    	        
    			break;
    		default:
    			System.out.println(" ");
    		}
    	}
    }

    public List<PasswordManagement> loadAllPasswords(List<PasswordManagement> passList) {
		try {
			FileInputStream loadAllPasswords = new FileInputStream("PasswordManagementDataFiles/ManagePassword.txt");
			ObjectInputStream loadAllPasswordsObjectData = new ObjectInputStream(loadAllPasswords);
			
			passList = (List<PasswordManagement>)loadAllPasswordsObjectData.readObject();
			
			loadAllPasswordsObjectData.close();
			loadAllPasswords.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getStackTrace());
		}

    	return passList;
    }
   
    public boolean SaveAllPasswords(List<PasswordManagement> passList) {
		try {
			
				FileOutputStream SaveAllPasswords = new FileOutputStream("PasswordManagementDataFiles/ManagePassword.txt");
				ObjectOutputStream SaveAllPasswordsObjectData = new ObjectOutputStream(SaveAllPasswords);
			
				SaveAllPasswordsObjectData.writeObject(passList);
				SaveAllPasswordsObjectData.flush();
				SaveAllPasswordsObjectData.close();
				SaveAllPasswords.close();
			
				return true;
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}

    }
    
    
    public List<EncryptionClass> loadAllEncryptedPassword(List<EncryptionClass> encrypPassList) {
		try {
			FileInputStream loadAllPasswords = new FileInputStream("PasswordManagementDataFiles/StoreEncryptedAndDecryptedPassword.txt");
			ObjectInputStream loadAllPasswordsObjectData = new ObjectInputStream(loadAllPasswords);
			
			encrypPassList = (List<EncryptionClass>)loadAllPasswordsObjectData.readObject();
			
			loadAllPasswordsObjectData.close();
			loadAllPasswords.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getStackTrace());
		}

    	return encrypPassList;
    }
    
   
    public boolean SaveAllEncrypetedPasswords(List<EncryptionClass> encrypPassList) {
		try {
			
				FileOutputStream SaveAllPasswords = new FileOutputStream("PasswordManagementDataFiles/StoreEncryptedAndDecryptedPassword.txt");
				ObjectOutputStream SaveAllPasswordsObjectData = new ObjectOutputStream(SaveAllPasswords);
			
				SaveAllPasswordsObjectData.writeObject(encrypPassList);
				SaveAllPasswordsObjectData.flush();
				SaveAllPasswordsObjectData.close();
				SaveAllPasswords.close();
			
				return true;
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}

    }
    
    public List<EncryptionClass> updateEncryptionList( List<EncryptionClass> encryptionPasswordList, String updatesearchByName, String password, String encryptedPasswordCode, String nameOfPlatfrom ){
    	
    	for (int i = 0; i < encryptionPasswordList.size(); i++) {
			if(encryptionPasswordList.get(i).getPasswordTypeName().toLowerCase().equals(updatesearchByName)){
				EncryptionClass encrypPass = encryptionPasswordList.get(i);
				encrypPass.setOriginalPassword(password);
				encrypPass.setEncryptedPassword(encryptedPasswordCode);
				encrypPass.setPasswordTypeName(nameOfPlatfrom);
				
				encryptionPasswordList.set(i, encrypPass);
			}
    	}
    	return encryptionPasswordList;
    }
    
    // Encryption method
    public static String encryptPassword(String originalPassword) {
        StringBuilder encryptedResult = new StringBuilder();

        for (char c : originalPassword.toCharArray()) {
            int asciiValue = (int) c;
            int encryptedAsciiValue = asciiValue + 5;
            encryptedResult.append(encryptedAsciiValue).append(",");
        }

        // Remove the trailing comma
        if (encryptedResult.length() > 0) {
            encryptedResult.deleteCharAt(encryptedResult.length() - 1);
        }

        return encryptedResult.toString();
    }
    
    // Decryption method
    public static String decryptPassword(String encryptedPasswords) {
        StringBuilder decryptedResult = new StringBuilder();
        String[] asciiValues = encryptedPasswords.split(",");

        for (String ascii : asciiValues) {
            int encryptedAsciiValue = Integer.parseInt(ascii);
            int originalAsciiValue = encryptedAsciiValue - 5;
            decryptedResult.append((char) originalAsciiValue);
        }

        return decryptedResult.toString();
    }
    
}
