package pl.take.clinic.rest;

import pl.take.clinic.model.Diagnosis;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

public interface DiagnosisRestModel {
    @GET
    @Path("")
    List<Diagnosis> getAll();

    @GET
    @Path("/{id}")
    Diagnosis getById (long id);

    @POST
    @Path("/")
    Response create(String note, Long diseaseId, Long visitId);

    @PUT
    @Path("/{id}")
    Response update(Long id, String note, Long diseaseId, Long visitId);
}
