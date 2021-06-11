package pl.take.clinic.rest;

import pl.take.clinic.model.Visit;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

public interface PersonInformation<T> {
    @POST
    String create(T person);

    @GET
    @Path("/{id}")
    T find(long id);

    @GET
    @Path("/{id}/visits")
    List<Visit> getVisits(long id);

    @GET
    List<T> get(String additionalInfo, String firstName, String lastName);

    @PUT
    String update(T person);
}
