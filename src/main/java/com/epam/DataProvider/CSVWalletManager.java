package com.epam.DataProvider;

import com.epam.Model.WalletModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CSVWalletManager {

  private static String[] HEADERS = {"Currency", "Amount", "CreditLimit", "MaxLimit"};
  private static Logger LOG = LogManager.getLogger(CSVWalletManager.class);

  private static InputStreamReader newReader(final InputStream inputStream) {
    return new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
  }

  public static WalletModel getWalletInfo(File file) {
    LOG.info("Get wallet info");
    WalletModel wallet = null;
    InputStream in = null;
    try {
      in = new FileInputStream(file);
      Iterable<CSVRecord> parser = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord(true)
          .parse(newReader(in));
      for (CSVRecord record : parser) {
        wallet = new WalletModel(record.get("Currency"), record.get("Amount"),
            record.get("CreditLimit"), record.get("MaxLimit"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return wallet;
  }

  public static void setWalletInfo(WalletModel wallet, File file) {
    LOG.info("Set wallet info");
    Writer out = null;
    CSVPrinter csvFilePrinter = null;
    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withNullString("");
    try {
      out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
      csvFilePrinter = new CSVPrinter(out, csvFileFormat);
      csvFilePrinter.printRecord(HEADERS);
      csvFilePrinter.printRecord(wallet.getCurrency(), wallet.getAmount(), wallet.getCreditLimit(),
          wallet.getMaxLimit());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        out.flush();
        out.close();
        csvFilePrinter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
