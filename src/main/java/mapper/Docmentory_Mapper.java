package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Docmentory;
import utils.SeachInfo;

@Repository("Docmentory_Mapper")

public interface Docmentory_Mapper extends BaicsMapper{
	//根据id  获取某行记录
    @Select("select * from yly_docmentory where yly_docmentory.id = #{id}")
    public Docmentory getbyid(Docmentory docmentory);
    //新增一条记录
    @Insert("insert into yly_docmentory(nowname,lastname,path,createtime,size_limit) values(#{nowname},#{lastname},#{path},#{createtime},#{size_limit})")
    public void insertjl(Docmentory doc);
    
    
}
