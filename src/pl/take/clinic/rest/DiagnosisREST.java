package pl.take.clinic.rest;

import pl.take.clinic.ejb.DiagnosisEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Diagnosis;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/diagnosis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DiagnosisREST implements DiagnosisRestModel {
    @EJB
    DiagnosisEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Diagnosis> getAll() {
        return bean.getAll();
    }

    @Override
    @GET
    @Path("/{id}")
    public Diagnosis getById(@PathParam("id") long id) {
        return bean.getById(id);
    }

    @Override
    @POST
    @Path("/")
    public Response create(@QueryParam("note") String note, @QueryParam("diseaseId") Long diseaseId, @QueryParam("visitId") Long visitId) {
        CreationStatus response = bean.create(note, diseaseId, visitId);

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
    public Response update(@PathParam("id") Long id, @QueryParam("note") String note, @QueryParam("diseaseId") Long diseaseId, @QueryParam("visitId") Long visitId) {
        CreationStatus response = bean.update(id, note, diseaseId, visitId);

        switch (response) {
            case Success:
                String json = " { \"status\": \"" + response + "\" } ";
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            default:
                return Response.serverError().entity("Received status: " + response).build();
        }
    }
}
