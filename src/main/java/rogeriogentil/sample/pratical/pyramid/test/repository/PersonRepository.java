package rogeriogentil.sample.pratical.pyramid.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rogeriogentil.sample.pratical.pyramid.test.domain.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByLastName(final String lastName);
}
