package pl.zenit.infobazademo.data.domain;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {}