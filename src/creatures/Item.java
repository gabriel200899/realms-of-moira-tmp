package creatures;

public class Item {

    private String name;
    private ItemType type;
    private int weight;
    private int value;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
