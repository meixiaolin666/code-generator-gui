package personal.mxl;

public class Column {
    //字段
    private String columnName;
    //字段类型
    private String dataTypeName;
    //字段大小
    private int columnSize;
    //字段排序
    private int ordinalPosition;
    //字段备注
    private String columnRemarks;

    public Column() {
    }
    public Column(String columnName, String dataTypeName, int columnSize, int ordinalPosition, String columnRemarks) {
        this.columnName = columnName;
        this.dataTypeName = dataTypeName;
        this.columnSize = columnSize;
        this.ordinalPosition = ordinalPosition;
        this.columnRemarks = columnRemarks;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(String columnRemarks) {
        this.columnRemarks = columnRemarks;
    }
}
