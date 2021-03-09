package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    //show list
    @GetMapping("")
    public ModelAndView showList(@PageableDefault(size = 3) Pageable pageable){
        Page<Product> products = productService.findALl(pageable);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("productList", products);
        return modelAndView;
    }
//    @GetMapping("")
//    public ModelAndView getAll(@PageableDefault(size = 3) Pageable pageable) {
//        Page<Product> products = productService.findALl(pageable);
//
//        ModelAndView modelAndView = new ModelAndView("home");
//        modelAndView.addObject("productList", products);
//        return modelAndView;
//    }
    //create
    @GetMapping("create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("create", "product", new Product());
        List<Category> categories = categoryService.findAll();
        modelAndView.addObject("listCategory",categories);
        return modelAndView;
    }
    //edit
    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute Product product){
        product.setId(id);
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }
    //delete
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam Long id){
        productService.deleteById(id);
        return new ModelAndView("redirect:/products");
    }
    //search
    @PostMapping("search")
    public ModelAndView searchProductByName(@RequestParam String name){
        name="%"+name+"%";
        List<Product> products = productService.findProductName(name);
        if(products.size() == 0) return new ModelAndView("error-404");
        else return new ModelAndView("search", "product",products);
    }
}
