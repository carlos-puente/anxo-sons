package eu.carlosjaime.anxosons.helper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import eu.carlosjaime.anxosons.R;
import eu.carlosjaime.anxosons.definition.ItemType;
import eu.carlosjaime.anxosons.objects.Item;
import eu.carlosjaime.anxosons.objects.ItemCollection;

public class Helper {

    private static void fillItems(ItemCollection itemCollection, List<Item> items, ItemType itemType) {
        items.forEach(item -> itemCollection.addItem(item, itemType));

    }

    public static void jsonGenerator() {
        ItemCollection itemCollection = new ItemCollection();
        fillItems(itemCollection, generateBells(), ItemType.BELLS);
        fillItems(itemCollection, generateAnimals(), ItemType.ANIMALS);
        fillItems(itemCollection, generateTransports(), ItemType.TRANSPORTS);
        fillItems(itemCollection, generateInstruments(), ItemType.INSTRUMENTS);
        Gson gson = new Gson();
        String json = gson.toJson(itemCollection);
    }

    private static List<Item> generateInstruments() {
        List<Item> instruments = new ArrayList<>();
        instruments.add(new Item(R.drawable.instrument1, R.drawable.instrument1_active, R.raw.instrument1));
        instruments.add(new Item(R.drawable.instrument2, R.drawable.instrument2_active, R.raw.instrument2));
        instruments.add(new Item(R.drawable.instrument3, R.drawable.instrument3_active, R.raw.instrument3));
        instruments.add(new Item(R.drawable.instrument4, R.drawable.instrument4_active, R.raw.instrument4));
        instruments.add(new Item(R.drawable.instrument5, R.drawable.instrument5_active, R.raw.instrument5));
        instruments.add(new Item(R.drawable.instrument6, R.drawable.instrument6_active, R.raw.instrument6));
        instruments.add(new Item(R.drawable.instrument7, R.drawable.instrument7_active, R.raw.instrument7));
        instruments.add(new Item(R.drawable.instrument8, R.drawable.instrument8_active, R.raw.instrument8));
        return instruments;
    }

    private static List<Item> generateTransports() {
        List<Item> transports = new ArrayList<>();
        transports.add(new Item(R.drawable.transport1, R.drawable.transport1_active, R.raw.transport1));
        transports.add(new Item(R.drawable.transport2, R.drawable.transport2_active, R.raw.transport2));
        transports.add(new Item(R.drawable.transport3, R.drawable.transport3_active, R.raw.transport3));
        transports.add(new Item(R.drawable.transport4, R.drawable.transport4_active, R.raw.transport4));
        transports.add(new Item(R.drawable.transport5, R.drawable.transport5_active, R.raw.transport5));
        transports.add(new Item(R.drawable.transport6, R.drawable.transport6_active, R.raw.transport6));
        return transports;
    }

    private static List<Item> generateAnimals() {
        List<Item> animals = new ArrayList<>();
        animals.add(new Item(R.drawable.animal1, R.drawable.animal1_active, R.raw.animal1));
        animals.add(new Item(R.drawable.animal2, R.drawable.animal2_active, R.raw.animal2));
        animals.add(new Item(R.drawable.animal3, R.drawable.animal3_active, R.raw.animal3));
        animals.add(new Item(R.drawable.animal4, R.drawable.animal4_active, R.raw.animal4));
        animals.add(new Item(R.drawable.animal5, R.drawable.animal5_active, R.raw.animal5));
        animals.add(new Item(R.drawable.animal6, R.drawable.animal6_active, R.raw.animal6));
        return animals;
    }

    private static List<Item> generateBells() {
        List<Item> bells = new ArrayList<>();
        bells.add(new Item(R.drawable.bell1, R.drawable.bell1_active, R.raw.bell1));
        bells.add(new Item(R.drawable.bell2, R.drawable.bell2_active, R.raw.bell2));
        bells.add(new Item(R.drawable.bell3, R.drawable.bell3_active, R.raw.bell3));
        bells.add(new Item(R.drawable.bell4, R.drawable.bell4_active, R.raw.bell4));
        bells.add(new Item(R.drawable.bell5, R.drawable.bell5_active, R.raw.bell5));
        return bells;
    }
}