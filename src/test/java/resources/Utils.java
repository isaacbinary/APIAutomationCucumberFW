package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req; //do  ot create another instance - so using static
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (req==null) { //using so that in case of multiple test cases, they are logged separately in the log file instead of replacing the older ones
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt")); //create logs in separate file
		
		//RestAssured.baseURI="https://rahulshettyacademy.com"; (taken care of this in respecbuilder below)

		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
		.addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))  //send request logs to a separate file
		.addFilter(ResponseLoggingFilter.logResponseTo(log)) //send response logs to a separate file
		.setContentType(ContentType.JSON)
		.build();
		
		return req;
		}
		return req;
	} 
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\HIMANSHU RAWAT\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		
		String resp =  response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
