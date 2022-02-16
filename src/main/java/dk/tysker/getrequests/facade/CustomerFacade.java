package dk.tysker.getrequests.facade;

import dk.tysker.getrequests.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public Customer getCustomerById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    public long addCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer.getId();
        } finally {
            em.close();
        }
    }

    public Customer deleteCustomerById(long customerId) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class, customerId);
            em.getTransaction().begin();

            if(c != null){
                em.remove(c);
                em.getTransaction().commit();
            } else {
                return null;
            }
            return c;
        } finally {
            em.close();
            emf.close();
        }

    }

    public Customer updateCustomer(Customer customer, long customerId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Customer c = em.find(Customer.class, customerId);
            c.setFirstName(customer.getFirstName());
            c.setLastName(customer.getLastName());
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Customer> getAllCustomer() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery query = em.createQuery("select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
}
