package view;

import controller.AuctionController;
import java.util.List;
import model.AuctionItem;

public class AuctionView {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("[Error] " + message);
    }

    public void showCategoryHeader(String categoryLabel) {
        System.out.println();
        System.out.println("--- " + categoryLabel + " ---");
    }

    public void showItems(List<? extends AuctionItem> items, boolean withUnitPrice) {
        for (AuctionItem item : items) {
            String line = item.getName()
                    + ", total unit " + item.getTotalQuantity()
                    + ", total harga " + formatNumber(item.getTotalPrice());
            if (withUnitPrice) {
                line += " (harga satuan @" + formatNumber(item.getUnitPrice()) + ")";
            }
            System.out.println(line);
        }
    }

    private String formatNumber(double value) {
        if (value == Math.floor(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
    public static void main(String[] args) {
        AuctionController mulai = new AuctionController();
        mulai.run();
    }
}
