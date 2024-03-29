package ru.ssau.tk.const1.labs.ui;

import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class WindowWithSettings extends JDialog {
    JTabbedPane tabbedPane = new JTabbedPane();
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    private static boolean check = true;

    public WindowWithSettings() {
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 400);

        tabbedPane.setPreferredSize(new Dimension(300, 300));
        JPanel factorySelection = new Panel();
        tabbedPane.addTab("Выбор фабрики", factorySelection);
        tabbedPane.setBackground(Color.lightGray);
        tabbedPane.setForeground(Color.white);

        getContentPane().add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static TabulatedFunctionFactory getFactory() {
        return factory;
    }


    static class Panel extends JPanel {
        ButtonGroup group = new ButtonGroup();

        public Panel() {
            JRadioButton fromArrays = new JRadioButton("Массивы", check);
            group.add(fromArrays);

            JRadioButton fromList = new JRadioButton("Связный список", !check);
            group.add(fromList);

            add(fromArrays);
            add(fromList);

            fromArrays.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new ArrayTabulatedFunctionFactory();
                    check = true;
                }
            });
            fromList.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    check = false;
                }
            });
        }
    }

    public static void main(String[] args) {
        new WindowWithSettings();
    }
}
