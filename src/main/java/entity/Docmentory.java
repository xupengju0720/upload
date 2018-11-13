package entity;

import java.util.Date;

public class Docmentory {
	private long id;// 主键自增;
	private String nowname;// 文件现在的名字
	private String lastname;// 文件之前的名字
	private String path;// 上传路径
	private long createtime;// 文件上传的日期
	private long size_limit;// 文件大小


	public Docmentory() {
		super();
	}

	@Override
	public String toString() {
		return "Docmentory [id=" + id + ", nowname=" + nowname + ", lastname=" + lastname + ", path=" + path
				+ ", createtime=" + createtime + ", size_limit=" + size_limit + "]";
	}


	public Docmentory(long id, String nowname, String lastname, String path, long createtime, long size_limit) {
		super();
		this.id = id;
		this.nowname = nowname;
		this.lastname = lastname;
		this.path = path;
		this.createtime = createtime;
		this.size_limit = size_limit;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNowname() {
		return nowname;
	}

	public void setNowname(String nowname) {
		this.nowname = nowname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize_limit() {
		return size_limit;
	}

	public void setSize_limit(long size_limit) {
		this.size_limit = size_limit;
	}


	public long getCreatetime() {
		return createtime;
	}


	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

}
