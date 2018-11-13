package entity;

import java.util.Date;

public class Docmentory {
	private long id;// ��������;
	private String nowname;// �ļ����ڵ�����
	private String lastname;// �ļ�֮ǰ������
	private String path;// �ϴ�·��
	private long createtime;// �ļ��ϴ�������
	private long size_limit;// �ļ���С


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
