package com.epam.Web.soap;

import com.epam.BO.WalletBO;
import com.epam.DataProvider.CSVWalletManager;
import com.epam.Model.WalletModel;
import java.io.File;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebService;

@WebService(endpointInterface = "com.epam.Web.soap.WalletService")
public class WalletServiceImp implements WalletService {

    private static final Logger LOG = LogManager.getLogger();
    private WalletBO walletBO = new WalletBO();
    private double result = 0;
    private File file = new File("src/main/resources/wallet.csv");

    @Override
    public double getAvailableAmount() {
        LOG.info("Get available amount");
        WalletModel wallet = CSVWalletManager.getWalletInfo(file);
        double amount = Double.valueOf(wallet.getAmount());
        double creditLimit = Double.valueOf(wallet.getCreditLimit());
        return amount + creditLimit;
    }

    @Override
    public boolean makeWithdraw(double withdrawAmount) {
        double availableAmount = getAvailableAmount();
        double scaleWithdrawAmount = BigDecimal.valueOf(withdrawAmount).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        if (availableAmount < scaleWithdrawAmount) {
            return false;
        } else {
            WalletModel wallet = CSVWalletManager.getWalletInfo(file);
            double amount = Double.valueOf(wallet.getAmount());
            amount = amount - scaleWithdrawAmount;
            wallet.setAmount(String.valueOf(amount));
            CSVWalletManager.setWalletInfo(wallet, file);
            return true;
        }
    }

    @Override
    public boolean makeIncome(double incomeAmount) {
        WalletModel wallet = CSVWalletManager.getWalletInfo(file);
        double amount = Double.valueOf(wallet.getAmount());
        double creditLimit = Double.valueOf(wallet.getCreditLimit());
        double availableAmount = amount + creditLimit;
        double availableAmountToIncome = Double.valueOf(wallet.getMaxLimit()) - availableAmount;
        double scaleIncomeAmount = BigDecimal.valueOf(incomeAmount).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        if (scaleIncomeAmount > availableAmountToIncome) {
            return false;
        } else {
            amount = amount + scaleIncomeAmount;
            wallet.setAmount(String.valueOf(amount));
            CSVWalletManager.setWalletInfo(wallet, file);
            return true;
        }
    }

}
