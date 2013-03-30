package pl.igore.shop.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Admin;
import pl.igore.shop.POJO.Category;

public class CategoryDAO  extends DAO{
	public static final CategoryDAO instance = new CategoryDAO();
	
	public CategoryDAO(){}
	
	public Category get(String name) throws AdException{
		Category cat =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Category where name=:name");
			query.setParameter("name", name);
			cat = (Category) query.uniqueResult();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Cat named = "+name,e);
		}	
		return cat;
	}
	
	public Category create(String name) throws AdException{
		Category cat =  new Category(name);
		try{
			begin();
			getSession().save(cat);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create Category named = "+name,e);
		}	
		return cat;
	}
	
	public List<Category> list() throws AdException{
		List<Category>list=null;
		try{
			begin();
			Query query = getSession().createQuery("from Category");
			list=query.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create Category list ",e);
		}	
		return list;
	}
}
