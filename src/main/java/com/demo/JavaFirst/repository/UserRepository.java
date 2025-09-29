package com.demo.JavaFirst.repository;

import com.demo.JavaFirst.entity.Demo;
import com.demo.JavaFirst.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Demo,Long> {

    @Query("select c from Demo c where c.username =:username and c.password =:password")
    Demo loginByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
