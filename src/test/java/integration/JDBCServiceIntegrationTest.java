package integration;

import org.junit.Test;
import org.nightschool.dao.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JDBCServiceIntegrationTest
{
    @Test
    public void shouldInsertFToDatabaseAndGet() throws Exception {
        JDBCService jdbcService = new JDBCService();
        jdbcService.insert("insert into item values(73,'hellokitty','1','2',1,1)");
        ResultSet resultSet = jdbcService.select("select * from item where name='hellokitty'");
        resultSet.next();
        assertThat(resultSet.getString("name"), is("hellokitty"));
        jdbcService.insert("delete from item where name='hellokitty'");
    }

    @Test
    public void shouldGetDataFromDatabase() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        JDBCService jdbcService = new JDBCService();
        ResultSet result= jdbcService.select("select * from item");
        result.next();
        assertThat(result.getString("name"), is("小清新光盘"));
    }
}
