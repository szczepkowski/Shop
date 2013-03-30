package pl.igore.shop.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.Transaction;
import pl.igore.shop.POJO.User;

public class TransactionDAO extends DAO{
	public static final TransactionDAO instance = new TransactionDAO();
	
	public TransactionDAO(){}
	
	public Transaction create(Offer offer) throws AdException{
		Transaction trans = new Transaction(offer);
		
		try{
			begin();
			getSession().save(trans);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create transaction from offer named = "+offer.getName(),e);
		}	
		finally{
			close();
		}
		return trans;
	}
	
	public void save(Transaction trans) throws AdException{
		try{
			begin();
			getSession().save(trans);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not save transaction from offer named = "+trans.getOffer().getName(),e);
		}	
		finally{
			close();
		}
	}
	
	public List<Transaction> getById(int id) throws AdException{
		 List<Transaction> trans = null;
				try{	
					begin();
					Query query = getSession().createQuery("from Transaction where id=:id");
					query.setParameter("id", id);
					trans=query.list();
					commit();
				}

			catch(HibernateException e){
				rollback();
				throw new AdException("Could not get Transasction with id = "+id,e);
			}
			return trans;
		}
	
	
	public List<Transaction>listByUser(String user){
		Criteria crit = getSession().createCriteria(Transaction.class);
		Criteria offerCrit = crit.createCriteria("offer");
		Criteria userCrit = offerCrit.createCriteria("buyer");
		userCrit.add(Restrictions.eq("name", user));
		List<Transaction>list=crit.list();

		
		return list;
	}
	
	public List<Transaction> list() throws AdException{
		List<Transaction> list =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Transaction");
			list=query.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Transaction list ",e);
		}	
		return list;
	}
}
