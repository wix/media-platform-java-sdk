package com.wix.mediaplatform.v6.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.job.JobList;
import com.wix.mediaplatform.v6.service.job.JobService;
import org.junit.Before;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JobServiceTest extends BaseTest {

    private JobService jobService = new JobService(configuration, authenticatedHttpClient);

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

        Job job = jobService.jobRequest("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f")
                .execute();

        assertThat(job.getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
    }

    @Test
    public void getJobs() throws Exception {
        stubFor(get(urlEqualTo("/_api/jobs/groups/71f0d3fde7f348ea89aa1173299146f8"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-job-group-response.json")));

        Job[] group = jobService.jobGroupRequest("71f0d3fde7f348ea89aa1173299146f8").execute();

        assertThat(group[0].getId(),
                is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
    }

    @Test
    public void searchJobs() throws Exception {
        stubFor(get(urlEqualTo("/_api/jobs?groupId=71f0d3fde7f348ea89aa1173299146f8"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("search-jobs-response.json")));

        JobList response = jobService.jobListRequest()
                .setGroupId("71f0d3fde7f348ea89aa1173299146f8")
                .execute();

        assertThat(response.getJobs()[0].getId(), is("71f0d3fde7f348ea89aa1173299146f8_19e137e8221b4a709220280b432f947f"));
    }
}
