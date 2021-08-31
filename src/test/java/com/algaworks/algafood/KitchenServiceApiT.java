package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class KitchenServiceApiT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private KitchenRepository kitchenRepository;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); /** */
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";

		databaseCleaner.clearTables();
		this.insertData();
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
			.body("", hasSize(2))
			.body("name", hasItems("Brasileira", "Tailandesa"));
	}

	@Test
	public void returnStatusOkWhenGetKitchenExistsTest() {
		given()
			.pathParam("kitchenId", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{kitchenId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo("Brasileira"));
	}

	@Test
	public void returnStatusNotFoundWhenGetKitcheNonexistentTest() {
		given()
			.pathParam("kitchenId", 100)
			.accept(ContentType.JSON)
		.when()
			.get("/{kitchenId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void insertData() {
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Tailandesa");
		kitchenRepository.save(kitchen1);

		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Brasileira");
		kitchenRepository.save(kitchen2);
	}
}