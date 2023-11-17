package com.sunbaseData.assignment.Assignment.service;

import com.sunbaseData.assignment.Assignment.model.Customer;
import com.sunbaseData.assignment.Assignment.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticate(User user) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

    public ResponseEntity<String> createCustomer(Customer customer, String token) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); // Set Bearer token

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

        return restTemplate.postForEntity(url, request, String.class);
    }

    public ResponseEntity<String> getCustomerList(String token) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Set Bearer token

        HttpEntity<?> request = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }

    public ResponseEntity<String> deleteCustomer(String uuid, String token) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid=" + uuid;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Set Bearer token

        HttpEntity<?> request = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }

    public ResponseEntity<String> updateCustomer(String uuid, Customer customer, String token) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid=" + uuid;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); // Set Bearer token

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

        return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }
}
