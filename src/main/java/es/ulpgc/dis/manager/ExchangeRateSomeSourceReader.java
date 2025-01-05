package es.ulpgc.dis.manager;

import es.ulpgc.dis.model.Exchange;
import es.ulpgc.dis.model.ExchangeRate;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateSomeSourceReader {
    private Exchange exchange;
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("EURUSD", 1.03);
        exchangeRates.put("USDEUR", 0.97);
        exchangeRates.put("EURGBP", 0.86);
        exchangeRates.put("GBPEUR", 1.16);
        exchangeRates.put("USDJPY", 110.0);
        exchangeRates.put("JPYUSD", 0.0091);
        exchangeRates.put("USDCAD", 1.25);
        exchangeRates.put("CADUSD", 0.80);
        exchangeRates.put("AUDUSD", 0.75);
        exchangeRates.put("USDAUD", 1.33);
        exchangeRates.put("EURCAD", 1.45);
        exchangeRates.put("CADEUR", 0.69);
        exchangeRates.put("EURJPY", 130.0);
        exchangeRates.put("JPYEUR", 0.0077);
        exchangeRates.put("GBPCAD", 1.72);
        exchangeRates.put("CADGBP", 0.58);
        exchangeRates.put("GBPAUD", 1.85);
        exchangeRates.put("AUDGBP", 0.54);
    }

    public ExchangeRateSomeSourceReader(Exchange exchange) {
        this.exchange = exchange;
    }

    public ExchangeRate load() {
        String fromCurrency = exchange.moneyFrom().currency().code();
        String toCurrency = exchange.currencyTo().code();
        String key = fromCurrency + toCurrency;
        double rate = exchangeRates.getOrDefault(key, 1.0); // Valor predeterminado en caso de que no se encuentre la tasa
        return new ExchangeRate(exchange.moneyFrom().currency(), exchange.currencyTo(), rate);
    }
}
