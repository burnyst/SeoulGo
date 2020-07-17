package com.seoulmate.seoulgo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.seoulmate.seoulgo.security.MailConfirm;
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

	// 이메일 인증번호 보내기
	@ResponseBody
	@RequestMapping("/emailAuth")
	public JSONObject emailAuth(MemberDTO mdto, Model model, ModelAndView mv) {
		System.out.println("UserController.emailAuth() 진입");
		MailConfirm mail = new MailConfirm();

		String memberID = mdto.getMemberID();
		String mName = mdto.getmName();
		String email = mdto.getEmail();
		System.out.println(memberID + "/" + mName + "/" + email);

		JSONObject obj = new JSONObject();
		
		// 아이디 찾기
		if (memberID == null) {
			MemberDTO result = uService.emailAuth(mdto);
			if (result != null) {
				System.out.println("searchID 이메일 인증 성공 email=" + email);
				obj.put("confirmNum", mail.sendMail(email));
				obj.put("result", result);
			} else {
				System.out.println("searchID 이메일 인증 실패");
				obj.put("msg", "fail");
			}
		} else {
			// 비밀번호 찾기
			MemberDTO result = uService.emailAuth(mdto);
			if (result != null) {
				System.out.println("searchPW 이메일 인증 성공 email=" + email);
				int confirmNum = mail.sendMail(email);
				obj.put("confirmNum", confirmNum);
				obj.put("result", result);
			} else {
				System.out.println("searchPW 이메일 인증 실패");
				obj.put("msg", "fail");
			}
		}
		return obj;
	}

	// 비밀번호 찾기 처리
	@RequestMapping("/searchPWProc")
	public ModelAndView searchPWProc(MemberDTO mdto, @RequestParam String memberPW, ModelAndView mv) {
		System.out.println("UserController.searchPWProc() 진입");
		
		String memberID = mdto.getMemberID();
		String mName = mdto.getmName();
		String email = mdto.getEmail();
		
		String encodePw = pwEncoder.encode(memberPW);
		mdto.setMemberPW(encodePw);
		
		uService.searchPWProc(mdto);

		mv.setViewName("/user/login");
		return mv;
	}

	// 비밀번호 찾기 폼 보여주기
	@RequestMapping("/searchPW")
	public void searchPWForm() {
	}

	// 아이디 찾기 결과 폼 보여주기
	@RequestMapping("/searchIDResult")
	public void searchIDResult() {
	}
	
	// 아이디 찾기
	@RequestMapping("/searchIDProc")
	public ModelAndView searchIDProc(MemberDTO mdto, ModelAndView mv) {
		System.out.println("UserController.searchIDProc() 진입");

		String mName = mdto.getmName();
		String email = mdto.getEmail();

		MemberDTO result = uService.emailAuth(mdto);

		mv.setViewName("/user/searchIDResult");
		mv.addObject("result", result);
		return mv;
	}

	// 아이디 찾기 폼 보여주기 
	@RequestMapping("/searchID")
	public void searchIDForm() {
	}

	// 회원가입 처리 요청 함수
	@RequestMapping("/registerProc")
	public ModelAndView registerProc(MemberDTO mdto, ModelAndView mv, MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpSession session, @RequestParam String phone, @RequestParam String memberID,
			@RequestParam String memberPW) {
		System.out.println("프로필 사진 업로드 시작");

		// 프로필 사진 파일이 저장될 위치
		String realPath = request.getSession().getServletContext().getRealPath("/resources/img");
		String savePath = realPath + "/member/";
		File filePath = new File(savePath);
		System.out.println("filePath: " + filePath);

		// filePath가 존재하지 않을 경우 filePath 생성
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		List<MultipartFile> fileList = multipartRequest.getFiles("files");

		// 업로드 파일의 원본 이름
		String oriName = mdto.getFiles().getOriginalFilename();
		System.out.println("file의 oriName=" + oriName);
		
		// 파일 이름과 확장자 분리
		int index = oriName.lastIndexOf(".");
		String fileOriName = oriName.substring(0, index);
		String ext = oriName.substring(index + 1);

		// 프로필 사진을 업로드 하지 않았을 경우
		if (multipartRequest.getFiles("files").get(0).getSize() == 0) {
			fileList = multipartRequest.getFiles("file");
		} else {
			// 프로필 사진을 업로드 했을 경우
			for (MultipartFile mf : fileList) {
				String tName = "t-" + memberID + "." + ext;
				String saveName = String.format("%s", tName);
				File file = new File(filePath, saveName);

				// 원본 파일 복사: transferTo()
				try {
					mdto.getFiles().transferTo(file);
					mdto.setProSaveName(tName);
				} catch (Exception e) {
					System.out.println("파일 복사 에러: " + e);
				}
			}
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
		mdto.setEnabled(true);
		System.out.println(mdto);

		uService.registerProc(mdto);

		RedirectView rv = new RedirectView("/user/login");
		mv.setView(rv);

		return mv;
	}

	// 회원가입시 이메일 중복 확인 요청 함수
	@ResponseBody
	@PostMapping("/emailCheck")
	public Map<String, Object> emailCheck(MemberDTO mdto) {
		System.out.println("emailCheck 실행");

		Map<String, Object> checkResult = new HashMap<String, Object>();

		if (uService.getEmail(mdto) != null) {
			checkResult.put("emailCheck", "fail");
		} else {
			checkResult.put("emailCheck", "success");
		}
		return checkResult;
	}

	// 회원가입시 닉네임 중복 확인 요청 함수
	@ResponseBody
	@PostMapping("/nickCheck")
	public Map<String, Object> nickCheck(MemberDTO mdto) {
		System.out.println("nickCheck 실행");

		Map<String, Object> checkResult = new HashMap<String, Object>();

		if (uService.getNickname(mdto) != null) {
			checkResult.put("nickCheck", "fail");
		} else {
			checkResult.put("nickCheck", "success");
		}
		return checkResult;
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

	// 로그인 폼 보여주기
	@RequestMapping("/login")
	public void loginForm() {
	}

	// 회원가입 폼 보여주기
	@RequestMapping("/register")
	public void registerForm() {
	}
}