package PullRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class MergedPullRequest {

    private final String token = "c7d7bba2bc25165685d9c607b1ae3a03aa990909";
    PullRequestTests pullRequestTests = new PullRequestTests();
   // int prNumber = pullRequestTests.createPullRequest();


    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.github.com/repos/aysegul1/GithubAPITesting/pulls";

    }

    @Test(description = "Merge a pull request")
    public void mergePullRequest(){
        Response response = given().auth().oauth2(token).log().everything().put(baseURI + "/1/merge").andReturn();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "mergePullRequest", description = "check if a pull request is merged")
    public void checkIfPRisMerged() {
        Response response = given().auth().oauth2(token).log().everything().get(baseURI + "/1/merge").andReturn();
        Assert.assertEquals(response.getStatusCode(), 204);
    }


}
