package com.edgarfrancisco2022.springboottesting.repository;

import com.edgarfrancisco2022.springboottesting.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
    }

    //JUnit test for save employee operation
    //given_when_then
    @DisplayName("JUnit test for save employee operation") //changes the name of the test from the method name
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
         //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        //when - action or behavior to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        //assertj library
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for getting all employees
    @DisplayName("JUnit test for getting all employees")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("1Ramesh1")
                .lastName("1Ramesh1")
                .email("1ramesh1@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when - action or the behavior we are goingn to test
        List<Employee> employees = employeeRepository.findAll();

        //then - verify the output
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
            //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);

            //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

            //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for get employee by email operation
    @DisplayName("givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);

        //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getEmail()).isEqualTo("ramesh@gmail.com");
    }

    //JUnit test for upadate employee operation
    @DisplayName("givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee () {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);

        //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();
        employeeDB.setEmail("rameshUpdate@gmail.com");
        employeeDB.setFirstName("RameshUpdate");
        Employee updatedEmployee = employeeRepository.save(employeeDB);

        //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("rameshUpdate@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("RameshUpdate");
    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);

        //when - action or the behavior we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        //then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    //JUnit test for custom query using JPQL with index params
    @DisplayName("JUnit test for custom query using JPQL with index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findByJPQL(firstName, lastName);

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for custom query using JPQL with name params
    @DisplayName("JUnit test for custom query using JPQL with name params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for custom query using native SQL index params
    @DisplayName("JUnit test for custom query using native SQL index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLIndexParams_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);
        //String firstName = "Ramesh";
        //String lastName = "Fadatare";

        //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for custom query using native SQL named params
    @DisplayName("JUnit test for custom query using native SQL named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail.com")
//                .build();

        employeeRepository.save(employee);
        //String firstName = "Ramesh";
        //String lastName = "Fadatare";

        //when - action or the behavior we are goingn to test
        Employee employeeDB = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }
}
