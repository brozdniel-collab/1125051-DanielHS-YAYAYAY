package model;

public class Potion extends AuctionItem {
    private final int hpRestoreValue;
    private final int manaRestoreValue;

    public Potion(String name, double totalPrice, int totalQuantity,
                  int hpRestoreValue, int manaRestoreValue) {
        super(name, totalPrice, totalQuantity, Category.POTION);
        this.hpRestoreValue = hpRestoreValue;
        this.manaRestoreValue = manaRestoreValue;
    }

    public int getHpRestoreValue() {
        return hpRestoreValue;
    }

    public int getManaRestoreValue() {
        return manaRestoreValue;
    }
}
