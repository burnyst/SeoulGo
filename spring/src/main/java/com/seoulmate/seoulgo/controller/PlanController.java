package com.seoulmate.seoulgo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PlacePage;
import com.seoulmate.seoulgo.page.PlanPage;
import com.seoulmate.seoulgo.service.PlaceService;
import com.seoulmate.seoulgo.service.PlanService;

@Controller
public class PlanController {
	
	@Autowired
	public PlanService planservice;
	
	@Autowired
	PlaceService service;

	// http://localhost/plan/plan
	@RequestMapping("/plan/plan")
	public ModelAndView planpage(ModelAndView mv, PlanDTO plan,PlaceDto place, Model model, PlanPage page2, HttpSession session)
			throws Exception {
		System.out.println("PlanController.planpage() 진입");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = principal.toString();
		System.out.println(mem_id+"님의 마이 플랜 페이지입니다.");
		session.setAttribute("memberid", mem_id );

		page2.setMemberid(mem_id);
		
		int TotalRow = planservice.personrow(page2);
		System.out.println("Plan.TotalRow="+TotalRow);
		page2.setTotalRow(TotalRow);

		List<PlanDTO> pList = (ArrayList)planservice.getplanboard(page2);
		//System.out.println("pdto 초기값 :"+pList);
		for(int i =0; i<pList.size();i++) { //게시판 수만큼 반복
			int planno = pList.get(i).getPlanNo();
			List<PlaceDto> placeinfo = (ArrayList)planservice.getplaceinfo(planno);
			System.out.println("placeinfo="+placeinfo);
			List<String> placenamelist = new ArrayList();
			for(int j=0;j<placeinfo.size();j++) {
				String placename = placeinfo.get(j).getPlaceName();
				placenamelist.add(placename);
				System.out.println("placenamelist="+placenamelist);
			}
			pList.get(i).setPlacenamelist(placenamelist);
			System.out.println("pdto="+pList.get(i).getPlacenamelist());
		}
		System.out.println("page2의 초기값 조회합니다."+page2);
		for(int i=0;i<pList.size();i++) {
			//System.out.println(pdto.size());
			try {
				if(pList.get(i).getPlanNo()==pList.get(i-1).getPlanNo()) {
					continue;
				}
			}catch(Exception ex) {}
		}
		mv.addObject("plist", pList);
		//mv.addObject("placelist",placelist);
		System.out.println("pdto의 최종값을 조회합니다."+pList);
		mv.addObject("page", page2);
		mv.setViewName("plan/plan");
		return mv;
	}

	
	@RequestMapping("/plan/planmodi")
	public ModelAndView planredirect(ModelAndView mv, HttpServletRequest req, PlacePage page) {
		int pno = Integer.parseInt(req.getParameter("planNo"));	//글번호
		System.out.println("PlanControler.planredirect() pno= "+pno);
		
		ArrayList<PlanDTO> pdto = (ArrayList)planservice.detailView(pno);	//DTO에 내용 넣기.
		
		for (int i=0;i<pdto.size(); i++) {
			int j = pdto.get(i).getPlanNo();
			System.out.println("j= "+j);
			List<PlaceDto> placedto = planservice.getplaceinfo(j);
			
			List<String> placenamelist = new ArrayList();
			for(int k=0;k<placedto.size();k++) {
				String placename = placedto.get(k).getPlaceName();
				placenamelist.add(placename);
			}
			mv.addObject("placedto",placedto);
			pdto.get(i).setPlacenamelist(placenamelist);
		}
		
		page.setPageRowNum(5);
		mv.addObject("placeNo", planservice.getpNo(pno));
		mv.addObject("page", service.list(page));
		mv.addObject("Pdto",pdto);
		mv.setViewName("plan/planmodi");
		return mv;
	}
	
	@PostMapping("/plan/planmodifin")
	public ModelAndView planmodifin(ModelAndView mv, HttpServletRequest req, PlanDTO plan,PlaceDto place) {
		planservice.planmodifinservice(plan,place, req);

		RedirectView rv = new RedirectView("/plan/plan");
		mv.setView(rv);
		return mv;
	}

	@RequestMapping("/plan/planSboard")
	public ModelAndView planShare(PlanDTO plan, ModelAndView mv, PlanPage page) {
		// 페이징 시작
		// startRow 계산
		// int listCnt = aService.searchList(search);
		System.out.println("planSboard진입");
		int TotalRow = planservice.getTotalRow(page);
		page.setTotalRow(TotalRow);
	
		ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.planview(page);
		
		for(int i =0; i<view.size();i++) { //게시판 수만큼 반복
			int planno = view.get(i).getPlanNo();
			List<PlaceDto> placeinfo =(ArrayList)planservice.getplaceinfo(planno);
			List<String> placenamelist = new ArrayList();
			for(int j=0;j<placeinfo.size();j++) {
				String placename = placeinfo.get(j).getPlaceName();
				placenamelist.add(placename);
			}
			view.get(i).setPlacenamelist(placenamelist);
		}
		
		
		System.out.println("plandto를 받은 변수 view의 값: " + view);
		mv.addObject("pageview", view);
		mv.addObject("page", page);
		mv.setViewName("plan/planSboard");
		return mv;
	}
	

	@RequestMapping("/plan/placeList")
	public ResponseEntity<PlacePage> placeList(PlacePage page) {
		page.setPageRowNum(5);
		return new ResponseEntity<>(service.list(page), HttpStatus.OK);
	}

	@RequestMapping("/plan/planwrite")
	public void planwrite(Model model, PlacePage page) {
		page.setPageRowNum(5);
		model.addAttribute("page", service.list(page));
	}

	@RequestMapping("/plan/planwritenter")
	public ModelAndView planwritenter(ModelAndView mv, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("planwritenter 호출완료");
		try {
			if (req.getParameter("placeNo") != null) {
				HttpSession session = req.getSession();
				int placeNo = Integer.parseInt(req.getParameter("placeNo"));
				System.out.println("첫번째 장소 번호 : " + placeNo);
				ArrayList choiceplace = (ArrayList) planservice.choiceplace(placeNo);
				System.out.println("choiceplace=" + choiceplace);
				mv.addObject("choice", choiceplace);
				session.setAttribute("choice1", choiceplace);
			} else if (req.getParameter("placeNo3") != null) {
				HttpSession session = req.getSession();
				int placeNo3 = Integer.parseInt(req.getParameter("placeNo3"));

				session.setAttribute("placeNo3", placeNo3);
				System.out.println("세번째 장소 번호 : " + placeNo3);
			}
		} catch (Exception e) {
			System.out.println("일정 중 일부는 등록되지 않았습니다.");
		}
		mv.setViewName("plan/planwritenter");
		return mv;
	}


	// 일정 상세보기 페이지<비회원, 회원 공용 (?)
	@RequestMapping("/plan/planview")
	public ModelAndView planviews(ModelAndView mv, HttpServletRequest req, PlanDTO plan, int planNo) {
		System.out.println("planview 호출완료");
		System.out.println("지금 받은 planno의 값 : " + planNo);
		// detailview에서 어레이 리스트로 해당 planno에 들어있는 게시글을 받았다.
		ArrayList<PlanDTO> detailview = planservice.detailView(planNo);
		ArrayList<PlaceDto> placeview = (ArrayList<PlaceDto>) planservice.getplaceinfo(planNo);
		for (int i = 0; i < detailview.size(); i++) {
			int j = detailview.get(i).getPlanNo();
			System.out.println(j + "j의값");
			List<String> placelist = planservice.getplacename(j);
			
			for (int k=0; k<placelist.size();k++) {
				int placeno = placeview.get(k).getPlaceNo();
				List<String> piclist = planservice.getplaceimagename(placeno);
				placeview.get(k).setImageNames(piclist);
			}
			
			System.out.println("placelist의 값들 : "+placelist);
			mv.addObject("placelist",placelist);
			
		}
		System.out.println("컨트롤러의 detailview 변수 : " + detailview);
		System.out.println("컨트롤러의 placeview 변수 : "+ placeview);
		mv.addObject("placeview",placeview);
		mv.addObject("Pdto", detailview);
		return mv;
	}
	@RequestMapping("/plan/plandelete")
	public ModelAndView plandelete(PlanDTO page, HttpServletRequest req, ModelAndView mv) {
		System.out.println("plandelete 페이지 호출함수★★★");

		int planno = Integer.parseInt(req.getParameter("planNo"));
		page.setPlanNo(planno);
		mv.addObject("deleteno", page);
		System.out.println("plandelete페이지 나감.");
		mv.setViewName("plan/plandelete");
		return mv;
	}

	@RequestMapping("plan/plandeleteok")
	public ModelAndView plandeleteok(ModelAndView mv, HttpServletRequest req) {
		System.out.println("삭제 페이지 진입중...");

//		일정을 삭제하는 함수.
		int planno = Integer.parseInt(req.getParameter("planNo"));
		System.out.println(planno);
		planservice.plandelete(planno);
		RedirectView rv = new RedirectView("/plan/plan");
		mv.setView(rv);
		return mv;
	}

	@RequestMapping("plan/viewplan")
	public String planview() {
		System.out.println("viewplan페이지 호출함수.,");
		return "plan/viewplan";
	}

	// 1.작성된 정보를 2.db로 날리는 작업
	@PostMapping("/plan/planWrited")
	public ModelAndView planWrited(ModelAndView mv, PlaceDto place, HttpServletRequest req, PlanDTO plan, PlanPage page) {
		System.out.println("PlanController.planWrited() 진입 ");
		
		String[] list = req.getParameterValues("placeNo");
		
		// 현재 로그인한 유저의 아이디
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = principal.toString();
		
		Date planDate = null;
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			planDate = fm.parse(req.getParameter("plandate"));
			System.out.println("형변환된 날짜="+planDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("날짜변환 중 오류가 발생하였습니다.");
		}
		plan.setMemberid(mem_id);
		plan.setPlanTitle(req.getParameter("planTitle"));
		plan.setPlanCate(req.getParameter("plancate"));
		plan.setPlanDate(planDate);

		planservice.planWrited(plan, place, list);

		int TotalRow = planservice.getTotalRow(page);
		page.setTotalRow(TotalRow);

		ArrayList<PlanDTO> pdto = (ArrayList) planservice.getplanboard(page);
		System.out.println(pdto);
		System.out.println("page2의 초기값 조회합니다." + page);
		System.out.println("pdto의 최종값을 조회합니다." + pdto);

		RedirectView rv = new RedirectView("/plan/plan");
		mv.addObject("plist", pdto);
		mv.addObject("page", page);
		mv.setView(rv);
		return mv;
	}
}