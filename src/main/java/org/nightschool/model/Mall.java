package org.nightschool.model;


import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.nightschool.controller.MallController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by carwest on 15-1-8.
 */

public class Mall {
    private ArrayList<Merchandise> merList;
    private ObjectMapper mapper = new ObjectMapper();
    private static Mall mall=null;
    private HashMap<Integer, Merchandise> merMap;
    private HashMap<Integer, Integer> cartMap;
    private int ItemCnt = 0;

    public static Mall getMall(){
        if ( mall == null ){
            mall = new Mall();
            return mall;
        }else {
            return mall;
        }
    }
    private void init(){
        Merchandise m1 = new Merchandise(1, "cz80", "fast usb", "pics/cz80.png", 300.0);
        Merchandise m2 = new Merchandise(2, "cz80", "fast usb", "pics/cz80.png", 200.0);
        Merchandise m3 = new Merchandise(3, "cz80", "fast usb", "pics/cz80.png", 100.0);
        merList.add( m1 );
        merList.add( m2 );
        merList.add( m3);
        merMap.put(m1.get_id(), m1);
        merMap.put(m2.get_id(), m2);
        merMap.put(m3.get_id(), m3);
        ItemCnt = 3;
    }
    private Mall(){
        merList = new ArrayList<Merchandise>();
        merMap = new HashMap<Integer, Merchandise>();
        cartMap = new HashMap<Integer, Integer>();
        init();
    }
    private void getMerchandises(){
        merList.add(new Merchandise(1, "cz80", "fast usb", "pics/cz80.png", 300.0));
    }

    public String showoff() throws IOException {

        MallController.notice("Mall:\t showoff");
        ArrayNode root = mapper.createArrayNode();

        for ( Merchandise m : merList){
            JsonNode node= mapper.createObjectNode();
            ((ObjectNode)node).put("id", m.get_id());
            ((ObjectNode)node).put("img", m.get_imgUrl());
            ((ObjectNode)node).put("name", m.get_name());
            ((ObjectNode)node).put("desc", m.get_desc());
            ((ObjectNode)node).put("price", m.get_price());
            root.add(node);
        }

        String returnList = mapper.writeValueAsString(root);
        MallController.notice(returnList);
        return returnList;
    }

    public String showoffCart() throws IOException {
        MallController.notice("Mall:\t showoffCart");
        ArrayNode root = mapper.createArrayNode();

        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            Integer key = entry.getKey();
            Integer number = entry.getValue();
            JsonNode node = mapper.createObjectNode();
            ((ObjectNode)node).put("id", key);
            ((ObjectNode)node).put("num", number);

            Merchandise m = getMerById(key);
            if ( m != null ) {
                ((ObjectNode) node).put("name", m.get_name());
                ((ObjectNode) node).put("price", m.get_price());
                ((ObjectNode) node).put("imgUrl", m.get_imgUrl());
                root.add(node);
            }

        }

        String returnList = mapper.writeValueAsString(root);
        MallController.notice(returnList);
        return returnList;
    }

    public List<Merchandise> getMerList() {
        return merList;
    }

    public void addToMall(String name, String desc, String url, double price) {
        ItemCnt += 1;
        Merchandise m = new Merchandise( ItemCnt, name, desc, url, price );
        merList.add(m);
        merMap.put(ItemCnt, m);
    }

    public void delById(int id) {
        System.out.println( "Mall: delById " + id);

        for ( Merchandise m : merList){
            if ( m.get_id() == id ){
                merMap.remove(id);
                merList.remove(m);
                break;
            }
        }
    }

    public Merchandise getMerById(int id){
        return merMap.get(id);
    }

    public void addToCartById(int id, int number) {
        if ( cartMap.get(id) == null ){
            cartMap.put(id, number);
        }else {
            cartMap.put( id, cartMap.get(id) + number);
        }
    }

    public HashMap getMerMap() {
        return merMap;
    }

    public HashMap getCartMap() {
        return cartMap;
    }

    public void delFromCartById(int id) {
        cartMap.remove(id);
    }

}
