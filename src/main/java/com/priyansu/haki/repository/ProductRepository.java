package com.priyansu.haki.repository;

import com.priyansu.haki.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Priyansu Sahoo
 * 11-04-2024
 * @Project haki
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * JpaRepository<Product, Long>:
     * HERE <Entity name, primary key type>
     */
}
