package stepsDefinations;

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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class stepDefinationForDataDriven extends Utils{
	RequestSpecification rs;
	ResponseSpecification res;
	Response response;
	
	@Given("Add the data through {string} {string} {string}")
	public void add_the_data_through(String name, String language, String address) throws IOException {
		TestDatabuild tbd  = new TestDatabuild();
		
		res  = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		rs = given().spec(requestSpecification()).body(tbd.dataDrivenPayload(name,language,address));
		
		
	}
	
	@When("user calls {string} with the post http request")
	public void user_calls_with_the_post_http_request(String apiCall) {
		response = rs.when().post("/maps/api/place/add/json").then().spec(res).extract().response();
		
	}
	
	@Then("the API call got success with the status code {int}")
		public void the_API_call_got_success_with_the_status_code(Integer code) {
		assertEquals(response.getStatusCode(),200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedvalue) {
		String resp=  response.asString();
		JsonPath jp = new JsonPath(resp);
		assertEquals(jp.get(keyValue).toString(),expectedvalue);
		
	}
		
	

}
