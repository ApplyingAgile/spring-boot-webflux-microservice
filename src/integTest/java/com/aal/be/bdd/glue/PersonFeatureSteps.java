package com.aal.be.bdd.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.aal.be.examplems.models.Person;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonFeatureSteps extends AbstractStepDefinitionConsumer {


    @Given("^baseUri is (.*)$")
    public void baseUri(String uri) throws Exception{
        Assert.notNull(uri);
        Assert.isTrue(!uri.isEmpty());
        baseUri = uri;
        createAPerson(new Person("tom2", 100, "10"));
    }

    private void createAPerson(Person person) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.setBody(mapper.writeValueAsString(person));
        this.request("/person",HttpMethod.POST);
    }

    @When("^the client calls (.*)$")
    public void get(String resource) {
        this.request(resource, HttpMethod.GET);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void responseCode(Integer status) {
        this.checkStatus(status, false);
    }

    @And("^response body should be valid json$")
    public void bodyIsValid() throws IOException {
        this.checkJsonBody();
    }

    @And("^response body should contain (.+)$")
    public void bodyContains(String bodyValue) throws IOException {
        this.checkBodyContains(bodyValue);
    }
}