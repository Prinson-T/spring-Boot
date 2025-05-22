package com.demo.JavaFirst.repository;

import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.entity.Sample;
import com.demo.JavaFirst.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Sample,Long> {
    @Query("select count (id) from Sample s where s.id =:id ")

    int countById(@Param("id")Long id);


    @Query("select c from Sample c where c.email =:email and c.password =:password")
    Sample loginByIdAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT s FROM Sample s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Sample> findByAllData(@Param("name") String name);

}
