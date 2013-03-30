package pl.igore.shop.DAO;

import java.util.Date;
import java.util.List;

import org.apache.catalina.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

public class OfferDAO  extends DAO{
	public static final OfferDAO instance = new OfferDAO();
	
	public OfferDAO(){}
	
	public void update(Offer offer) throws AdException{
		try{
			begin();
			getSession().saveOrUpdate(offer);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not saveOrUpdate offer named = "+offer.getName(),e);
		}	

	}
	
	public List<Offer> getById(int id) throws AdException{
		List<Offer> list = null;
			try{	
				begin();
				Query query = getSession().createQuery("from Offer where id=:id");
				query.setParameter("id", id);
				list=query.list();
				commit();
			}

		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Offer with id = "+id,e);
		}
		return list;
	}
	
/*	public Offer get(String name) throws AdException{
		Offer offer =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Offer where offer_name=:name");
			query.setParameter("offer_name", name);
			offer = (Offer)query.uniqueResult();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Offer named = "+name,e);
		}	
		return offer;
	}
	*/
	public Offer create(Offer offer) throws AdException{
		try{
			begin();
			getSession().save(offer);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create offer named = "+offer.getName(),e);
		}	
		finally{
			close();
		}
		return offer;
	}
	
	public Offer create(User user,String name,Category cat, double price, String spec,Date startDate,Date endDate ) throws AdException{	
		Offer offer =  new Offer(user,name,cat,price,spec,startDate,endDate);
			try{
				begin();
				getSession().save(offer);
				commit();
			}
			catch(HibernateException e){
				rollback();
				throw new AdException("Could not create offer named = "+name,e);
			}	
			finally{
				close();
			}
			return offer;
		}
	
	public List<Offer> list() throws AdException{
		List<Offer> list =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Offer");
			list=query.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Offer list ",e);
		}	
		return list;
	}
	
	public List<Offer> activeListByCategory(String catS) throws AdException{
		List<Offer> list =  null;
		//CategoryDAO catD = CategoryDAO.instance;
		//Category cat = catD.get(catS);
		try{
			begin();
			Criteria crit = getSession().createCriteria(Offer.class);
			Criterion offCrit = Restrictions.eq("active", true);
			crit.add(offCrit);
			Criteria catCrit = crit.createCriteria("category");
			catCrit.add(Restrictions.eq("name", catS));
			list=crit.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get listByCategory  ",e);
		}	
		return list;
	}
	/*
	 * User must be String - buyer or seller
	 */
	public List<Offer> notActiveListByBuyer(String user) throws AdException{
		List<Offer> list =  null;
		//CategoryDAO catD = CategoryDAO.instance;
		//Category cat = catD.get(catS);
		try{
			begin();
			Criteria crit = getSession().createCriteria(Offer.class);
			Criterion offCrit = Restrictions.eq("active", false);
			crit.add(offCrit);
			Criteria catCrit = crit.createCriteria("buyer");
			catCrit.add(Restrictions.eq("name", user));
			list=crit.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get listByBuyer  ",e);
		}	
		return list;
	}
	
	public List<Offer> notActiveListBySeller(String user) throws AdException{
		List<Offer> list =  null;
		//CategoryDAO catD = CategoryDAO.instance;
		//Category cat = catD.get(catS);
		try{
			begin();
			Criteria crit = getSession().createCriteria(Offer.class);
			Criterion offCrit = Restrictions.eq("active", true);
			crit.add(offCrit);
			Criteria catCrit = crit.createCriteria("seller");
			catCrit.add(Restrictions.eq("name", user));
			list=crit.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get listBySeller  ",e);
		}	
		return list;
	}
	
	public Offer deleteByID(int id) throws AdException{
		Offer offer =  null;
		
		try{
			begin();
			Query query = getSession().createQuery("from Offer where id=:id");
			query.setParameter("id", id);
			offer = (Offer)query.uniqueResult();
			getSession().delete(offer);
			System.out.println(offer);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not delete offer named = "+offer.getName(),e);
		}	
		finally{
			close();
		}
		return offer;
	}
	
}


