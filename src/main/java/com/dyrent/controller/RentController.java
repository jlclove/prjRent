package com.dyrent.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyrent.base.model.JsonResult;
import com.dyrent.model.HouseMessage;
import com.dyrent.service.HouseMessageService;
import com.dyrent.service.HousePictureService;
import com.dyrent.utils.BarCodeUtil;
import com.google.zxing.WriterException;

/**
 * @author Jail hu
 */
@Controller
public class RentController extends BaseController {

	@Autowired
	private HouseMessageService houseMessageService;
	@Autowired
	private HousePictureService housePictureService;

	@RequestMapping("/{lijikanId}")
	protected String detail(@PathVariable int lijikanId, Model model) {
		HouseMessage houseMessage = houseMessageService
				.getHouseMessageById(lijikanId);
		houseMessage.setHousePictureList(housePictureService
				.getPictureListByLijikanId(lijikanId));
		model.addAttribute("houseMessage", houseMessage);
		model.addAttribute("similars",
				houseMessageService.getSimilarHouseMessageList(lijikanId));
		return "house/detail";
	}

	@RequestMapping("/share/{lijikanId}")
	protected String share(@PathVariable int lijikanId, Model model) {
		HouseMessage houseMessage = houseMessageService
				.getHouseMessageById(lijikanId);
		houseMessage.setHousePictureList(housePictureService
				.getPictureListByLijikanId(lijikanId));
		model.addAttribute("houseMessage", houseMessage);
		return "house/share";
	}

	@RequestMapping("/share/done/{lijikanId}")
	protected String shareDone(@PathVariable int lijikanId, Model model) {
		model.addAttribute("lijikanId", lijikanId);
		return "house/shareDone";
	}

	@RequestMapping(value = "/share/submit", method = RequestMethod.POST)
	@ResponseBody
	protected JsonResult shareSubmit(@ModelAttribute HouseMessage houseMessage) {
		HouseMessage old = houseMessageService.getHouseMessageById(houseMessage
				.getId());
		houseMessage.setHouseCode(old.getHouseCode());
		houseMessage.setAttachName(old.getAttachName());
		int messageId = houseMessageService.insertMessage(houseMessage);
		return messageId > 0 ? JsonResult.OK.put("messageId", messageId)
				: JsonResult.FAIL;
	}

	@RequestMapping("/getPictureList/{propertyId}")
	@ResponseBody
	protected void getPictureList(@PathVariable String propertyId,
			HttpServletResponse response) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		response.setContentType("text/json;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// return HttpClientUtil
		// .doGet("http://fy.dooioo.com/api/queryProperty?id="
		// + propertyId);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// return "";
		//
		// }
	}

	/*
	 * @RequestMapping("afad22s")
	 * 
	 * @ResponseBody protected String aaab() { HttpPost post = new
	 * HttpPost("https://login.dooioo.com"); HttpClient client = new
	 * DefaultHttpClient(); PostMethod method = new
	 * PostMethod("https://login.dooioo.com"); try { return HttpClientUtil
	 * .doGet(
	 * "http://ljk.dooioo.com/sms/getProperty?propertyNo=2012112820524426978JC9HYIXVNJ1LF#"
	 * ); } catch (IOException e) { // TODO Auto-generated catch block return
	 * "";
	 * 
	 * } }
	 */

	@RequestMapping("/getBarCode")
	protected void asdfa(HttpServletRequest request,
			HttpServletResponse response) {
		String content = request.getParameter("content");
		try {
			ImageIO.write(BarCodeUtil.encode(content), "jpg",
					response.getOutputStream());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@RequestMapping("/more")
	protected String more(Model model,@ModelAttribute HouseMessage houseMessage) {
		model.addAttribute("houseMessage",  houseMessage);
		model.addAttribute("messageList",  houseMessageService
				.getListPage(houseMessage));
		model.addAttribute("totalCount",  houseMessageService
				.getListCount(houseMessage));
		return "house/more";
	}
}
