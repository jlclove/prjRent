package com.dyrent.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dyrent.base.model.JsonResult;
import com.dyrent.common.Contants;
import com.dyrent.model.HouseMessage;
import com.dyrent.service.HouseMessageService;

@Controller
public class HomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 509625753389700379L;
	@Autowired
	private HouseMessageService houseMessageService;

	@RequestMapping("/")
	protected String index(Model model,
			@ModelAttribute HouseMessage houseMessage) {
		model.addAttribute("houseMessage", houseMessage);
		model.addAttribute("messageList",
				houseMessageService.getListPage(houseMessage));
		model.addAttribute("totalCount",
				houseMessageService.getListCount(houseMessage));
		return "/house/more";
	}

	@RequestMapping("/upload")
	protected String upload(HttpServletRequest request, Model model) {
		model.addAttribute("type", request.getParameter("type"));
		return "/upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	protected String uploadPost(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
		String type = request.getParameter("type");
		model.addAttribute("type", request.getParameter("type"));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("pic");
		System.out.println(file.getOriginalFilename());
		// 获得输入流：
		try {
			URL path = type.equals("img") ? request.getServletContext()
					.getResource("/WEB-INF/imgs/") : request
					.getServletContext().getResource("/WEB-INF/rars/");
			String newFileName = System.currentTimeMillis()
					+ getSuffix(file.getOriginalFilename());
			System.out.println(path.getPath());
			InputStream input = file.getInputStream();
			OutputStream outputStream = new FileOutputStream(path.getPath()
					+ newFileName);
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = input.read(bytes)) != -1) {
				outputStream.write(bytes, 0, len);
			}
			input.close();
			outputStream.close();
			outputStream.flush();
			model.addAttribute("newFileName", newFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/upload";
	}

	private String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}

	@RequestMapping("/password/change")
	@ResponseBody
	protected JsonResult changePassword(HttpServletRequest request, Model model) {
		Contants.PASSWORD = request.getParameter("password");
		return JsonResult.OK;
	}

	@RequestMapping("/password/get")
	@ResponseBody
	protected JsonResult getPassword(HttpServletRequest request, Model model) {
		return JsonResult.OK.message(Contants.PASSWORD);
	}
}
