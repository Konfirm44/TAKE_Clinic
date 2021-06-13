package pl.take.clinic.rest;

import pl.take.clinic.ejb.DiseaseEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Disease;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/diseases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DiseaseREST implements DiseaseRestModel {
    @EJB
    DiseaseEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Disease> getAll() {
        return bean.getAll();
    }

    @Override
    @GET
    @Path("/{id}")
    public Disease getById(@PathParam("id") Integer id) {
        return bean.getById(id);
    }

    @Override
    @POST
    @Path("/")
    public CreationStatus createPersist(Disease disease) {
        return bean.createPersist(disease);
    }

    @Override
    @POST
    @Path("/create")
    public CreationStatus create(@QueryParam("contagious") String contagious, @QueryParam("name") String name) {
        return bean.create(contagious, name);
    }

    @Override
    @PUT
    @Path("/")
    public CreationStatus updateMerge(Disease disease) {
        return bean.updateMerge(disease);
    }

    @Override
    @PUT
    @Path("/update/{id}")
    public CreationStatus update(@PathParam("id") Integer id, @QueryParam("contagious") String contagious, @QueryParam("name") String name) {
        return bean.update(id, contagious, name);
    }
}
