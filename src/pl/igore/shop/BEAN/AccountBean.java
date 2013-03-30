package pl.igore.shop.BEAN;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.DAO.TransactionDAO;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.Transaction;

@ManagedBean(name="account") // lub @Named("user")
@SessionScoped

public class AccountBean implements Serializable{
	@ManagedProperty(value="#{user}")
	private UserBean userBean;
	private int transId;
	
	public void setUserBean(UserBean userBean){this.userBean=userBean;}
	
	public AccountBean(){}
	
	public int getTransId(){
		return transId;
	}
	
	public void setTransId(int transId){
		this.transId = transId;
	}
	
	public String pay(){
		  
		TransactionDAO tranD = TransactionDAO.instance;
		List<Transaction> trans= null;
		try {
			trans = tranD.getById(new Integer(transId));
			trans.get(0).setPaid(true);
			
		} catch (NumberFormatException | AdException e) {
			e.getMessage();
		}
		
		return "payList";
	}
	
	public List<Transaction>getTransactionById(){
		  
		  TransactionDAO tranD = TransactionDAO.instance;
		  List<Transaction> list= null;
		  System.out.println(transId);
		  try {
			list = tranD.getById(new Integer(transId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AdException e) {
			e.printStackTrace();
		}
		  return list;
	}
	
	public List<Transaction>getTransactionList(){
		TransactionDAO transD = TransactionDAO.instance;
		List<Transaction> list= transD.listByUser(userBean.getName());
		Iterator<Transaction> it = list.iterator();
		while(it.hasNext()){
			System.out.println( it.next().getOffer().getBuyer().getName());
		}
		return list;
		
	}
	
	public List<Offer> getBoughtList(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer> list = null;
		
		try {
			list = offerD.notActiveListByBuyer(userBean.getName());
		} catch (AdException e) {
			e.getMessage();
		}
		
		return list;
	}
	
	public List<Offer> getSoldList(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer> list = null;
		
		try {
			list = offerD.notActiveListBySeller(userBean.getName());
		} catch (AdException e) {
			e.getMessage();
		}

		return list;
	}

}
