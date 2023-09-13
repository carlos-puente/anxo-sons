package eu.carlosjaime.anxosons.objects;

import java.util.ArrayList;
import java.util.List;

import eu.carlosjaime.anxosons.definition.ItemType;

public class ItemCollection {
    private boolean isActive = false;
    private int currentPosition = 0;
    private ItemType activeType = ItemType.BELLS;
    private static List<Item> bells = new ArrayList<>();
    private static List<Item> animals = new ArrayList<>();
    private static List<Item> transports = new ArrayList<>();
    private static List<Item> instruments = new ArrayList<>();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setActiveType(ItemType activeType) {
        this.activeType = activeType;
    }

    public ItemType getActiveType() {
        return activeType;
    }

    public void addItem(Item item, ItemType itemType) {
        switch (itemType) {
            case INSTRUMENTS:
                instruments.add(item);
                break;
            case ANIMALS:
                animals.add(item);
                break;
            case BELLS:
                bells.add(item);
                break;
            case TRANSPORTS:
                transports.add(item);
                break;
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        if (this.currentPosition < 0) {
            this.currentPosition = getMaxPosition();
        }
        if (this.currentPosition > getMaxPosition()) {
            this.currentPosition = 0;
        }
    }

    private int getMaxPosition() {
        switch (activeType) {
            case INSTRUMENTS:
                return instruments.size() - 1;
            case ANIMALS:
                return animals.size() - 1;
            case BELLS:
                return bells.size() - 1;
            case TRANSPORTS:
                return transports.size() - 1;
        }
        return 0;
    }

    public Item getCurrentItem() {
        return getItemByPosition(currentPosition);
    }

    public Item getNextItem() {
        int nextPosition = this.currentPosition + 1;
        if (nextPosition > getMaxPosition()) {
            nextPosition = 0;
        }
        return getItemByPosition(nextPosition);
    }

    public Item getPreviousItem() {
        int previousPosition = this.currentPosition - 1;
        if (previousPosition < 0) {
            previousPosition = getMaxPosition();
        }
        return getItemByPosition(previousPosition);
    }

    private Item getItemByPosition(int position) {
        switch (activeType) {
            case INSTRUMENTS:
                return instruments.get(position);
            case ANIMALS:
                return animals.get(position);
            case BELLS:
                return bells.get(position);
            case TRANSPORTS:
                return transports.get(position);
        }
        throw new RuntimeException("Error: not valid itemType");
    }
}
