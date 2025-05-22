package com.demo.JavaFirst.repository;

import com.demo.JavaFirst.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Demo,Long> {

}
