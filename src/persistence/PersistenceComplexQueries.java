package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SuppressWarnings({ "unchecked", "serial" })
public class PersistenceComplexQueries  implements Serializable{
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dt340a"); 
	
	public static void findEventIdAndCauseCodeByIMSI(long imsi) {
		System.out.println("\nRetrieving Event ID and Cause Code for IMSI " + imsi + "...");
		EntityManager em = emf.createEntityManager();
		List<Integer[]> results = (List<Integer[]>) em.createNamedQuery("ErrorEvent.findEventIdAndCauseCodeByIMSI").setParameter("imsi", imsi).getResultList();
		
		for(Object[] result : results)
			System.out.println("EventID :" + result[0] + ", Cause Code: " + result[1]);
	}
	
	public static void findEventIdAndCauseCodeByIMSIGroupByEventId(long imsi) {
		System.out.println("\nRetrieving Event ID and Cause Code for IMSI " + imsi + ", grouped by Event ID...");
		EntityManager em = emf.createEntityManager();
		List<Integer[]> results = (List<Integer[]>) em.createNamedQuery("ErrorEvent.findEventIdAndCauseCodeByIMSIGroupByEventId").setParameter("imsi", imsi).getResultList();
		
		for(Object[] result : results)
			System.out.println("EventID :" + result[0] + ", Cause Code: " + result[1]);
	}
	
	public static void findEventIdAndCauseCodeByIMSIGroupByCauseCode(long imsi) {
		System.out.println("\nRetrieving Event ID and Cause Code for IMSI " + imsi + ", grouped by Cause Code...");
		EntityManager em = emf.createEntityManager();
		List<Integer[]> results = (List<Integer[]>) em.createNamedQuery("ErrorEvent.findEventIdAndCauseCodeByIMSIGroupByCauseCode").setParameter("imsi", imsi).getResultList();
		
		for(Object[] result : results)
			System.out.println("Cause Code: " + result[1] + ", Event ID: " + result[0]);
	}
	
	public static void findCauseCodeAndCountry(){
		System.out.println("\nRetrieving all Cause Codes and Countries...");
		EntityManager em = emf.createEntityManager();
		List<Object[]> results = (List<Object[]>) em.createNamedQuery("ErrorEvent.findCauseCodeAndCountry").getResultList();
		em.close();
		
		for(Object[] result : results)
			System.out.println("Cause Code: " + result[0] + ", Country: " + result[1]);
	}
	
	public static void findCauseCodeAndCountryGroupByCountry(){
		System.out.println("\nRetrieving all Cause Codes and Countries, grouped by Country...");
		EntityManager em = emf.createEntityManager();
		List<Object[]> results = (List<Object[]>) em.createNamedQuery("ErrorEvent.findCauseCodeAndCountryGroupByCountry").getResultList();
		em.close();
		
		for(Object[] result : results)
			System.out.println("Country: " + result[1] + ", Cause Code: " + result[0]);
	}
}
