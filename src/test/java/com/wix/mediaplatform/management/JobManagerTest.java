package com.wix.mediaplatform.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.Authenticator;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.request.SearchJobsRequest;
import com.wix.mediaplatform.dto.response.SearchJobsResponse;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JobManagerTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient AuthenticatedHTTPClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);
    private JobManager jobManager = new JobManager(configuration, AuthenticatedHTTPClient);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void getJob() throws Exception {
        stubFor(get(urlEqualTo("/_api/jobs/71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-job-response.json")));

        Job job = jobManager.getJob("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f");

        assertThat(job.getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
    }

    @Test
    public void getJobs() throws Exception {
        stubFor(get(urlEqualTo("/_api/jobs/groups/71f0d3fde7f348ea89aa1173299146f8"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-job-group-response.json")));

        Job[] jobs = jobManager.getJobGroup("71f0d3fde7f348ea89aa1173299146f8");

        assertThat(jobs[0].getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
    }

    @Test
    public void searchJobs() throws Exception {
        stubFor(get(urlEqualTo("/_api/jobs?groupId=71f0d3fde7f348ea89aa1173299146f8"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("search-jobs-response.json")));

        SearchJobsRequest searchJobsRequest = new SearchJobsRequest()
                .setGroupId("71f0d3fde7f348ea89aa1173299146f8");
        SearchJobsResponse response = jobManager.searchJobs(searchJobsRequest);

        assertThat(response.getJobs()[0].getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
    }
}
