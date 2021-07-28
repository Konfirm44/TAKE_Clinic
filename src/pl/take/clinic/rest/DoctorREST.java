package pl.take.clinic.rest;

import pl.take.clinic.ejb.DoctorEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Doctor;
import pl.take.clinic.model.Visit;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/doctors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorREST implements DoctorRestModel {
    @EJB
    DoctorEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Doctor> get(
            @QueryParam("speciality") String speciality,
            @QueryParam("first_name") String firstName,
            @QueryParam("last_name") String lastName
    ) {
        return bean.get(speciality, firstName, lastName);
    }

    @Override
    @GET
    @Path("/{id}")
    public Doctor getById(@PathParam("id") Long id) {
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
    @Path("/")
    public Response create(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("speciality") String speciality) {
        CreationStatus response = bean.create(firstName, lastName, speciality);

        switch (response) {
            case Success:
                String json = " { \"status\": \"" + response + "\" } ";
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            default:
                return Response.serverError().entity("Received status: " + response).build();
        }
    }

    @Override
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("speciality") String speciality) {
        CreationStatus response = bean.update(id, firstName, lastName, speciality);

        switch (response) {
            case Success:
                String json = " { \"status\": \"" + response + "\" } ";
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            default:
                return Response.serverError().entity("Received status: " + response).build();
        }
    }
}
