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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

    public ResponseEntity getAll(){//responseEntity é uma classe que é utilizada como padrao em apirest, pois
        //ja possuem metodos específicos
        List<Product> listProducts = repository.findAll(); //trazer todos os produtos do tipo produtos que estiverem cadastrados na base de dados
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }
}
