package com.example.persistence.jpahibernate.request;

public record AddressRequest(String street, String city, String state, Integer zipCode) {

}
