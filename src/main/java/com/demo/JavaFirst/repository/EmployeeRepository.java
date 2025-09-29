package com.demo.JavaFirst.repository;

import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Long> {

@Query("select e from Employees e where e.salary > '10000'")
    List<Employees> findEmployeesWithSalaryGreaterThanTenThousand();

    @Query("select c from Employees c where c.name =:name and c.password =:password")
    Employees loginByNameAndPassword(@Param("name") String name, @Param("password") String password);


    @Query("SELECT s FROM Employees s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employees> findByAllData(@Param("name") String name);

    int countById(@Param("id") Long id);
}
