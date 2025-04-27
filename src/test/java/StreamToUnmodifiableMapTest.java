import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamToUnmodifiableMapTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final String KEY4 = "key3";
    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;
    private static final Integer VALUE3 = 3;
    private static final Integer VALUE4 = 3;

    @Test
    void testToUnmodifiableMapIsMutable() {
        Map<String, Integer> map = Stream.of(new Object[][] {
                { KEY1, VALUE1 },
                { KEY2, VALUE2 }
        }).collect(Collectors.toUnmodifiableMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        ));

        assertThrows(UnsupportedOperationException.class, () -> map.put(KEY1, VALUE1));
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
    void testToUnmodifiableMapAllowsNullKey() {
        assertThrows(NullPointerException.class, () -> Stream.of(new Object[][] {
                { null, VALUE1 }
        }).collect(Collectors.toUnmodifiableMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        )));
    }

    @Test
    void testToUnmodifiableMapDoesNotAllowNullValue() {
        assertThrows(NullPointerException.class, () -> Stream.of(new Object[][] {
                { KEY1, null }
        }).collect(Collectors.toUnmodifiableMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        )));
    }

    @Test
    void testToUnmodifiableMapDuplicateKeysThrowsException() {
        assertThrows(IllegalStateException.class, () -> Stream.of(new Object[][] {
                { KEY1, VALUE1 },
                { KEY1, VALUE2 }
        }).collect(Collectors.toUnmodifiableMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        )));
    }
}