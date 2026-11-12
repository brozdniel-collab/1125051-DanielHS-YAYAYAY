package util;

import exception.EmptyListException;
import model.AuctionItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListUtil {
    private ListUtil() {
    }

    /**
     * Generic method dengan bounded type: mengembalikan salinan list yang sudah diurutkan.
     */
    public static <T extends AuctionItem> List<T> sortedCopy(List<T> list, Comparator<? super T> comparator)
            throws EmptyListException {
        if (list == null || list.isEmpty()) {
            throw new EmptyListException("Daftar barang masih kosong.");
        }
        List<T> copy = new ArrayList<>(list);
        copy.sort(comparator);
        return copy;
    }
}
