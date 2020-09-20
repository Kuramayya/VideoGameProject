package com.qa.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC1_VideoGameAPITest {
	
	@Test(priority = 1)
	public void test_getAllVideoGames() {
			given()
			
			.when()
			.get("http://localhost:8080/app/videogames")
			
			
			.then()
			.statusCode(200);
	}
	
	@Test (priority = 2)
	public void test_addVideoGame() {
		
		
		Map<String,String> data = new HashMap<String,String>();
		
		
		data.put("id", "20");
		data.put("name", "Spider Man - Fly again!!!");
		data.put("releaseDate", "2001-04-23");
		data.put("reviewScore", "90");
		data.put("category", "Jumping+");
		data.put("rating", "Disney+");
		
		Response response =
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
		.post("http://localhost:8080/app/videogames")
		
		.then().statusCode(200)
		.log().body()
		.extract().response();
		String message = response.asString(); 
		Assert.assertEquals(message.contains("Record Added Successfully"), true);
	}
	
	@Test (priority = 3)
	public void test_getVideoGame() {
		
		given()
		
			.when()
				.get("http://localhost:8080/app/videogames/10")
			.then()	
				.statusCode(200)
				.log().body()
				.body("videoGame.id", equalTo("10"))
				.body("videoGame.name", equalTo("Grand Theft Auto III"));
			
				
	}
	
	@Test(priority = 4)
	public void test_putVideoGame() {
		
		Map<String,String> data = new HashMap<String,String>();
		
		data.put("id", "20");
		data.put("name", "Spider Man returns - Fly again updated");
		data.put("releaseDate", "2001-04-23");
		data.put("reviewScore", "90");
		data.put("category", "Jumping");
		data.put("rating", "Disney");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("http://localhost:8080/app/videogames/20")
			
		.then()
			.statusCode(200)
			.log().body()		
			.body("videoGame.id", equalTo("20"))
			.body("videoGame.name", equalTo("Spider Man returns - Fly again updated"));
			
	}
	
	@Test(priority = 5)
	public void test_deleteVideoGame() {
		
		Response res = 
		given()
		
			.when()
				.delete("http://localhost:8080/app/videogames/20")
		
			.then()
				.statusCode(200)
				.log().body()
				.extract().response();
		
		String message = res.asString();
		Assert.assertEquals(message.contains("Record Deleted Successfully"), true);
		}

}
