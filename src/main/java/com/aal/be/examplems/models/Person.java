package com.aal.be.examplems.models;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class Person {

    private  String name;
    private  int age;
    @Id
    private  String id;

}
