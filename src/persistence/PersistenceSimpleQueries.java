package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.ErrorEvent;
import entity.EventCause;
import entity.FailureClass;
import entity.InvalidErrorEvent;
import entity.MCC_MNC;
import entity.UEType;

@SuppressWarnings({ "unchecked", "serial" })
public class PersistenceSimpleQueries  implements Serializable{
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dt340a");
	
	public static List<ErrorEvent> findAllErrorEvents(){
		System.out.println("\nRetreiving all ErrorEvents...");
		EntityManager em = emf.createEntityManager();
		
		List<ErrorEvent> errorEvents = (List<ErrorEvent>) em.createNamedQuery("ErrorEvent.findAll").getResultList();
		em.close();
		
		return errorEvents;
	}

	public static ErrorEvent findErrorEventByEventId(int eventId){
		System.out.println("\nRetrieving ErrorEvent with Event ID " + eventId + "...");
		EntityManager em = emf.createEntityManager();
		List<ErrorEvent> errorEvents = (List<ErrorEvent>) em.createNamedQuery("ErrorEvent.findErrorEventByEventId").setParameter("eventId", eventId).getResultList();
		em.close();
		
		if (errorEvents.isEmpty())
			return new ErrorEvent();
		return errorEvents.get(0);
	}

	public static List<EventCause> findAllEventCauses() {
		System.out.println("\nRetrieving all Event-Causes...");
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findAll").getResultList();
		em.close();
		
		return eventCauses;
	}
	
	public static EventCause findEventCauseByEventID(int eventId){
		System.out.println("\nRetrieving Event-Cause with Event ID " + eventId + "...");
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findByEventId").setParameter("eventId", eventId).getResultList();
		em.close();
		
		if(eventCauses.isEmpty())
			return new EventCause();
		return eventCauses.get(0);
		
	}
	
	public static EventCause findEventCauseByCauseCode(int causeCode){
		System.out.println("\nRetrieving Event-Cause with Cause Code " + causeCode + "...");
		
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findByCauseCode").setParameter("causeCode", causeCode).getResultList();
		em.close();
		
		if(eventCauses.isEmpty())
			return new EventCause();
		return eventCauses.get(0);
	}

	public static List<FailureClass> findAllFailureClasses() {
		System.out.println("\nRetrieving all Failure Classes...");
		EntityManager em = emf.createEntityManager();
		List<FailureClass> failureClasses = (List<FailureClass>) em.createNamedQuery("FailureClass.findAll").getResultList();
		em.close();
		
		return failureClasses;
	}

	public static FailureClass findFailureClassByFailureClass(int failureClass){
		System.out.println("\nRetrieving Failure Class with ID " + failureClass + "...");
		
		EntityManager em = emf.createEntityManager();
		List<FailureClass> failureClasses = (List<FailureClass>) em.createNamedQuery("FailureClass.findFailureClassByFailureClass").setParameter("failureClass", failureClass).getResultList();
		em.close();
		
		if(failureClasses.isEmpty())
			return new FailureClass();
		return failureClasses.get(0);
		
	}

	public static List<MCC_MNC> findAllMCC_MNCs() {
		System.out.println("\nRetrieving all MCC_MNCs...");
		EntityManager em = emf.createEntityManager();
		List<MCC_MNC> mcc_mncs = (List<MCC_MNC>) em.createNamedQuery("MCC_MNC.findAll").getResultList();
		em.close();
		
		return mcc_mncs;
	}
	
	public static MCC_MNC findMCC_MNCByMCC(int mcc){
		System.out.println("\nRetrieving MCC_MNC with MCC " + mcc + "...");
		EntityManager em = emf.createEntityManager();
		List<MCC_MNC> mcc_mncs = (List<MCC_MNC>) em.createNamedQuery("MCC_MNC.findbyMCC").setParameter("mcc", mcc).getResultList();
		em.close();
		
		if(mcc_mncs.isEmpty())
			return new MCC_MNC();
		return mcc_mncs.get(0);
	}
	
	public static MCC_MNC findMCC_MNCByMNC(int mnc){
		System.out.println("\nRetrieving MCC_MNC with MNC " + mnc + "...");
		EntityManager em = emf.createEntityManager();
		List<MCC_MNC> mcc_mncs = (List<MCC_MNC>) em.createNamedQuery("MCC_MNC.findbyMNC").setParameter("mnc", mnc).getResultList();
		em.close();
		
		if(mcc_mncs.isEmpty())
			return new MCC_MNC();
		return mcc_mncs.get(0);
	}
	
	public static List<UEType> findAllUETypes() {
		System.out.println("\nRetrieving all UE Types...");
		EntityManager em = emf.createEntityManager();
		List<UEType> ueTypes = (List<UEType>) em.createNamedQuery("UEType.findAll").getResultList();
		em.close();
		
		return ueTypes;
	}
	
	public static UEType findUETypeByTAC(int tac){
		System.out.println("\nRetrieving UE Type with TAC " + tac + "...");
		EntityManager em = emf.createEntityManager();
		List<UEType> ueTypes = (List<UEType>) em.createNamedQuery("UEType.findUETypeByTAC").setParameter("tac", tac).getResultList();
		em.close();
		
		if(ueTypes.isEmpty())
			return new UEType();
		return ueTypes.get(0);
	}
	
	public static List<InvalidErrorEvent> findAllInvalidErrorEvents(){
		System.out.println("\nRetrieving all InvalidErrorEvents...");
		EntityManager em = emf.createEntityManager();
		
		List<InvalidErrorEvent> invalidErrorEvents = (List<InvalidErrorEvent>) em.createNamedQuery("InvalidErrorEvent.findAll").getResultList();
		em.close();
		
		return invalidErrorEvents;
	}

	public static InvalidErrorEvent findInvalidErrorEventByEventId(int eventId){
		System.out.println("\nRetrieving InvalidErrorEvent with Event ID " + eventId + "...");
		EntityManager em = emf.createEntityManager();
		List<InvalidErrorEvent> invalidErrorEvents = (List<InvalidErrorEvent>) em.createNamedQuery("InvalidErrorEvent.findInvalidErrorEventByEventId").setParameter("eventId", eventId).getResultList();
		em.close();
		
		if (invalidErrorEvents.isEmpty())
			return new InvalidErrorEvent();
		return invalidErrorEvents.get(0);
	}
}
