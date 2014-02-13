package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

@SuppressWarnings("serial")
public class PersistenceUtil implements Serializable {
	protected static EntityManagerFactory emf;
	protected static SessionFactory sessionFactory;
	private static boolean liveDatabase = false;
	
	public static void setDatabaseState(boolean usingLiveDatabase){
		liveDatabase = usingLiveDatabase;
	}
	
	private static void checkDatabaseState() {
		if(liveDatabase)
			emf = Persistence.createEntityManagerFactory("dt340a");
		else
			emf = Persistence.createEntityManagerFactory("dt340atest");
	}
	
	/** 
	 * Persists a List of objects in the database. Only requires one connection/transaction.
	 * 
	 * @param entities	List of objects to be stored in the database
	 */
	public static void persistMany(List<Object> entities){
		checkDatabaseState();
	
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for(Object e : entities)
			em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Object merge(Object entity) {
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		checkDatabaseState();
		
		return emf.createEntityManager();
	}
	
	/**
	 * Drops the following tables in the current database:<br />
	 * <ul><li>ErrorEvent</li>
	 *     <li>EventCause</li>
	 *     <li>FailureClass</li>
	 *     <li>MCC_MNC</li>
	 *     <li>UEType</li>
	 *     <li>InvalidErrorEvent</li></ul>
	 */
	public static void dropTables(){
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		SessionFactory sessionFactory = ((org.hibernate.impl.SessionImpl) em.getDelegate()).getSessionFactory();
		Session session = sessionFactory.openSession();
	    session.createSQLQuery("DROP TABLE ErrorEvent, EventCause, FailureClass, MCC_MNC, UEType, InvalidErrorEvent").executeUpdate();
	    em.close();
	}
	
	/**
	 * Truncates the following tables in the current database:<br />
	 * <ul><li>ErrorEvent</li>
	 *     <li>EventCause</li>
	 *     <li>FailureClass</li>
	 *     <li>MCC_MNC</li>
	 *     <li>UEType</li>
	 *     <li>InvalidErrorEvent</li></ul>
	 */
	public static void truncateTables(){
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		SessionFactory sessionFactory = ((org.hibernate.impl.SessionImpl) em.getDelegate()).getSessionFactory();
		Session session = sessionFactory.openSession();
	    session.createSQLQuery("TRUNCATE TABLE ErrorEvent").executeUpdate();
	    session.createSQLQuery("TRUNCATE TABLE EventCause").executeUpdate();
	    session.createSQLQuery("TRUNCATE TABLE FailureClass").executeUpdate();
	    session.createSQLQuery("TRUNCATE TABLE MCC_MNC").executeUpdate();
	    session.createSQLQuery("TRUNCATE TABLE UEType").executeUpdate();
	    session.createSQLQuery("TRUNCATE TABLE InvalidErrorEvent").executeUpdate();
	    em.close();
	}
	
	
}