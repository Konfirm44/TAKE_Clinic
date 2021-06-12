package pl.take.clinic.rest;

import pl.take.clinic.ejb.VisitsEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Visit;
import pl.take.clinic.model.VisitStatus;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/visits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitREST implements VisitRestModel {
    @EJB
    VisitsEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Visit> getAll() {
        return bean.getAll();
    }

    @Override
    @GET
    @Path("/{id}")
    public Visit getById(@PathParam("id") Long id) {
        return bean.getById(id);
    }

    @Override
    @POST
    @Path("/")
    public CreationStatus createPersist(Visit visit) {
        return bean.createPersist(visit);
    }

    @Override
    @POST
    @Path("/create")
    public CreationStatus create(
            @QueryParam("note") String note,
            @QueryParam("status") VisitStatus status,
            @QueryParam("doctorId") Long doctorId,
            @QueryParam("patientId") Long patientId
    ) {
        return bean.create(note, status, doctorId, patientId);
    }

    @Override
    @PUT
    @Path("/")
    public CreationStatus updateMerge(Visit visit) {
        return bean.updateMerge(visit);
    }

    @Override
    @PUT
    @Path("/update")
    public CreationStatus update(
            @QueryParam("id") Long id,
            @QueryParam("note") String note,
            @QueryParam("status") VisitStatus status,
            @QueryParam("doctorId") Long doctorId,
            @QueryParam("patientId") Long patientId
    ) {
        return bean.update(id, note, status, doctorId, patientId);
    }
}
