package logic.context;


import java.util.HashMap;

public class TestContext {
    private HashMap<String, Object> map;

    public TestContext() {
        this.map = new HashMap<>();
    }

    public <T> T get(String key) {
        Object item = map.get(key);
        if (item != null) {
            return (T) item;
        } else {
            throw new IllegalArgumentException("Item not found in context");
        }
    }

    public void put(String key, Object item) {
        map.put(key, item);
    }
}
