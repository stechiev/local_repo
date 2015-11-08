package ru.home.testapp.ws.rest;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.home.testapp.db.model.DailyStat;
import ru.home.testapp.db.model.Question;
import ru.home.testapp.exception.VoteSystemException;
import ru.home.testapp.service.QuestionService;
import ru.home.testapp.service.VoteService;
import ru.home.testapp.util.DateUtils;
import ru.home.testapp.ws.json.VoteInfo;

@Path(value = "voting")
@Component
@Scope("request")
public class VoteController {

	@Autowired
	private VoteService voteService;
	@Autowired
	private QuestionService questionService;

	private Logger log = Logger.getLogger(this.getClass().getName());

	public VoteController() {
	}

	/**
	 * @param request
	 *            HttpServletRequest
	 * @param vInfo
	 *            VoteInfo JSON { "userId":"intValue",
	 *            "restarauntId":"intValue"}
	 * @return 400 BAD_REQUEST - if userId or restarauntId is null or if user or
	 *         restaraunt with such ids not exists
	 *         <p>
	 *         403 FORBIDDEN - if vote was sended after 11:00
	 *         <p>
	 *         200 OK "accepted" if vote was successfully processed
	 */
	@POST
	@Path("/vote")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response vote(@Context HttpServletRequest request, VoteInfo vInfo) {
		Date requestDate = new Date();
		if (vInfo.getUserId() == null) {
			return Response.status(Status.BAD_REQUEST).entity("userId must not be null").build();
		}
		if (vInfo.getRestarauntId() == null) {
			return Response.status(Status.BAD_REQUEST).entity("restarauntId must not be null").build();
		}
		if (requestDate.after(DateUtils.getCurrentDateLimit())) {
			return Response.status(Status.FORBIDDEN).entity("Too late to vote; try next day from 00:00 to 11:00")
					.build();
		}
		Question question = questionService.getCurrentQuestion();

		try {
			voteService.addVote(vInfo, question);
		} catch (VoteSystemException ve) {
			log.severe(ve.getDescr());
			return Response.status(ve.getHttpCode()).entity(ve.getDescr()).build();

		}

		return Response.ok("accepted").build();
	}

	/**
	 * Getting voting statistics for current day
	 * 
	 * @param request
	 * @return 200 ok wit JSON body like :[ { "restarauntId": 15, "votesCount":
	 *         3 }, { "restarauntId": 19, "votesCount": 1 } ]
	 *         <p>
	 *         200 empty - if statistics is empty
	 */
	@GET
	@Path("/votestat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response currentVoteStat(@Context HttpServletRequest request) {

		log.log(Level.INFO, "New request URL : {0} from HOST: " + request.getRemoteHost(),
				request.getRequestURL().append("?").append(request.getQueryString()));
		List<DailyStat> statList = voteService.getDailyStat();
		if (statList.isEmpty()) {
			return Response.status(Status.OK).entity("empty").build();
		}
		return Response.status(Status.OK).entity(statList).build();
	}

}
