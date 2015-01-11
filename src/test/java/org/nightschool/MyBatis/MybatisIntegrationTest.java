package org.nightschool.MyBatis;

import org.junit.Test;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MybatisIntegrationTest
{
    @Test
    public void shouldGetDisks() throws Exception {
        DiskMapper mapper= MybatisUtil.getFactory().openSession().getMapper(DiskMapper.class);
        List<Disk> disks = mapper.getDisks();
        assertThat(disks.size(), is(49));
    }

    @Test
    public void shouldInsertItem() throws Exception {
        DiskMapper mapper = MybatisUtil.getFactory().openSession().getMapper(DiskMapper.class);

        mapper.insert( new Disk("myname", "myurl", "mydesc", 2, 20.0) );
        List<Disk> disks = mapper.getDisks();
        assertThat(disks.size(), is(50));
        //assertThat( disks.size(), is(47));
    }
}