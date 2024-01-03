package org.example.addressserivce.repo;

import org.example.addressserivce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    // address based on an employeeId
    @Query(value = "SELECT ea.id, ea.lane1, ea.lane2, ea.state, ea.zip FROM microservices.address ea join microservices.employee e on e.id = ea.employee_id where ea.employee_id = :employeeId", nativeQuery = true)
    Address findAddressByEmployeeId(@Param("employeeId") String employeeId);

}
