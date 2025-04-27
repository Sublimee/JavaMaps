import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewHashMapTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;

    @Test
    void testHashMapIsMutable() {
        Map<String, Integer> map = new HashMap<>();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        map.put(KEY1, VALUE1);

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());

        map.put(KEY2, VALUE2);

        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    void testHashMapAllowsNullKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put(null, VALUE1);
        assertEquals(VALUE1, map.get(null));
    }

    @Test
    void testHashMapAllowsNullValue() {
        Map<String, Integer> map = new HashMap<>();
        map.put(KEY1, null);
        assertNull(map.get(KEY1));
    }

    @Test
    void testHashMapDuplicates() {
        Map<String, Integer> map = new HashMap<>();
        map.put(KEY1, VALUE1);
        map.put(KEY1, VALUE2);
        assertEquals(1, map.size());
        assertEquals(VALUE2, map.get(KEY1));
    }
}