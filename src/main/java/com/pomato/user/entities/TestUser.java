package com.pomato.user.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TestUser {

    ADMIN("loic" , "Tsayem" , "loictsayem@test.com" ,
            "test" ,
            new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.ADMIN , UserRole.MODERATOR))),
    MODERATOR("mike" , "sahus" , "mikesahus@test.com" ,
                  "test" , new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.MODERATOR))),
    USER("guerin" , "Fossi" , "guerinfossi1@test.com" ,
            "test" ,
            new HashSet<UserRole>(Arrays.asList(UserRole.USER)));

    public final String firstName;
    public final String lastName;
    public final String email;
    public final String password;
    public final Set<UserRole> grantedAuthorities;

    TestUser(String firstName , String lastName , String email , String password ,
             Set<UserRole> grantedAuthorities){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }
}