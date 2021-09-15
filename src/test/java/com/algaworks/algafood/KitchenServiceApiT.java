package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

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

	private int kitchenCount;

	private String jsonInsertKitchen;

	private Kitchen kitchenBrasileira;

	private final int kitchenIdInvalid = 100;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); /** */
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";

		this.jsonInsertKitchen = ResourceUtils.getContentFromResource("/json/kitchen-mock-json.json");
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
	public void returnStatusCreatedWhenInsertKitchenTest() {
		given()
			.body(jsonInsertKitchen)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void checkQuantityKitchensInQueryTest() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(this.kitchenCount))
			.body("name", hasItems(kitchenBrasileira.getName()));
	}

	@Test
	public void returnStatusOkWhenGetKitchenExistsTest() {
		given()
			.pathParam("kitchenId", kitchenBrasileira.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{kitchenId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo(kitchenBrasileira.getName()));
	}

	@Test
	public void returnStatusNotFoundWhenGetKitcheNonexistentTest() {
		given()
			.pathParam("kitchenId", kitchenIdInvalid)
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

		kitchenBrasileira = new Kitchen();
		kitchenBrasileira.setName("Brasileira");
		kitchenRepository.save(kitchenBrasileira);

		this.kitchenCount = kitchenRepository.findAll().size();
	}
}