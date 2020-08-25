package com.tui.proof.ws.model;

import lombok.Data;

import java.util.List;

@Data
public class Holder {

    private String name;

    private String lastName;

    private String address;

    private String postalCode;

    // ISO 3166 Country Codes
    private String country;

    private String email;

    private List<String> phones;

}
