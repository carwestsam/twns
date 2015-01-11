package org.nightschool.MyBatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MybatisUtilTest {
    @Test
    public void shouldGetSessionFactory() throws Exception {
        SqlSessionFactory factory = MybatisUtil.getFactory();
        assertNotNull(factory);
    }
}