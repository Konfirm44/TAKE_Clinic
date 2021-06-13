package pl.take.clinic.rest;

import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Diagnosis;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
    CreationStatus createPersist(Diagnosis diagnosis);

    @POST
    @Path("/create")
    CreationStatus create(String note, Long diseaseId, Long visitId);

    @PUT
    @Path("/")
    CreationStatus updateMerge(Diagnosis diagnosis);

    @PUT
    @Path("/update/{id}")
    CreationStatus update(Long id, String note, Long diseaseId, Long visitId);
}
