package Models;

import java.io.Serializable;

public class Game extends PasswordManagement implements Serializable{

    private String gameName;
    private String gameDeveloper;
    private static Game gm;

    // Constructor for GamePassword
    private Game(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user,
    		String type, String gameName, String gameDeveloper) {
        super(name, password, dateOfCreate, dateOfUpdate, user, type);
        this.gameName = gameName;
        this.gameDeveloper = gameDeveloper;
    }
    
    public static Game getgame(String name, String password, String dateOfCreate, String dateOfUpdate, UserAccount user,
    		String type, String gameName, String gameDeveloper) {
    	if(gm == null) {
    		gm = new Game(name, password, dateOfCreate, dateOfUpdate, user, type, gameName, gameDeveloper);
    	}
    	return gm;
    }
    
    public void displayGameInfo() {
        System.out.println("Game Information: " + gameName + ", Developer: " + gameDeveloper);
    }

	public String getGameName() {
		return gameName;
	}

	public String getGameDeveloper() {
		return gameDeveloper;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public void setGameDeveloper(String gameDeveloper) {
		this.gameDeveloper = gameDeveloper;
	}
    
    
}
