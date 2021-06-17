package pl.take.clinic.rest;


import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Doctor;
import pl.take.clinic.model.Visit;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

public interface DoctorRestModel {
    @GET
    @Path("/")
    List<Doctor> get(String additionalInfo, String firstName, String lastName);

    @GET
    @Path("/{id}")
    Doctor getById(Long id);

    @GET
    @Path("/{id}/visits")
    List<Visit> getVisits(Long id);

    @POST
    @Path("/object")
    CreationStatus createPersist(Doctor person);

    @POST
    @Path("/")
    CreationStatus create(String firstName, String lastName, String speciality);

    @PUT
    @Path("/")
    CreationStatus updateMerge(Doctor person);

    @PUT
    @Path("/{id}")
    CreationStatus update(Long id, String firstName, String lastName, String speciality);
}
