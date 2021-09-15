package com.algaworks.algafood;

import static io.restassured.RestAssured.given;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

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
public class RestaurantServiceApiT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private KitchenRepository kitchenRepository;

	private final int RESTAURANT_ID_INVALID = 100;

	private String jsonRestaurant;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurants";

		jsonRestaurant = ResourceUtils.getJsonDataObject("/json/restaurant-mock-json.json", "valid");
		databaseCleaner.clearTables();
		this.insertData();
	}
	
	@Test
	public void returnStatusOkWhenGetRestaurantsTest() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void returnStatusCreatedWhenInsertRestaurantsTest() {
		given()
			.body(jsonRestaurant)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	private void insertData() {
		Kitchen kitchen = new Kitchen();
		kitchen.setName("Brasileira");
		kitchenRepository.save(kitchen);

		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Americana");
		kitchenRepository.save(kitchen2);
	}
}