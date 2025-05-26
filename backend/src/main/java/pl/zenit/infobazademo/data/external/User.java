package pl.zenit.infobazademo.data.external;

record User(
        int id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {}
