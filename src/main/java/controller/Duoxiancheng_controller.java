package controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
	MD5 md5 = new MD5();//���ڸ��ļ�������
	InetAddress ia=null;//���ڻ�ȡ����ip
	@RequestMapping("/upload")
	public @ResponseBody JsonInfo add(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// ���������е�����
		
		MultipartFile file = mpRequest.getFile("file");// �ļ���MultipartFile���͵�

		Docmentory doc = new Docmentory();

		MD5 md5 = new MD5();

		Date date = new Date();// ���õ�ǰʱ��
		
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//����uuid
		
		String lastname = file.getOriginalFilename(); // ��ȡ�ļ�����

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		String datime = format.format(date);// ��ȡ��ǰʱ����ַ���
		
		String nowname = md5.MD5(uuid).substring(0, 8);// ���ļ������ܲ��ҽ�ȡǰ��λ
		
		String day = datime.substring(0, 8);//��ȡ����
		
		String hour = datime.substring(8, 10);//��ȡ��ǰʱ���Сʱ

		doc.setLastname(lastname);

		doc.setCreatetime(date.getTime());
		
        String localip=ia.getLocalHost().getHostAddress();//��ȡ����ip
        
        String filename = nowname+ lastname.substring(lastname.lastIndexOf("."), lastname.length());
        
		String downloadpath = "http://"+localip+":8080/upload/upload/" + day + "/" + hour + "/" + filename;// �洢�����ݿ�·��
		
		String path = request.getRealPath("/upload/"+day+"/"+hour);// �����ϴ�·��
		
		doc.setPath(downloadpath);
		
		doc.setNowname(nowname);
		
		// ��MultipartFile�ļ�ת��Ϊfile�ļ�
	    long size_limit =	file.getSize();// ��ȡ�ļ���С
	    
		doc.setSize_limit(size_limit);

		service.insertjl(doc);// �޸����ݿ�
		
		gongjulei  gg = new gongjulei(file,filename,path);
		
		new Thread(gg, "�߳�"+uuid).start(); // ����һ���߳� �ϴ��ļ�
		
		//System.out.println(downloadpath);// ����洢�����ݿ��·��

		//System.out.println(path);// ����洢�����������ϼ�·��

		return new JsonInfo(1, downloadpath);
	}
}
