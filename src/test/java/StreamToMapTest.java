import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamToMapTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final Integer VALUE1 = 1;
    private static final Integer VALUE2 = 2;
    private static final Integer VALUE3 = 3;

    @Test
    void testToMapIsMutable() {
        Map<String, Integer> map = Stream.of(new Object[][] {
                { KEY1, VALUE1 },
                { KEY2, VALUE2 }
        }).collect(Collectors.toMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        ));

        map.put(KEY3, VALUE3);
        assertEquals(3, map.size());
        assertEquals(VALUE1, map.get(KEY1));
        assertEquals(VALUE2, map.get(KEY2));
        assertEquals(VALUE3, map.get(KEY3));
    }

    @Test
    void testToMapAllowsNullKey() {
        Map<String, Integer> map = Stream.of(new Object[][] {
                { null, VALUE1 },
                { KEY2, VALUE2 }
        }).collect(Collectors.toMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        ));

        assertEquals(VALUE1, map.get(null));
        assertEquals(2, map.size());
    }

    @Test
    void testToMapDoesNotAllowNullValue() {
        assertThrows(NullPointerException.class, () -> Stream.of(new Object[][] {
                { KEY1, null },
                { KEY2, VALUE2 }
        }).collect(Collectors.toMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        )));
    }

    @Test
    void testToMapDuplicateKeysThrowsException() {
        assertThrows(IllegalStateException.class, () -> Stream.of(new Object[][] {
                { KEY1, VALUE1 },
                { KEY1, VALUE2 }
        }).collect(Collectors.toMap(
                data -> (String) data[0],
                data -> (Integer) data[1]
        )));
    }
}