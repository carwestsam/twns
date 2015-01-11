package org.nightschool.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by carwest on 15-1-10.
 */
public interface DiskMapper {
    @Select("Select * from item")
    public List<Disk> getDisks();
//    public Disk(String name, String imgUrl, String desc, int number, double price) {

    //@SelectKey(statement="call next value for TestSequence", keyProperty="id", before=true, resultType=int.class)
    @Insert("insert into item (id, name, img_url, description, price, count) values(101, #{name}, #{imgUrl}, #{desc}, #{price}, #{number})")
    public int insert(Disk disk);
}



