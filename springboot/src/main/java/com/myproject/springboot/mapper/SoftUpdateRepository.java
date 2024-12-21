package com.myproject.springboot.mapper;


import com.myproject.springboot.entity.SoftUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftUpdateRepository extends JpaRepository<SoftUpdateEntity, Long> {
    SoftUpdateRepository findBySoftName(String SoftName);
    void deleteBySoftName(String SoftName);
}
