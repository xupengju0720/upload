package serviceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import entity.Docmentory;
import mapper.Docmentory_Mapper;
import service.Docmentory_Service;
@Service("Docmentory_ServiceImpl")
public class Docmentory_ServiceImpl extends basicServiceImpl<Docmentory> implements Docmentory_Service{
	
@Resource(name="Docmentory_Mapper")
Docmentory_Mapper mapper;

public Docmentory getbyid(Docmentory docmentory) {
	// TODO Auto-generated method stub
	return mapper.getbyid(docmentory);
}

public void insertjl(Docmentory doc) {
mapper.insertjl(doc);	
}



}


