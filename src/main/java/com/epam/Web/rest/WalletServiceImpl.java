package com.epam.Web.rest;

import com.epam.BO.WalletBO;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

public class WalletServiceImpl implements WalletService {

  private Logger LOGGER = Logger.getLogger(WalletServiceImpl.class);

  @Override
  public Response getAvailableAmount() {
    WalletBO walletBO = new WalletBO();
    return Response.ok().entity(walletBO.getAvailableAmount()).build();
  }

  @Override
  public Response makeWithdraw(String withdrawAmount) {
    WalletBO walletBO = new WalletBO();
    return Response.ok().entity(walletBO.makeWithdraw(withdrawAmount)).build();
  }

  @Override
  public Response makeIncome(String incomeAmount) {
    WalletBO walletBO = new WalletBO();
    return Response.ok().entity(walletBO.makeIncome(incomeAmount)).build();
  }

  @Override
  public Response makeReturn() {
    WalletBO walletBO = new WalletBO();
    return Response.ok().entity(walletBO.makeReturn()).build();
  }

}
