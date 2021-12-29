package ru.ssau.tk.const1.labs.ui;

import ru.ssau.tk.const1.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayWindow extends JDialog {
    private List<String> xValues;
    private List<String> yValues;
    private MyTableModel tableModel;
    private JTable table;
    private final JButton addRowButton = new JButton("Добавить строчку");
    private final JButton removeRowButton = new JButton("Удалить строчку");
    private JButton create;
    private final JButton countButton;
    private JScrollPane tableScrollPane;
    private final GroupLayout layout = new GroupLayout(getContentPane());
    private final JTextField field;
    private final TabulatedFunctionFactory factory;
    TabulatedFunction function;
    private int count;
    public ArrayWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        JLabel label = new JLabel("Размер табулированной функции:");
        field = new JTextField(5);
        countButton = new JButton("Ввести");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(label);
        getContentPane().add(field);
        getContentPane().add(countButton);
        addButtonListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButtonListeners() {
        countButton.addActionListener(e -> {
            try {
                count = Integer.parseInt(field.getText());
                if (count <= 0) {
                    ExceptionHandling.Processing("отрицательное число!");
                    return;
                }

            } catch (NumberFormatException exception) {
                ExceptionHandling.Processing("неправильный формат");
                return;
            }
            getContentPane().removeAll();
            getContentPane().setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            xValues = new ArrayList<>(count);
            yValues = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                xValues.add("");
                yValues.add("");
            }
            tableModel = new MyTableModel(xValues, yValues);
            table = new JTable(tableModel);
            tableScrollPane = new JScrollPane(table);
            create = new JButton("создать");
            create.addActionListener(e1 -> {
                try {
                    double[] xArray = new double[count];
                    double[] yArray =new double[count];
                    for (int i = 0; i < count; i++) {
                        xArray[i] = Double.parseDouble(xValues.get(i));
                        yArray[i] = Double.parseDouble(yValues.get(i));
                    }
                    function = factory.createFromArray(xArray, yArray);
                    dispose();
                } catch (NumberFormatException exp1) {
                    ExceptionHandling.Processing("Введите число в виде десятичной дроби через точку");
                } catch (ArrayIsNotSortedException exp2) {
                    ExceptionHandling.Processing("X неотсортирован");
                } catch (IllegalArgumentException exp3) {
                    ExceptionHandling.Processing(exp3.getMessage());
                }
            });
            compose();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        });
        addRowButton.addActionListener(e -> {
            xValues.add("");
            yValues.add("");
            tableModel.fireTableDataChanged();
        });
        removeRowButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                xValues.remove(selectedRow);
                yValues.remove(selectedRow);
                tableModel.fireTableDataChanged();
            }
        });
    }

    public void compose() {
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(create)
                        .addComponent(addRowButton)
                        .addComponent(removeRowButton))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(create)
                        .addComponent(addRowButton)
                        .addComponent(removeRowButton))

                .addComponent(tableScrollPane)
        );
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    public static void main(String[] args) {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        ArrayWindow window = new ArrayWindow(factory);
    }
}
