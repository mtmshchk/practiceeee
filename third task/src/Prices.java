enum Prices {

    ECONOMY(50),
    STANDARD(100),
    LUX(200),
    ULTRA_LUX(300);

    private final double price;

    Prices(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}