import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class DoubleCurlyBracersHashMapTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;
    private static final Integer VALUE3 = 3;

    @Test
    void testHashMapIsMutable() {
        Map<String, Integer> map = new HashMap<>() {{
            put(KEY1, VALUE1);
            put(KEY2, VALUE2);
        }};

        assertEquals(2, map.size());
        assertFalse(map.isEmpty());

        map.put(KEY3, VALUE3);

        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    void testHashMapAllowsNullKey() {
        Map<String, Integer> map = new HashMap<>() {{
            put(null, VALUE1);
        }};
        assertEquals(VALUE1, map.get(null));
        map.put(null, VALUE2);
        assertEquals(VALUE2, map.get(null));
    }

    @Test
    void testHashMapAllowsNullValue() {
        Map<String, Integer> map = new HashMap<>() {{
            put(KEY1, null);
        }};
        assertNull(map.get(KEY1));
        map.put(KEY2, null);
        assertNull(map.get(KEY2));
    }

    @Test
    void testHashMapDuplicates() {
        Map<String, Integer> map = new HashMap<>() {{
            put(KEY1, VALUE1);
            put(KEY1, VALUE2);
        }};

        assertEquals(1, map.size());
        assertEquals(VALUE2, map.get(KEY1));

        map.put(KEY1, VALUE3);

        assertEquals(1, map.size());
        assertEquals(VALUE3, map.get(KEY1));
    }
}