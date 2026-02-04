package com.example.WebBanHang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Customer;
import com.example.WebBanHang.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
public class CustomerService {
    @Autowired 
    public CustomerRepository customerRepository;
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public ResponseEntity<ApiResponse<Object>>   login(String email, String password , HttpSession session) {
        try {
            Customer customer = this.findByEmail(email);
        
        if (customer != null ) {
            if(BCrypt.checkpw(password, customer.getPassword())) {
                return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Sai mật khẩu", null));
            }
            if(customer.getIsActive() == false) {
                return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Customer đã bị xoá", null));
            }
            if(session.getAttribute("customer") != null) {
                session.removeAttribute("customer"); 
            }
            session.setAttribute("customer", customer);
            return  ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Login success", customer));
        }
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Không tồn tại email ", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Login failed", e.getMessage() ));
        }
    }
    public ResponseEntity<ApiResponse<Object>> register ( Customer customer ) {
        try {
            Customer findCustomer  = findByEmail(customer.getEmail()) ;
        if(findCustomer != null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Email already exists", null)); 
        }
        String pwhash = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
        customer.setPassword(pwhash);
        if (customer.getIsActive() == null) {
            customer.setIsActive(true);
        }
        save(customer);
        return  ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Register success", customer));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Register failed", e.getMessage() ));
        }
        

    } 
    
    public ResponseEntity<ApiResponse<Object>> logout(HttpSession session) {
        try {
            session.removeAttribute("customer");
            return ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Logout success", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Logout failed", e.getMessage() ));
        }
    } 
    public ResponseEntity <ApiResponse<Object>> getProfile(HttpSession session) {
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                return ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Get profile success", customer));
            }
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Customer not found", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Get profile failed", e.getMessage() ));
        }
    }  
    public ResponseEntity <ApiResponse<Object>> updateProfile(Customer customer , HttpSession session) {
        try {
            Customer findCustomer = (Customer) session.getAttribute("customer");
            if (findCustomer != null) {
                findCustomer.setName(customer.getName());
                findCustomer.setPhone(customer.getPhone());
                findCustomer.setBirthDate(customer.getBirthDate());
                findCustomer.setGender(customer.getGender());
                save(findCustomer);
                return ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Update profile success", findCustomer));
            }
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Customer not found", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Update profile failed", e.getMessage() ));
        }
    }   
}
