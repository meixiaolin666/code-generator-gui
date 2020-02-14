package personal.mxl;

import java.util.ArrayList;
import java.util.List;

public class TableInfo {
    //表名
    private String tableName;
    //实体类名
    private String entityName;
    //表备注
    private String tableRemarks;

    private List<Column> columnList=new ArrayList<>();

    public TableInfo() {
    }

    public TableInfo(String tableName, String entityName,  String tableRemarks) {
        this.tableName = tableName;
        this.entityName = entityName;
        this.tableRemarks = tableRemarks;
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

    public String getTableRemarks() {
        return tableRemarks;
    }

    public void setTableRemarks(String tableRemarks) {
        this.tableRemarks = tableRemarks;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
}
