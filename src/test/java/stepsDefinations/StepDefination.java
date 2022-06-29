package stepsDefinations;

import static io.restassured.RestAssured.given;



import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.TestDatabuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification rs;
	ResponseSpecification res;
	Response response;

	@Given("Add Place Payload")
	public void add_Place_Payload() throws IOException {
		//RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Serialize
		// Pojo class object is created and send it to the body

		TestDatabuild tbd = new TestDatabuild();

//		String response = given().queryParam("key", "qaclick123").body(pc).when().post("/maps/api/place/add/json").then().assertThat()
		// .statusCode(200).extract().response().asString();
		//RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		//		.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		rs = given().spec(requestSpecification()).body(tbd.addPlacePayload());

	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String apiCall) {
		response = rs.when().post("/maps/api/place/add/json").then().spec(res).extract().response();
		System.out.println(response);

	}

	@Then("the API call got success with the status code {int}")
	public void the_API_call_got_success_with_the_status_code(Integer code) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void status_in_response_body(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath jp = new JsonPath(resp);
		assertEquals(jp.get(keyValue).toString(), expectedValue);

	}

}
