package rogeriogentil.sample.pratical.pyramid.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rogeriogentil.sample.pratical.pyramid.test.domain.Person;
import rogeriogentil.sample.pratical.pyramid.test.repository.PersonRepository;

import java.util.Optional;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello/{lastName}")
    public String hello(@PathVariable final String lastName) {
        Optional<Person> foundPerson = personRepository.findByLastName(lastName);
        return foundPerson
                .map(
                        person -> String.format("Hello, %s %s!",
                                person.getFirstName(),
                                person.getLastName())
                ).orElse(
                        String.format("Who is this '%s' you're talking about?", lastName)
                );
    }
}
