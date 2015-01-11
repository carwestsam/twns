package org.nightschool.MyBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.eclipse.jetty.util.resource.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * Created by carwest on 15-1-10.
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSessionFactory getFactory() throws IOException {
        String resource = "mybatis/config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        if ( sqlSessionFactory == null) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;
    }
}
