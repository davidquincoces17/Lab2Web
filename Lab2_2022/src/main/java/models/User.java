package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {
	
	/*
	 CREATE TABLE `users` (
  		`usr` varchar(255) NOT NULL,
  		`mail` varchar(255) NOT NULL,
  		`pwd` varchar(255) NOT NULL,
  		PRIMARY KEY (`usr`),
  		UNIQUE KEY `mail` (`mail`)
	 ); 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String username = "";
	private String mail = "";
	private String pwd1 = "";
	private String pwd2 = "";
	private String nickname = "";
	private String gender = "";
	private String birth = "";
	
	private boolean[] error = {false,false,false,false,false,false,false};
	
	public User() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		/* We can simulate that a user with the same name exists in our DB and mark error[0] as true  */
		//TODO: regex
		error[0] = true;
		//this.user = user;
		//System.out.println(user);
//		this.username = username;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		//TODO: mail length
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
			System.out.println(mail);
		} else {
			error[1]=true;
			System.out.println(mail);
		}
		
	}
	
	public String getPwd1() {
		return this.pwd1;
	}
	
	public void setPwd1(String pwd1) {
		/* TODO check restriction with pattern */
		this.pwd1 = pwd1;
		System.out.println(pwd1);
	}
	
	public String getPwd2() {
		return this.pwd2;
	}
	
	public void setPwd2(String pwd2) {
		/* TODO check restriction with pattern and check if pwd1=pwd2*/
		this.pwd2 = pwd2;
		System.out.println(pwd2);
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		if(nickname.matches("^.{1,30}$")) {
			this.nickname = nickname;
		}else {
			error[4] = true;
		}	
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		ArrayList<String> options = new ArrayList<String>();
		options.add("male");
		options.add("female");
		options.add("notsay");
		options.add("other");
		if(options.contains(gender)) {
			this.gender = gender;
		}else {
			error[5] = true;
		}
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		Date birthdate = null;
		try {
		    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    formatter.setLenient(false);
		    birthdate = formatter.parse(birth);
		} catch (ParseException e) { 
		    error[6] = true;
		}
		Date currentdate = new Date();
		long diff = currentdate.getTime() - birthdate.getTime();
		if(diff >= 504910816000L) { //older than 16 years
			this.birth = birth;			
		} else {
		    error[6] = true;
		}
	}

	public void setError(boolean[] error) {
		this.error = error;
	}
	
	public boolean[] getError() {
		return error;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", mail=" + mail + ", pwd1=" + pwd1 + ", pwd2=" + pwd2 + ", nickname=" + nickname
				+ ", gender=" + gender + ", birth=" + birth + ", error=" + Arrays.toString(error) + "]";
	}
		
}
