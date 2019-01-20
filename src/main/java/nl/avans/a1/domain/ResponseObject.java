package nl.avans.a1.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObject {

    String title, description;
    int status;

    public ResponseObject(String title, String description, int status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
