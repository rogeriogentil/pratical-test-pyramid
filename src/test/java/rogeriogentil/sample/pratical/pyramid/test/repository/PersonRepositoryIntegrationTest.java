package rogeriogentil.sample.pratical.pyramid.test.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rogeriogentil.sample.pratical.pyramid.test.domain.Person;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PersonRepositoryIntegrationTest {

    @Autowired
    PersonRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveAndFetchPerson() {
        final String lastName = "Gentil";

        Person person = new Person();
        person.setFirstName("Rogerio");
        person.setLastName(lastName);

        repository.save(person);

        Optional<Person> personFetched = repository.findByLastName(lastName);
        
        assertThat(personFetched.get(), is(equalTo(person)));
    }

}