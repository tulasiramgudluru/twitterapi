package com.letskodeit.statuses;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testBase.TestBase;

import com.letskodeit.common.RestUtilities;
import com.letskodeit.constants.EndPoints;
import com.letskodeit.constants.Path;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


public class UserTimelineTest extends TestBase {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	//String tweetId = "";
	
	@BeforeClass
	public void setup() {
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.queryParam("user_id", "gudluruthulasi1");
		reqSpec.basePath(Path.STATUSES);
		
		resSpec = RestUtilities.getResponseSpecification();
	}

	@Test
	public void readTweets1() {
		given()
			.spec(RestUtilities.createQueryParam(reqSpec, "count", "1"))
		.when()
			.get(EndPoints.STATUSES_USER_TIMELINE)
		.then()
			//.log().all()
			.spec(resSpec)
			.body("user.screen_name", hasItem("gudluruthulasi1"));
		
		//System.out.println(resSpec);
	}
	
	@Test
	public void readTweets2() {
		RestUtilities.setEndPoint(EndPoints.STATUSES_USER_TIMELINE);
		Response res = RestUtilities.getResponse(
				RestUtilities.createQueryParam(reqSpec, "count", "2"), "get");
		ArrayList<String> screenNameList = res.path("user.screen_name");
		System.out.println("Read Tweets 2 Method");
		Assert.assertTrue(screenNameList.contains("gudluruthulasi1"));
		/*JsonPath jsPath = RestUtilities.getJsonPath(res);
		tweetId = jsPath.get("id_str");
		System.out.println("The response.path: " + tweetId);*/
		
	}
}