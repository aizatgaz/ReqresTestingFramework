package ru.aizatgaz;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ru.aizatgaz.config.RestAssuredConfig;
import ru.aizatgaz.dto.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ReqresTest {

    @Test
    void getUsersFromSecondPageTest() {
        RestAssuredConfig.setUp(200);
        List<UserData> userList = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .extract().jsonPath().getList("data", UserData.class);
        assertEquals(6, userList.size());
        userList.forEach(user -> assertTrue(user.getEmail().endsWith("@reqres.in")));
    }

    @Test
    void getSecondUserTest() {
        RestAssuredConfig.setUp(200);
        UserData userData = given()
                .when()
                .get("api/users/2")
                .then()
                .extract().body().jsonPath().getObject("data", UserData.class);
        assertEquals(2, userData.getId());
    }

    @Test
    void userNotFoundTest() {
        RestAssuredConfig.setUp(404);
        RestAssured.when()
                .get("api/users/23")
                .then().assertThat();
    }

    @Test
    void getResourceListTest() {
        RestAssuredConfig.setUp(200);
        List<ResourceData> data = given()
                .when()
                .get("api/unknown")
                .then()
                .extract().body().jsonPath().getList("data", ResourceData.class);
        assertEquals(6, data.size());
        data.forEach(resource -> assertTrue(resource.getYear() >= 2000));
    }

    @Test
    void getSecondResourceTest() {
        RestAssuredConfig.setUp(200);
        ResourceData ResourceData = RestAssured.when()
                .get("api/unknown/2")
                .then()
                .extract().body().jsonPath().getObject("data", ResourceData.class);
        assertEquals(2, ResourceData.getId());
    }

    @Test
    void resourceNotFoundTest() {
        RestAssuredConfig.setUp(404);
        RestAssured.when()
                .get("api/unknown/23")
                .then().assertThat();
    }

    @Test
    void createUserTest() {
        RestAssuredConfig.setUp(201);
        UserRequestAndResponseData data = UserRequestAndResponseData.builder()
                .name("morpheus")
                .job("leader").build();

        UserRequestAndResponseData object = given()
                .body(data)
                .when()
                .post("api/users")
                .then()
                .extract().jsonPath().getObject("", UserRequestAndResponseData.class);
        assertNotNull(object);
    }

    @Test
    void updateUserTest() {
        RestAssuredConfig.setUp(200);
        UserRequestAndResponseData data = UserRequestAndResponseData.builder()
                .name("morpheus")
                .job("zion resident").build();

        UserRequestAndResponseData userResponseData = given()
                .body(data)
                .when()
                .put("api/users/2")
                .then()
                .extract().as(UserRequestAndResponseData.class);
        assertNotNull(userResponseData);
    }

    @Test
    void deleteUserTest() {
        RestAssuredConfig.setUp(204);
        RestAssured.when()
                .delete("api/users/2")
                .then();
    }

    @Test
    void registerSuccessfulTest() {
        RestAssuredConfig.setUp(200);
        RegisterData registerData = RegisterData.builder()
                .email("eve.holt@reqres.in")
                .password("pistol").build();

        TokenData tokenData = given()
                .body(registerData)
                .when()
                .post("api/register")
                .then()
                .extract().as(TokenData.class);
        assertEquals("QpwL5tke4Pnpja7X4", tokenData.getToken());
    }

    @Test
    void registerUnsuccessfulTest() {
        RestAssuredConfig.setUp(400);
        RegisterData registerData = RegisterData.builder()
                .email("sydney@fife").build();

        TokenData error = given()
                .body(registerData)
                .when()
                .post("api/register")
                .then()
                .extract().as(TokenData.class);
        assertEquals("Missing password", error.getError());
    }

    @Test
    void loginSuccessfulTest() {
        RestAssuredConfig.setUp(200);
        RegisterData registerData = RegisterData.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka").build();

        TokenData tokenData = given()
                .body(registerData)
                .when()
                .post("api/register")
                .then()
                .extract().as(TokenData.class);
        assertEquals("QpwL5tke4Pnpja7X4", tokenData.getToken());
    }

    @Test
    void loginUnsuccessfulTest() {
        RestAssuredConfig.setUp(400);
        RegisterData registerData = RegisterData.builder()
                .email("peter@klaven").build();

        TokenData error = given()
                .body(registerData)
                .when()
                .post("api/register")
                .then()
                .extract().as(TokenData.class);
        assertEquals("Missing password", error.getError());
    }
}
