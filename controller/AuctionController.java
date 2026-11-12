package controller;

import exception.EmptyInputException;
import exception.EmptyListException;
import exception.InvalidNumberException;
import model.Armor;
import model.ArmorType;
import model.AuctionItem;
import model.AuctionItemList;
import model.Category;
import model.Potion;
import model.Weapon;
import model.WeaponType;


import util.CLIUtil;
import util.ListUtil;

import view.AuctionView;

import java.util.Comparator;
import java.util.List;

public class AuctionController {
    private final AuctionItemList<Potion> potions = new AuctionItemList<>();
    private final AuctionItemList<Weapon> weapons = new AuctionItemList<>();
    private final AuctionItemList<Armor> armors = new AuctionItemList<>();
    private final AuctionView view = new AuctionView();

    // Anonymous inner class: urut berdasarkan harga keseluruhan
    private final Comparator<AuctionItem> totalPriceComparator = new Comparator<AuctionItem>() {
        @Override
        public int compare(AuctionItem a, AuctionItem b) {
            return Double.compare(a.getTotalPrice(), b.getTotalPrice());
        }
    };

    // Anonymous inner class: urut berdasarkan harga satuan
    private final Comparator<AuctionItem> unitPriceComparator = new Comparator<AuctionItem>() {
        @Override
        public int compare(AuctionItem a, AuctionItem b) {
            return Double.compare(a.getUnitPrice(), b.getUnitPrice());
        }
    };

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=== MENU LELANG ===");
            System.out.println("1. Tambah barang lelang");
            System.out.println("2. Tampilkan berdasarkan harga keseluruhan termurah");
            System.out.println("3. Tampilkan berdasarkan harga satuan termurah");
            System.out.println("0. Keluar");
            try {
                int choice = CLIUtil.readInt("Pilih menu: ");
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        showAll(false);
                        break;
                    case 3:
                        showAll(true);
                        break;
                    case 0:
                        running = false;
                        view.showMessage("Terima kasih.");
                        break;
                    default:
                        view.showError("Menu tidak tersedia.");
                }
            } catch (EmptyInputException | InvalidNumberException e) {
                view.showError(e.getMessage());
            }
        }
    }

    private void addItem() throws EmptyInputException, InvalidNumberException {
        view.showMessage("Kategori: 1. Potion  2. Weapon  3. Armor");
        int categoryChoice = CLIUtil.readInt("Pilih kategori: ");
        Category category = toCategory(categoryChoice);
        if (category == null) {
            view.showError("Kategori tidak valid.");
            return;
        }

        String name = CLIUtil.readString("Nama barang: ");
        double totalPrice = readPositiveDouble("Harga jual keseluruhan: ");

        switch (category) {
            case POTION:
                int quantity = readPositiveInt("Jumlah jual keseluruhan: ");
                int hp = readPositiveInt("HP restore value: ");
                int mana = readPositiveInt("Mana restore value: ");
                potions.add(new Potion(name, totalPrice, quantity, hp, mana));
                break;
            case WEAPON:
                int attack = readPositiveInt("Attack value: ");
                WeaponType weaponType = readWeaponType();
                if (weaponType == null) {
                    view.showError("Tipe weapon tidak valid.");
                    return;
                }
                weapons.add(new Weapon(name, totalPrice, attack, weaponType));
                break;
            case ARMOR:
                int defense = readPositiveInt("Defense value: ");
                ArmorType armorType = readArmorType();
                if (armorType == null) {
                    view.showError("Tipe armor tidak valid.");
                    return;
                }
                armors.add(new Armor(name, totalPrice, defense, armorType));
                break;
            default:
                break;
        }
        view.showMessage("Barang berhasil ditambahkan.");
    }

    private void showAll(boolean byUnitPrice) {
        Comparator<AuctionItem> comparator = byUnitPrice ? unitPriceComparator : totalPriceComparator;
        showCategory(Category.POTION, potions, comparator, byUnitPrice);
        showCategory(Category.WEAPON, weapons, comparator, byUnitPrice);
        showCategory(Category.ARMOR, armors, comparator, byUnitPrice);
    }

    private <T extends AuctionItem> void showCategory(Category category, AuctionItemList<T> list,
                                                      Comparator<AuctionItem> comparator,
                                                      boolean withUnitPrice) {
        view.showCategoryHeader(category.name());
        try {
            List<T> sorted = ListUtil.sortedCopy(list.getItems(), comparator);
            view.showItems(sorted, withUnitPrice);
        } catch (EmptyListException e) {
            view.showError(e.getMessage());
        }
    }

    private Category toCategory(int choice) {
        switch (choice) {
            case 1:
                return Category.POTION;
            case 2:
                return Category.WEAPON;
            case 3:
                return Category.ARMOR;
            default:
                return null;
        }
    }

    private WeaponType readWeaponType() throws EmptyInputException, InvalidNumberException {
        view.showMessage("Tipe: 1. Sword  2. Axe  3. Spear  4. Bow");
        int choice = CLIUtil.readInt("Pilih tipe weapon: ");
        WeaponType[] types = WeaponType.values();
        return (choice >= 1 && choice <= types.length) ? types[choice - 1] : null;
    }

    private ArmorType readArmorType() throws EmptyInputException, InvalidNumberException {
        view.showMessage("Tipe: 1. Heavy  2. Medium  3. Light");
        int choice = CLIUtil.readInt("Pilih tipe armor: ");
        ArmorType[] types = ArmorType.values();
        return (choice >= 1 && choice <= types.length) ? types[choice - 1] : null;
    }

    private int readPositiveInt(String prompt) throws EmptyInputException, InvalidNumberException {
        int value = CLIUtil.readInt(prompt);
        if (value <= 0) {
            throw new InvalidNumberException("Angka harus lebih dari 0.");
        }
        return value;
    }

    private double readPositiveDouble(String prompt) throws EmptyInputException, InvalidNumberException {
        double value = CLIUtil.readDouble(prompt);
        if (value <= 0) {
            throw new InvalidNumberException("Angka harus lebih dari 0.");
        }
        return value;
    }
}
