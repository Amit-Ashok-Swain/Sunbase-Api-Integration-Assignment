package com.sunbaseData.assignment.Assignment.controller;


import com.sunbaseData.assignment.Assignment.model.Customer;
import com.sunbaseData.assignment.Assignment.model.User;
import com.sunbaseData.assignment.Assignment.service.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UIController {

    private final ApiService apiService;

    public UIController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/customer-list")
    public String customerListPage(Model model) {
        try {
            String token = apiService.authenticate(new User("test@sunbasedata.com", "Test@123"));
            ResponseEntity<String> response = apiService.getCustomerList(token);

            if (response.getStatusCode().is2xxSuccessful()) {
                List<Customer> customers = parseCustomerList(response.getBody());
                model.addAttribute("customers", customers);
            } else {
                model.addAttribute("errorMessage", "Failed to fetch customers' list. Please try again.");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred. Please try again later.");
        }
        return "customerList"; // Replace with your customer list page name
    }

    @GetMapping("/add-customer")
    public String showAddCustomerPage() {
        return "addCustomer"; // Replace with your add customer page name
    }

    @PostMapping("/create-customer")
    public String createCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        String token = apiService.authenticate(new User("test@sunbasedata.com", "Test@123"));

        ResponseEntity<String> response = apiService.createCustomer(customer, token);
        if (response.getStatusCode().is2xxSuccessful()) {
            redirectAttributes.addFlashAttribute("successMessage", "Customer created successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create customer");
        }
        return "redirect:/customer-list";
    }

    @GetMapping("/delete-customer/{uuid}")
    public String deleteCustomer(@PathVariable String uuid, RedirectAttributes redirectAttributes) {
        String token = apiService.authenticate(new User("test@sunbasedata.com", "Test@123"));

        ResponseEntity<String> response = apiService.deleteCustomer(uuid, token);
        if (response.getStatusCode().is2xxSuccessful()) {
            redirectAttributes.addFlashAttribute("successMessage", "Customer deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete customer");
        }
        return "redirect:/customer-list";
    }

    @PostMapping("/update-customer/{uuid}")
    public String updateCustomer(@PathVariable String uuid, @ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        String token = apiService.authenticate(new User("test@sunbasedata.com", "Test@123"));

        ResponseEntity<String> response = apiService.updateCustomer(uuid, customer, token);
        if (response.getStatusCode().is2xxSuccessful()) {
            redirectAttributes.addFlashAttribute("successMessage", "Customer updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update customer");
        }
        return "redirect:/customer-list";
    }

    private List<Customer> parseCustomerList(String responseBody) {
        return List.of(); // For now, return an empty list
    }
}
