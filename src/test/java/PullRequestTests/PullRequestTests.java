package PullRequestTests;

import pullRequestUtil.pullRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;



public class PullRequestTests {

    private final String token = "b512d4377e5cac0c0f2e3fad4f5962d6f2c403f2";
    private int newPR;

    String prToCreate = "{\n" +
            "  \"title\": \"Amazing new feature\",\n" +
            "  \"head\": \"octocat:new-feature\",\n" +
            "  \"base\":\"master\",\n" +
            "  \"body\": \"Please pull this in\"\n" +
            "}";
    String prToUpdate = "{\n" +
            "  \"title\": \"new title\",\n" +
            "  \"state\": \"open\",\n" +
            "  \"base\":\"master\",\n" +
            "  \"body\": \"Updated body\"\n" +
            "}";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.github.com/repos";
    }

    @Test(description = "checking status code when trying to access pull request")
    public void checkPullRequestAccess() {
        Response response = given().auth().oauth2(token).log().everything().get(baseURI + "/aysegul1/GithubAPITesting/pulls").andReturn();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "checkPullRequestAccess", description = "create a pull request")
    public int createPullRequest() {
        Response response = given().auth().oauth2(token).log().everything().body(prToCreate).post(baseURI + "/aysegul1/GithubAPITesting/pulls").andReturn();
        Assert.assertEquals(response.statusCode(), 201);
        return newPR = response.as(pullRequest.class).getNumber();
    }

    @Test(dependsOnMethods = "createPullRequest", description = "get newly created pull request")
    public void getNewPullRequest() {
        Response response = given().auth().oauth2(token).log().everything().get(baseURI + "/aysegul1/GithubAPITesting/pulls/" + newPR).andReturn();
        pullRequest pullRequest = response.as(pullRequest.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(pullRequest.getTitle().contains("Amazing new feature"));
    }

    @Test(dependsOnMethods = "getNewPullRequest", description = "edit existing pull request")
    public void updateExistingPullRequest() {
        Response response = given().auth().oauth2(token).body(prToUpdate).with().contentType("application/json").log().everything().patch(baseURI + "/aysegul1/GithubAPITesting/pulls/" + newPR).andReturn();
        String actualTitle = response.as(pullRequest.class).getTitle();
        String expectedTitle = "new title";

        String actualState = response.as(pullRequest.class).getState();
        String expectedState = "open";

        String actualBase = response.as(pullRequest.class).getBase();
        String expectedBase = "master";

        String actualBody = response.as(pullRequest.class).getBody();
        String expectedBody = "Updated body";

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(expectedTitle, actualTitle);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(expectedState, actualState);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(expectedBase, actualBase);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(expectedBody, actualBody);
    }

    @Test(dependsOnMethods = "updateExistingPullRequest", description = "delete pull request")
    public void deletePullRequest() {
        Response response = given().auth().oauth2(token).log().everything().delete(baseURI + "/aysegul1/GithubAPITesting/pulls/" + newPR).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
