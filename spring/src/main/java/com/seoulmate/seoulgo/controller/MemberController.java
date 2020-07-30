package com.seoulmate.seoulgo.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {

	@Autowired
	MemberService mService;

	@Inject
	BCryptPasswordEncoder pwEncoder;

	// 로그아웃
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	// 회원탈퇴
	@RequestMapping("/deleteSuccess")
	public ModelAndView deleteSuccess(MemberDTO mdto, HttpSession session, ModelAndView mv, HttpServletRequest request, @RequestParam String memberPW, @RequestParam String memberID) {
		MemberDTO pwCheck = mService.findMember(memberID);
		boolean result = pwEncoder.matches(memberPW, pwCheck.getMemberPW());
		System.out.println("회원탈퇴 비밀번호 일치여부 result=" + result);
		if(result==true) {
			pwCheck.setEnabled(false);
			mService.deleteSuccess(pwCheck);
			
			RedirectView rv = new RedirectView(request.getContextPath()+"/user/login");
			mv.setView(rv);
			mv.addObject("msg", "deleteSuccess");
			
			// 회원탈퇴 후 세션 무효화
			session.invalidate();
		}else {
			mv.setViewName(request.getContextPath()+"/member/mypage");
			mv.addObject("msg", "deleteFail");
		}
		return mv;
	}

	// 회원탈퇴 폼 보여주기
	@RequestMapping("/deleteAccount")
	public void deleteAccount() {
	}

	// 비밀번호 변경 처리
	@RequestMapping("/pwUpdate")
	public ModelAndView pwUpdate(MemberDTO mdto, ModelAndView mv, HttpSession session, HttpServletRequest request,@RequestParam String pw) {
		System.out.println("MemberController.pwUpdate() 진입");

		// 비밀번호 일치 여부 확인
		MemberDTO pwCheck = mService.findMember(mdto.getMemberID());
		boolean result = pwEncoder.matches(pw, pwCheck.getMemberPW());
		if (result == true) {
			// 새 비밀번호 암호화 및 DB저장
			String newPW = pwEncoder.encode(mdto.getMemberPW());
			mdto.setMemberPW(newPW);
			mService.pwUpdate(mdto);

			RedirectView rv = new RedirectView(request.getContextPath()+"/user/login");
			mv.setView(rv);
			mv.addObject("msg", "pwUpdate");

			// 비밀번호 변경 후 세션 무효화
			session.invalidate();
		} else {
			mv.setViewName(request.getContextPath()+"/member/mypage");
			mv.addObject("msg", "pwFail");
		}
		return mv;
	}

	// 비밀번호 변경 폼 보여주기
	@RequestMapping("/changePW")
	public void changePW() {
	}

	// 회원정보 수정
	@RequestMapping("/memberInfoUpdate")
	public ModelAndView memberInfoUpdate(MemberDTO mdto, MultipartHttpServletRequest multipartRequest, HttpServletRequest request, ModelAndView mv, MultipartFile files, @RequestParam String phone, @RequestParam String nickname, @RequestParam String email) {
		System.out.println("MemberController.memberInfoUpdate() 진입"+mdto);
		
		//현재 로그인 한 회원 아이디 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String memberID = principal.toString();
				
		MemberDTO findMember = mService.findMember(memberID); 
		System.out.println("findMember: "+findMember);

		if(findMember != null) {
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

			// 프로필 사진을 업로드 하지 않았을 경우
			if (multipartRequest.getFiles("files").get(0).getSize() == 0) {
				fileList = multipartRequest.getFiles("file");
			} else {
				// 프로필 사진을 업로드 했을 경우
				for (MultipartFile mf : fileList) {
					// 업로드 파일의 원본 이름
					System.out.println("files.getName(): "+files.getOriginalFilename());
					String oriName = mdto.getFiles().getOriginalFilename();
					System.out.println("file의 oriName=" + oriName);
					
					// 파일 이름과 확장자 분리
					int index = oriName.lastIndexOf(".");
					String fileOriName = oriName.substring(0, index);
					String ext = oriName.substring(index + 1);
					
					// 파일이름을 t-memberID로 변경하는 코드
					String tName = "t-" + memberID + "." + ext;
					File file = new File(filePath, tName);

					// 원본 파일 복사: transferTo()
					try {
						mdto.getFiles().transferTo(file);
						findMember.setProSaveName(tName);
					} catch (Exception e) {
						System.out.println("파일 복사 에러: " + e);
					}
				}
			}
			
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
			
			findMember.setNickname(nickname);
			findMember.setEmail(email);
			findMember.setPhone1(phone1);
			findMember.setPhone2(phone2);
			findMember.setPhone3(phone3);

			mService.memberInfoUpdate(findMember);
			
			RedirectView rv = new RedirectView(request.getContextPath()+"/member/mypage");
			mv.addObject("mem", findMember);
			mv.addObject("msg", "updateSuccess");
			mv.setView(rv);
		}else {
			mv.setViewName(request.getContextPath()+"/member/mypage");
			mv.addObject("msg", "updateFail");
		}
		return mv;
	}

	// 회원정보 수정 폼 보여주기
	@RequestMapping("/memberInfo")
	public void memberInfo(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String memberID = principal.toString();
		System.out.println("MemberController.memberInfo().username=" + memberID);
		MemberDTO mem = mService.findMember(memberID);
		session.setAttribute("mem", mem);
	}

	// 마이페이지 폼 보여주기
	@RequestMapping("/mypage")
	public void mypage(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String memberID = ((UserDetails) principal).getUsername();
			System.out.println("if.username=" + memberID);
			MemberDTO mem = mService.findMember(memberID);
			session.setAttribute("mem", mem);
		} else {
			String memberID = principal.toString();
			System.out.println("else.username=" + memberID);
			MemberDTO mem = mService.findMember(memberID);
			session.setAttribute("mem", mem);
		}
	}
}