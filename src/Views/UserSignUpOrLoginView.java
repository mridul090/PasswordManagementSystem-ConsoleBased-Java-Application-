package Views;

import java.util.Scanner;

import Models.UserAccount;

public class UserSignUpOrLoginView {
	private String userEmail;
	private String userPassword;
	private UserAccount userAcc;
	Scanner input = new Scanner(System.in);
	
	int choseOption;
	
	private static UserSignUpOrLoginView log_sinup;
	
	public static UserSignUpOrLoginView getLogInSignUp() {
		
		if(log_sinup == null) {
			log_sinup = new UserSignUpOrLoginView();
		}
		
		return log_sinup;
	}
	
	public UserSignUpOrLoginView UserSignUpOrLoginView(UserSignUpOrLoginView sign_lgin_view) {
		System.out.println("\t\t\t Password Management System\n\n");
		System.out.println("Do you want to 1. logIn or 2. Register new User to this system. \nPlease chose an opption between 1 & 2.");
		try {
			sign_lgin_view.setChoseOption(input.nextInt());
			input.nextLine();
		
			if(sign_lgin_view.choseOption == 1) {
				System.out.println(" Please Input user Email");
				String email = input.nextLine();
				sign_lgin_view.setUserEmail(email);
				
				System.out.println(" Please Input user password");
				String pass = input.nextLine();
				sign_lgin_view.setUserPassword(pass);
				
				return sign_lgin_view;
			}
			else if(sign_lgin_view.choseOption == 2) {
				userAcc = UserAccount.getUserAccount();
				
				System.out.println(" Please input User First Name, Last Name, Name, Email, Password, User Type(Chose 1. admin or 2. user)");
				userAcc.registerUser(userAcc, input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextInt());
				input.nextLine();
				sign_lgin_view.setUserAcc(userAcc);
				
				return sign_lgin_view;
			}
			else {
				System.out.println(" Sorry!! You didn't put the right number.");
			}
				
				
				
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}

//	get functions
	public int getChoseOption() {
		return choseOption;
	}

	public void setChoseOption(int choseOption) {
		this.choseOption = choseOption;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public UserAccount getUserAcc() {
		return userAcc;
	}

//	set functions
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserAcc(UserAccount userAcc) {
		this.userAcc = userAcc;
	}
	
	
}
