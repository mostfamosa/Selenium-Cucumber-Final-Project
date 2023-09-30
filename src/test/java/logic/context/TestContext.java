package logic.context;


import logic.entites.enums.TestContextKey;

import java.util.HashMap;

public class TestContext {
    private HashMap<TestContextKey, Object> map;

    public TestContext() {
        this.map = new HashMap<>();
    }

    public <T> T get(TestContextKey key) {
        Object item = map.get(key);
        if (item != null) {
            return (T) item;
        } else {
            throw new IllegalArgumentException("Item not found in context");
        }
    }

    public void put(TestContextKey key, Object item) {
        map.put(key, item);
    }
}
