import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HashMapFromMapOfTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final String KEY4 = "key4";

    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;
    private static final Integer VALUE3 = 3;

    @Test
    void testIsMutable() {
        Map<String, Integer> map = new HashMap<>(Map.of(
                KEY1, VALUE1,
                KEY2, VALUE2
        ));

        map.put(KEY3, VALUE3);
        assertEquals(3, map.size());
        assertEquals(VALUE3, map.get(KEY3));
    }

    @Test
    void testCannotCreateMapWithNullKey() {
        assertThrows(NullPointerException.class, () -> new HashMap<>(Map.of(
                null, VALUE1,
                KEY2, VALUE2
        )));
    }

    @Test
    void testCannotCreateMapWithNullValue() {
        assertThrows(NullPointerException.class, () -> new HashMap<>(Map.of(
                KEY1, null,
                KEY2, VALUE2
        )));
    }

    @Test
    void testNewHashMapAllowsNullKeyAndNullValueAfterCreate() {
        Map<String, Integer> map = new HashMap<>(Map.of(
                KEY1, VALUE1,
                KEY2, VALUE2
        ));

        map.put(null, VALUE3);
        map.put(KEY4, null);

        assertEquals(4, map.size());
        assertEquals(VALUE3, map.get(null));
        assertNull(map.get("KEY4"));
    }
}