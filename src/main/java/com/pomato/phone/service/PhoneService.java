package com.pomato.phone.service;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    public List<Phone> getPhones(){
        return phoneRepository.findAll();
    }

    public Optional<Phone> getPhone(Long id){
        return phoneRepository.findById(id);
    }

    public void save(Phone phone){
        if(phone == null) throw new NullPointerException("Phone must not be null");
        phoneRepository.save(phone);
    }


    public List<Phone> searchPhoneInGap(Long min , Long max){

        List<Phone> phones = new ArrayList<>();
        phones.addAll(phoneRepository.findPhonesByPriceGreaterThanAndPriceLessThan(min,max));
        return phones;
    }

    public List<Phone> searchPhoneStars(Integer stars){

        List<Phone> phones = new ArrayList<>();
        phones.addAll(phoneRepository.findPhonesByNotationIs(stars));
        return phones;
    }

    public void uniquePhone(Long id){
        if(id == null) throw new NullPointerException();
        Optional<Phone> phone = getPhone(id);

            return;



    }
}
