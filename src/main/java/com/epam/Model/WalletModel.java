package com.epam.Model;

public class WalletModel {

  private String currency;
  private String amount;
  private String creditLimit;
  private String maxLimit;

  public WalletModel(String currency, String amount, String creditLimit, String maxLimit) {
    this.currency = currency;
    this.amount = amount;
    this.creditLimit = creditLimit;
    this.maxLimit = maxLimit;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(String creditLimit) {
    this.creditLimit = creditLimit;
  }

  public String getMaxLimit() {
    return maxLimit;
  }

  public void setMaxLimit(String maxLimit) {
    this.maxLimit = maxLimit;
  }
}
