package dk.tysker.getrequests.facade;

import dk.tysker.getrequests.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public Customer getCustomerById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }
}
