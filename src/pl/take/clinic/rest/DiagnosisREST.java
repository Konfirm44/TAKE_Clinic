package pl.take.clinic.rest;

import pl.take.clinic.ejb.DiagnosisEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Diagnosis;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.awt.*;
import javax.ws.rs.core.MediaType;
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
    @Path("/create")
    public CreationStatus create(@QueryParam("note") String note, @QueryParam("diseaseId") Long diseaseId, @QueryParam("visitId") Long visitId) {
        return bean.create(note, diseaseId, visitId);
    }

    @Override
    @PUT
    @Path("/update")
    public CreationStatus update(@QueryParam("id") Long id, @QueryParam("note") String note, @QueryParam("diseaseId") Long diseaseId, @QueryParam("visitId") Long visitId) {
        return bean.update(id, note, diseaseId, visitId);
    }
}
