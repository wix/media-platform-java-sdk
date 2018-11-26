package com.wix.mediaplatform.v6.service.flowcontrol;

import com.wix.mediaplatform.v6.BaseTest;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FlowControlServiceTest extends BaseTest {

    private FlowControlService flowControlService = new FlowControlService(configuration, authenticatedHttpClient);

    @Test
    public void getFlowState() throws Exception {
        stubFor(get(urlEqualTo("/_api/flow_control/flow/49eca277747047c5833f15a0eed137b9"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-flow-state-response.json")));

        FlowState flowState = flowControlService.flowStateRequest("49eca277747047c5833f15a0eed137b9")
                .execute();

        assertThat(flowState.getId(), is("49eca277747047c5833f15a0eed137b9"));
    }

    @Test
    public void invokeFlow() throws Exception {
        stubFor(post(urlEqualTo("/_api/flow_control/flow"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("invoke-flow-response.json")));

        FlowState flowState = flowControlService.flowInvocationRequest().execute();

        assertThat(flowState.getId(), is("49eca277747047c5833f15a0eed137b9"));
    }

    @Test
    public void abortFlow() throws Exception {
        stubFor(delete(urlEqualTo("/_api/flow_control/flow/49eca277747047c5833f15a0eed137b9"))
            .willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("null-payload-response.json")));

        flowControlService.abortFlowRequest("49eca277747047c5833f15a0eed137b9").execute();
    }
}
