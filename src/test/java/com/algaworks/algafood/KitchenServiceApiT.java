package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import static org.hamcrest.Matchers.hasItems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenServiceApiT {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";
	}

	@Test
	public void returnStatusOkWhenGetKitchenTest() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void have4KitchensInQueryTest() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(4))
			.body("name", hasItems("Indiana", "Tailandesa"));
	}
}