package pages;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SubunitApiPage {
	RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    
	public String sendApiAndGetResponse (String cookies) {
		Cookie orangeHrmCookie = new Cookie.Builder("orangehrm", cookies)
                .build();
		 requestSpecification = given()
	                .baseUri("https://opensource-demo.orangehrmlive.com/web/index.php")
	                .header("Content-Type","application/json")
	                .cookie(orangeHrmCookie);

	        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
	                .expectContentType(ContentType.JSON)
	                .build();
	        
		 Response response = given().spec(requestSpecification).get("api/v2/dashboard/employees/subunit")
	                .then().spec(responseSpecification)
	                .extract().response();

	    return response.prettyPrint();
	}
	
	public void getValueOfMetaDataAndSubunitNameWithId(String response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response);
		
        // Get values for otherEmployeeCount, unassignedEmployeeCount, totalSubunitCount
        JSONObject meta = jsonObject.getJSONObject("meta");
        int otherEmployeeCount = meta.getInt("otherEmployeeCount");
        int unassignedEmployeeCount = meta.getInt("unassignedEmployeeCount");
        int totalSubunitCount = meta.getInt("totalSubunitCount");

        System.out.println("Other Employee Count: " + otherEmployeeCount);
        System.out.println("Unassigned Employee Count: " + unassignedEmployeeCount);
        System.out.println("Total Subunit Count: " + totalSubunitCount);

        // Find subunit name with id = 13
        JSONArray dataArray = jsonObject.getJSONArray("data");
        String subunitName = null;
        
        // Get the subunits array
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject subunitObject = dataArray.getJSONObject(i).getJSONObject("subunit");
            int id = subunitObject.getInt("id");

            if (id == 13) {
                subunitName = subunitObject.getString("name");
                System.out.println("Subunit Name with ID 13: " + subunitName);
                break; // Exit loop once found
            }
        }
	}
}
