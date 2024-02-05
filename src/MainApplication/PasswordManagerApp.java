package MainApplication;

import Controller.logInSignUpController;
import Controller.PasswordManagementSystemController;

public class PasswordManagerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logInSignUpController signLnControl = new logInSignUpController();
		PasswordManagementSystemController PasswordController = new PasswordManagementSystemController(signLnControl.getUser());
	}

}
