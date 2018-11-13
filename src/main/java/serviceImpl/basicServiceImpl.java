package serviceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import service.basicService;
import utils.SeachInfo;
public class basicServiceImpl <T> implements basicService<T>{
	public Object getmapper() throws Exception{
		Field f=this.getClass().getDeclaredField("mapper");
		f.setAccessible(true);
		 return f.get(this);
	} 
	
	public List<T> getAll() {
		try {
			Object mapper=getmapper();
			return (List<T>) mapper.getClass().getDeclaredMethod("getAll").invoke(mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> getWhere(SeachInfo where) {
		try {
			Object mapper=getmapper();
			if(where.canpage){
				int count=(Integer) mapper.getClass().getDeclaredMethod("getSize",SeachInfo.class).invoke(mapper,where);
			 where.setSize(count);
			}
			
			Method m=mapper.getClass().getDeclaredMethod("getWhere",SeachInfo.class);
			return (List<T>) m.invoke(mapper,where);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insert(T t) {
		try {
			Object mapper=getmapper();
			Method m=mapper.getClass().getDeclaredMethod("insert",t.getClass());
			 return (Integer) m.invoke(mapper,t);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int delete(int id) {
		try {
			Object mapper=getmapper();
			Method m=mapper.getClass().getDeclaredMethod("delete",int.class);
			 return (Integer) m.invoke(mapper,id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int update(T t) {
		try {
			Object mapper=getmapper();
			Method m=mapper.getClass().getDeclaredMethod("update",t.getClass());
			 return (Integer) m.invoke(mapper,t);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public T getByid(int id) {
		try {
			Object mapper=getmapper();
			Method m=mapper.getClass().getDeclaredMethod("getByid",int.class);
			return (T) m.invoke(mapper,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getSize(SeachInfo where) {
		try {
			Object mapper=getmapper();
				return (Integer) mapper.getClass().getDeclaredMethod("getSize",SeachInfo.class).invoke(mapper,where);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<T> getWhereAll(String where) {
		return getWhere(new SeachInfo(where, false));
	}

}
