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
        phoneService.save( new Phone(10L , "Iphone XS max" , "images/1.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 3));

        phoneService.save( new Phone(110L , "Iphone X pro max" , "images/2.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 4));

        phoneService.save( new Phone(2110L , "Iphone 11 pro max " , "images/3.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 5));

        phoneService.save( new Phone(1980L , "Iphone 12 pro max +" , "images/4.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 2));

        phoneService.save( new Phone(159L , "Iphone XR" , "images/5.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 3));

        phoneService.save( new Phone(219L , "Iphone 8 +" , "images/6.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 5));

        phoneService.save( new Phone(975L , "Iphone 6 +" , "images/1.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 2));

        phoneService.save( new Phone(749L , "Iphone 7" , "images/2.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 3));

        phoneService.save( new Phone(1349L , "Iphone 7+" , "images/3.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 4));

        phoneService.save( new Phone(8888850L , "Iphone pro max +" , "images/4.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 2));

        phoneService.save( new Phone(980L , "Iphone X" , "images/3.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 4));

        phoneService.save( new Phone(550L , "Iphone XS max" , "images/1.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 3));
    }
}
