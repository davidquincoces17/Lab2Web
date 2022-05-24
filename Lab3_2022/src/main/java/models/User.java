package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import managers.ManageUsers;

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
	
	private boolean[] error = {false,false,false,false,false,false,false,false,false};
	
	public User() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		/* We can simulate that a user with the same name exists in our DB and mark error[0] as true  */
		//TODO: regex
		//error[0] = true;
		//this.user = user;
		//System.out.println(user);
		//this.username = username;
		
		String regex = "^[a-zA-Z\\d_]{1,10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		
		if (matcher.matches()) {
			//verify uniqueness in DB
			ManageUsers manager = new ManageUsers();
			Boolean unique = manager.requestUniqueness("username", username);
			
			if (unique) {
				this.username = username;
			} else {
				error[0]=true;
			}
		} else {
			error[1]=true;
		}
		System.out.println(username);
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		//TODO: mail length
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		
		if (matcher.matches() && mail.length() <= 50) {
			
			//verify uniqueness in DB
			ManageUsers manager = new ManageUsers();
			Boolean unique = manager.requestUniqueness("mail", mail);
			
			if (unique) {
				this.mail = mail;
			} else {
				error[2]=true;
			}
			
		} else {
			error[3]=true;
		}
		System.out.println(mail);
		
	}
	
	public String getPwd1() {
		return this.pwd1;
	}
	
	public void setPwd1(String pwd1) {
		/* TODO check restriction with pattern */
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&_+\\-*\\/#])[A-Za-z\\d@$!%?&_+\\-*\\/#]{8,50}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd1);
		
		if (matcher.matches()) {
			this.pwd1 = pwd1;
		} else {
			error[4]=true;
		}
		System.out.println(pwd1);
	}
	
	public String getPwd2() {
		return this.pwd2;
	}
	
	public void setPwd2(String pwd2) {
		/* TODO check restriction with pattern and check if pwd1=pwd2*/
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&_+\\-*\\/#])[A-Za-z\\d@$!%?&_+\\-*\\/#]{8,50}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd2);
		
		if (matcher.matches() && pwd2.equals(pwd1)) {
			this.pwd2 = pwd2;
		} else {
			error[5]=true;
		}
		System.out.println(pwd2);

	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		if(nickname.matches("^.{1,30}$")) {
			this.nickname = nickname;
		}else {
			error[6] = true;
		}
		System.out.println(nickname);
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
			error[7] = true;
		}
		System.out.println(gender);
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
		    error[8] = true;
		}
		System.out.println(birth);
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
