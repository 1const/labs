package ru.ssau.tk.const1.labs.ui;

import java.util.List;

public class PartiallyEditableTable extends MyTableModel {
        private static final long serialVersionUID = 4319180793901191382L;

        public PartiallyEditableTable(List<String> xValues, List<String> yValues) {
                super(xValues, yValues);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                        case X_COLUMN_NUMBER:
                                return false;
                        case Y_COLUMN_NUMBER:
                                return true;
                }
                return false;
        }
}