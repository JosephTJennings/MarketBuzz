package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.Person;
import _cw_6.marketbuzz.model.Following;
import _cw_6.marketbuzz.repository.PersonRepository;
import _cw_6.marketbuzz.repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FollowingRepository followingRepository;

    @GetMapping("people")
    List<Person> GetAllPeople(){
        return personRepository.findAll();
    }
    @GetMapping("following")
    List<String> getAllTypeFollowers() {
        List<Following> currentlyFollowing = followingRepository.findAll();
        ArrayList<String> followingString = new ArrayList<>();
        for(int i = 0; i < currentlyFollowing.size(); i++) {
            followingString.add(currentlyFollowing.get(i).getFollowing());
        }
        return followingString;
    }
    @PostMapping("following/post")
    Following PostFollowingByBody(@RequestBody Following newFollowing) {
        followingRepository.save(newFollowing);
        return newFollowing;
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
