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
	MD5 md5 = new MD5();//用于给文件名加密
	InetAddress ia=null;//用于获取本机ip
	@RequestMapping("/upload")
	public @ResponseBody JsonInfo add(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// 解析请求中的数据
		
		MultipartFile file = mpRequest.getFile("file");// 文件是MultipartFile类型的

		Docmentory doc = new Docmentory();

		MD5 md5 = new MD5();

		Date date = new Date();// 设置当前时间
		
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//生成uuid
		
		String lastname = file.getOriginalFilename(); // 获取文件名称

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		String datime = format.format(date);// 获取当前时间的字符串
		
		String nowname = md5.MD5(uuid).substring(0, 8);// 对文件名加密并且截取前八位
		
		String day = datime.substring(0, 8);//获取当天
		
		String hour = datime.substring(8, 10);//获取当前时间的小时

		doc.setLastname(lastname);

		doc.setCreatetime(date.getTime());
		
        String localip=ia.getLocalHost().getHostAddress();//获取本机ip
        
        String filename = nowname+ lastname.substring(lastname.lastIndexOf("."), lastname.length());
        
		String downloadpath = "http://"+localip+":8080/upload/upload/" + day + "/" + hour + "/" + filename;// 存储到数据库路径
		
		String path = request.getRealPath("/upload/"+day+"/"+hour);// 设置上传路径
		
		doc.setPath(downloadpath);
		
		doc.setNowname(nowname);
		
		// 将MultipartFile文件转换为file文件
	    long size_limit =	file.getSize();// 获取文件大小
	    
		doc.setSize_limit(size_limit);

		service.insertjl(doc);// 修改数据库
		
		gongjulei  gg = new gongjulei(file,filename,path);
		
		new Thread(gg, "线程"+uuid).start(); // 启动一个线程 上传文件
		
		//System.out.println(downloadpath);// 输出存储到数据库的路径

		//System.out.println(path);// 输出存储到服务器的上级路径

		return new JsonInfo(1, downloadpath);
	}
}
