package es.ulpgc.dis.view;

import es.ulpgc.dis.model.Exchange;
import es.ulpgc.dis.model.Money;

import javax.swing.*;

import static es.ulpgc.dis.view.MainUI.components;

public class MoneyResultField implements MoneyDisplay {
    private Exchange exchange;
    private double result;

    public MoneyResultField(Exchange exchange, double result) {
        this.exchange = exchange;
        this.result = result;
    }

    @Override
    public void show() {
        ((JTextField) components().get("ResultTextField")).setText(new Money(exchange.currencyTo(), result).toString());
        components().get("ResultTextField").repaint();
    }
}
