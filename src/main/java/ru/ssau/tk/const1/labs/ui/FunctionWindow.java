package ru.ssau.tk.const1.labs.ui;

import ru.ssau.tk.const1.labs.functions.*;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FunctionWindow extends JDialog {

    private final JLabel firstLabel = new JLabel("Введите количество точек разбиения:");
    private final JTextField firstTextField = new JTextField("");
    private final JLabel secondLabel = new JLabel("Интервал с");
    private final JTextField secondTextField = new JTextField("");
    private final JLabel thirdLabel = new JLabel("по");
    private final JTextField thirdTextField = new JTextField("");
    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{
            "Единичная функция", "Квадратичная функция", "Константная функция", "Нулевая функция", "Арксинусная функция", "Тождественная функция", "Функция x^2/3"
    });
    private final JButton createButton = new JButton("Создать");

    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public FunctionWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TabulatedFunction getFunction() {
        System.out.println(function);
        return function;
    }

    private void createFunction() {
        Map<String, MathFunction> functionsMap = new HashMap<>();
        functionsMap.put("Единичная функция", new UnitFunction());
        functionsMap.put("Квадратичная функция", new SqrFunction());
        functionsMap.put("Константная функция", new ConstantFunction(3));
        functionsMap.put("Нулевая функция", new ZeroFunction());
        functionsMap.put("Арксинусная функция", new AsinFunction());
        functionsMap.put("Тождественная функция", new IdentityFunction());
        functionsMap.put("Функция x^2/3", new DegreeFunction());

        String functionName = (String) comboBox.getSelectedItem();
        MathFunction selectedFunction = functionsMap.get(functionName);
        double from = Double.parseDouble(secondTextField.getText());
        double to = Double.parseDouble(thirdTextField.getText());
        int count = Integer.parseInt(firstTextField.getText());

        function = factory.createFromFunction(selectedFunction, from, to, count);
    }

    private void addButtonListeners() {
        createButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(firstTextField.getText());
                if (size <= 0) {
                    ExceptionHandling.Processing("отрицательное число");
                }
                createFunction();
                dispose();
            } catch (NumberFormatException exp) {
                ExceptionHandling.Processing("неправильный формат");
            } catch (IllegalArgumentException exp) {
                ExceptionHandling.Processing(exp.getMessage());
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(firstLabel)
                        .addComponent(firstTextField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(secondLabel)
                        .addComponent(secondTextField)
                        .addComponent(thirdLabel)
                        .addComponent(thirdTextField))
                .addComponent(comboBox)
                .addComponent(createButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstLabel)
                        .addComponent(firstTextField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(secondLabel)
                        .addComponent(secondTextField)
                        .addComponent(thirdLabel)
                        .addComponent(thirdTextField))
                .addComponent(comboBox)
                .addComponent(createButton)
        );
    }

    public static void main(String[] args) {
        new FunctionWindow(new ArrayTabulatedFunctionFactory());
    }
}
