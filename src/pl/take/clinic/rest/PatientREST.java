package pl.take.clinic.rest;

import pl.take.clinic.ejb.PatientEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Patient;
import pl.take.clinic.model.Visit;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/patients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatientREST implements PatientRestModel {
    @EJB
    PatientEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Patient> get(@QueryParam("pesel") String pesel,
                             @QueryParam("first_name") String firstName,
                             @QueryParam("last_name") String lastName) {
        System.err.println(pesel + " " + firstName + " " + lastName);
        return bean.get(pesel, firstName, lastName);
    }

    @Override
    @GET
    @Path("/{id}")
    public Patient getById(@PathParam("id") Long id) {
        return bean.find(id);
    }

    @Override
    @GET
    @Path("/{id}/visits")
    public List<Visit> getVisits(@PathParam("id") Long id) {
        return bean.getVisits(id);
    }


    @Override
    @POST
    @Path("/object")
    public CreationStatus createPersist(Patient patient) {
        try {
            bean.createPersist(patient);
            return CreationStatus.Success;
        } catch (Exception e) {
            return CreationStatus.Failed;
        }
    }

    @Override
    @POST
    @Path("/")
    public CreationStatus create(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("pesel") String pesel
    ) {
        return bean.create(firstName, lastName, pesel);
    }

    @Override
    @PUT
    @Path("/")
    public CreationStatus updateMerge(Patient patient) {
        try {
            bean.updateMerge(patient);
            return CreationStatus.Success;
        } catch (Exception e) {
            return CreationStatus.Failed;
        }
    }

    @Override
    @PUT
    @Path("/{id}")
    public CreationStatus update(
            @PathParam("id") Long id,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("pesel") String pesel
    ) {
        return bean.update(id, firstName, lastName, pesel);
    }
}
