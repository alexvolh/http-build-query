package com.apnot.hquery.service;

import com.apnot.hquery.HqueryApplication;
import com.apnot.hquery.model.Details;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.apnot.hquery.helper.ParamsHelper.httpBuildQuery;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({DetailsServiceClient.class, HqueryApplication.class})
public class DetailsServiceClientIntegrationTest {

    @Autowired
    private DetailsServiceClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, Object> params;

    private final String expectedURI = "https://megapersonals.com/user?authenticate%5Buser%5D=admin&authenticate%5Bpassword%5D=xxxxx&transaction%5BProfileID%5D=999&transaction%5BCustomerID%5D=777&transaction%5BCustomerTransID%5D=x789dsahj67679";

    @Before
    public void setUpServer() throws Exception {
        String detailsString = objectMapper.
                writeValueAsString(new Details("test_admin", "s@40!bxE%5", 812, 34, "x789dsahj6767"));
        System.out.println("ValueAsString : " + detailsString);
        this.server.expect(requestTo(expectedURI))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }

    //Init nested map with params data
    @Before
    public void setUpParams() {
        params = new LinkedHashMap<>();

        //Nested map authenticate level 1
        Map<String, Object> authenticate = new LinkedHashMap<>();
        authenticate.put("user", "admin");
        authenticate.put("password", "xxxxx");

        //Nested map transaction level 1
        Map<String, Object> transaction = new LinkedHashMap<>();
        transaction.put("ProfileID", "999");
        transaction.put("CustomerID", "777");
        transaction.put("CustomerTransID", "x789dsahj67679");

        /*//Map rdata level 2
        Map<String, Object> rdata = new LinkedHashMap<>();
        rdata.put("level-2-1", "param-1");
        rdata.put("level-2-2", "param-2");

        transaction.put("rdata", rdata);*/

        params.put("authenticate", authenticate);
        params.put("transaction", transaction);

//        params.put("list", Arrays.asList("Val0 Val0", "Val1", "Val2"));
    }

    @Test
    public void testGetDetails() {
        Details details = client.getDetails(httpBuildQuery(params, "UTF-8"));
        System.out.println("Details : " + details);
    }

    @Test
    public void testExchangeWithPost() throws JsonProcessingException {
        String requestParams = httpBuildQuery(params, "UTF-8");

        System.out.println("requestParams : " + requestParams);

        Details details = client.exchangeWithPost(requestParams).getBody();
        String jsonNode = objectMapper.writeValueAsString(details);

        System.out.println("JsonNode : " + jsonNode);
//        System.out.println("Response body: " + client.exchangeWithPost(requestParams));
    }
}
