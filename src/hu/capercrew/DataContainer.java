package hu.capercrew;

import java.util.HashMap;
import java.util.Map;

public class DataContainer {
    private final Map<DataType, Object> data;

    public DataContainer() {
        this.data = new HashMap<>();
    }

    public void add(DataType key, Object ob) {
        this.data.put(key, ob);
    }

    public Object get(DataType key) {
        return this.data.get(key);
    }
}
