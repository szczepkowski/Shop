package pl.igore.shop.BEAN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.igore.shop.App;
import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.AdminDAO;
import pl.igore.shop.POJO.Admin;

@ManagedBean(name="admin") // lub @Named("user")
@SessionScoped

public class AdminBean extends UserBean{
	private String position;
	
	public AdminBean(){
		super();
	}
	public String showCat(){
		App.showCat();
		return "";
	}
	
	public String add100RandomOffers(){
		App.add100RandomOffers();
		return"";
	}
	
	public String initUsers(){
		App.loadUsers();
		return"";
	}
	
	public String initCategory(){
		App.loadCat();
		return "";
	}
	
	@PostConstruct
	public void initialize(){
		AdminDAO adminD = AdminDAO.instance;
		try {
			if( !adminD.contains("admin")){
				adminD.create("admin","a","admin@lib-gore.rhcloud.com","headAdmin");
			}
		} catch (AdException e) {
			e.printStackTrace();
		}
	}
	
	  public String verifyUser(){
		   AdminDAO adminD = new AdminDAO();
		   try {
			if (!adminD.contains(super.getName()) ){
				super.setVerify(-1);
				return"";
			   }
		} catch (AdException e) {
			e.getMessage();
		}
		   Admin admin = null;
		   try {
			admin = adminD.get(getName());
		} catch (AdException e) {
			e.printStackTrace();
		}
		   if(super.getPassword().equals(admin.getPassword())){
			   super.setVerified(true);
			   super.setNotVerified(false);
			   return "adminAccount";
		   }
		   else{
			   super.setVerify(-2);
			   return "";
		   } 
	   }
	
	public String create(){
		AdminDAO adminD = AdminDAO.instance;
		try {
			if( !adminD.contains(super.getName())){
				adminD.create(super.getName(),super.getPassword(),super.getMail(),this.getPosition());
			}
		} catch (AdException e) {
			e.printStackTrace();
		}
		return "";
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}



