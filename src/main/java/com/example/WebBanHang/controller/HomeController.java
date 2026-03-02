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

import com.example.WebBanHang.dto.ProductSummaryDto;
import com.example.WebBanHang.model.Brand;
import com.example.WebBanHang.model.Category;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.Sport;
import com.example.WebBanHang.model.User;
import com.example.WebBanHang.service.BrandService;
import com.example.WebBanHang.service.CategoryService;
import com.example.WebBanHang.service.ProductService;
import com.example.WebBanHang.service.SportService;
import com.example.WebBanHang.service.WishListService;

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
    private WishListService wishListService;
    
    // ==========================================
    // TRANG CHỦ
    // ==========================================
    @RequestMapping("/")
    public String home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size, // Tôi đổi size mặc định thành 12 cho đẹp giao diện
            HttpSession session, Model model) {
        
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
        }

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories); 
        List<Sport> sports = sportService.getAllSports();
        List<Brand> brands = brandService.getAllBrands(); 
        model.addAttribute("sports", sports); 
        model.addAttribute("brands", brands); 
        
        Pageable pageable = PageRequest.of(page, size);
        
        // Gọi hàm listSummaryPaginated mới nhất chúng ta đã làm
        Page<ProductSummaryDto> summaryPage = productService.listSummaryPaginated(
            currentUser != null ? currentUser.getId() : null, pageable
        );
        
        model.addAttribute("products", summaryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", summaryPage.getTotalPages());
        
        return "client/index"; 
    }
    
    // ==========================================
    // CHI TIẾT SẢN PHẨM
    // ==========================================
    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "client/product";
    } 
    
    // ==========================================
    // TRANG LỌC SẢN PHẨM (FILTER)
    // ==========================================
    @GetMapping("/filter")
    public String filterProducts(
            @RequestParam(required = false) List<Integer> categoryId,
            @RequestParam(required = false) List<Integer> sportId,
            @RequestParam(required = false) List<Integer> brandId,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            HttpSession session,
            Model model) {

        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
        }

        // Đổ dữ liệu ra Sidebar
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("sports",     sportService.getAllSports());
        model.addAttribute("brands",     brandService.getAllBrands());
        
        // Giữ lại trạng thái lọc cũ để Thymeleaf checked vào ô
        model.addAttribute("selectedCategoryIds", categoryId);
        model.addAttribute("selectedSportIds",    sportId);
        model.addAttribute("selectedBrandIds",    brandId);
        model.addAttribute("sortBy",    sortBy);
        model.addAttribute("minPrice",  minPrice);
        model.addAttribute("maxPrice",  maxPrice);

        Pageable pageable = PageRequest.of(page, size);
        
        // Gọi Service lọc dữ liệu
        Page<ProductSummaryDto> resultPage = productService.filterProducts(
                currentUser != null ? currentUser.getId() : null,
                categoryId,
                sportId,
                brandId,
                sortBy,
                minPrice,
                maxPrice,
                pageable);

        model.addAttribute("products",       resultPage.getContent());
        model.addAttribute("currentPage",    page);
        model.addAttribute("totalPages",     resultPage.getTotalPages());
        model.addAttribute("totalElements",  resultPage.getTotalElements());
            
        return "client/filter";
    }
}