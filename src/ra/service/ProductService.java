package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductService implements IService<Product, String> {
    private List<Product> productList;

    public ProductService() {
        productList = new ArrayList<>();
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
    public Product findById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        Product productToRemove = null;
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }


    public void sortByPriceDescending() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getProductPrice(), p1.getProductPrice());
            }
        });
    }



    public List<Product> searchByName(String productName) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }
}
