package com.project.SpringREST.products;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;


@RestController
public class ProductController {

    private final Bucket bucket;

    private ProductData productData = ProductData.getInstance();

    public ProductController() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/products")
    public ResponseEntity<?> index() {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(productData.getProducts());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> showProduct(@PathVariable String id) {
        if (bucket.tryConsume(1)) {
            int productId = Integer.parseInt(id);
            if (productId < 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: Index out of bounds!");
            }
            Product product = productData.getById(productId);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: Product not found!");
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<?> showByCat(@PathVariable String category) {
        if (bucket.tryConsume(1)) {
            if (productData.getByCategory(category).size() > 0) {
                return ResponseEntity.ok(productData.getByCategory(category));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: No products in this category!");
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }

    @GetMapping("/products/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        if (bucket.tryConsume(1)) {
            if (productData.getByName(name) != null) {
                return ResponseEntity.ok(productData.getByName(name));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: No products with this name!");
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        if (bucket.tryConsume(1)) {
            String name = body.get("name");
            String price = body.get("price");
            float prodPrice = Float.parseFloat(price);
            String category = body.get("category");
            return ResponseEntity.ok(productData.createProduct(name, prodPrice, category));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,
                                           @RequestBody Map<String, String> body) {
        if (bucket.tryConsume(1)) {
            int idx = Integer.parseInt(id);
            String name = body.get("name");

            if (name != null) {
                return ResponseEntity.ok(productData.updateProductName(idx, name));
            }
            String category = body.get("category");
            if (category != null) {
                return ResponseEntity.ok(productData.updateProductCategory(idx, category));
            }
            float price;
            try {
                price = Float.parseFloat(body.get("price"));
            } catch (NumberFormatException ex) {
                return null;
            }
            return ResponseEntity.ok(productData.updateProductPrice(idx, price));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (bucket.tryConsume(1)) {
            int idx = Integer.parseInt(id);
            if (!productData.deleteProduct(idx)) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Product not found!");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Item deleted");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Error: Too many requests!");
    }
}
