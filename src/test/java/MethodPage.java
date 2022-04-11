import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.RestAssuredConfig.config;

public class MethodPage {

    public static final Logger logger = Logger.getLogger("RestAssured");

    public void getInstallmentTrue() {

        RestAssured.config = config().decoderConfig(decoderConfig().useNoWrapForInflateDecoding(true));

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = (Response) requestSpecification
                .header("Accept-Encoding", "gzip")
                .queryParam("installment",1)
                .contentType(ContentType.JSON)
                .when()
                .get(BasePage.baseUrl).body();

        int getPostmanCollection = given()
                .contentType(ContentType.JSON)
                .queryParam("installment",1)
                .when()
                .get(BasePage.baseUrl)
                .getStatusCode();

        logger.info("Response Code: " + getPostmanCollection);
        Assert.assertEquals(getPostmanCollection,200);
        logger.info("İlgili postman collection başarılı bir şekilde listelendi.");
        logger.info("Taksit seçeneği olan ürünlerin listelenmesi işlemi başladı.");

        List<Map<JsonPath, JsonPath>> itemResponseBody = response.jsonPath().getList("item.response.body");

        String installmentTrue = String.valueOf(itemResponseBody.get(0));

        int i = installmentTrue.indexOf("{");
        installmentTrue = installmentTrue.substring(i);
        JSONObject json = new JSONObject(installmentTrue.trim());

        JSONObject jsonResult = json.getJSONObject("result");
        JSONObject jsonData = jsonResult.getJSONObject("data");
        JSONArray jsonProduct = jsonData.getJSONArray("products");

        for (int j=1; j<jsonProduct.length();j++){

            JSONObject jsonObjectTrueBody = jsonProduct.getJSONObject(j);
            String name = jsonObjectTrueBody.optString("name");
            String installmentText = jsonObjectTrueBody.optString("installmentText");
            String productGroupId = jsonObjectTrueBody.optString("productGroupId");
            String installment = jsonObjectTrueBody.optString("installment");
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + name);
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + installmentText);
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + productGroupId);
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + installment + "\n");
        }
    }

    public void getInstallmentFalse() {

        RestAssured.config = config().decoderConfig(decoderConfig().useNoWrapForInflateDecoding(true));

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = (Response) requestSpecification
                .header("Accept-Encoding", "gzip")
                .queryParam("installment",0)
                .contentType(ContentType.JSON)
                .when()
                .get(BasePage.baseUrl).body();

        int getPostmanCollection = given()
                .contentType(ContentType.JSON)
                .queryParam("installment",0)
                .when()
                .get(BasePage.baseUrl)
                .getStatusCode();

        logger.info("Response Code: " + getPostmanCollection);
        Assert.assertEquals(getPostmanCollection,200);
        logger.info("İlgili postman collection başarılı bir şekilde listelendi.");
        logger.info("Taksit seçeneği olmayan ürünlerin listelenmesi işlemi başladı.");

        List<Map<JsonPath, JsonPath>> itemResponseBody = response.jsonPath().getList("item.response.body");

        String installmentFalse = String.valueOf(itemResponseBody.get(1));

        int i = installmentFalse.indexOf("{");
        installmentFalse = installmentFalse.substring(i);
        JSONObject json = new JSONObject(installmentFalse.trim());

        JSONObject jsonResult = json.getJSONObject("result");
        JSONObject jsonData = jsonResult.getJSONObject("data");
        JSONArray jsonProduct = jsonData.getJSONArray("products");


        for (int j=1; j<jsonProduct.length();j++){

            JSONObject jsonObjectFalseBody = jsonProduct.getJSONObject(j);
            String name = jsonObjectFalseBody.optString("name");
            String installmentText = jsonObjectFalseBody.optString("installmentText");
            String productGroupId = jsonObjectFalseBody.optString("productGroupId");
            String installment = jsonObjectFalseBody.optString("installment");
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + name);
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + installmentText);
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + productGroupId);
            logger.info("Taksit seçeneği olan product listesi " + j +".ürün: " + installment + "\n");
        }
    }

    public void getInstallmentNull() {

        RestAssured.config = config().decoderConfig(decoderConfig().useNoWrapForInflateDecoding(true));

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = (Response) requestSpecification
                .header("Accept-Encoding", "gzip")
                .queryParam("installment","")
                .contentType(ContentType.JSON)
                .when()
                .get(BasePage.baseUrl).body();

        int getPostmanCollection = given()
                .contentType(ContentType.JSON)
                .queryParam("installment","")
                .when()
                .get(BasePage.baseUrl)
                .getStatusCode();

        logger.info("Response Code: " + getPostmanCollection);
        Assert.assertEquals(getPostmanCollection,200);
        logger.info("İlgili postman collection başarılı bir şekilde listelendi.");
        logger.info("Status code 500 kontrol işlemi başladı.");

        List<Map<JsonPath, JsonPath>> itemResponseCode = response.jsonPath().getList("item.response.code");
        List<Map<JsonPath, JsonPath>> itemResponseStatus = response.jsonPath().getList("item.response.status");

        String installmentNullCode = String.valueOf(itemResponseCode.get(2));
        String installmentNullStatus = String.valueOf(itemResponseStatus.get(2));
        logger.info("Code: " + installmentNullCode);
        logger.info("Status: " + installmentNullStatus);
    }
}
