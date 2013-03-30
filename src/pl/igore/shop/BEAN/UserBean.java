package pl.igore.shop.BEAN;

import java.io.Serializable;
import javax.faces.bean.ManagedBean; 
   // lub import javax.inject.Named;
import javax.faces.bean.SessionScoped; 
import javax.faces.context.FacesContext;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.UserDAO;
import pl.igore.shop.POJO.User;

@ManagedBean(name="user") // lub @Named("user")
@SessionScoped

public class UserBean implements Serializable {
   private String name;
   private String password;
   private String mail;
   private int verify;
   private boolean verified;
   private boolean notVerified;
   
   public UserBean(){
	   name="";
	   verify=0;
	   verified=false;
	   notVerified=true;
   }
   
   public void setVerify(int v){
	   this.verify=v;
   }
  
   public boolean isVerified() {return verified;}
   public void setVerified(boolean verified) {this.verified = verified;}
   
   public String getName() { return name; }   
   public void setName(String newValue) { name = newValue; }

   public String getPassword() { return password; }
   public void setPassword(String newValue) { password = newValue; }   
   
   public String getMail(){return mail;}
   public void setMail(String newValue){mail=newValue;}
   
   public String getToString(){
	return "imie : "+ name+" password = "+password+" mail = "+mail;
	   
   }
   
   public String verifyUser(){
	   UserDAO userD = new UserDAO();
	   try {
		if (!userD.contains(name) ){
			verify=-1;
			return"";
		   }
	} catch (AdException e) {
		e.getMessage();
	}
	   User user = null;
	   try {
		user = userD.get(name);
	} catch (AdException e) {
		e.getMessage();
	}
	   if(password.equals(user.getPassword())){
		   verified=true;
		   notVerified=false;
		   return "index";
	   }
	   else{
		   verify=-2;
		   return "";
	   } 
   }
   public String getVerify(){
	   if(name=="")return "";
	   if(verify==-2) return"Wrong password";
	   if(verify==-1) return"User doesn't exist";
	   else{return"";}
   }
   
   public String create(){
	   UserDAO userD = new UserDAO();   
	   try {
		userD.create(name, password,mail);
	} catch (AdException e) {
		e.getMessage();
	}
	return "registered";
   }

	public boolean isNotVerified() {
		return notVerified;
	}
	
	public void setNotVerified(boolean notVerified) {
		this.notVerified = notVerified;
	}
	
	public String logout() {
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "index";
	}

}
