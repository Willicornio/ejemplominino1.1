package edu.upc.eetac.dsa.services;
import edu.upc.eetac.dsa.MyBike;
import edu.upc.eetac.dsa.*;

import edu.upc.eetac.dsa.MyBikeImpl;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;

import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;

import javax.ws.rs.core.GenericEntity;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Api(value = "/bikes", description = "Endpoint to Bike Service")

@Path("/bikes")

public class MyBikeService {

    final static Logger log = Logger.getLogger(MyBikeService.class.getName());
private MyBike mb;

    public MyBikeService(){

        this.mb = MyBikeImpl.getInstance();



    }

    @GET
    @ApiOperation(value = "Obetener las bivis de usuario")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "exito", response = Bike.class, responseContainer = "Lista de bicis"),
            @ApiResponse(code = 401, message = "Usuario no econtrado")
    })
    @Path("/{User}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByUser(@PathParam("user") String user) throws Exception{
        List<Bike> listabicis = new ArrayList<>();
        try {
            listabicis = this.mb.bikesByUser(user);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(listabicis){};
            //return Response.status(201) entity(numerobicis).build(); --> en caso de que te pidan un int, no se pone lo de antes y solo se pone eso.
            return Response.status(201).entity(entity).build();
            }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(404).build();


        }


    }


    @POST
    @ApiOperation(value = "Añadir Usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "exito")
    })
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser ( User u){
        mb.addUser(u.getNombre(), u.getSurname(), u.getIdUser());
        return Response.status(201).build();

    }

    @POST
    @ApiOperation(value = "añadir bicis", notes ="x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "exito"),
            @ApiResponse(code = 401, message = "notfound estacion"),
            @ApiResponse(code = 402, message = "full estacion")

    })
    @Path("/addbici")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBike( Bike bike) throws Exception{
        try{
        mb.addBike(bike.getBikeId(), bike.getDescription(), bike.getKms(), bike.getIdStation());
        return Response.status(201).build();
    }
        catch (StationFullException e){
            e.printStackTrace();
            return Response.status(402).build();
        }
        catch (StationNotFoundException e){
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @POST
    @ApiOperation(value= "Añadir estacion", notes ="x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ok")
})
    @Path("/addStation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(Station p){
        this.mb.addStation(p.getIdStation(), p.getDescription(), p.getMax(), p.getLat(), p.getLon());
        return Response.status(201).build();
    }



}
