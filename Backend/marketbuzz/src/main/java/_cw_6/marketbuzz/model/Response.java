package _cw_6.marketbuzz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Response {

    @Id
    @GeneratedValue
    private String response;

    public Response(String resp) {
        this.response = resp;
    }

}
