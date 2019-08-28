package personal.mxl;

public class TableInfo {
    //表名
    private String tableName;
    //实体类名
    private String entityName;
    //字段
    private String columnName;
    //字段类型
    private String dataTypeName;
    //字段大小
    private int columnSize;
    //字段排序
    private int ordinalPosition;
    //备注
    private String remarks;

    public TableInfo() {
    }

    public TableInfo(String tableName, String entityName, String columnName, String dataTypeName,int columnSize, String remarks, int ordinalPosition) {
        this.tableName = tableName;
        this.entityName = entityName;
        this.columnName = columnName;
        this.dataTypeName = dataTypeName;
        this.columnSize = columnSize;
        this.remarks = remarks;
        this.ordinalPosition = ordinalPosition;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }
}
