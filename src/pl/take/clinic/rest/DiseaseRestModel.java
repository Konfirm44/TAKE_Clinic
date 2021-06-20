package pl.take.clinic.rest;

import pl.take.clinic.model.Disease;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
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
    Response create(String contagious, String name);

    @PUT
    @Path("/{id}")
    Response update(Integer id, String contagious, String name);
}
