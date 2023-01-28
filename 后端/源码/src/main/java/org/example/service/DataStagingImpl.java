package org.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DataStagingImpl implements DataStaging {
    /**
     * 保存所有用户数据的数据库
     * 外层使用LinkedHashMap实现LRU缓存, 负责映射: id->用户暂存的KV对
     * 内核使用HashMap, 负责映射: 字段名
     */
    private LinkedHashMap<String, HashMap<String, String>> database = null;
    final private static HashMap<String, String> EMPTY = new HashMap<>();

    @Value("${staging.capacity: 10000}")
    private int CAPACITY = 3;

    @Value("${staging.load_factor: 0.75f}")
    private float LOAD_FACTOR = 0.75f;

    public DataStagingImpl() {
        this.database = new LinkedHashMap<>(CAPACITY, LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, HashMap<String, String>> eldest) {
                return this.size() > CAPACITY;
            }
        };
    }

    @Override
    public boolean delete(String id) {
        if (database.containsKey(id)) {
            database.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(String id, Map<String, String> maps) {
        if (id == null || maps == null)
            return false;
        HashMap<String, String> userTable;
        if (database.containsKey(id)) {
            userTable = database.get(id);
        } else {
            userTable = new HashMap<>();
            database.put(id, userTable);
        }
        userTable.putAll(maps);

        return true;
    }

    @Override
    public Map<String, String> search(String id) {
        return database.getOrDefault(id, EMPTY);
    }
}
