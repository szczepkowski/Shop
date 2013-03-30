package pl.igore.shop.POJO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction  implements Serializable{
	private int id;
	private Date date;
	private Offer offer;
	private boolean paid;
	
	public Transaction(){
		this.setPaid(false);
		this.date=new Date();
	}
	
	public Transaction(Offer offer){
		this.setPaid(false);
		this.date=new Date();
		this.offer=offer;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@OneToOne(targetEntity=Offer.class)
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	@Column(nullable=false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@Column(nullable=false)
	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
