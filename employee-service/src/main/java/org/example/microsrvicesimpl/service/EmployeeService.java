package org.example.microsrvicesimpl.service;

import org.example.microsrvicesimpl.openfeignclient.AddressClient;
import org.example.microsrvicesimpl.response.AddressResponse;
import org.example.microsrvicesimpl.entity.Employee;
import org.example.microsrvicesimpl.repo.EmployeeRepo;
import org.example.microsrvicesimpl.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired  // Added missing annotation
    private RestTemplate restTemplate;

    @Autowired
    private AddressClient addressClient;
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepo.findById(id).orElse(null);

        if (employee == null) {
            // Handle the case when no employee is found with the given id
            return null;
        }

        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);
        AddressResponse addressResponse = addressResponseEntity.getBody();

        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }

    private AddressResponse callToAddressServiceUsingWebClient(int id) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://address-service/address/" + id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
    }

    private AddressResponse callToAddressServiceUsingLoadBalancer(int id) {
        // Corrected the usage of LoadBalancerClient
        ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");
        String uri = serviceInstance.getUri().toString();
        return webClientBuilder
                .baseUrl(uri)
                .build()
                .get()
                .uri("/address/" + id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
    }
}
