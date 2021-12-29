package ru.ssau.tk.const1.labs.ui;

import java.util.List;

public class NotEditableTable extends MyTableModel {

    private static final long serialVersionUID = 3264684362156617638L;

    public NotEditableTable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
