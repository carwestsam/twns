package org.nightschool.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import org.nightschool.model.Mall;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MallControllerTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_delete_from_mall_by_id() throws Exception, IOException{
        MallController mallController = new MallController();

        JsonNode data = mapper.createObjectNode();
        ((ObjectNode)data).put("Id", "2");

        assertNotNull(data);
        Mall mall = Mall.getMall();
        mallController.dealDeleteByIdFromMall(data);
        assertThat(mall.getMerList().size(), is(2) );
        assertThat(mall.getMerList().get(1).get_id(), is(3));
    }
}
