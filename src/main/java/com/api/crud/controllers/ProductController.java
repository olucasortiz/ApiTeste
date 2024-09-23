package com.api.crud.controllers;

import com.api.crud.model.Product;
import com.api.crud.repositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
//estou informando para a IDE que no momento que ela compila o projeto toda a classe se trata
//de uma classe controller
@RequestMapping("/products")
//no momento que fizer as requisições http passando como parametro na url esse parametro
//estou informando que em /products estou utilizando todos os metodos contidos nessa classe


public class ProductController {//serve para receber as requisições http e devolver informações para o cliente(usuario)
    //conter os metodos para fazer essas tratativas


    @Autowired//faz todas as injeções de dependencias necessarias para usar o objeto
    ProductRepository repository;


    @GetMapping//estou informando para o metodo abaixo que ele vai ser do tipo get

    public ResponseEntity getAll() {//responseEntity é uma classe que é utilizada como padrao em apirest, pois
        //ja possuem metodos específicos
        List<Product> listProducts = repository.findAll(); //trazer todos os produtos do tipo produtos que estiverem cadastrados na base de dados
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    @GetMapping("/by-id")
    public ResponseEntity getProductById(@RequestParam Integer id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create-product")
    public ResponseEntity createProduct(@RequestBody Product product) {
        Product savedProduct = repository.save(product);
        if (savedProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/update-product")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        Optional<Product> productOptional = repository.findById(product.getId());//optional pois ele pode nao existir
        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            //atualiza os campos se necessário
            repository.save(existingProduct);
            return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("delete-product")
    public ResponseEntity deleteProduct(@RequestParam Integer id) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            repository.delete(productOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/by-name")
    public ResponseEntity getProductByNome(@RequestParam String name) {
        List<Product> products = repository.findByName(name);

        if(products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


}
