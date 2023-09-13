package eu.carlosjaime.anxosons.definition;
import org.junit.Test;

import static org.junit.Assert.*;
public class ItemTypeTest {
    @Test
    public void testEnumValues() {
        ItemType[] values = ItemType.values();
        assertEquals(4, values.length);
        assertEquals(ItemType.BELLS, values[0]);
        assertEquals(ItemType.ANIMALS, values[1]);
        assertEquals(ItemType.TRANSPORTS, values[2]);
        assertEquals(ItemType.INSTRUMENTS, values[3]);
    }

    @Test
    public void testEnumToString() {
        assertEquals("BELLS", ItemType.BELLS.toString());
        assertEquals("ANIMALS", ItemType.ANIMALS.toString());
        assertEquals("TRANSPORTS", ItemType.TRANSPORTS.toString());
        assertEquals("INSTRUMENTS", ItemType.INSTRUMENTS.toString());
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(ItemType.BELLS, ItemType.valueOf("BELLS"));
        assertEquals(ItemType.ANIMALS, ItemType.valueOf("ANIMALS"));
        assertEquals(ItemType.TRANSPORTS, ItemType.valueOf("TRANSPORTS"));
        assertEquals(ItemType.INSTRUMENTS, ItemType.valueOf("INSTRUMENTS"));
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, ItemType.BELLS.ordinal());
        assertEquals(1, ItemType.ANIMALS.ordinal());
        assertEquals(2, ItemType.TRANSPORTS.ordinal());
        assertEquals(3, ItemType.INSTRUMENTS.ordinal());
    }

    @Test
    public void testEnumEquality() {
        assertNotEquals(ItemType.BELLS, ItemType.ANIMALS);
        assertNotEquals(ItemType.ANIMALS, ItemType.TRANSPORTS);
        assertNotEquals(ItemType.TRANSPORTS, ItemType.INSTRUMENTS);
        assertEquals(ItemType.BELLS, ItemType.BELLS);
    }
}
