package es.ulpgc.dis.control;


import es.ulpgc.dis.manager.ExchangeRateSomeSourceReader;
import es.ulpgc.dis.manager.MoneyCalculator;
import es.ulpgc.dis.manager.UserSelectedExchange;
import es.ulpgc.dis.model.Exchange;
import es.ulpgc.dis.view.MoneyResultField;

public class CalculateMoneyCommand implements Command {
    @Override
    public void execute() {
        Exchange exchange = new UserSelectedExchange().load();
        double amount = exchange.moneyFrom().amount();
        double rate = new ExchangeRateSomeSourceReader(exchange).load().rate();
        double result = new MoneyCalculator().calculate(amount, rate);
        new MoneyResultField(exchange, result).show();
    }
}
