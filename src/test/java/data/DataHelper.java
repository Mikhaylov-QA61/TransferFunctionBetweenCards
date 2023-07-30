package data;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.restassured.http.ContentType;
public class DataHelper {

    static Random random = new Random();
    private DataHelper(){
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    static void sendRequest(RegisteredUser user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegisteredUser getRegisteredUser(){
        return new RegisteredUser("vasya","qwerty123");
    }

    public static VerificationCode getVerificationCode(){
        VerificationCode VerificationCode = new VerificationCode("12345");
        return VerificationCode;
    }

    public static InfoCard getFirstCard(){
        InfoCard InfoCard = new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
        return InfoCard;
    }

    public static  InfoCard getSecondCard(){
        InfoCard InfoCard = new InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        return InfoCard;
    }

    public static int generateValidAmount(int balance){
        int validBalance = random.nextInt(balance) +1;
        return validBalance;
    }
    public static int generateInvalidAmount(int balance){
        int invalidBalance = balance + (10_000 + random.nextInt(66_666));
        return invalidBalance;
    }


    @Value
    public static class VerificationCode{
        String code;
    }
    @Value
    public static class InfoCard{
        String numberCard;
        String testId;
    }
    @Value
    public static class RegisteredUser {
        String login;
        String password;

    }
}
