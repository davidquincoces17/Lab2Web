package models;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import managers.ManageVenda;

public class Venda implements java.io.Serializable {
	
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
	
	private String data = "";
	private String hora = "";
	private String pagament = "";
	private String client = "";
	private String venedor = "";
		
	public Venda() {
		
	}

	


	public String getDate() {
		return data;
	}




	public void setDate(String data) {
		this.data = data;
	}




	public String getHora() {
		return hora;
	}




	public void setHora(String hora) {
		this.hora = hora;
	}




	public String getPagament() {
		return pagament;
	}




	public void setPagament(String pagament) {
		this.pagament = pagament;
	}




	public String getClient() {
		return client;
	}




	public void setClient(String client) {
		this.client = client;
	}




	public String getVenedor() {
		return venedor;
	}




	public void setVenedor(String venedor) {
		this.venedor = venedor;
	}




	@Override
	public String toString() {
		return "User [data=" + data + ", hora=" + hora + ", pagament=" + pagament + ", client=" + client + ", venedor=" + venedor + "]";
	}
		
}
