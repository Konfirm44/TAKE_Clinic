package pl.take.clinic.rest;

import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Visit;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

public interface VisitRestModel {
    @GET
    @Path("")
    List<Visit> getAll();

    @GET
    @Path("/{id}")
    Visit getById (Long id);

    @POST
    @Path("/")
    Response create(String note, Integer status, Long doctorId, Long patientId);

    @PUT
    @Path("/{id}")
    Response update(Long id, String note, Integer status, Long doctorId, Long patientId);

    @PUT
    @Path("/{id}/status")
    Response updateStatus(Long id, Integer status);
}
