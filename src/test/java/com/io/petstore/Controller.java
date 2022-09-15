package com.io.petstore;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.internal.matchers.StringContains.containsString;

public class Controller {
    public static String BASE_URL = "https://petstore.swagger.io/v2";
    public static String PET_URL = BASE_URL + "/pet";

    private RequestSpecification requestSpecification;

    public Controller(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }

    public Pet addNewPet(Pet pet){
        return given(requestSpecification)
                .body(pet)
                .post(PET_URL).as(Pet.class);
    }

    public List<Pet> getPetsByStatus (Status status){
        return given(requestSpecification)
                .queryParam("status", Status.available.toString())
                .get(PET_URL + "/findByStatus")
                .then().log().all()
                .extract().body()
                .jsonPath().getList("", Pet.class);
    }

    public Pet findPet(Pet pet){
        return given(requestSpecification)
                .pathParam("petId", pet.getId())
                .get(PET_URL + "/{petId}").as(Pet.class);
    }

    public void deletePet (Pet pet){
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .delete(PET_URL + "/{petId}");
    }

    public void verifyDeletedPet (Pet pet){
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .get("PET_URL" + "/{petId}")
                .then()
                .body(containsString("Pet not found"));
    }
}
