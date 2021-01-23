package com.mproduits.web.controller;

import com.mproduits.configurations.ApplicationPropertiesConfiguration;
import com.mproduits.repository.ProductRepository;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    private final ApplicationPropertiesConfiguration appProperties;

    @GetMapping(value = "/Produits")
    public List<Product> listeDesProduits() {

        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

//        return products.subList(0, appProperties.getLimitDeProduits());
        return products;

    }

    //Récuperer un produit par son id
    @GetMapping(value = "/Produits/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

        return product;
    }
}

