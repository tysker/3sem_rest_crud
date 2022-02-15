package dk.tysker.getrequests;

import dk.tysker.getrequests.entity.Customer;
import dk.tysker.getrequests.facade.CustomerFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/customer")
public class HelloResource {

    CustomerFacade facade = new CustomerFacade();

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public String hello(@PathParam("id") long id) {
        Customer c = facade.getCustomerById(id);
        return String.format("Firstname: %s, Lastname: %s", c.getFirstName(), c.getLastName());
    }
}

