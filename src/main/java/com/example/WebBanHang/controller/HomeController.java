package com.example.WebBanHang.controller;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.model.Brand;
import com.example.WebBanHang.model.Category;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.Sport;
import com.example.WebBanHang.service.BrandService;
import com.example.WebBanHang.service.CategoryService;
import com.example.WebBanHang.service.ProductService;
import com.example.WebBanHang.service.SportService;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService; 
    @Autowired
    private SportService sportService;  
    @Autowired
    private ProductService productService;  
    @Autowired
    private BrandService brandService;   
    @Autowired
    private com.example.WebBanHang.service.WishListService wishListService;
    
    @RequestMapping("/")
    public String home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            HttpSession session, Model model) {
        
        if (session.getAttribute("currentUser") != null) {
            model.addAttribute("currentUser", session.getAttribute("currentUser"));

            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories); 
            List<Sport> sports = sportService.getAllSports();
            List<Brand> brands = brandService.getAllBrands(); 
            model.addAttribute("sports", sports); 
            model.addAttribute("brands", brands); 
            
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> productPage = productService.getPaginatedProducts(pageable);
            model.addAttribute("products", productPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", productPage.getTotalPages());
            
            com.example.WebBanHang.model.User currentUser = (com.example.WebBanHang.model.User) session.getAttribute("currentUser");
            if (currentUser != null) {
                java.util.List<Integer> wishListProductIds = wishListService.getWishListProductIds(currentUser.getId());
                model.addAttribute("wishListProductIds", wishListProductIds);
            }
            
            return "client/index"; 
        }
        return "client/login";
    }
    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "client/product";
    } 
}
