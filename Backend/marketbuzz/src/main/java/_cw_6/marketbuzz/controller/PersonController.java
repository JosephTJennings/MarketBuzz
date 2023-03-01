package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.Person;
import _cw_6.marketbuzz.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("people")
    List<Person> GetAllPeople(){
        return personRepository.findAll();
    }

    /*@RequestMapping(value="people", method = RequestMethod.GET)
    public @ResponseBody Person
    {

    }*/

    @PostMapping("people/post/{un}/{ln}/{fn}/{p}")
    Person PostPersonByPath(@PathVariable String un, @PathVariable String ln, @PathVariable String fn, @PathVariable String p){
        Person newPerson = new Person();
        newPerson.setUsername(un);
        newPerson.setLastName(ln);
        newPerson.setFirstName(fn);
        newPerson.setPassword(p);
        personRepository.save(newPerson);
        return newPerson;
    }

    @PostMapping("people/post")
    Person PostUserByBody(@RequestBody Person newPerson){
        personRepository.save(newPerson);
        return Response("success");
    }
}
