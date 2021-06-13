package pl.take.clinic.rest;

import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Disease;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

public interface DiseaseRestModel {
    @GET
    @Path("")
    List<Disease> getAll();

    @GET
    @Path("/{id}")
    Disease getById (Integer id);

    @POST
    @Path("/")
    CreationStatus createPersist(Disease disease);

    @POST
    @Path("/create")
    CreationStatus create(String contagious, String name);

    @PUT
    @Path("/")
    CreationStatus updateMerge(Disease disease);

    @PUT
    @Path("/update/{id}")
    CreationStatus update(Integer id, String contagious, String name);
}
