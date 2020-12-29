package com.pomato.general;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.service.PhoneService;
import com.pomato.user.entities.TestUser;
import com.pomato.user.entities.User;
import com.pomato.user.services.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    private static final Logger LOG = LoggerFactory.getLogger(Initializer.class);

    public PhoneService phoneService;
    public UserManagement userManagement;

    @Autowired
    public Initializer(PhoneService phoneService , UserManagement userManagement){
        this.phoneService = phoneService;
        this.userManagement = userManagement;
        setupDemoPhones();
        setupDemoUsers();
    }

    public void setupDemoPhones(){
        phoneService.save( new Phone(819L , "Iphone XS max" , "images/1.png" , "Apple IPhone XS Max 256GB space Gray, Dual SIM" , 3 , 10L));

        phoneService.save( new Phone(661L , "Iphone X" , "images/2.png" , "Apple IPhone X 256 Gb - Silber, Dual SIM" , 4, 10L));

        phoneService.save( new Phone(1298L , "Iphone 11 pro max " , "images/3.png" , "APPLE iPhone 11 Pro Max 512 GB Gold Dual SIM" , 5, 3L));

        phoneService.save( new Phone(820L , "Iphone 11 Pro" , "images/4.png" , "Apple IPhone 11 Pro 64GB - Gold" , 2, 1L));

        phoneService.save( new Phone(565L , "Iphone XR" , "images/5.png" , "APPLE iPhone XR 64 GB Coral Dual SIM" , 3, 10L));

        phoneService.save( new Phone(459L , "Iphone 8 +" , "images/6.png" , "Apple IPhone 8 plus, Dual SIM" , 5, 10L));

        phoneService.save( new Phone(190L , "Iphone 7" , "images/7.png" , "Apple iPhone 7 128GB - Roségold" , 2, 10L));

        phoneService.save( new Phone(130L , "Iphone 6s" , "images/8.png" , "Apple iPhone 6s 64GB - Silber, Dual SIM" , 3, 10L));

        phoneService.save( new Phone(300L , "Iphone 8" , "images/9.png" , "Apple iPhone 8 256GB - Silber" , 4, 10L));

        phoneService.save( new Phone(630L , "Iphone 11" , "images/10.png" , "Apple iPhone 11 64GB - Grün, Dual SIM" , 2, 10L));

        phoneService.save( new Phone(500L , "Iphone XS" , "images/11.png" , "Apple iPhone XS 256GB - Silber, Dual SIM" , 4, 10L));

        phoneService.save( new Phone(876L , "Iphone 12" , "images/12.png" , "APPLE iPhone 12 5G 64 GB Blau, Dual SIM" , 3, 10L));
    }

    public void setupDemoUsers(){
        LOG.info("Creating default Users.");

        User user;
        for (TestUser testUser : TestUser.values()) {
            user = new User(testUser.firstName , testUser.lastName , testUser.email ,
                    testUser.password , testUser.grantedAuthorities);

            userManagement.generateAndSaveNewValidationTokenForUser(user);
            userManagement.rehashPassword(testUser.password , user);
        }
    }
}