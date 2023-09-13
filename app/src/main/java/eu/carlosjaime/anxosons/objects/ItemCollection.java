package eu.carlosjaime.anxosons.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eu.carlosjaime.anxosons.definition.ItemType;

public class ItemCollection implements Serializable {
    private boolean isActive = false;
    private int currentPosition = 0;
    private ItemType activeType = ItemType.BELLS;
    private  List<Item> bells = new ArrayList<>();
    private  List<Item> animals = new ArrayList<>();
    private  List<Item> transports = new ArrayList<>();
    private  List<Item> instruments = new ArrayList<>();

    public boolean isActive() {
        return isActive;
    }

    public  List<Item> getBells() {
        return bells;
    }

    public  void setBells(List<Item> bells) {
        this.bells = bells;
    }

    public  List<Item> getAnimals() {
        return animals;
    }

    public  void setAnimals(List<Item> animals) {
        this.animals = animals;
    }

    public  List<Item> getTransports() {
        return transports;
    }

    public  void setTransports(List<Item> transports) {
        this.transports = transports;
    }

    public  List<Item> getInstruments() {
        return instruments;
    }

    public  void setInstruments(List<Item> instruments) {
        this.instruments = instruments;
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
