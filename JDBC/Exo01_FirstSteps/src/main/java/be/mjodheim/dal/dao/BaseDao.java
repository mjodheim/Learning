package be.mjodheim.dal.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDao<TEntity, TKey extends Serializable> {
    private final String tableName;
    private final String idProperty;
    private final Map<String, String> columnMapping;

    protected BaseDao(String tableName, String idProperty, Map<String, String> columnMapping) {
        this.tableName = tableName;
        this.idProperty = idProperty;
        this.columnMapping = Collections.unmodifiableMap(new LinkedHashMap<>(columnMapping));

        getColumnName(idProperty);
    }

    public List<TEntity> getAll(String... properties) {
        String[] selectedProperties = normalizeProperties(properties);
    }

    private String getColumnName(String property) {
        String column = columnMapping.get(property);
        if (column == null) {
            throw new RuntimeException(String.format("Column '%s' not found", property));
        }
        return column;
    }

    private String[] normalizeProperties(String... properties) {

    }
}
