package model;

public class Armor extends AuctionItem {
    private final int defenseValue;
    private final ArmorType type;

    public Armor(String name, double totalPrice, int defenseValue, ArmorType type) {
        super(name, totalPrice, 1, Category.ARMOR);
        this.defenseValue = defenseValue;
        this.type = type;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public ArmorType getType() {
        return type;
    }
}
