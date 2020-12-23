package com.pomato.general;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class Initializer {
    private static final Logger LOG = LoggerFactory.getLogger(Initializer.class);

    public PhoneService phoneService;

    @Autowired
    public Initializer(PhoneService phoneService){
        this.phoneService = phoneService;
        setupDemoPhones();
    }

    public void setupDemoPhones(){
        phoneService.save( new Phone(1550L , "Iphone pro max" , "images/3.png" , "xxxx xxxx xxxx xxxx"));

        phoneService.save( new Phone(10110L , "Iphone pro max 2" , "images/3.png" , "xxxx xxxx xxxx xxxx"));

        phoneService.save( new Phone(1110L , "Iphone pro max 5" , "images/3.png" , "xxxx xxxx xxxx xxxx"));

        phoneService.save( new Phone(1980L , "Iphone pro max +" , "images/3.png" , "xxxx xxxx xxxx xxxx"));
    }
}
