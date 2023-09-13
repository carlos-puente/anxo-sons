package eu.carlosjaime.anxosons.objects;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class ItemTest {

    private Item item1;
    private Item item2;
    private Item item3;

    @Before
    public void setUp() {
        item1 = new Item(1, 2, 3);
        item2 = new Item(1, 2, 3);
        item3 = new Item(4, 5, 6);
    }

    @Test
    public void testEquals() {
        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
    }

    @Test
    public void testHashCode() {
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Item{drawableBaseImage=1, drawableActiveImage=2, rawSound=3}";
        assertEquals(expectedString, item1.toString());
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, item1.getDrawableBaseImage().intValue());
        assertEquals(2, item1.getDrawableActiveImage().intValue());
        assertEquals(3, item1.getRawSound().intValue());

        item1.setDrawableBaseImage(4);
        item1.setDrawableActiveImage(5);
        item1.setRawSound(6);

        assertEquals(4, item1.getDrawableBaseImage().intValue());
        assertEquals(5, item1.getDrawableActiveImage().intValue());
        assertEquals(6, item1.getRawSound().intValue());
    }
}