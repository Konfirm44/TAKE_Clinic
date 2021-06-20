package pl.take.clinic.rest;


import pl.take.clinic.model.Patient;
import pl.take.clinic.model.Visit;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

public interface PatientRestModel {
    @GET
    @Path("/")
    List<Patient> get(String additionalInfo, String firstName, String lastName);

    @GET
    @Path("/{id}")
    Patient getById(Long id);

    @GET
    @Path("/{id}/visits")
    List<Visit> getVisits(Long id);

    @POST
    @Path("/")
    Response create(String firstName, String lastName, String speciality);

    @PUT
    @Path("/{id}")
    Response update(Long id, String firstName, String lastName, String speciality);
}
