package ru.home.testapp.ws.rest;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.home.testapp.db.model.Question;
import ru.home.testapp.db.model.Restaraunt;
import ru.home.testapp.exception.VoteSystemException;
import ru.home.testapp.service.QuestionService;
import ru.home.testapp.service.RestarauntService;
import ru.home.testapp.service.UserService;
import ru.home.testapp.service.VoteService;
//import ru.home.testapp.db.model.WebTransaction;
import ru.home.testapp.ws.json.RestarauntInfo;
import ru.home.testapp.ws.json.VoteInfo;

//TODO implement methods for adding multiple restaurants per POST with JSON array in the body

@Path(value = "restaurant")
@Component
@Scope("request")
public class RestaurantController {

	@Autowired
	private RestarauntService restarauntService;
	@Autowired
	private UserService userService;

	private Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * add new lunch info for one particular restaurant
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param rInfo
	 *            RestarauntInfo JSON, example: { "userId": "1", "restarauntId":
	 *            "15", "dishes": [ { "name": "steak", "price": "5.55" }, {
	 *            "name": "ratatois", "price": "1.45" } ] }
	 * @return 400 BAD_REQUEST - if userId or restarauntId is null or if user or
	 *         restaraunt with such ids not exists or if user with userId has no
	 *         admin rights
	 *         <p>
	 *         200 OK success - if request was successfully processed
	 */
	@POST
	@Path("/newlunch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newLunchInfo(@Context HttpServletRequest request, RestarauntInfo rInfo) {
		log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(),
				request.getRequestURL().append("?").append(request.getQueryString()));
		log.info("Consumed new RestarauntInfo :" + rInfo.toString());

		if (!userService.isUserAdmin(rInfo.getUserId())) {
			return Response.status(Status.BAD_REQUEST)
					.entity("Not enough privileges for user with id = " + rInfo.getUserId()).build();
		}

		if (rInfo.getRestarauntId() == null) {
			return Response.status(Status.BAD_REQUEST).entity("restarauntId must not be null!").build();
		}
		if (rInfo.getDishes() == null || rInfo.getDishes().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity("dishes list must not be null!").build();
		}

		Restaraunt restaraunt;
		try {
			restaraunt = restarauntService.addLunchInfo(rInfo.getRestarauntId(), rInfo.getDishes());
		} catch (VoteSystemException ve) {
			log.severe(ve.getDescr());
			return Response.status(ve.getHttpCode()).entity(ve.getDescr()).build();
		}

		return Response.status(200).entity(/* restaraunt */ "success").build();

	}

	/**
	 * get list of available restaurants with their menu
	 * 
	 * @param request
	 * @return 200 OK and JSON list in response body: [{ "id": 17, "address":
	 *         "another stupid address", "phone": "911", "name":
	 *         "test json cafe", "dishes": [ { "id": 9, "name": "burger",
	 *         "price": 2, "active": false }, { "id": 11, "name": "pancake",
	 *         "price": 4, "active": false } ] }, { "id": 19, "address":
	 *         "another stupid address", "phone": "911", "name":
	 *         "test json cafe", "dishes": [ { "id": 21, "name": "steak",
	 *         "price": 5.55, "active": false }, { "id": 23, "name": "ratatois",
	 *         "price": 1.45, "active": false } ] } ]
	 */
	@POST
	@Path("/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRestaraunts(@Context HttpServletRequest request) {

		log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(),
				request.getRequestURL().append("?").append(request.getQueryString()));

		List<Restaraunt> restaraunts = restarauntService.getRestaraunts();

		return Response.ok().entity(restaraunts).build();
	}

	/**
	 * Add new restaurant
	 * 
	 * @param request
	 * @param rInfo
	 *            RestarauntInfo JSON, example: { "userId": "1", "restaraunt":{
	 *            "address":"some new address", "name":"funkyJava", "dishes": [
	 *            { "name": "steak", "price": "5.55" }, { "name": "ratatois",
	 *            "price": "1.45" } ]
	 * 
	 *            } }
	 * @return 400 BAD_REQUEST - if userId or restaraunt is null
	 *         <p>
	 *         400 BAD_REQUEST - restaurant fields 'name', 'address' cannot be
	 *         null
	 *         <p>
	 *         403 FORBIDDEN - if user with userId has no admin rights
	 *         <p>
	 *         200 OK success - if request was successfully processed
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRestaraunt(@Context HttpServletRequest request, RestarauntInfo rInfo) {
		log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(),
				request.getRequestURL().append("?").append(request.getQueryString()));
		log.info("Consumed new RestarauntInfo :" + rInfo.toString());
		if (rInfo.getUserId() == null) {
			return Response.status(Status.BAD_REQUEST).entity("userId must not be null").build();
		}
		if (!userService.isUserAdmin(rInfo.getUserId())) {
			return Response.status(Status.FORBIDDEN)
					.entity("Not enough privileges for user with id = " + rInfo.getUserId()).build();
		}
		if (rInfo.getRestaraunt() == null) {
			return Response.status(Status.BAD_REQUEST).entity("Restaraunt must not be null").build();
		}

		try {
			Restaraunt restaraunt = restarauntService.addRestaraunt(rInfo.getRestaraunt());
			return Response.ok().entity(restaraunt).build();
		} catch (VoteSystemException ve) {
			log.severe(ve.getDescr());
			return Response.status(ve.getHttpCode()).entity(ve.getDescr()).build();
		}

	}

}
