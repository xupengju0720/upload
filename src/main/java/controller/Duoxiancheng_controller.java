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
		// 解析请求中的数据
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mpRequest.getFile("file");// 文件是MultipartFile类型的

		Docmentory doc = new Docmentory();

		MD5 md5 = new MD5();

		Date date = new Date();// 设置当前时间
		String lastname = file.getOriginalFilename(); // 获取文件名称

		String nowname = md5.MD5(lastname).substring(0, 8);// 对文件名加密并且截取前八位

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		String datime = format.format(date);// 获取当前时间的字符串
		
		String day = datime.substring(0, 8);
		
		String hour = datime.substring(8, 10);

		String time = datime.substring(14,17);
		
		doc.setLastname(lastname);

		doc.setNowname(datime + nowname);

		doc.setCreatetime(date.getTime());

		String file_name = md5.MD5(datime+lastname).substring(0, 8);
		String downloadpath = "http://192.168.28.101:8080/examples/WEB-INF/upload/" + day + "/" + hour + "/" + file_name
				+ lastname.substring(lastname.lastIndexOf("."), lastname.length());// 存储到数据库路径
		doc.setPath(downloadpath);
		String path = request.getRealPath("/WEB-INF/upload/"+day+"/"+hour);// 设置上传路径 根目录+时间串+文件名MD5加密之后的前八位
		
		String filename = file_name+ lastname.substring(lastname.lastIndexOf("."), lastname.length());
		
		// 将MultipartFile文件转换为file文件
		CommonsMultipartFile cf = (CommonsMultipartFile) file;

		DiskFileItem fi = (DiskFileItem) cf.getFileItem();

		File f = fi.getStoreLocation();

		long size_limit = f.length();// 获取文件大小

		doc.setSize_limit(size_limit);// 设置类对象属性

		service.insertjl(doc);// 修改数据库
		
		gongjulei  gg = new gongjulei(file,filename,path);
		
		new Thread(gg, "线程"+time).start(); // 启动一个线程 上传文件
		
		System.out.println(downloadpath);// 输出存储到数据库的路径

		System.out.println(path);// 输出存储到服务器的上级路径

		return new JsonInfo(1, downloadpath);
	}
}
