package sejong.europlanner.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sejong.europlanner.enumtype.Gender;
import sejong.europlanner.repository.CountryRepository;
import sejong.europlanner.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserEntityTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testUserAndCountryEntities() {
        // Create a user
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setName("Test User");
        user.setBirthdate("1990-01-01");
        user.setGender(Gender.MAN);

        // Save the user
        userRepository.save(user);

        // Create a country
        CountryEntity country = new CountryEntity();
        country.setAttraction("Test Attraction");
        country.setLongitude(123.45f);
        country.setLatitude(67.89f);
        country.setUserEntity(user);

        // Save the country
        countryRepository.save(country);

        // Retrieve the user
        UserEntity retrievedUser = userRepository.findById(user.getId()).orElse(null);
        assertNotNull(retrievedUser);
        assertEquals(user.getUsername(), retrievedUser.getUsername());

        // Retrieve the country
        CountryEntity retrievedCountry = countryRepository.findById(country.getId()).orElse(null);
        assertNotNull(retrievedCountry);
        assertEquals(country.getAttraction(), retrievedCountry.getAttraction());

        // Check the relationship
        assertEquals(retrievedUser.getId(), retrievedCountry.getUserEntity().getId());
    }
}