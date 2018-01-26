package com.cloud.service.dao.jpa;

import com.cloud.service.domain.Sample;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SampleResposity extends CrudRepository<Sample, String> {

    @Cacheable(value = "city" , keyGenerator = "wiselyKeyGenerator")
    Sample findById(String id);


    @Query(value = "select * from ad a where a.id =?1",nativeQuery = true)
    List<Sample> findSomeData(String id);
}
