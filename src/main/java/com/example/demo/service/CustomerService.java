package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CustomerDto;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;



    public List<Customer> getAllCustomers(){
        List<Customer> customerList =  customerRepository.findAll();
        return customerList;

    }

    public Customer getOneCustomer(Integer id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public ApiResponse addCustomer(CustomerDto customerDto) {
        boolean existsByPhoneNumber = customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (existsByPhoneNumber){
            return new ApiResponse("Mijoz Mavjud",false);
        }

        Customer customer = new Customer();
        customer.setFullName(customerDto.getFullName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);

        return new ApiResponse("mijoz saqlandi", true);
    }

    public ApiResponse editCustomer(Integer id, CustomerDto customerDto){
        boolean existsByPhoneNumberAndIdNot = customerRepository.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot){
            return new ApiResponse("Bunday raqamli mijoz mavjud", false);
        }
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday mijoz mavjud emas!",false);
        }

        Customer customer = optionalCustomer.get();
        customer.setFullName(customerDto.getFullName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customerRepository.save(customer);
        return new ApiResponse("Mijoz Taxrirlandi", true);


    }

    public ApiResponse deleteCustomer(Integer id){
        customerRepository.deleteById(id);
        return new ApiResponse("mijoz o`xhirilid", true);
    }
}
