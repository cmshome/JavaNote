package com.lxk.bean.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Objects;

/**
 * Table metadata.
 *
 * @author lxk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableMetaData {
    
    private Collection<ColumnMetaData> columnMetaData;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableMetaData that = (TableMetaData) o;
        if (columnMetaData.size() != that.getColumnMetaData().size()) {
            return false;
        }
        return columnMetaData.containsAll(that.getColumnMetaData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(columnMetaData);
    }
}
