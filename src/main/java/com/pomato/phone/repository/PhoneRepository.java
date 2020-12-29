package com.pomato.phone.repository;

import com.pomato.phone.entities.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends CrudRepository<Phone,Long> {

    @Override
    List<Phone> findAll();
    List<Phone> findPhonesByPriceGreaterThanAndPriceLessThan(Long min, Long max);
    List<Phone> findPhonesByNotationIs(Integer Stars);
    List<Phone> findPhonesByStockIs(Long stock);
    Optional<Phone> findPhonesByStock(Long stock);

}
