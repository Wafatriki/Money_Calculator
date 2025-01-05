package es.ulpgc.dis.manager;

import es.ulpgc.dis.model.Currency;
import es.ulpgc.dis.model.CurrencySet;
import es.ulpgc.dis.view.CurrencySetLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class CurrencySetFileReader implements CurrencySetLoader{
    @Override
    public CurrencySet load() throws IOException {
        CurrencySet currencySet = new CurrencySet();
        File file = new File("coins.txt");
        BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
        String line;
        while ((line = reader.readLine()) != null){
            String[] fields = line.split(",");
            currencySet.add(new Currency(fields[0],fields[1],fields[2]));
        }
        reader.close();
        return currencySet;
    }
}