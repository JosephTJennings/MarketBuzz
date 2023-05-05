package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.Owns;
import _cw_6.marketbuzz.model.Person;
import _cw_6.marketbuzz.model.Following;
import _cw_6.marketbuzz.model.StaticStock;
import _cw_6.marketbuzz.repository.OwnsRepository;
import _cw_6.marketbuzz.repository.PersonRepository;
import _cw_6.marketbuzz.repository.FollowingRepository;
import _cw_6.marketbuzz.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonController {
//Need more info 
    @Autowired
    PersonRepository personRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    FollowingRepository followingRepository;

    @Autowired
    OwnsRepository ownsRepository;


    @GetMapping("people")
    List<Person> GetAllPeople(){
        List<Person> pi = personRepository.findAll();
        return pi;
    }

    @GetMapping("owns")
    List<Owns> GetAllOwns(){return ownsRepository.findAll();}

    @PostMapping("following/people") //X
    List<Following> getCurrentFollowing(@RequestBody Person person) {
        List<Following> activeFollowing = new ArrayList<>();
        List<Following> allFollowing = followingRepository.findAll();
        for(Following follower : allFollowing) {
            if(person.getUsername().equals(follower.getUsername().getUsername())) {
                activeFollowing.add(follower);
            }
        }
        return activeFollowing;
    }

    @GetMapping("following")
    List<Following> getAllTypeFollowers() {
        List<Following> currentlyFollowing = followingRepository.findAll();
        List<String> followingString = new ArrayList<>();
        for(int i = 0; i < currentlyFollowing.size(); i++) {
            followingString.add(currentlyFollowing.get(i).getFollowingUser());
        }
        return currentlyFollowing;
    }

    @PostMapping("person/stocks")
    public List<Owns> getOwnedStocks(@RequestBody Person person) {
        List<Owns> currentOwns = ownsRepository.findAll();
        List<Owns> ownedStocks = new ArrayList<>();
        for(Owns curPerson: currentOwns) {
            if(curPerson.getOwner().getUsername().equals(person.getUsername())) {
                ownedStocks.add(curPerson);
            }
        }
        return ownedStocks;
    }

    @PostMapping("person/data")
    public Person getPersonInformation(@RequestBody Person person){
        List<Person> currentUsers = personRepository.findAll();
        for (Person t: currentUsers){
            if (t.getUsername().equals(person.getUsername())){
                return t;
            }
        }
        return null;
    }

    @PostMapping("username/string/data")
    public Person getPersonInformationByString(@RequestBody String username){
        List<Person> currentUsers = personRepository.findAll();
        username = username.split(":")[1];
        username = username.replaceAll("\"", "");  // String result = input.replaceAll("\"", "");
        username = username.replaceAll("}", "");
        username = username.trim();
        for (Person t: currentUsers){
            if (t.getUsername().equals(username)){
                return t;
            }
        }
        return null;
    }

    @PostMapping("stock/data")
    public StaticStock getStockInformation(@RequestBody StaticStock ticker){
        List<StaticStock> currentStaticStocks = stockRepository.findAll();
        for (StaticStock s: currentStaticStocks){
            if (s.getTicker().equals(ticker.getTicker())){
                return s;
            }
        }
        return null;
    }

    @PostMapping("stock/data/string")
    public StaticStock getStockInformationByString(@RequestBody String ticker){
        List<StaticStock> currentStaticStocks = stockRepository.findAll();
        for (StaticStock s: currentStaticStocks){
            if (s.getTicker().equals(ticker)){
                return s;
            }
        }
        return null;
    }

    @PostMapping("following/post") //X
    Following PostFollowingByBody(@RequestBody Following newFollowing) {
        List<Person> allPeople = personRepository.findAll();
        for(Person people : allPeople) {
            if(people.getUsername().equals(newFollowing.getUsername().getUsername())) {
                Following tmp = new Following(people, newFollowing.getFollowingUser());
                people.addFollowing(tmp);
                followingRepository.save(tmp);
                return tmp;
            }
        }
        return null;
    }

    @GetMapping("leaderboard")
    List<Person> getLeaderboard(){return personRepository.findAll(Sort.by(Direction.DESC, "totalValue"));}

    @DeleteMapping("person/delete/{username}") //X
    public Map<String, Boolean> deletePerson(@PathVariable(value = "username") String username){
        List<Person> currentUsers = personRepository.findAll();
        for (Person t: currentUsers){
            if (t.getUsername().equals(username)){
                personRepository.delete(t);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return response;
            }
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.FALSE);
        return response;
    }


//    @PostMapping("people/stocks/buy")
//    Owns PostBuyingStockByBody(@RequestBody String ticker, @RequestBody String username, @RequestBody String quantity){
    @PostMapping("people/stocks/buy")
    Owns PostBuyingStockByBody(@RequestBody Owns request){

        //System.out.println(request);
        Person user = getPersonInformation(request.getOwner());
        StaticStock staticStock = getStockInformation(getStockInformationByString(request.getTicker()));
        List<Owns> ownsList = ownsRepository.findAll();
        for(Owns o : ownsList) {
            Person p = o.getOwner();
            StaticStock s = o.getStock();
            if(p.getUsername().equals(user.getUsername()) && s.getTicker().equals(staticStock.getTicker())) {
                if (p.getCashValue() >= (s.getCurrVal() * Integer.valueOf(request.getQuantity()))) {
                    o.setQuantity(Integer.valueOf(request.getQuantity()));
                    p.setCashValue(p.getCashValue() - (s.getCurrVal() * Integer.valueOf(request.getQuantity())));
                    user.addStock(o);
                    return o;
                } else {
                    return null;
                }
            }
        }
        request.setOwner(user);
        request.setOwnedStock(staticStock);
        user.addStock(request);
        personRepository.save(user);
        ownsRepository.save(request);

        return request;
    }

    @PostMapping("people/stocks/sell")
    Owns PostSellStockByBody(@RequestBody Owns request){
        Person user = getPersonInformation(request.getOwner());
        StaticStock staticStock = getStockInformation(getStockInformationByString(request.getTicker()));
        List<Owns> ownsList = ownsRepository.findAll();
        for(Owns o : ownsList) {
            Person p = o.getOwner();
            StaticStock s = o.getStock();

            if(p.getUsername().equals(user.getUsername()) && s.getTicker().equals(staticStock.getTicker())) {
                if ((o.getQuantity() - Integer.valueOf(request.getQuantity())) >= 0){
                    o.setQuantity(o.getQuantity() - Integer.valueOf(request.getQuantity()));
                    p.setCashValue(p.getCashValue() + (s.getCurrVal()*Float.valueOf(request.getQuantity())));
                    user.addStock(o);
                    return o;
                }
                else{
                    return null;
                }
            }
        }
        request.setOwner(user);
        request.setOwnedStock(staticStock);
        user.addStock(request);
        personRepository.save(user);
        ownsRepository.save(request);
        return null;
    }

    @PostMapping("people/post") //X
    Person PostUserByBody(@RequestBody Person newPerson){
        List<Following> list = new ArrayList<Following>();
        newPerson.setFollowingList(list);
        newPerson.setCashValue(10000);
        newPerson.setTotalValue(10000);
        personRepository.save(newPerson);
        return newPerson;
    }
    @PostMapping("people/authenticate")//x
    public Message AuthenticateLogin(@RequestBody Person p){
        //find if the user exists in the server
        List<Person> currentUsers = personRepository.findAll();
        //check if the password in the user is equal
        for (Person t: currentUsers){
            if (t.getUsername().equals(p.getUsername()) && t.getPassword().equals(p.getPassword())){
//                Map<String, String> map = new HashMap<>();
//                map.put("message", "success");
                return new Message("success");
            }
        }
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "failure");
//        System.out.print(map.toString());
        return new Message("failure");
    }

    @PostMapping("people/authenticate/register") //x
    public Message AuthenticateRegistration(@RequestBody String username){
        //find if the user exists in the server

        List<Person> currentUsers = personRepository.findAll();
        username = username.split(":")[1];
        username = username.replaceAll("\"", "");  // String result = input.replaceAll("\"", "");
        username = username.replaceAll("}", "");
        username = username.trim();
        //System.out.println("given username: " + username + " length: " + username.length());
        //check if the password in the user is equal
        for (Person t: currentUsers){
//            System.out.println("t.getUsername(): " + t.getUsername() + " length: " + t.getUsername().length());
//            System.out.println(t.getUsername().equals(username));
            if (t.getUsername().equals(username)){
                return new Message("failure");
            }
        }
        return new Message("success");
    }

//    @PostMapping("people/stocks/buy/authenticate")
//    public Message AuthenticateBuy(@RequestBody St)
}
