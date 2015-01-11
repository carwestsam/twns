package org.nightschool.model;

/**
 * Created by carwest on 15-1-10.
 */

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.nightschool.model.Disk;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class MallTest {

    Mall mall = Mall.getMall();
    @Before
    public void setUp() throws Exception {
        Mall mall = Mall.getMall();
    }

    @Test
    public void test_init_with_3_merchant() throws Exception {

        assertThat(mall.getMerList().size(), is(3));
    }

    @Test
    public void test_add_a_merchant_to_mall() throws Exception {
        mall.addToMall("name", "this is desc", "a url", 100.0);
        assertThat(mall.getMerList().size(), is(4));
        List<Merchandise> list = mall.getMerList();
        Merchandise merchandise = list.get( list.size() -1 );
        assertThat(merchandise.get_id(), is(4));
        assertThat(merchandise.get_name(), is("name"));
        assertThat(merchandise.get_desc(), is("this is desc"));
        assertThat(merchandise.get_imgUrl(), is("a url"));
        assertThat(merchandise.get_price(), Is.<Double>is(100.0));
    }

    @Test
    public void test_del_a_merchant_by_id() throws Exception {
        mall.delById(2);
        List<Merchandise> list = mall.getMerList();
        assertThat(list.size(), is(2));
        assertThat(list.get(0).get_id(), is(1));
        assertThat(list.get(1).get_id(), is(3));
    }

    @Test
    public void test_Merchant_HashMap() throws Exception {
        HashMap hashMap = mall.getMerMap();
        assertThat(hashMap.size(), is(3));
        Merchandise m1 = (Merchandise) hashMap.get(new Integer(1));
        assertThat(m1.get_id(), is(1));
        assertThat(m1.get_price(), Is.<Double>is(300.0));
        Merchandise m2 = (Merchandise) hashMap.get(new Integer(3));
        assertThat(m2.get_id(), is(3));
        assertThat(m2.get_price(), Is.<Double>is(100.0));
    }

    @Test
    public void test_add_a_merchant_to_Cart_by_id() throws Exception {
        HashMap hashMap = mall.getCartMap();
        assertThat(hashMap.size(), is(0));
        mall.addToCartById(2, 2);
        assertThat(hashMap.size(), is(1));
        mall.addToCartById(2,3);
        assertNotNull(hashMap.get(2));
        int num = (int) hashMap.get(2);
        assertThat(num, is(5));
    }

    @Test
    public void test_del_a_merchant_from_Cart_by_id() throws Exception {
        HashMap hashMap = mall.getCartMap();
        mall.addToCartById(2,3);
        mall.addToCartById(1,1);
        mall.delFromCartById(1);
        assertThat(hashMap.size(), is(1));
        assertNotNull(hashMap.get(2));
        int num = (int) hashMap.get(2);
        assertThat(num, is(3));
    }
}

