package br.com.agua.planeta.planeta_das_aguas_mobile.entities;

public class ProductObject {

    private int productId;

    private String productName;

    private int productImage;

    private String productDescription;

    private double productPrice;


    public ProductObject(int productId, String productName, int productImage, String productDescription, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;

    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }


    @Override
    public String toString() {
        return "ID do Produto e Nome: " + productId + " " + productName;
    }
}