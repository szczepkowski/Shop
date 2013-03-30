package pl.igore.shop.BEAN;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.DAO.TransactionDAO;
import pl.igore.shop.DAO.UserDAO;
import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

@ManagedBean(name="offer") // lub @Named("user")
@ApplicationScoped


public class OfferBean implements Serializable {
	private String categoryS;
	private int offId;
	private String offTitle;
	private String userS;
	private OfferDAO offerD ;
	private boolean active;
	
	public OfferBean(){
		offerD =  OfferDAO.instance;
	}
	
	public boolean isOwnOffer(){
		String userS = null;
		try {
			userS = offerD.getById(offId).get(0).getSeller().getName();
		} catch (AdException e) {
			e.getMessage();
		}
		System.out.println(userS);
		return this.userS.equals(userS);
	}
	
	public String preBuy(){
		  FacesContext context= FacesContext.getCurrentInstance();
		  Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		  this.userS = params.get("userS");
		  
		 try {
			setActive(offerD.getById(getOffId()).get(0).isActive());
		} catch (AdException e) {
			e.getMessage();
		}
		  
		return"buy";
	}
	
	public String buy(){
		UserDAO userD = UserDAO.instance;
		TransactionDAO transD = TransactionDAO.instance;
				
		Offer offer = null;

		try {
			offer = offerD.getById(getOffId()).get(0);
			offer.setBuyer(userD.get(userS));
			offer.setActive(false);
			offerD.update(offer);
			transD.create(offer);
			
		} catch (AdException e) {
			e.getMessage();
		}
		return"bought";
	}
	
	public void setOffId(int id){
		this.offId=id;
	}
	
	public int getOffId(){
		return offId;
	}
	
	public String setCat(String catS){
		categoryS=catS;
		return "category";
	}
	
	public List<Category>getCategoryList(){
		CategoryDAO catD = CategoryDAO.instance;
		List<Category>list=null;
		try {
			list=catD.list();
		} catch (AdException e) {
			e.getMessage();
		}
		return list;
	}
	
	public List<Offer>getOffersList(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer>list=null;
		try {
			list=offerD.activeListByCategory(categoryS);
		} catch (AdException e) {
			e.getMessage();
		}
		
		return list;
	}

	public String getCategoryS() {
		return categoryS;
	}

	public void setCategoryS(String categoryS) {
		this.categoryS = categoryS;
	}
	
	public List<Offer> getOfferDetails(){
		List<Offer> offer=null;
		try {
			offer = offerD.getById(offId);
		} catch (AdException e) {
			e.getMessage();
	
		}
		System.out.println(offer);
		return offer;
	}

	public String getOffTitle() {
		return offTitle;
	}

	public void setOffTitle(String offTitle) {
		this.offTitle = offTitle;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
