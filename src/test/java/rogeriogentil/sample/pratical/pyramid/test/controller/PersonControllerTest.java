package rogeriogentil.sample.pratical.pyramid.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rogeriogentil.sample.pratical.pyramid.test.domain.Person;
import rogeriogentil.sample.pratical.pyramid.test.repository.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonRepository personRepository;

    @Test
    void shouldReturnFullNameOfPerson() {
        final String lastName = "Gentil";

        Person person = new Person();
        person.setFirstName("Rogerio");
        person.setLastName(lastName);

        when(personRepository.findByLastName(lastName))
                .thenReturn(Optional.of(person));

        String greeting = personController.hello(lastName);

        assertEquals(greeting, "Hello, Rogerio Gentil!");
    }

    @Test
    void shouldTellIfPersonIsUnknown() {
        final String lastName = "abc";
        when(personRepository.findByLastName(lastName))
                .thenReturn(Optional.empty());

        String greeting = personController.hello(lastName);

        assertEquals(greeting, "Who is this 'abc' you're talking about?");
    }
}