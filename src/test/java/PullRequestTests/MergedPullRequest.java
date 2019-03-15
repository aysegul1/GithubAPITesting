package PullRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class MergedPullRequest {

    private final String token = "fb3f9461a6c5cb365d4402c3e59d15b2418bdbd5";
    PullRequestTests pullRequestTests = new PullRequestTests();
    int prNumber = pullRequestTests.createPullRequest();


    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.github.com/repos/aysegul1/GithubAPITesting/pull";

    }

    @Test(description = "Merge a pull request")
    public void mergePullRequest(){
        Response response = given().auth().oauth2(token).log().everything().put(baseURI + prNumber+"/merge").andReturn();
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test(dependsOnMethods = "mergePullRequest", description = "check if a pull request is merged")
    public void checkIfPRisMerged() {
        Response response = given().auth().oauth2(token).log().everything().get(baseURI + prNumber + "/merge").andReturn();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
