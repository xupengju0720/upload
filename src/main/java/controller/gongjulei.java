package controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class gongjulei extends Thread {
	private MultipartFile file;// ��Ҫ�ϴ����ļ�
    private String file_name; //�ļ�����
	private String path="";// �ļ��ϴ���·��
	public gongjulei() {
		super();
	}
	public gongjulei(MultipartFile file, String file_name, String path) {
		super();
		this.file = file;
		this.file_name = file_name;
		this.path = path;
	}


	public String getFile_name() {
		return file_name;
	}


	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}


	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public synchronized void run() {
		File file_path = new File(path);
		try {
			if (!file_path.exists()) {// ����ļ��в�����
				file_path.mkdirs();// �����ļ���
			}
			file.transferTo(new File(path+"/"+ file_name));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}