package pl.zenit.infobazademo.data.external;

record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {}