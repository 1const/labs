package ru.ssau.tk.const1.labs.ui;

import ru.ssau.tk.const1.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayWindow extends JFrame {
    private List<Double> xValues;
    private List<Double> yValues;
    private MyTableModel tableModel;
    private JTable table;
    private final JButton addRowButton = new JButton("Добавить строчку");
    private final JButton removeRowButton = new JButton("Удалить строчку");
    private JButton create;
    private final JButton countButton;
    private JScrollPane tableScrollPane;
    private final GroupLayout layout = new GroupLayout(getContentPane());
    private final JTextField field;
    private final TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();

    public ArrayWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("Размер табулированной функции:");
        field = new JTextField(5);
        countButton = new JButton("Ввести");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(label);
        getContentPane().add(field);
        getContentPane().add(countButton);
        addButtonListeners();
    }

    private void addButtonListeners() {
        countButton.addActionListener(e -> {
            int count = 0;
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
                xValues.add(0.0);
                yValues.add(0.0);
            }
            tableModel = new MyTableModel(xValues, yValues);
            table = new JTable(tableModel);
            tableScrollPane = new JScrollPane(table);
            create = new JButton("создать");
            int finalCount = count;
            create.addActionListener(e1 -> {
                try {
                    double[] xArray = xValues.stream().mapToDouble(d -> d).toArray();
                    double[] yArray = yValues.stream().mapToDouble(d -> d).toArray();
                    TabulatedFunction function = arrayFactory.createFromArray(xArray, yArray);
                    System.out.println(function);
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
            xValues.add(0.0);
            yValues.add(0.0);
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

    public static void main(String[] args) {
        ArrayWindow window = new ArrayWindow();
        window.setVisible(true);
    }
}
