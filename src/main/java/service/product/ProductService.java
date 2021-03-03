package service.product;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    private static Map<Integer, Product> products = new HashMap<>();
    static {
        products.put(1, new Product(1, "Iphone X", 15000000,"Like New 99%"));
        products.put(2, new Product(2, "Iphone XR", 17000000,"Like New 99%"));
        products.put(3, new Product(3, "Iphone 11", 19000000,"Like New 99%"));
        products.put(4, new Product(4, "Iphone 12", 20000000,"Like New 99%"));
        products.put(5, new Product(5, "Iphone 12 Pro Max", 25000000,"Like New 99%"));
    }
    @Override
    public List<Product> showAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public Product searchByName(String name) {
        List<Product> products = showAll();
        for (Product p:
             products) {
            if (p.getName().equals(name)) return p;
        }
        return null;
    }
}
