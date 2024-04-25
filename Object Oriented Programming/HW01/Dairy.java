public class Dairy {
    private String product = "";
    private int quantity = 0;
    private double cost = 0.0;
    private int daysToExpiration = 0;
    private boolean isConsumable = true;
    public Dairy() {
        this("yoghurt", 4, 3.99, 3);
        if (daysToExpiration >= 3) {
            System.out.println("Good Choice!");
        } else {
            System.out.println("Check the expiration date.");
        }
    }
    public Dairy(String product, int quantity) {
        this(product, quantity, 5.59, 5);
        if (daysToExpiration >= 3) {
            System.out.println("Good Choice!");
        } else {
            System.out.println("Check the expiration date.");
        }
    }
    public Dairy(String product, int quantity, double cost) {
        this(product, quantity, cost, 0);
        if (daysToExpiration >= 3) {
            System.out.println("Good Choice!");
        } else {
            System.out.println("Check the expiration date.");
        }
    }
    public Dairy(String product, int quantity, double cost, int daysToExpiration) {
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
        this.daysToExpiration = daysToExpiration;
        if (daysToExpiration >= 3) {
            System.out.println("Good Choice!");
        } else {
            System.out.println("Check the expiration date.");
        }
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}