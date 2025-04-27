import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionsEmptyMapTest {

    private static final String KEY = "key";
    private static final Integer VALUE = 1;

    @Test
    void testEmptyMapIsImmutable() {
        Map<String, Integer> map = Collections.emptyMap();

        assertThrows(UnsupportedOperationException.class, () -> map.put(KEY, VALUE));
        assertThrows(UnsupportedOperationException.class, () -> map.putAll(Collections.singletonMap(KEY, VALUE)));

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        assertNull(map.remove(KEY));
        assertDoesNotThrow(() -> map.clear());

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
}