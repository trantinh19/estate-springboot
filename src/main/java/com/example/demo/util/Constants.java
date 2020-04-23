package com.example.demo.util;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60;
    public static final String SIGNING_KEY = "secret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";


    public static final int ACTIVE = 1;
    public static final int IN_ACTIVE = 0;

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_STAFF = "STAFF";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ANONYMOUS = "ANONYMOUS";

}
