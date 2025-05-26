package pl.zenit.infobazademo.data.controller;

import org.junit.jupiter.api.Test;
import pl.zenit.infobazademo.data.domain.User;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RosterUserDomainAdapterTest {

    private final RosterUserDomainAdapter adapter = new RosterUserDomainAdapter();

    @Test
    void testTranslate_ValidInput_ReturnsCorrectRosterUser() {
        pl.zenit.infobazademo.data.domain.User domainUser = new pl.zenit.infobazademo.data.domain.User(
                1, "John Doe", "johndoe", "john.doe@example.com", null, "1234", "", null);

        RosterUser result = adapter.translate(domainUser);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals("John Doe", result.name());
        assertEquals("johndoe", result.username());
        assertEquals("john.doe@example.com", result.email());
        assertEquals("1234", result.phone());
    }

    @Test
    void testTranslate_nullInput_shouldThrowNPE() {
        assertThrows(NullPointerException.class, () -> adapter.translate((Collection<User>) null));
    }

}