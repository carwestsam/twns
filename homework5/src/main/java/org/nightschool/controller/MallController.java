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
        String tmp = "failed";
        try {
            notice("MallController:\t showoff");
            tmp = mall.showoff();
            return tmp;
        }catch ( IOException e ) {
            return "get error at MallController:showoff";
        }
    }

    @GET
    @Path("cart")
    public String getCartInform()  {
        notice ("MallController:getCartInform");
        try{
            String tmp = mall.showoffCart();
            return tmp;
        }catch (IOException e){
            System.out.println("get error at getCartInform");
            return "failed";
        }

    }

    @POST
    @Path("instr")
    public void dealPosts( String post ) throws IOException{
        JsonNode rootNode =  mapper.readTree(post);
        String instr = rootNode.path("instruct").textValue();
        JsonNode data = rootNode.path("data");
        notice("deal Posts");
        notice(instr);
        if ( instr.equals("addToMall") ){
            dealAddToMall( rootNode.path("data") );
        }else if ( instr.equals("deleteByIdFromMall") ){
            dealDeleteByIdFromMall(data);
        }else if ( instr.equals("addToCart")){
            dealAddToCart(data);
        }
    }

    private void dealAddToCart(JsonNode data) throws JsonProcessingException {
        notice("MallController: dealAddToCart");
        outJson(data);
        int id = data.path("Id").asInt();
        int num = data.path("Num").asInt();
        Mall.getMall().addToCartById(id, num);
        notice("MallController: dealAddToCart finish");

    }

    private void dealAddToMall(JsonNode data) throws JsonProcessingException {
        String str;

        str = mapper.writeValueAsString(data);
        noticeWithTitle("dealAddToMall", str);
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
    public static void notice(String note){
        System.out.println("-------------");
        System.out.println(note);
        System.out.println("-------------");
    }

    public void dealDeleteByIdFromMall(JsonNode data) throws JsonProcessingException {
        notice("MallController: delete By Id From Mall");
        outJson(data);
        int id = data.path("Id").asInt();
        notice(String.valueOf(id));
        Mall.getMall().delById(id);
        notice("MallController: delete By Id From Mall ** finish");
    }

    public void outJson( JsonNode data ) throws JsonProcessingException {
        String str = mapper.writeValueAsString(data);
        notice( str );
    }
}
