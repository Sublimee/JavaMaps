import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapOfTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final String KEY4 = "key4";

    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;
    private static final Integer VALUE3 = 3;
    private static final Integer VALUE4 = 4;

    @Test
    void testMapOfIsImmutable() {
        Map<String, Integer> map = Map.of(
                KEY1, VALUE1,
                KEY2, VALUE2
        );

        assertThrows(UnsupportedOperationException.class, () -> map.put(KEY2, VALUE3));
        assertThrows(UnsupportedOperationException.class, () -> map.put(KEY3, VALUE3));
        assertThrows(UnsupportedOperationException.class, () -> map.putAll(Collections.singletonMap(KEY4, VALUE4)));

        assertEquals(2, map.size());
        assertFalse(map.isEmpty());

        assertThrows(UnsupportedOperationException.class, () -> map.remove(KEY1));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(KEY2));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(KEY3));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(KEY4));
        assertThrows(UnsupportedOperationException.class, () -> map.clear());

        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    void testMapOfDoesNotAllowNullKey() {
        assertThrows(NullPointerException.class, () -> Map.of(
                null, VALUE1,
                KEY2, VALUE2
        ));
    }

    @Test
    void testMapOfDoesNotAllowNullValue() {
        assertThrows(NullPointerException.class, () -> Map.of(
                KEY1, null,
                KEY2, VALUE2
        ));
    }

    @Test
    void testMapOfZeroSize() {
        Map<String, Integer> map = Map.of();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    void testMapOfDuplicateKeysThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Map.of(
                KEY1, VALUE1,
                KEY1, VALUE2
        ));
    }
}