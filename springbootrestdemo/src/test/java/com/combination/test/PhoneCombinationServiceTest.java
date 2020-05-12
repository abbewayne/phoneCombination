package com.combination.test;

import com.combination.rest.SpringBootDemoApplication;
import com.combination.rest.service.PhoneCombinationServiceI;
import com.combination.rest.util.PaginatedResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= SpringBootDemoApplication.class)
public class PhoneCombinationServiceTest {

    @Autowired
    private PhoneCombinationServiceI phoneCombinationService;

    @Test
    public void simpleResultTest() throws JSONException, JsonProcessingException {
        List<String> result = new ArrayList<String>();
        result.add("2");
        result.add("A");
        result.add("B");
        result.add("C");

        Assert.assertEquals(result, phoneCombinationService.phoneCombinationCalc("2"));

        PaginatedResponse paginatedResponse = new PaginatedResponse(0, phoneCombinationService.phoneCombinationCalc("2"));

        String actual = mapToJson(paginatedResponse);
        Assert.assertEquals(0, paginatedResponse.getPageNumber());
        Assert.assertEquals(4, paginatedResponse.getTotalRow());
        Assert.assertEquals(10, paginatedResponse.getRow());
        Assert.assertEquals(1, paginatedResponse.getTotalPage());

        PaginatedResponse paginatedResponse1 = new PaginatedResponse(1, phoneCombinationService.phoneCombinationCalc("2"));

        String actual1 = mapToJson(paginatedResponse);
        Assert.assertEquals(1, paginatedResponse1.getPageNumber());
        Assert.assertEquals(4, paginatedResponse1.getTotalRow());
        Assert.assertEquals(10, paginatedResponse1.getRow());
        Assert.assertEquals(1, paginatedResponse1.getTotalPage());

    }

    @Test
    public void emptyInputResultTest() {
        List<String> result = new ArrayList<String>();

        Assert.assertEquals(result, phoneCombinationService.phoneCombinationCalc(""));
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
