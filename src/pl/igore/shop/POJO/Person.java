package pl.igore.shop.POJO;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.sql.DataSource;

@MappedSuperclass
public abstract class Person implements Serializable {
	private int id;
	private String name;
	private String password;
	private String mail;
	
	public Person(){}
	
	public Person(String name, String pass,String mail){
		this.name = name;
		this.password=pass;
		this.mail=mail;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false,unique=true)
	public String getName() {
		return name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	@Column(nullable=false,unique=true)
	public String getMail() {
		return mail;
	}

}
