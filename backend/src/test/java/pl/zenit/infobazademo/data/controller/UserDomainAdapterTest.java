package pl.zenit.infobazademo.data.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.zenit.infobazademo.data.domain.Geo;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserDomainAdapterTest {

    private final UserDomainAdapter adapter = new UserDomainAdapter();

    @Test
    void testTranslate_validInput_shouldTranslate() {
        pl.zenit.infobazademo.data.domain.Address domainAddress = new pl.zenit.infobazademo.data.domain.Address(
                "123 Main St", "Apt 4B", "Springfield", "12345", new Geo("123", "ABC")
        );
        pl.zenit.infobazademo.data.domain.Company domainCompany = new pl.zenit.infobazademo.data.domain.Company(
                "Tech Corp", "Innovate Now", "IT Services"
        );
        pl.zenit.infobazademo.data.domain.User domainUser = new pl.zenit.infobazademo.data.domain.User(
                1, "John Doe", "johndoe", "john@example.com",
                domainAddress, "555-1234", "www.johndoe.com", domainCompany
        );

        User result = adapter.translate(domainUser);

        assertEquals(domainUser.id(), result.id());
        assertEquals(domainUser.name(), result.name());
        assertEquals(domainUser.username(), result.username());
        assertEquals(domainUser.email(), result.email());
        assertEquals(domainUser.phone(), result.phone());
        assertEquals(domainUser.website(), result.website());

        Address dtoAddress = result.address();
        assertEquals(domainAddress.street(), dtoAddress.street());
        assertEquals(domainAddress.suite(), dtoAddress.suite());
        assertEquals(domainAddress.city(), dtoAddress.city());
        assertEquals(domainAddress.zipcode(), dtoAddress.zipcode());

        Company dtoCompany = result.company();
        assertEquals(domainCompany.name(), dtoCompany.name());
        assertEquals(domainCompany.catchPhrase(), dtoCompany.catchPhrase());
        assertEquals(domainCompany.bs(), dtoCompany.bs());
    }

    @Test
    void testTranslate_nullInput_shouldThrowNPE() {
        assertThrows(NullPointerException.class, () -> adapter.translate((Collection<pl.zenit.infobazademo.data.domain.User>) null));
    }

}