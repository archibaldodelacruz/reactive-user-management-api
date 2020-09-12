package com.example.webflux.initialize;

import com.example.webflux.document.Person;
import com.example.webflux.repository.PersonReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Profile("!test")
public class PersonDataInitializer implements CommandLineRunner {

  PersonReactiveRepository personReactiveRepository;

  MongoOperations mongoOperations;

  public PersonDataInitializer(
      PersonReactiveRepository personReactiveRepository,
        MongoOperations mongoOperations) {
    this.mongoOperations = mongoOperations;
    this.personReactiveRepository = personReactiveRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    initialDataSetup();
  }

  private void initialPersonDataSetup() {}



  public List<Person> personData() {
    return Arrays.asList(
        new Person(null, "pepecrash","12345", "Jose", "Guerrero", 32, "masculino"),
        new Person(null, "nicoq","12345" ,"Nicolas", "Pastor", 25, "trans"),
        new Person(null, "jlezama","12345" , "Jose", "Lezama", 34, "masculino"));
  }

  private void initialDataSetup() {
    personReactiveRepository
        .deleteAll()
        .log(".: Persons :. collection have been erased")
        .thenMany(Flux.fromIterable(personData()))
        .flatMap(
            person ->
                personReactiveRepository
                    .save(person)
                    .log("Saving " + person.getUsername() + " person to memory database"))
        .subscribe();
  }


}
