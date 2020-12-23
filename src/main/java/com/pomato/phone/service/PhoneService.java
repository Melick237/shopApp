package com.pomato.phone.service;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    public List<Phone> getPhones(){
        return phoneRepository.findAll();
    }

    public void save(Phone phone){
        if(phone == null) throw new NullPointerException("Phone must not be null");
        phoneRepository.save(phone);
    }
}
