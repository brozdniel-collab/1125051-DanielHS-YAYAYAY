package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic class dengan bounded type: hanya menerima turunan AuctionItem.
 */
public class AuctionItemList<T extends AuctionItem> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}
