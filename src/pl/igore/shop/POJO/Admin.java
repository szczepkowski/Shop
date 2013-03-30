package pl.igore.shop.POJO;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name="admin")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@AttributeOverrides({
		@AttributeOverride(name="id", column=@Column(name="admin_id") ),
		@AttributeOverride(name="name", column=@Column(name="admin_name") ),
		@AttributeOverride(name="password", column=@Column(name="admin_password") ),
		@AttributeOverride(name="mail", column=@Column(name="admin_mail"))
        })
@DiscriminatorColumn(
		name="Discriminator",
		discriminatorType=DiscriminatorType.INTEGER
		)
@DiscriminatorValue(value = "2")

public class Admin extends Person{
	private String position;

	public Admin(){
	}
	
	public Admin(String name,String pass,String mail,String position){
		super(name, pass, mail);
		this.position=position;
	}
	
	public Admin(String position){
		this.setPosition(position);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
