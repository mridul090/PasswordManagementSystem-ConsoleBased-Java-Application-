package Controller;

import Views.UserSignUpOrLoginView;

import java.util.Set;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import Models.UserAccount;

public class logInSignUpController {
	private UserAccount user;
	UserSignUpOrLoginView sign_lgin_view;
	Set<UserAccount> userAccountSet = new HashSet<>();
	boolean isSuccess = false;
	
	public logInSignUpController(){
		
		try {
		sign_lgin_view = UserSignUpOrLoginView.getLogInSignUp();
		
		sign_lgin_view = sign_lgin_view.UserSignUpOrLoginView(sign_lgin_view);
		
		if(sign_lgin_view.getChoseOption() == 2) {
			userAccountSet = loadUsersData(userAccountSet);
			
			if(!userAccountSet.add(sign_lgin_view.getUserAcc())) {
				 System.out.println("User " + sign_lgin_view.getUserAcc().getUserName() + " with the Email: "+sign_lgin_view.getUserAcc().getUserEmailAddress()+" can't be added because its already in the system.");
				 System.out.println("Can't be register!!");
		    }
			else {
				isSuccess = saveUserAccountInDataFile(userAccountSet);
			
				if(isSuccess) {
					System.out.println(" User Register Success");
					this.setUser(sign_lgin_view.getUserAcc());
				}
				else {
					System.out.println(" Can't register!!");
					this.setUser(null);
				}
			}
		}
		else if (sign_lgin_view.getChoseOption() == 1){
			userAccountSet = loadUsersData(userAccountSet);
			
			this.setUser(sign_lgin_view.getUserAcc().searchAUserByEmail(userAccountSet, sign_lgin_view.getUserEmail()));
			
			sign_lgin_view.setUserAcc( this.user );
			
			if(sign_lgin_view.getUserAcc() != null && sign_lgin_view.getUserAcc().getUserPassword().equals(sign_lgin_view.getUserPassword())) {
				System.out.println("LogIn Success for user "+ sign_lgin_view.getUserAcc().getUserName());
			}
			else {
				System.out.println(" Sorry can't find the user ");
				this.setUser(null);
			}

		}
	}
	catch(Exception e) {
		System.out.println(e.fillInStackTrace());
	}
		
	}
	
	public Set<UserAccount> loadUsersData(Set<UserAccount> setArrayList) {
		try {
			FileInputStream loadAllUsersData = new FileInputStream("PasswordManagementDataFiles/UserAccountDataFile.txt");
			ObjectInputStream loadSavedUserAccountObjectData = new ObjectInputStream(loadAllUsersData);
			
			setArrayList = (Set<UserAccount>)loadSavedUserAccountObjectData.readObject();
			
			loadSavedUserAccountObjectData.close();
			loadAllUsersData.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
//		for(UserAccount userDetails: setArrayList) {
//			System.out.println("User Name "+userDetails.getUserName()+" User Email "+ userDetails.getUserEmailAddress()+ " User Type "+ userDetails.getUserTypes());
//		}
		return setArrayList;
	}
	
	public boolean saveUserAccountInDataFile(Set<UserAccount> setArrayList) {
		// write this newly added data to file 
        try {
			FileOutputStream SaveUserAccounttoDataFile = new FileOutputStream("PasswordManagementDataFiles/UserAccountDataFile.txt");
			ObjectOutputStream SaveUserAccountObjectToDataFile = new ObjectOutputStream(SaveUserAccounttoDataFile);
			
			SaveUserAccountObjectToDataFile.writeObject(setArrayList);
			SaveUserAccountObjectToDataFile.flush();
			SaveUserAccounttoDataFile.close();
			
			return true;
			
			
		} catch (Exception e) {
			// handle All type exception
			System.out.println(e.getMessage());
			return false;
		}
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}
	
	
}
