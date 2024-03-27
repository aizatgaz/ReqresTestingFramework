package ru.aizatgaz.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import static ru.aizatgaz.constants.Constants.REQRES_URL;

public class RestAssuredConfig {

    public static void setUp(Integer statusCode) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(REQRES_URL)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

}
