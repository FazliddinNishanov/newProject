package com.example.demo.controller;


import com.example.demo.entity.Customer;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CustomerDto;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();
        return customerList;
    }

    @GetMapping("/{id}")
    public Customer getOneCustomer(@PathVariable Integer id){
        Customer oneCustomer = customerService.getOneCustomer(id);
        return oneCustomer;
    }

    @PostMapping()
    public ApiResponse addCustomer(@Valid @RequestBody CustomerDto customerDto){
           ApiResponse apiResponse = customerService.addCustomer(customerDto);
           return apiResponse;
    }


    @PutMapping("/{id}")
    public ApiResponse editCustomer(@Valid @PathVariable Integer id, @RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.editCustomer(id, customerDto);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
        ApiResponse apiResponse = customerService.deleteCustomer(id);
        return apiResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
