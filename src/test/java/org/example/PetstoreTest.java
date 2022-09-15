package org.example;

import com.io.petstore.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;
import static org.testng.Assert.assertEquals;


public class PetstoreTest {
    private static final String PHOTO_URL = "https://vetstreet.brightspotcdn.com/dims4/default/05410e1/2147483647/crop/0x0%2B0%2B0/resize/645x380/quality/90/?url=https%3A%2F%2Fvetstreet-brightspot.s3.amazonaws.com%2F9f%2Feb8180a80111e0a0d50050568d634f%2Ffile%2FDalmatian-5-645mk070411.jpg";

    Controller controller;

    Pet pet = new Pet.Builder()
            .withId(RandomStringUtils.randomNumeric(2))
            .withName("Cookie")
            .withPhotoUrls(Collections.singletonList(PHOTO_URL))
            .withStatus(Status.available)
            .withTags(Collections.singletonList(new Tag(1, "Dalmatian")))
            .inCategory(new Category(1, "Dogs")).build();

    @BeforeClass
    public void beforeTest() {
        controller = new Controller();
    }

    @Test(priority = 0)
    public void addNewPet() {
        RequestSpecification requestSpecification = RestAssured.given();

        Pet expectedPet = controller.addNewPet(pet);
        Response createdPetResponse = requestSpecification.body(expectedPet).expect().statusCode(SC_UNSUPPORTED_MEDIA_TYPE)
                .when().post("https://petstore.swagger.io/v2/pet");
        Pet actualPet = createdPetResponse.as(Pet.class);
        assertEquals(actualPet.getName(), expectedPet.getName(),
                "Expected pet doesn't have correct name");
    }

    @Test(priority = 1)
    public void verifyNewPet() {
        RequestSpecification requestSpecification = RestAssured.given();
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);

        Response response = requestSpecification.expect().statusCode(SC_METHOD_NOT_ALLOWED)
                .when().get("https://petstore.swagger.io/v2/pet");
        List<Pet> pet = response.jsonPath().getList("", Pet.class);
        Assert.assertEquals(pet.size(), 1,
                "Expected size is not as actual");

        //another method
//        Pet expectedPet = controller.findPet(pet);
//        Pet actualPet = createdPetResponse.as(Pet.class);
//        Assert.assertEquals(expectedPet.getId());
    }
}
