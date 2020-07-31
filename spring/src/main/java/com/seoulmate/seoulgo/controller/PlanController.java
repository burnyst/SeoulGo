package com.seoulmate.seoulgo.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PlanPage;
import com.seoulmate.seoulgo.service.PlanService;

@Controller
public class PlanController {
	@Autowired
	public PlanService planservice;

	// http://127.0.0.1:9000/plan/plan ?nowPage=1
	@RequestMapping("/plan/plan")
	public ModelAndView planpage(ModelAndView mv, PlanDTO plan, Model model, PlanPage page2, HttpSession session)
			throws Exception {
		System.out.println("planpage진입요청함수");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = principal.toString();

		System.out.println(mem_id);

		page2.setMemberid(mem_id);
		int TotalRow = planservice.personrow(page2);
		page2.setTotalRow(TotalRow);

		List<PlanDTO> pdto = (ArrayList)planservice.getplanboard(page2);
		System.out.println("pdto 초기값 :  "+pdto);

		//System.out.println(pdto.get(0).getPlacename()); // null
		System.out.println("page2의 초기값 조회합니다."+page2);
		
		for(int i=0;i<pdto.size();i++) {
			//System.out.println(pdto.size());
			try {
				if(pdto.get(i).getPlanNo()==pdto.get(i-1).getPlanNo()) {
					continue;
				}
			}catch(Exception ex) {}
			List<PlanDTO> list = new ArrayList<PlanDTO>();
			if(pdto.get(i).getPlaceName()==null) {
				
				int e = pdto.get(i).getPlanNo();
				System.out.println("planNo숫자의 값 - 컨트롤러 "+e);
				List<String> placemany = planservice.getplacename(e);
				System.out.println("placemany의 값  :  "+placemany);
				pdto.get(i).setPlaceName(placemany);
				
			}
		}
			mv.addObject("plist",pdto);
		System.out.println("pdto의 최종값을 조회합니다."+pdto);
		mv.addObject("page",page2);
		mv.setViewName("plan/plan");
		return mv;
	}

	//http://127.0.0.1:9000/plan/planmodi
		@RequestMapping("/plan/planmodi")
		public ModelAndView planredirect(ModelAndView mv,HttpServletRequest req) {
			int pno  =  Integer.parseInt(req.getParameter("pno"));//글번호
			System.out.println("planmodi도착, pno의 숫자: "+pno);
			
			ArrayList<PlanDTO> pdto = (ArrayList)planservice.detailView(pno);//DTO에 내용 넣기.
			for (int i=0;i<pdto.size(); i++) {
				int j = pdto.get(i).getPlanNo();
				System.out.println(j+"j의값");
				ArrayList<PlanDTO> view2 = planservice.addrservice(j);
				System.out.println(view2);
				//view2.get(0).getAdd1();
				pdto.get(i).setAddr1(view2.get(0).getAddr1());
				//view2.get(0).getAdd2();
				pdto.get(i).setAddr2(view2.get(0).getAddr2());
				pdto.get(i).setPlaceName(view2.get(0).getPlaceName());
			}
			mv.setViewName("plan/planmodi");
			mv.addObject("Pdto",pdto);
			return mv;
		}
	@PostMapping("/plan/planmodifin")
	public ModelAndView planmodifin(ModelAndView mv, HttpServletRequest req, PlanDTO plan) {
		planservice.planmodifinservice(plan, req);

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
		System.out.println("planobject planpage()" + page);

		ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.planview(page);
		System.out.println("plandto를 받은 변수 view의 값: " + view);
		mv.addObject("pageview", view);
		mv.addObject("page", page);
		mv.setViewName("plan/planSboard");
		return mv;
	}
	
	// 일정을 작성할때 사용할 페이지 폼
	@RequestMapping("/plan/planwrite")
	public void planwrite(Model model, PlanPage page) {
		System.out.println("planwrite 호출완료");

		int TotalRow = planservice.sarchplaceint(page);
		page.setTotalRow(TotalRow);
		System.out.println("totalrow의 값: " + TotalRow);

		ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.placeSarch(page);
		System.out.println("view 변수의 값 : " + view);
		model.addAttribute("placeview", view);
		model.addAttribute("page", page);
		// mv.setViewName("plan/placesarch");
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
				ArrayList choiceplace3 = (ArrayList) planservice.choiceplace3(placeNo3);
				System.out.println("세번째 받은 값 :" + choiceplace3);
				mv.addObject("choice3", choiceplace3);
			}
		} catch (Exception e) {
			System.out.println("일정 중 일부는 등록되지 않았습니다.");
		}
		mv.setViewName("plan/planwritenter");
		return mv;
	}

	@RequestMapping("/plan/planwritenter2")
	public ModelAndView planwritenter2(ModelAndView mv, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("planwritenter2 호출완료");

		HttpSession session = req.getSession();
		ArrayList dto = (ArrayList) session.getAttribute("choice1");
		int placeNo2 = Integer.parseInt(req.getParameter("placeNo2"));

		session.setAttribute("placeNo2", placeNo2);

		System.out.println("두번째 장소 번호 : " + placeNo2);

		ArrayList choiceplace2 = (ArrayList) planservice.choiceplace2(placeNo2);

		System.out.println("두번째 받은 값" + choiceplace2);

		mv.addObject("choice2", choiceplace2);
		mv.addObject("choice1", dto);
		mv.setViewName("plan/planwritenter2");
		return mv;
	}

	
	
	//일정 상세보기 페이지<비회원, 회원 공용 


	// 일정 상세보기 페이지<비회원, 회원 공용 (?)

	@RequestMapping("/plan/planview")
	public ModelAndView planviews(ModelAndView mv, HttpServletRequest req, PlanDTO plan, int planNo) {
		System.out.println("planview 호출완료");
		System.out.println("지금 받은 planno의 값 : " + planNo);
		// detailview에서 어레이 리스트로 해당 planno에 들어있는 게시글을 받았다.
		ArrayList<PlanDTO> detailview = planservice.detailView(planNo);
		for (int i = 0; i < detailview.size(); i++) {
			int j = detailview.get(i).getPlanNo();
			System.out.println(j + "j의값");
			ArrayList<PlanDTO> view2 = planservice.addrservice(j);
			System.out.println(view2);
			// view2.get(0).getAdd1();
			detailview.get(i).setAddr1(view2.get(0).getAddr1());
			// view2.get(0).getAdd2();
			detailview.get(i).setAddr2(view2.get(0).getAddr2());
			detailview.get(i).setPlaceName(view2.get(0).getPlaceName());
		}
		System.out.println("dtd를 어레이 리스트로-planview : " + detailview);

		mv.addObject("Pdto", detailview);
		return mv;
	}

	// 장소검색 폼
	@RequestMapping("plan/placesarch")
	public ModelAndView placeSarch(ModelAndView mv, PlanPage page) {
		// place정보를 다 긁어와야 한다.

		// 페이징 시작
		/*
		 * int TotalRow = planservice.sarchplaceint(page); page.setTotalRow(TotalRow);
		 * ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.placeSarch(page);
		 * 
		 * System.out.println("totalrow의 값: " + TotalRow);
		 * System.out.println("view 변수의 값 : " + view); mv.addObject("placeview", view);
		 * mv.addObject("page", page); mv.setViewName("plan/placesarch");
		 */
		return mv;
	}

	// 장소검색 폼
	@RequestMapping("plan/placesarch2")
	public ModelAndView placeSarch2(ModelAndView mv, PlanPage page) {

		// 페이징 시작
		/*
		 * int TotalRow = planservice.sarchplaceint(page); page.setTotalRow(TotalRow);
		 * ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.placeSarch(page);
		 * 
		 * System.out.println("totalrow의 값: " + TotalRow);
		 * System.out.println("view 변수의 값 : " + view); mv.addObject("placeview", view);
		 * mv.addObject("page", page); mv.setViewName("plan/placesarch2");
		 */
		return mv;
	}

	// 장소검색 폼
	@RequestMapping("plan/placesarch3")
	public ModelAndView placeSarch3(ModelAndView mv, PlanPage page) {
		// place정보를 다 긁어와야 한다.

		// 페이징 시작
		/*
		 * int TotalRow = planservice.sarchplaceint(page); page.setTotalRow(TotalRow);
		 * ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.placeSarch(page);
		 * 
		 * System.out.println("totalrow의 값: " + TotalRow);
		 * System.out.println("view 변수의 값 : " + view); mv.addObject("placeview", view);
		 * mv.addObject("page", page); mv.setViewName("plan/placesarch3");
		 */
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
	@PostMapping("/plan/planwrited")
	public ModelAndView planwriteed(PlanDTO p, ModelAndView mv, HttpServletRequest req, PlanDTO plan, PlanPage page) {
		System.out.println("PlanController.planwriteed() 진입 p=" + p);
		planservice.planwritedservice(plan, req);

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