package _cw_6.marketbuzz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Response {

    @Id
    @GeneratedValue
    private String response;

    public Response(String resp) {
        this.response = resp;
    }

}
