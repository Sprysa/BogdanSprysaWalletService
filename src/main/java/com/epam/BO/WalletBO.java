package com.epam.BO;

import com.epam.DataProvider.CSVWalletManager;
import com.epam.Model.WalletModel;
import java.io.File;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WalletBO {

  private static Logger LOG = LogManager.getLogger(WalletBO.class);
  private File file = new File("src/main/resources/wallet.csv");
  private WalletModel wallet;

  public double getAvailableAmount() {
    LOG.info("Get available amount from wallet");
    wallet = CSVWalletManager.getWalletInfo(file);
    double amount = Double.valueOf(wallet.getAmount());
    double creditLimit = Double.valueOf(wallet.getCreditLimit());
    return amount + creditLimit;
  }

  public boolean makeWithdraw(String withdrawAmount) {
    LOG.info("Make withdraw from wallet");
    double availableAmount = getAvailableAmount();
    double scaleWithdrawAmount = BigDecimal.valueOf(Double.parseDouble(withdrawAmount))
        .setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    if (availableAmount < scaleWithdrawAmount) {
      return false;
    } else {
      wallet = CSVWalletManager.getWalletInfo(file);
      double amount = Double.valueOf(wallet.getAmount());
      amount = amount - scaleWithdrawAmount;
      wallet.setAmount(String.valueOf(amount));
      CSVWalletManager.setWalletInfo(wallet, file);
      return true;
    }
  }

  public boolean makeIncome(String incomeAmount) {
    LOG.info("Make income to wallet");
    wallet = CSVWalletManager.getWalletInfo(file);
    double amount = Double.valueOf(wallet.getAmount());
    double creditLimit = Double.valueOf(wallet.getCreditLimit());
    double availableAmount = amount + creditLimit;
    double availableAmountToIncome = Double.valueOf(wallet.getMaxLimit()) - availableAmount;
    double scaleIncomeAmount = BigDecimal.valueOf(Double.parseDouble(incomeAmount))
        .setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    if (scaleIncomeAmount > availableAmountToIncome) {
      return false;
    } else {
      amount = amount + scaleIncomeAmount;
      wallet.setAmount(String.valueOf(amount));
      CSVWalletManager.setWalletInfo(wallet, file);
      return true;
    }
  }

  public boolean makeReturn() {
    LOG.info("Return to default state of wallet");
    wallet = new WalletModel("EUR", "100.0", "50.0", "1000");
    CSVWalletManager.setWalletInfo(wallet, file);
    return true;
  }

}
