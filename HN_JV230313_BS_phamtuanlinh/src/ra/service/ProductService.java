package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService implements IService<Product, String> {
    private List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public void findById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                System.out.println(product);
                return;
            }
        }
        System.out.println("Product not found");
    }

    @Override
    public void delete(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                productList.remove(product);
                System.out.println("Product delete");
                return;
            }
        }
        System.out.println("Product not found");
    }
    public void sortProductsByPriceDescending(){
        Collections.sort(productList, Comparator.comparingDouble(Product::getProductPrice).reversed());
    }
    public void updateProductInfo(String productId, String newName, double newPeice, String newDescription, int newStock ){
        for (Product product: productList){
            if (product.getProductId().equals(productId)){
                product.setProductName(newName);
                product.setProductPrice(newPeice);
                product.setDescription(newDescription);
                product.setStock(newStock);
                System.out.println("Product update");
                return;
            }
        }
        System.out.println("Product not found");
    }
}
