package pl.take.clinic.rest;

import pl.take.clinic.ejb.PatientEJB;
import pl.take.clinic.model.Patient;
import pl.take.clinic.model.Visit;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/patients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatientREST {
    @EJB
    PatientEJB bean;

    @POST
    public String create(Patient patient) {
        bean.create(patient);
        return "patient created!";
    }

    @GET
    @Path("/{id}")
    public Patient find(@PathParam("id") long id) {
        return bean.find(id);
    }

    @GET
    @Path("/{id}/visits")
    public List<Visit> getVisits(@PathParam("id") long id) {
        return bean.getVisits(id);
    }

    @GET
    public List<Patient> get(@QueryParam("pesel") String pesel,
                             @QueryParam("first_name") String firstName,
                             @QueryParam("last_name") String lastName) {
        System.err.println(pesel + " " + firstName + " " + lastName);
        return bean.get(pesel, firstName, lastName);
    }

    @PUT
    public String update(Patient patient) {
        try {
            bean.update(patient);
            return "patient updated";
        } catch (Exception e) {
            e.printStackTrace();
            return "patient not updated";
        }
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        bean.delete(id);
    }
}
