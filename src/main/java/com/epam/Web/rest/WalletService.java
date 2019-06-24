package com.epam.Web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/walletREST")
public interface WalletService {

  @GET
  @Path("/amount")
  @Produces("application/json; charset=UTF-8")
  Response getAvailableAmount();

  @GET
  @Path("/withdraw/{x}")
  @Produces("application/json; charset=UTF-8")
  Response makeWithdraw(@PathParam("x") String withdrawAmount);

  @GET
  @Path("/income/{x}")
  @Produces("application/json; charset=UTF-8")
  Response makeIncome(@PathParam("x") String incomeAmount);

  @GET
  @Path("/return")
  @Produces("application/json; charset=UTF-8")
  Response makeReturn();

}
