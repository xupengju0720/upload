package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import entity.Docmentory;
import service.Docmentory_Service;
import utils.JsonInfo;
import utils.MD5;

@Controller
@RequestMapping("docmentory")
public class Duoxiancheng_controller {

	@Resource(name = "Docmentory_ServiceImpl")
	Docmentory_Service service;
	MD5 md5 = new MD5();

	@RequestMapping("/upload")
	public @ResponseBody JsonInfo add(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// ���������е�����
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mpRequest.getFile("file");// �ļ���MultipartFile���͵�

		Docmentory doc = new Docmentory();

		MD5 md5 = new MD5();

		Date date = new Date();// ���õ�ǰʱ��
		String lastname = file.getOriginalFilename(); // ��ȡ�ļ�����

		String nowname = md5.MD5(lastname).substring(0, 8);// ���ļ������ܲ��ҽ�ȡǰ��λ

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		String datime = format.format(date);// ��ȡ��ǰʱ����ַ���
		
		String day = datime.substring(0, 8);
		
		String hour = datime.substring(8, 10);

		String time = datime.substring(14,17);
		
		doc.setLastname(lastname);

		doc.setNowname(datime + nowname);

		doc.setCreatetime(date.getTime());

		String file_name = md5.MD5(datime+lastname).substring(0, 8);
		String downloadpath = "http://192.168.28.101:8080/examples/WEB-INF/upload/" + day + "/" + hour + "/" + file_name
				+ lastname.substring(lastname.lastIndexOf("."), lastname.length());// �洢�����ݿ�·��
		doc.setPath(downloadpath);
		String path = request.getRealPath("/WEB-INF/upload/"+day+"/"+hour);// �����ϴ�·�� ��Ŀ¼+ʱ�䴮+�ļ���MD5����֮���ǰ��λ
		
		String filename = file_name+ lastname.substring(lastname.lastIndexOf("."), lastname.length());
		
		// ��MultipartFile�ļ�ת��Ϊfile�ļ�
		CommonsMultipartFile cf = (CommonsMultipartFile) file;

		DiskFileItem fi = (DiskFileItem) cf.getFileItem();

		File f = fi.getStoreLocation();

		long size_limit = f.length();// ��ȡ�ļ���С

		doc.setSize_limit(size_limit);// �������������

		service.insertjl(doc);// �޸����ݿ�
		
		gongjulei  gg = new gongjulei(file,filename,path);
		
		new Thread(gg, "�߳�"+time).start(); // ����һ���߳� �ϴ��ļ�
		
		System.out.println(downloadpath);// ����洢�����ݿ��·��

		System.out.println(path);// ����洢�����������ϼ�·��

		return new JsonInfo(1, downloadpath);
	}
}
