package pkg_tauliya;

public class Product {
        private String name;
        private int quantity,id,sold_quantity;
        private String dateAdded;
        private double costprice,saleprice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostprice() {
        return costprice;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setCostprice(double costprice) {
        this.costprice = costprice;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(int sold_quantity) {
        this.sold_quantity = sold_quantity;
    }


}
