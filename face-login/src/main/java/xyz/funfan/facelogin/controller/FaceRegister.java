package xyz.funfan.facelogin.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.funfan.facelogin.dao.entity.User;

@RestController
public class FaceRegister {

	@RequestMapping("/register/face")
	public String faceRegister(@RequestParam("img") String imgstr, @RequestParam("user") User user)
			throws JSONException {

		// 处理图片流编码
		byte[] imgbytes = Base64.getDecoder().decode(imgstr);

		// 人脸识别开放接口(个人)
		AipFace face = new AipFace("10415883", "ASO8Z2Q0ViKoARtkB9NXDM5p", "zjQefzc1Mr3cmigbiKot7EjinzD3ViGD");

		// 设置网络连接参数
		face.setConnectionTimeoutInMillis(60000);
		face.setSocketTimeoutInMillis(60000);

		// 设置请求参数
		String uid = user.getTelNumber();

		// 用户信息设置
		HashMap<String, String> options = new HashMap<String, String>(1);
		List<String> groupId = new LinkedList<String>();
		groupId.add("test");

		// 注册信息替换
		options.put("action_type", "replace");

		// 用户信息注册
		JSONObject result = face.addUser(uid, str, groupId, imgbytes, options);
		System.out.println(result);
		boolean registerResult = result.toString().contains("error_msg");
		if (registerResult) {
			return "注册失败！";
		} else {
			return "success";
		}

	}

}
