public class Bakery {
    private String product = "";
    private int quantity = 0;
    private double cost = 0.0;
    /*public Bakery() {
        this("", 0, 0.0);
    }*/
    public Bakery(String product, int quantity, double cost) {
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }
    public String getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getCost() {
        return cost;
    }
}