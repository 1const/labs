package ru.ssau.tk.const1.labs.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JButton arrayButton = new JButton("Создание функции с помощью массивов");
    private final JButton buttonTF = new JButton("С помощью встроенных простых функций");
    private final JButton operationButton = new JButton("Операции над функциями");
    private final JButton settingsButton = new JButton("Настройки");
    private final JButton exitButton = new JButton("Выход");
    private final JButton deriveButton = new JButton("Дифференцирование функции");

    public MainWindow() {
        super("MainWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(500, 500);
        setResizable(false);

        arrayButton.setFocusPainted(false);
        buttonTF.setFocusPainted(false);
        operationButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);
        deriveButton.setFocusPainted(false);

        compose();
        addButtonListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(layout);
        getContentPane().add(Box.createRigidArea(new Dimension(0,100)));
        addButton(arrayButton);
        addButton(buttonTF);
        addButton(operationButton);
        addButton(settingsButton);
        addButton(exitButton);
        addButton(deriveButton);
    }
    private void addButton(JButton button){
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(button);
    }
    private void addButtonListeners() {
        arrayButton.addActionListener(e -> new ArrayWindow(WindowWithSettings.getFactory()));
        buttonTF.addActionListener(e -> new FunctionWindow(WindowWithSettings.getFactory()));
        operationButton.addActionListener(e -> new SimpleOperationsWindow(WindowWithSettings.getFactory()));
        settingsButton.addActionListener(e -> new WindowWithSettings());
        deriveButton.addActionListener(e -> new DerivationWindow(WindowWithSettings.getFactory()));
        exitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
