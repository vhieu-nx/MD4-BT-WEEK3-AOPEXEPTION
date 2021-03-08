package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.product.IProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    //show list
    @GetMapping("")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("home");
        List<Product> productList = productService.showAll();
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }
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
        return modelAndView;
    }
    //edit
    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam Integer id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam Integer id, @ModelAttribute Product product){
        product.setId(id);
        productService.update(product);
        return new ModelAndView("redirect:/products");
    }
    //delete
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam Integer id){
        productService.remove(id);
        return new ModelAndView("redirect:/products");
    }
    //search
    @PostMapping("search")
    public ModelAndView searchProductByName(@RequestParam String name){
        Product product = productService.searchByName(name);
        if(product == null) return new ModelAndView("error-404");
        else return new ModelAndView("search", "product",product);
    }
}
