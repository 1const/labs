package ru.ssau.tk.const1.labs.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel {
    private static final int X_COLUMN_NUMBER = 0;
    private static final int Y_COLUMN_NUMBER = 1;
    private final List<Double> xValues;
    private final List<Double> yValues;
    public MyTableModel(List<Double> xValues, List<Double> yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public List<Double> getXValues() {
        return xValues;
    }

    public List<Double> getYValues() {
        return yValues;
    }

    @Override
    public int getRowCount() {
        return xValues.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN_NUMBER:
                return xValues.get(rowIndex);
            case Y_COLUMN_NUMBER:
                return yValues.get(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case X_COLUMN_NUMBER:
                    xValues.set(rowIndex, Double.parseDouble(String.valueOf(aValue)));
                case Y_COLUMN_NUMBER:
                    yValues.set(rowIndex, Double.parseDouble(String.valueOf(aValue)));
            }
        }
        catch (NumberFormatException e){
            ExceptionHandling.Processing("число в неправильном формате");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case X_COLUMN_NUMBER:
                return "x";
            case Y_COLUMN_NUMBER:
                return "y";
        }
        return super.getColumnName(column);
    }

}
