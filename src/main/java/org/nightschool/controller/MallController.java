package org.nightschool.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nightschool.model.Mall;
import org.nightschool.model.Merchandise;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by carwest on 15-1-8.
 */

@Path("/mall")
@Produces(MediaType.APPLICATION_JSON)
public class MallController {
    private Mall mall = Mall.getMall();
    private ObjectMapper mapper = new ObjectMapper();

    @GET
    public String showoff()  {
        //System.out.println("\n\n\n\n\nhere\n\n\n\n\n");
        String tmp = "failed";
        try {
            tmp = mall.showoff();
            return tmp;
        }catch ( IOException e ) {
            return "";
        }
    }

    @POST
    @Path("instr")
    public void dealPosts( String post ) throws IOException{
        JsonNode rootNode =  mapper.readTree(post);
        String instr = rootNode.path("instruct").textValue();
        if ( instr.equals("addToMall") ){
            dealAddToMall( rootNode.path("data") );
        }
    }

    private void dealAddToMall(JsonNode data) throws JsonProcessingException {
        String str;

        str = mapper.writeValueAsString(data);
        noticeWithTitle("dealAddToMall", str);
        String url = data.path("imgUrl").textValue();
        System.out.println(data.path("title"));
        mall.addToMall(
                data.path("title").textValue(),
                data.path("desc").textValue(),
                data.path("imgUrl").textValue(),
                Double.parseDouble(data.path("price").textValue()));
    }

    private void noticeWithTitle( String title, String note){
        System.out.println("**" + title + "**");
        notice(note);
    }
    private void notice(String note){
        System.out.println("-------------");
        System.out.println(note);
        System.out.println("-------------");
    }
}
