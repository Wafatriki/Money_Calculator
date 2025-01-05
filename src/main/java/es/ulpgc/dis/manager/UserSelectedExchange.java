package es.ulpgc.dis.manager;

import es.ulpgc.dis.model.Currency;
import es.ulpgc.dis.model.Exchange;
import es.ulpgc.dis.model.Money;
import es.ulpgc.dis.view.ExchangeRateLoader;

import javax.swing.*;

import static es.ulpgc.dis.view.MainUI.components;
import static es.ulpgc.dis.view.MainUI.currencySet;


public class UserSelectedExchange implements ExchangeRateLoader {
    @Override
    public Exchange load() {
        return new Exchange(new Money(currencyFrom(), amount()), currencyTo());
    }

    public Currency currencyFrom() {
        try {
            return currencySet().findCurrencyInCurrencySetByCode(((JComboBox<?>) components().get("OriginalCombo")).getSelectedItem().toString());
        } catch (Exception e) {
            System.err.println("Error: No se pudo obtener la moneda de origen.");
            return null;
        }
    }

    public double amount() {
        try {
            return Double.parseDouble(((JTextField) components().get("OriginalMoneyTextField")).getText());
        } catch (NumberFormatException e) {
            System.err.println("Error: El valor ingresado no es un número válido.");
            return 0;
        }
    }

    public Currency currencyTo() {
        try {
            return currencySet().findCurrencyInCurrencySetByCode(((JComboBox<?>) components().get("ExchangeToCombo")).getSelectedItem().toString());
        } catch (Exception e) {
            System.err.println("Error: No se pudo obtener la moneda de destino.");
            return null;
        }
    }
}

