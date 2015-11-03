package ru.home.testapp.ws.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import ru.home.testapp.db.dao.WebTransactionDAOImpl;
import ru.home.testapp.db.model.WebTransaction;

@Path(value = "test")
@Component
public class GreetingController {
	
	@Autowired
	private WebTransactionDAOImpl transactionDAO;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@GET
    @Path("/transaction")
    public Response greeting(@Context HttpServletRequest request,
    		@QueryParam(value = "transid") String transid) {
    	
    	log.debug("New request URL : {} from HOST: {} ", request.getRequestURL().append("?").append(request.getQueryString()), request.getRemoteHost());
    	System.out.println("TransactionDAO is:" + transactionDAO);
    	WebTransaction trans = transactionDAO.find(transid);
    	
        return Response.status(Status.OK).entity(trans.getSubscriberId()).build();
    }
	
	@GET
	@Path("/mkyong")
	public Response savePayment() {


		return Response.status(200).entity("mkyong").build();

	}
}
