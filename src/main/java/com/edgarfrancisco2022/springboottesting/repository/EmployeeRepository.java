package com.edgarfrancisco2022.springboottesting.repository;

import com.edgarfrancisco2022.springboottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    //Define custom JPQL query with index parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    //Define custom JPQL query with named parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName")
    Employee findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //Define custom native SQL query with index parameters
    @Query(value = "SELECT * FROM employees WHERE first_name = ?1 AND last_name = ?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    //Define custom native SQL query with name parameters
    @Query(value = "SELECT * FROM employees WHERE first_name = :firstName AND last_name = :lastName", nativeQuery = true)
    Employee findByNativeSQLNamed(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
