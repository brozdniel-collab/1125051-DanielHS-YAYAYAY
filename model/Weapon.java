package model;

public class Weapon extends AuctionItem {
    private final int attackValue;
    private final WeaponType type;

    public Weapon(String name, double totalPrice, int attackValue, WeaponType type) {
        super(name, totalPrice, 1, Category.WEAPON);
        this.attackValue = attackValue;
        this.type = type;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public WeaponType getType() {
        return type;
    }
}
