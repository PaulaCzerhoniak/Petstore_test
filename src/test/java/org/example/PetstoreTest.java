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
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
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
        assertEquals(expectedPet.getName(), pet.getName(),
                "Expected pet doesn't have correct name");
    }

    @Test(priority = 1)
    public void verifyNewPet() {
        RequestSpecification requestSpecification = RestAssured.given();
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);

        Pet petToSearch = controller.addNewPet(pet);
        Response response = requestSpecification.expect().statusCode(SC_OK)
                .when().get("https://petstore.swagger.io/v2/pet" + petToSearch.getId());

        Pet actualPet = response.as(Pet.class);
        Assert.assertEquals(actualPet.getId(), petToSearch.getId(),
                "Expected size is not as actual");
    }

    @Test(priority = 2)
    public void updatePet() {
        pet.setName("New pet name");
        Pet petResponse = controller.updatePet(pet);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
    }

    @Test(priority = 3)
    public void deletePet() {
        controller.deletePet(pet);
        controller.verifyDeletedPet(pet);
    }
}
