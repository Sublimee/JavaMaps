import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsSingletonMapTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;
    private static final Integer VALUE3 = 3;

    @Test
    void testSingletonMapIsImmutable() {
        Map<String, Integer> map = Collections.singletonMap(KEY1, VALUE1);

        assertThrows(UnsupportedOperationException.class, () -> map.put(KEY2, VALUE2));
        assertThrows(UnsupportedOperationException.class, () -> map.putAll(Collections.singletonMap(KEY3, VALUE3)));

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());

        assertThrows(UnsupportedOperationException.class, () -> map.remove(KEY1));
        assertNull(map.remove(KEY2));
        assertNull(map.remove(KEY3));
        assertThrows(UnsupportedOperationException.class, () -> map.clear());

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    void testSingletonMapAllowsNullKey() {
        Map<String, Integer> map = Collections.singletonMap(null, VALUE1);

        assertEquals(VALUE1, map.get(null));
        assertEquals(1, map.size());
    }

    @Test
    void testSingletonMapAllowsNullValue() {
        Map<String, Integer> map = Collections.singletonMap(KEY1, null);

        assertNull(map.get(KEY1));
        assertEquals(1, map.size());
    }
}