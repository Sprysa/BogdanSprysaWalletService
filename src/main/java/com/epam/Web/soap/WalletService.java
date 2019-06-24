package com.epam.Web.soap;

import javax.jws.WebService;

@WebService
public interface WalletService {

    double getAvailableAmount();

    boolean makeWithdraw(double withdrawAmount);

    boolean makeIncome(double incomeAmount);
}
