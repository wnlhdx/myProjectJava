package com.myproject.dataprocessing.repository;

import com.myproject.dataprocessing.entity.DataEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends ReactiveCrudRepository<DataEntity, Long> {
}
