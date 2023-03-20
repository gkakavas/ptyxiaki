package application;

public class LoginPageData {

private String userName;
private String passWord;

public LoginPageData(String USERNAME,String PASSWORD) {
	this.userName = USERNAME;
	this.passWord = PASSWORD;
}

public String getUserName() {
	return userName;
}

public void setUserName(String USERNAME) {
	this.userName = USERNAME;
}

public String getPassWord() {
	return passWord;
}

public void setPassWord(String PASSWORD) {
	this.passWord = PASSWORD;
}

}
