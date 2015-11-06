package ru.home.testapp.ws.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.home.testapp.db.model.Restaraunt;
import ru.home.testapp.service.RestarauntService;
import ru.home.testapp.service.UserService;
//import ru.home.testapp.db.model.WebTransaction;
import ru.home.testapp.ws.json.RestarauntInfo;
import ru.home.testapp.ws.json.VoteInfo;

@Path(value = "test")
@Component
@Scope("session")
public class GreetingController {
	
	@Autowired
	private RestarauntService restarauntService;
	@Autowired
	private UserService userService;
	
	//private Logger log = LoggerFactory.getLogger(this.getClass());
	private Logger log = Logger.getLogger(this.getClass().getName());

	
	@POST
	@Path("/newlunch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newLunchInfo(@Context HttpServletRequest request, RestarauntInfo rInfo) {
		log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(), request.getRequestURL().append("?").append(request.getQueryString()));
		log.info("Consumed new RestarauntInfo :" + rInfo.toString());
		
		if(!userService.isUserAdmin(rInfo.getUserId())){
    		return Response.status(Status.BAD_REQUEST)
    				.entity("Not enough privileges for user with id = " + rInfo.getUserId()).build();
    	}
		
		if(rInfo.getRestarauntId()==null){
			return Response.status(Status.BAD_REQUEST).entity("restarauntId must not be null!").build();
		}
		if(rInfo.getDishes()==null || rInfo.getDishes().isEmpty()){
			return Response.status(Status.BAD_REQUEST).entity("dishes list must not be null!").build();
		}
		
		Restaraunt restaraunt = restarauntService
				.addLunchInfo(rInfo.getRestarauntId(), rInfo.getDishes());

		return Response.status(200).entity(restaraunt).build();

	}
	
	@POST
    @Path("/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response listRestaraunts(@Context HttpServletRequest request) {
    	
    	log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(), request.getRequestURL().append("?").append(request.getQueryString()));
    	
    	List<Restaraunt> restaraunts = restarauntService.getRestaraunts();
    	
        return Response.ok().entity(restaraunts).build();
    }
	
	@POST
    @Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response addRestaraunt(@Context HttpServletRequest request,
    		RestarauntInfo rInfo) {    	
    	log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(), request.getRequestURL().append("?").append(request.getQueryString()));
    	log.info("Consumed new RestarauntInfo :" + rInfo.toString());
    	if(rInfo.getUserId()==null){
    		return Response.status(Status.BAD_REQUEST).entity("userId must not be null").build();
    	}
    	if(!userService.isUserAdmin(rInfo.getUserId())){
    		return Response.status(Status.FORBIDDEN)
    				.entity("Not enough privileges for user with id = " + rInfo.getUserId()).build();
    	}
    	if(rInfo.getRestaraunt()==null){
    		return Response.status(Status.BAD_REQUEST).entity("Restaraunt must not be null").build();
    	}
    	
    	Restaraunt restaraunt = restarauntService
    			.addRestaraunt(rInfo.getRestaraunt());
    	return Response.ok().entity(restaraunt).build();
    } 
	
	
	@POST
    @Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response vote(@Context HttpServletRequest request,
    		VoteInfo vInfo){
		if(vInfo.getUserId()==null){
    		return Response.status(Status.BAD_REQUEST).entity("userId must not be null").build();
    	}
		if(vInfo.getRestarauntId()==null){
    		return Response.status(Status.BAD_REQUEST).entity("restarauntId must not be null").build();
    	}
		
		
		
		return Response.ok().build();
	}	
	
}


