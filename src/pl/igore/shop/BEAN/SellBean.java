package pl.igore.shop.BEAN;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.AdminDAO;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.DAO.UserDAO;
import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

@ManagedBean(name="sell")
@SessionScoped

public class SellBean implements Serializable{
	private String userS;
	private String name;
	private String categoryS;
	private String specification;
	private double price;
	private String startDate;
	private int startDateMin;
	private int startDateHour;
	private int days;
	
	public SellBean(){
		reset();
	}
	
	public String addOffer(){
		return"addOffer";
	}
	
	public String createOffer(){
		UserDAO userD =UserDAO.instance;
		CategoryDAO catD = CategoryDAO.instance;
		OfferDAO offerD = OfferDAO.instance;
		
		User user = null;
		Category cat = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		userS = params.get("userS");
		System.out.println(userS);
		try {
			user = userD.get(userS);
			cat = catD.get(categoryS);
		} catch (AdException e) {
			e.getMessage();
		}
		Date endDate = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_YEAR,days);
		
		Offer offer = new Offer(user,name,cat,price,specification,
				formatDate(startDate,startDateMin,startDateHour),
				endDate);
		
		try {
			offerD.create(offer);
		} catch (AdException e) {
			e.printStackTrace();
		}
		reset();
		return "offerSuccess";
	}
	
	public  void reset(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		startDate=format.format(date);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		startDateMin = cal.get(Calendar.MINUTE);
		startDateHour = cal.get(Calendar.HOUR_OF_DAY);
		specification="";
		
		name="";
		price=0;
	}
	
	public Date formatDate(String dateS,int minute,int hour){
		StringTokenizer t = new StringTokenizer(dateS,"-");
		Date date = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, new Integer(t.nextToken()));
		cal.set(Calendar.MONTH, new Integer(t.nextToken())-1);
		cal.set(Calendar.YEAR, new Integer(t.nextToken()) );
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}
	
	public List<String>getCatNamesList(){
		CategoryDAO catD = CategoryDAO.instance;
		List<Category>list=null;
		try {
			list=catD.list();
		} catch (AdException e) {
			e.getMessage();
		}
		Iterator<Category> it = list.iterator();
		List<String> catNamesList = new ArrayList<String>();
		while(it.hasNext()){
			catNamesList.add(it.next().getName());
		}
		return catNamesList;
	}
	
	public List<Integer> getDaysList(){
		List<Integer>list = new ArrayList<Integer>();
		for(int i=4;i<15;i++){
			list.add(i);
		}
		return list;
	}
	
	public List<Integer> getMinute(){
		List<Integer>list = new ArrayList<Integer>();
		for(int i=0;i<60;i++){
			list.add(i);
		}
		return list;
	}
	
	public List<Integer>getHour(){
		List<Integer>list = new ArrayList<Integer>();
		for(int i=0;i<24;i++){
			list.add(i);
		}
		return list;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return categoryS;
	}

	public void setCategory(String category) {
		this.categoryS = category;
	}

	public String getUser() {
		return userS;
	}

	public void setUser(String user) {
		this.userS = user;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	

	public int getStartDateMin() {
		return startDateMin;
	}

	public void setStartDateMin(int startDateMin) {
		this.startDateMin = startDateMin;
	}

	public int getStartDateHour() {
		return startDateHour;
	}

	public void setStartDateHour(int startDateHour) {
		this.startDateHour = startDateHour;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	public int getDays(){
		return days;
	}
	
}
