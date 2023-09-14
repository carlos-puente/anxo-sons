package eu.carlosjaime.anxosons.objects;

import eu.carlosjaime.anxosons.definition.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ItemCollectionTest {

    private ItemCollection itemCollection;

    @Before
    public void setUp() {
        itemCollection = new ItemCollection();
    }

    @Test
    public void testIsActive() {
        assertFalse(itemCollection.isActive());
        itemCollection.setActive(true);
        assertTrue(itemCollection.isActive());
    }

    @Test
    public void testSetActiveType() {
        assertEquals(ItemType.BELLS, itemCollection.getActiveType());
        itemCollection.setActiveType(ItemType.ANIMALS);
        assertEquals(ItemType.ANIMALS, itemCollection.getActiveType());
    }

    @Test
    public void testAddItem() {
        Item item = new Item(1, 2, 3);
        itemCollection.addItem(item, ItemType.ANIMALS);

        List<Item> animals = itemCollection.getAnimals();
        assertEquals(1, animals.size());
        assertEquals(item, animals.get(0));
    }

    @Test
    public void testGetCurrentPosition() {
        assertEquals(0, itemCollection.getCurrentPosition());
        itemCollection.setCurrentPosition(2);
        assertEquals(0, itemCollection.getCurrentPosition());
    }

    @Test
    public void testGetCurrentItem() {
        Item item = new Item(1, 2, 3);
        itemCollection.addItem(item, ItemType.BELLS);
        assertEquals(item, itemCollection.getCurrentItem());
    }

    @Test
    public void testGetNextItem() {
        Item item1 = new Item(1, 2, 3);
        Item item2 = new Item(4, 5, 6);
        itemCollection.addItem(item1, ItemType.BELLS);
        itemCollection.addItem(item2, ItemType.BELLS);

        assertEquals(item1, itemCollection.getCurrentItem());
        assertEquals(item2, itemCollection.getNextItem());
    }

    @Test
    public void testGetPreviousItem() {
        Item item1 = new Item(1, 2, 3);
        Item item2 = new Item(4, 5, 6);
        itemCollection.addItem(item1, ItemType.BELLS);
        itemCollection.addItem(item2, ItemType.BELLS);

        assertEquals(item1, itemCollection.getCurrentItem());
        assertEquals(item2, itemCollection.getNextItem());
        assertEquals(item2, itemCollection.getPreviousItem());
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidItemType() {
        Item item = new Item(1, 2, 3);
        itemCollection.addItem(item, null);
    }

    @Test
    public void testCycleItems() {
        List<Item> testItems = new ArrayList<>();
        testItems.add(new Item(1, 2, 3));
        testItems.add(new Item(4, 5, 6));
        testItems.add(new Item(7, 8, 9));
        testItems.add(new Item(10, 11, 12));

        for (Item item : testItems) {
            itemCollection.addItem(item, ItemType.BELLS);
        }

        for (int i = 0; i < testItems.size(); i++) {
            assertEquals(testItems.get(i), itemCollection.getCurrentItem());
            assertEquals(testItems.get((i + 1) % testItems.size()), itemCollection.getNextItem());
            assertEquals(testItems.get((i - 1 + testItems.size()) % testItems.size()), itemCollection.getPreviousItem());

            itemCollection.setCurrentPosition((itemCollection.getCurrentPosition() + 1) % testItems.size());
        }
    }
}
