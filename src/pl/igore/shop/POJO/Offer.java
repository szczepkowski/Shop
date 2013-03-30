package pl.igore.shop.POJO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="offer")
public class Offer implements Serializable {
	private int id;
	private User seller;
	private User buyer;
	private String name;
	private Category category;
	private double price;
	private String specification;
	private Date startDate;
	private Date endDate;
	private boolean active;
	
	public Offer(){}
	
	public String toString(){
		return "name = "+name+"user = "+seller.getName()+"Category = "+category.getName()+" price = "+price;
	}
	
	public Offer(User seller,String name,Category cat, double price, String spec,Date startDate,Date endDate ){	
		this.setSeller(seller);
		this.name=name;
		this.category=cat;
		this.price=price;
		this.specification=spec;
		this.startDate=endDate;
		this.endDate=endDate;
		this.setActive(true);
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="offer_name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity=Category.class)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	@Column(nullable=false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {	
		this.price = price;
	}
	@Transient
	public String getPriceCurrency(){
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.getDefault()); 
		String s = n.format(price);
		return s;
	}
	
	@Column(nullable=false,columnDefinition="TEXT")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	@Column(nullable=false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(nullable=false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Transient
	public String getEndDateWithFormat(){
		return shopDateFormat(endDate);
	}
	
	@Transient
	public String getStartDateWithFormat(){
		return shopDateFormat(startDate);
	}
	
	@Transient
	public String shopDateFormat(Date date){
		DateFormat format = new SimpleDateFormat("kk:mm, dd-MM-yyyy");
		String dateS =	format.format(date);
		return dateS;
	}
	@ManyToOne(targetEntity=User.class)
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
	@ManyToOne(targetEntity=User.class)
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
