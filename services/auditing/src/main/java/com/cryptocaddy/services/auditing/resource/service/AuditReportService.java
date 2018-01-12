package com.cryptocaddy.services.auditing.resource.service;

import com.cryptocaddy.core.exchanges.Coin;
import com.cryptocaddy.core.exchanges.binance.BinanceController;
import com.cryptocaddy.core.exchanges.bittrex.BittrexController;
import com.cryptocaddy.core.exchanges.coinbase.CoinbaseController;
import com.cryptocaddy.core.exchanges.gdax.GdaxController;
import com.cryptocaddy.services.auditing.resource.model.AuditReport;
import com.cryptocaddy.services.auditing.resource.model.attributes.AuditReportAttributes;
import com.cryptocaddy.services.auditing.resource.model.attributes.AuditReportPathAttributes;
import com.cryptocaddy.services.common.builder.Builder;
import org.knowm.xchange.dto.account.AccountInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class AuditReportService {


    //TODO: Remove test functions and actually implement real audit report retriever
    public AuditReport getAuditReport(AuditReportPathAttributes auditReportPathAttributes,
                                      AuditReportAttributes auditReportAttributes) {

        /* This function is currently useless. Don't let it fool you. */



        runTestRoutines();

        ArrayList<Coin> binanceCoinList = testBinance();

        return Builder.build(AuditReport.class)
                .with(auditReport -> auditReport.setCoins(binanceCoinList))
                .get();
    }

    private void runTestRoutines(){
//        testBinance();
//        testBittrex();
//        testGDAX();

        //Coinbase is currently not working as is.
        //testCoinbase();

    }

    /*
    TEMPORARY TESTING FUNCTIONS. USE config-local.yml to hardcode api keys for testing
     */

    @Value("${binance.binancekey}")
    private String binanceKey;
    @Value("${binance.binancesecret}")
    private String binanceSecret;
    private ArrayList<Coin> testBinance(){
        if (binanceSecret == "" || binanceKey == "") {
            return null;
        }
        BinanceController binanceController = new BinanceController(binanceKey, binanceSecret);
        return binanceController.getAllCoins();
    }


    @Value("${bittrex.bittrexkey}")
    private String bittrexKey;
    @Value("${bittrex.bittrexsecret}")
    private String bittrexSecret;
    private ArrayList<Coin> testBittrex(){
        if (bittrexKey == "" || binanceSecret == "") {
            return null;
        }
        BittrexController bittrexController = new BittrexController(bittrexKey, bittrexSecret);
        return bittrexController.getAllCoins();
    }

    @Value("${coinbase.coinbasekey}")
    private String coinbaseKey;
    @Value("${coinbase.coinbasesecret}")
    private String coinbaseSecret;
    private ArrayList<Coin> testCoinbase(){
        if (coinbaseKey == "" || coinbaseSecret == "") {
            return null;
        }
        CoinbaseController coinbaseController = new CoinbaseController(coinbaseKey, coinbaseSecret);
        return coinbaseController.getAllCoins();
    }

    @Value("${gdax.gdaxkey}")
    private String gdaxKey;
    @Value("${gdax.gdaxsecret}")
    private String gdaxSecret;
    @Value("${gdax.gdaxpass}")
    private String gdaxPass;
    private ArrayList<Coin> testGDAX(){
        if (gdaxKey == "" || gdaxSecret == "" || gdaxPass == "") {
            return null;
        }
        GdaxController gdaxController = new GdaxController(gdaxKey, gdaxSecret, gdaxPass);
        return gdaxController.getAllCoins();
    }

}
