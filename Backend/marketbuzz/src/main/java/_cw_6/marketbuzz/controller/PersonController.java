package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.Person;
import _cw_6.marketbuzz.model.Following;
import _cw_6.marketbuzz.repository.PersonRepository;
import _cw_6.marketbuzz.repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        List<String> followingString = new ArrayList<>();
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

//    @PostMapping("people/post/{un}/{ln}/{fn}/{p}")
//    Person PostPersonByPath(@PathVariable String un, @PathVariable String ln, @PathVariable String fn, @PathVariable String p){
//        Person newPerson = new Person();
//        newPerson.setUsername(un);
//        newPerson.setLastName(ln);
//        newPerson.setFirstName(fn);
//        newPerson.setPassword(p);
//        //newPerson.setFollowingList(p);
//        personRepository.save(newPerson);
//        return newPerson;
//    }

    @PostMapping("people/post")
    Person PostUserByBody(@RequestBody Person newPerson){
        List<Following> list = new ArrayList<Following>();
        newPerson.setFollowingList(list);
        personRepository.save(newPerson);
        return newPerson; //Response("success");
    }
    @PostMapping("people/authenticate")
    String AuthenticateLogin(@RequestBody Person p){
        //find if the user exists in the server
        List<Person> currentUsers = personRepository.findAll();
        //check if the password in the user is equal
        for (Person t: currentUsers){
            if (t.getUsername().equals(p.getUsername()) && t.getPassword().equals(p.getPassword())){
//                Map<String, String> map = new HashMap<>();
//                map.put("message", "success");
                return "success";
            }
        }
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "failure");
//        System.out.print(map.toString());
        return "failure";
    }
}
