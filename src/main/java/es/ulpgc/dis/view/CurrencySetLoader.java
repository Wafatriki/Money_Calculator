package es.ulpgc.dis.view;

import es.ulpgc.dis.model.CurrencySet;

import java.io.IOException;

public interface CurrencySetLoader {
    CurrencySet load() throws IOException;
}