package com.pomato.phone.repository;

import com.pomato.phone.entities.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhoneRepository extends CrudRepository<Phone,Long> {

    @Override
    List<Phone> findAll();
    List<Phone> findPhonesByPriceGreaterThanAndPriceLessThan(Long min, Long max);
    List<Phone> findPhonesByNotationIs(Integer Stars);
}
