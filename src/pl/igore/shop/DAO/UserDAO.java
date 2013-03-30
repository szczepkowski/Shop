package pl.igore.shop.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.User;

public class UserDAO extends DAO implements Serializable{
	public static final UserDAO instance = new UserDAO();
	
	public UserDAO(){}
	
	public void update(User user) throws AdException{
		try{
			begin();
			getSession().saveOrUpdate(user);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not saveOrUpdate User named = "+user.getName(),e);
		}	

	}
	
	public User get(String name)throws AdException{
		User user =  null;
		try{
			begin();
			Query query = getSession().createQuery("from User where name=:name");
			query.setParameter("name", name);
			user = (User) query.uniqueResult();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get User named = "+name,e);
		}	
		return user;
	}
	
	public List<User> list()throws AdException{
		List<User> list =  null;
		try{
			begin();
			Query query = getSession().createQuery("from User");
			list=query.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get User named = ",e);
		}	
		return list;
	}
	
	public User create(String name, String pass, String mail)throws AdException{
		User user = new User(name,pass,mail);
		try{
			begin();
			getSession().save(user);
			commit();
			return user;
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create User = "+user.getName(),e);
		}	
		finally{close();}
	}
	
	public boolean contains(String name)throws AdException{ 
		boolean contain=false;
		try{
			begin();
			Query query = getSession().createQuery("from User where name=:name");
			query.setParameter("name", name);
			if( !(query.list().size()==0 )) contain=true;		
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not check does db contains User named = "+name,e);
		}	
		finally{close();}
		return contain;
	}
}