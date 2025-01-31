package es.ulpgc.dis.model;

public class Exchange {
    private final Money money;
    private final Currency currency;

    public Exchange(Money money, Currency currency) {
        this.money = money;
        this.currency = currency;
    }

    public Money moneyFrom() {
        return money;
    }

    public Currency currencyTo() {
        return currency;
    }
}