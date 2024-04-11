package com.priyansu.haki.services;

import com.priyansu.haki.models.Product;
import com.priyansu.haki.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Priyansu Sahoo
 * 11-04-2024
 * @Project haki
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    /**
     *
     * Constructor for the ProductService class.
     * @param productRepository A reference to an object of the ProductRepository class.
     *                          This repository is used for data access operations related to products.
     */
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     *
     * This method is used to retrieve all the products from the database.
     * It calls the 'findAll' method of the 'productRepository' which returns a list of all products.
     *
     * @return List<Product> - This returns a list of all products present in the database.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     *
     * @param product An object of type Product that contains the details of the product to be created.
     * @return Returns the created Product object saved in the database.
     */
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     *
     * We use Optional<> because if we do not get "id" it will return empty object.
     * Else it will throw "NullPointerException".
     *
     * @param id id The ID to search for.
     * @return An Optional containing the object if found, or an empty Optional if not.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     *
     * @param id The unique identifier of the product to be updated. This should be a non-null Long value.
     * @param productDetails An instance of the Product class containing the updated details of the product.
     *                       This should include the new name, quantity, and unit price of the product.
     * @return Returns the updated Product object after it has been saved to the repository.
     * If the product with the given id does not exist, this method may throw a NoSuchElementException.
     */
    @Transactional
    public Optional<Product> updateProduct(Long id,Product productDetails) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product productToUpdate = product.get();

            productToUpdate.setName(productDetails.getName());
            productToUpdate.setQuantity(productDetails.getQuantity());
            productToUpdate.setUnitPrice(productDetails.getUnitPrice());

            return Optional.of(productRepository.save(productToUpdate));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product with ID " + id + " not found"
            );
        }

    }

    /**
     *
     * @param id The unique identifier of the product to be deleted. This should be a non-null Long value.
     */
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
