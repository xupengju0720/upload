package mapper;

import org.apache.ibatis.annotations.Select;

public interface BaicsMapper {
	@Select("select @@identity ")
	public int Maxid();
}
