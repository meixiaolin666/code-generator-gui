package personal.mxl;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    // 表格列标题
    private Object[] columnNames = {"表名", "生成实体类名", "注释"};

    private Object[][] rowData = {};

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    public Object[][] getRowData() {
        return rowData;
    }

    public void setRowData(Object[][] rowData) {
        this.rowData = rowData;
    }
}
