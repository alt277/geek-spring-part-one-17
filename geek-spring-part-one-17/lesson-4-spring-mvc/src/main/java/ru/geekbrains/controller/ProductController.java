package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepo;
import ru.geekbrains.persist.repo.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRepo productRepo;

//    @GetMapping
//    public String allProducts(Model model)throws SQLException {
//
//        List<Product> productList= productRepository.findAll();
//
//        model.addAttribute("products", productList);
//        return "products";
//    }
    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "price", required = false) String price) {

        logger.info("Filtering by name: {}", title);

        List<Product> productList;

        if (title == null || title.isEmpty()) {
            productList = productRepository.findAll();
//            max = productRepo.findMaxPrice();
//        } else {
//            productList = productRepository.findByTitleLike("%" + title + "%");
//        }
        } else {
            productList = productRepository.findByTitleOrderByPriceDesc (title);
        }

//        if (price == null || price.isEmpty()) {
//            productList = productRepository.findAll();;
//        } else {
//            productList = productRepository.findByTitleOrderByPriceDesc();
//        }
//        model.addAttribute("products", productList);

        model.addAttribute("products", productList);
        return "products";
    }
    @GetMapping("/max")
    public String maxPrice(Model model) {
        Product max =new Product();
        max = productRepo.findMaxPrice();
        model.addAttribute("products", max);
        return "prices";
    }
    @GetMapping("/min")
    public String minPrice(Model model) {
        Product min =new Product();
//        min = productRepo.findMinPrice();
        min = productRepository.findMinPrice();
        model.addAttribute("products", min);
        return "prices";
    }
    @GetMapping("/min-max")
    public String minmaxPrice(Model model) {

        List<Product> productList;
        productList = productRepo.findMinMaxPrice();
        model.addAttribute("products", productList);
        return "prices";
    }
}
