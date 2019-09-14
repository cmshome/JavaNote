
package com.lxk.bean.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Column metadata.
 *
 * @author lxk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ColumnMetaData {
    
    private String columnName;
    
    private String columnType;
    
    private boolean primaryKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColumnMetaData that = (ColumnMetaData) o;
        return primaryKey == that.primaryKey &&
                Objects.equals(columnName, that.columnName) &&
                Objects.equals(columnType, that.columnType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(columnName, columnType, primaryKey);
    }

    @Override
    public String toString() {
        return "ColumnMetaData{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", primaryKey=" + primaryKey +
                '}';
    }
}
