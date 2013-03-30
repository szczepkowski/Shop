package pl.igore.shop.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Admin;
import pl.igore.shop.POJO.User;

public class AdminDAO extends DAO{
	public static final AdminDAO instance = new AdminDAO();
	
	public AdminDAO(){}
	
	public Admin get(String name) throws AdException{
		Admin admin =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Admin where name=:name");
			query.setParameter("name", name);
			admin = (Admin) query.uniqueResult();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get User named = "+name,e);
		}	
		return admin;
	}
	
	public Admin create(String name,String password,String mail,String position) throws AdException{
		Admin admin =  new Admin(name,password,mail,position);
		try{
			begin();
			getSession().save(admin);
			commit();
			return admin;
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create User = "+admin.getName(),e);
		}	
		finally{close();}
	}
	
	public boolean contains(String name) throws AdException{
		boolean contain=false;
		try{
			begin();
			Query query = getSession().createQuery("from Admin where name=:name");
			query.setParameter("name", name);
			if( !(query.list().size()==0 )) contain=true;		
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not check does db contains Admin named = "+name,e);
		}	
		finally{close();}
		return contain;
	}
		
}





