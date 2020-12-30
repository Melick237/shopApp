package com.pomato.phone.service;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.forms.PhoneEditForm;
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
        List<Phone> phones = new ArrayList<>();

        for(Phone p : phoneRepository.findAll()){
            if(p.getStock() > 1)
                phones.add(p);
        }
        return phones;
    }

    public List<Phone> getPhonesAdmin(){
        return phoneRepository.findAll();
    }

    public Optional<Phone> getPhone(Long id){
        return phoneRepository.findById(id);
    }

    public void editPhone(PhoneEditForm phoneEditForm , Phone phone){
        if(phone == null) throw new NullPointerException("Phone must not be null");
        if(phoneEditForm == null) throw new NullPointerException("phoneEditForm must not be null");

          phone.setDescription(phoneEditForm.description);
          phone.setPrice(phoneEditForm.price);
          phone.setImagePath(phoneEditForm.imagePath);
          phone.setName(phoneEditForm.name);
          phone.setStock(phoneEditForm.stock);

          save(phone);
    }

    public void addPhone(PhoneEditForm phoneEditForm){
        if(phoneEditForm == null) throw new NullPointerException("phoneEditForm must not be null");

        Phone phone = new Phone(phoneEditForm.price , phoneEditForm.name , phoneEditForm.imagePath , phoneEditForm.description , 1 , phoneEditForm.stock);
        save(phone);
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

    public List<Phone> searchPhoneStock(Long stock){

        List<Phone> phones = new ArrayList<>();
        phones.addAll(phoneRepository.findPhonesByStockIs(stock));
        return phones;
    }

    public void deletePhone(Phone phone){
        if(phone == null) throw new NullPointerException("Phone must not be null");
        phoneRepository.delete(phone);
    }

    public Optional<Phone> getPhoneStock(Long stock){
        return phoneRepository.findPhonesByStock(stock);
    }

    public void buyPhone(Phone phone){
        if(phone == null) throw new NullPointerException("Phone must not be null");
        if(phone.getStock() < 1)
            return;
        Phone p = phone;
        p.setStock(phone.getStock() - 1);
        save(p);
    }
}
