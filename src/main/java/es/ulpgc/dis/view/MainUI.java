package es.ulpgc.dis.view;

import es.ulpgc.dis.control.CalculateMoneyCommand;
import es.ulpgc.dis.control.Command;
import es.ulpgc.dis.manager.CurrencySetFileReader;
import es.ulpgc.dis.model.CurrencySet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainUI extends JFrame {
    private Map<String, Command> commands = new HashMap<>();
    private static Map<String, JComponent> components = new HashMap<>();
    private static CurrencySet currencySet;

    public MainUI() throws HeadlessException, IOException {
        currencySet = new CurrencySetFileReader().load();
        deployUI();
        addCommands();
    }

    private void addCommands() {
        commands.put("Calculate", new CalculateMoneyCommand());
    }

    private void deployUI() {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 200));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(240, 240, 240));
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.getContentPane().add(originalLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.getContentPane().add(originalCombo(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.getContentPane().add(exchangeToLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.getContentPane().add(exchangeToCombo(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.getContentPane().add(originalMoneyLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.getContentPane().add(originalMoneyTextField(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.getContentPane().add(exchangeButton(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        this.getContentPane().add(resultLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        this.getContentPane().add(resultTextField(), gbc);
    }

    private JButton exchangeButton() {
        JButton button = new JButton("Calculate");
        button.setPreferredSize(new Dimension(80, 30));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(e -> commands.get("Calculate").execute());
        return button;
    }

    private JTextField resultTextField() {
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100, 24));
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 12));
        components.put("ResultTextField", text);
        return text;
    }

    private JLabel resultLabel() {
        JLabel label = new JLabel("Exchange: ");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private JTextField originalMoneyTextField() {
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100, 24));
        text.setFont(new Font("Arial", Font.PLAIN, 12));
        components.put("OriginalMoneyTextField", text);
        return text;
    }

    private JComboBox<String> exchangeToCombo() {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(new Font("Arial", Font.PLAIN, 12));
        components.put("ExchangeToCombo", combo);
        currencySet.currencyMap().keySet().stream()
                .filter(code -> !((JComboBox<?>) components.get("OriginalCombo")).getSelectedItem().toString().equals(code))
                .forEach(combo::addItem);
        combo.addActionListener(e -> ((JTextField) components.get("ResultTextField")).setText(""));
        return combo;
    }

    private JLabel originalMoneyLabel() {
        JLabel label = new JLabel("Amount: ");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private JLabel exchangeToLabel() {
        JLabel label = new JLabel("To: ");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private JComboBox<String> originalCombo() {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(new Font("Arial", Font.PLAIN, 12));
        components.put("OriginalCombo", combo);
        currencySet.currencyMap().keySet().forEach(combo::addItem);
        combo.addActionListener(e -> {
            ((JComboBox<?>) components.get("ExchangeToCombo")).removeAllItems();
            currencySet.currencyMap().keySet().stream()
                    .filter(code -> !combo.getSelectedItem().toString().equals(code))
                    .forEach(((JComboBox<String>) components.get("ExchangeToCombo"))::addItem);
            ((JTextField) components.get("ResultTextField")).setText("");
        });
        return combo;
    }

    private JLabel originalLabel() {
        JLabel label = new JLabel("From: ");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    public static Map<String, JComponent> components() {
        return components;
    }

    public static CurrencySet currencySet() {
        return currencySet;
    }
}
