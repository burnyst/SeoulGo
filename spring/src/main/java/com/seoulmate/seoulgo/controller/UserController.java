package com.seoulmate.seoulgo.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dao.UserDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserService uService;
	
	@Autowired
	UserDAO uDAO;

	@Inject
	BCryptPasswordEncoder pwEncoder;

	// 비밀번호 찾기
	@RequestMapping("/searchPWProc")
	public void searchPWProc() {
	}

	// 비밀번호 찾기 폼 보여주기
	@RequestMapping("/searchPWForm")
	public void searchPWForm() {
	}

	// 아이디 찾기
	@RequestMapping("/searchIDProc")
	public void searchIDProc() {
	}

	// 아이디 찾기 폼 보여주기
	@RequestMapping("/searchIDForm")
	public void searchIDForm() {
	}

	// 로그인 처리 요청 함수
	@RequestMapping("/auth/loginProc")
	public ModelAndView loginProc(MemberDTO mdto, HttpSession session, ModelAndView mv) {
		System.out.println("loginProc 함수 진입");

		String result = null;

		MemberDTO login = uService.loginProc(mdto);

		// DB에 있는 암호화된 비밀번호와 로그인창에 입력된 비밀번호 일치 여부 비교
		boolean pwMatch = pwEncoder.matches(mdto.getMemberPW(), login.getMemberPW());

		HashMap hmap = new HashMap();

		hmap.put("memberID", mdto.getMemberID());
		hmap.put("memberPW", mdto.getMemberPW());

		HashMap map = uDAO.loginResult(hmap);

		if (login != null && pwMatch == true) {
			result = "login Success";

			session.setAttribute("loginProc", login);
			session.setAttribute("MNICK", map.get("NICKNAME"));

			System.out.println("로그인 회원 MNICK=" + session.getAttribute("MNICK"));
			System.out.println(result);
		} else {
			result = "login Fail";
			System.out.println(result);
		}

		RedirectView rv = new RedirectView("../../");
		mv.setView(rv);
		return mv;
	}

	// 회원가입 처리 요청 함수
	@RequestMapping("/registerProc")
	public ModelAndView registerProc(MemberDTO mdto, ModelAndView mv, MultipartHttpServletRequest request,
			@RequestParam String phone, @RequestParam String memberPW) {
		System.out.println("프로필 사진 업로드 시작");
		File filePath = new File("d:\\upload\\temp"); // 프로필 사진 파일이 저장될 위치

		// filePath가 존재하지 않을 경우 filePath 생성
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		String oriName = mdto.getFiles().getOriginalFilename();
		if (request.getFiles("files").get(0).getSize() != 0) {
			fileList = request.getFiles("file");
		}

		long time = System.currentTimeMillis();
		String saveName = String.format("%d_%s", time, oriName);
		mdto.setProSaveName(saveName);
		File file = new File(filePath, saveName);

		System.out.println("프로필 사진 실제 저장 이름: " + saveName);

		try {
			mdto.getFiles().transferTo(file);
		} catch (Exception e) {
			System.out.println("파일 복사 에러: " + e);
		}

		System.out.println("프로필 사진 썸네일 생성");
		String oriPath = filePath + "\\" + saveName;
		File oriFile = new File(oriPath);

		int index = oriPath.lastIndexOf(".");
		String ext = oriPath.substring(index + 1);

		// 썸네일 저장 경로
		String tPath = oriFile.getParent() + File.separator + "t-" + oriFile.getName();
		File tFile = new File(tPath);

		// 이미지 축소 비율
		double ratio = 2;

		try {
			BufferedImage oriImage = ImageIO.read(oriFile);
			int tWidth = (int) (oriImage.getWidth() / ratio);
			int tHeight = (int) (oriImage.getHeight() / ratio);

			// 썸네일 이미지
			BufferedImage tImage = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = tImage.createGraphics();
			Image image = oriImage.getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
			graphic.drawImage(image, 0, 0, tWidth, tHeight, null);
			graphic.dispose();

			ImageIO.write(tImage, ext, tFile);
		} catch (Exception e) {
			System.out.println("썸네일 생성 에러: " + e);
		}

		System.out.println("registerProc 함수 진입");
		String phone1 = phone.substring(0, 3);
		String phone2;
		String phone3;

		if (phone.length() == 10) {
			phone2 = phone.substring(3, 6);
			phone3 = phone.substring(6);
		} else {
			phone2 = phone.substring(3, 7);
			phone3 = phone.substring(7);
		}
		String encodePw = pwEncoder.encode(memberPW);
		mdto.setMemberPW(encodePw);

		mdto.setPhone1(phone1);
		mdto.setPhone2(phone2);
		mdto.setPhone3(phone3);
		mdto.setmLevel("ROLE_MEMBER");

		uService.registerProc(mdto);

		RedirectView rv = new RedirectView("../user/loginForm");
		mv.setView(rv);

		return mv;
	}

	// 회원가입시 아이디 중복 확인 요청 함수
	@ResponseBody
	@PostMapping("/idCheck")
	public Map<String, Object> idCheck(MemberDTO mdto) {
		System.out.println("idCheck 실행");

		Map<String, Object> checkResult = new HashMap<String, Object>();

		if (uService.getMemberID(mdto) != null) {
			checkResult.put("idCheck", "fail");
		} else {
			checkResult.put("idCheck", "success");
		}

		return checkResult;
	}

	// 아이디 조회
	public ModelAndView getMemberID(MemberDTO mdto, ModelAndView mv) {
		mv.addObject("member", uService.getMemberID(mdto));
		mv.setViewName("#");

		return mv;
	}

	// 회원가입 폼 보여주기
	@RequestMapping("/registerForm")
	public void registerForm() {
	}

	// 로그인 폼 보여주기
	@RequestMapping("/loginForm")
	public void loginForm() {
	}
}
