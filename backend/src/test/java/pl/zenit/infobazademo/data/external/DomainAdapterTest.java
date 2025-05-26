package pl.zenit.infobazademo.data.external;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DomainAdapterTest {

    private DomainAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new DomainAdapter();
    }

    @Test
    public void testTranslate_validInput_shouldTranslate() {
        Geo geoDto = new Geo("40.7128", "-74.0060");
        Address addressDto = new Address("123 Main St", "Apt 4B", "New York", "10001", geoDto);
        Company companyDto = new Company("Doe Inc.", "Innovating the future", "Synergize paradigms");
        User inputUser = new User(1, "John Doe", "johndoe", "john.doe@example.com", addressDto, "123-456-7890", "www.johndoe.com", companyDto);

        pl.zenit.infobazademo.data.domain.User result = adapter.translate(inputUser);

        assertEquals(1, result.id());
        assertEquals("John Doe", result.name());
        assertEquals("johndoe", result.username());
        assertEquals("john.doe@example.com", result.email());
        assertEquals("123-456-7890", result.phone());
        assertEquals("www.johndoe.com", result.website());
        assertEquals("123 Main St", result.address().street());
        assertEquals("Apt 4B", result.address().suite());
        assertEquals("New York", result.address().city());
        assertEquals("10001", result.address().zipcode());
        assertEquals("40.7128", result.address().geo().lat());
        assertEquals("-74.0060", result.address().geo().lng());
        assertEquals("Doe Inc.", result.company().name());
        assertEquals("Innovating the future", result.company().catchPhrase());
        assertEquals("Synergize paradigms", result.company().bs());
    }

    @Test
    void testTranslate_nullInput_shouldThrowNPE() {
        assertThrows(NullPointerException.class, () -> adapter.translate((Collection<User>)null));
    }

    @Test
    public void testTranslate_nullFields_shouldThrowNPE() {
        User inputUser = new User(1, "", "", "", null, "", "", null);

        assertThrows(NullPointerException.class, ()-> adapter.translate(inputUser) );
    }


}