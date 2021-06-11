package pl.take.clinic.rest;

import pl.take.clinic.ejb.DoctorEJB;
import pl.take.clinic.model.Doctor;
import pl.take.clinic.model.Visit;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/doctors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorREST implements PersonInformation<Doctor> {
    @EJB
    DoctorEJB bean;

    @Override
    @POST
    public String create(Doctor doctor) {
        bean.create(doctor);
        return "doctor created!";
    }

    @Override
    @GET
    @Path("/{id}")
    public Doctor find(@PathParam("id") long id) {
        return bean.find(id);
    }

    @Override
    @GET
    @Path("/{id}/visits")
    public List<Visit> getVisits(@PathParam("id") long id) {
        return bean.getVisits(id);
    }

    @Override
    @GET
    public List<Doctor> get(@QueryParam("speciality") String speciality,
                            @QueryParam("first_name") String firstName,
                            @QueryParam("last_name") String lastName) {
        System.err.println(speciality + " " + firstName + " " + lastName);
        return bean.get(speciality, firstName, lastName);
    }

    @Override
    @PUT
    public String update(Doctor doctor) {
        try {
            bean.update(doctor);
            return "doctor updated";
        } catch (Exception e) {
            e.printStackTrace();
            return "doctor not updated";
        }
    }
}
