package com.seoulmate.seoulgo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.ReviewDTO;
import com.seoulmate.seoulgo.page.PlacePage;
import com.seoulmate.seoulgo.page.ReviewPage;
import com.seoulmate.seoulgo.service.ReviewService;
import com.seoulmate.seoulgo.util.FileUtil;

@Controller
@RequestMapping("/review/")
public class ReviewController {

	@Autowired
	ReviewService rService;
	
	// 1. 장소 리스트 폼 보기
	@RequestMapping("PlaceListView")
	public String getPlaceListViewForm(PlacePage placePage, Model model) {
		System.out.println("ReviewController.getPlaceListViewForm()- 장소 목록 보기 진입");
		// 해당 페이지에 출력할 목록조회
		model.addAttribute("list", rService.getPlaceListView(placePage));
		// 페이징처리
		model.addAttribute("page", placePage);
		
		return "review/placeListView";
	}
	// 2. 리뷰 작성 폼 보기
	@RequestMapping("writeReview")
	public String getwriteReviewForm(Model model, HttpServletRequest request) {
		System.out.println("getwriteReviewForm() 진입");
		// 파라미터 placeNo=글번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		
		ArrayList<PlaceDto> Info = rService.getPlaceInfo(placeNo);
		
		// Model&View
		request.setAttribute("placeNo", placeNo);
		request.setAttribute("Info",Info);
		return "review/writeReview";
	}
	
	// 2.1. 글 작성 처리
	@RequestMapping("writeProc")
	public String writeProc(ReviewDTO rDTO, HttpSession session, HttpServletRequest request) {
		System.out.println("ReviewController.writeProc()- 글 작성 처리");
		// 파라미터 placeNo=글번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
				
		// 파일 업로드 
		// 1. 업로드한 파일 저장할 폴더 지정
		String path="D:\\upload";
		// 2. 파일이름 뽑아내기
		ArrayList list = new ArrayList();
		for(int i=0; i<rDTO.getFiles().length; i++) {
			// 파일의 실제이름 뽑기
			String oriName = rDTO.getFiles()[i].getOriginalFilename();
			
			// 파일이 업로드 되지 않았다면? -> 다음 작업을 시도한다.
			if(oriName == null || oriName.length()==0) {
				continue; // 다음 작업을 실행해라.
			}
			// 업로드 파일 중복 방지 위해 모듈화된 클래스 사용
			String iSaveName = FileUtil.renameTo(path, oriName);
			
			// 파일 저장을 위해 객체 생성
			File file = new File(path, iSaveName);
			
			try {
				rDTO.getFiles()[i].transferTo(file);
			} catch(Exception e) {
				System.out.println("파일 복사 관련 에러 = "+e);
			}
			// 업로드 파일을 map으로 묶어 넘긴다.
			HashMap map = new HashMap();
			map.put("path", path);
			map.put("oriName", oriName);
			map.put("iSaveName", iSaveName);
			map.put("len", file.length());
			list.add(map);
		}
		
		// 비즈니스로직 처리
		rService.insertReview(rDTO, session, list);
		
		// View 처리
		return "redirect:http://localhost:9000/review/detailView?placeNo="+placeNo;
	}

	// 3. 리뷰 상세 폼 보기
	@RequestMapping("detailView")
	public String getDetailViewForm(ReviewPage reviewPage, HttpServletRequest request, ReviewDTO rDTO)
	{
		System.out.println("ReviewController.getDetailViewForm- 리뷰 상세 보기 진입");
		// 파라미터 placeNo=장소번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		
		// 비즈니스 로직
		ArrayList<PlaceDto> Info = rService.getPlaceInfo(placeNo);		// 장소 목록 조회
		reviewPage.setPlaceNo(placeNo);
		ArrayList<ReviewDTO> list = rService.getDetailList(reviewPage);	// 리뷰 상세 내용 조회
		
		// Model
		request.setAttribute("placeNo", placeNo);
		request.setAttribute("Info",Info);
		request.setAttribute("rDTO",list);
		request.setAttribute("page", reviewPage);
		
		// 4. View
		return "review/detailView";
	} 
	
	// 4. 리뷰 수정 폼 보기
	@RequestMapping("modifyReview")
	public String getModifyViewForm(Model model, HttpServletRequest request) {
		System.out.println("getModifyViewForm() 진입");
		// 파라미터 placeNo=글번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int rno = Integer.parseInt(request.getParameter("rno"));
		ArrayList<PlaceDto> Info = rService.getPlaceInfo(placeNo);
		
		request.setAttribute("placeNo", placeNo);
		request.setAttribute("rno", rno);
		request.setAttribute("Info",Info);
		return "review/modifyReview";
	}
	
	// 4.1. 글수정하기
	@RequestMapping("modifyProc")
	public String modifyProc(ReviewDTO rDTO, HttpSession session, HttpServletRequest request)
	{
		System.out.println("ReviewController.modifyProc()- 글 수정 처리");
		// 파라미터 placeNo=글번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int rno = Integer.parseInt(request.getParameter("rno"));
		rDTO.setrNo(rno);
		
		// 1.첨부파일 확인
		boolean isUpload = false;
		for(int i=0; i<rDTO.getFiles().length; i++) {
		String tempName = rDTO.getFiles()[i].getOriginalFilename();
			if( tempName!=null && tempName.length()!=0 ) {
				isUpload = true;
				break; 
			}
		}
				
		String path="D:\\upload";
		ArrayList fileList = new ArrayList();
				
		//2.첨부파일에 대한 정보 알아두기(첨부파일이 ★ '있는 경우에만'★  정보를 알아두면 된다.) + 파일복사
		if(isUpload==true) {
			for(int i=0; i<rDTO.getFiles().length; i++) { 
				String oriName = rDTO.getFiles()[i].getOriginalFilename();
					if(oriName == null || oriName.length()==0) {
						continue; 
					}
					String iSaveName = FileUtil.renameTo(path, oriName);
					File file = new File(path, iSaveName);
					try {
						rDTO.getFiles()[i].transferTo(file);
					} catch(Exception e) {
						System.out.println("파일 복사 관련 에러 = "+e);
					}
						HashMap map = new HashMap();
						map.put("path", path);
						map.put("oriName", oriName);
						map.put("iSaveName", iSaveName);
						map.put("len", file.length());
						fileList.add(map);
					}// end of for
				}// end of if
				
		// 3.내용수정하고
		rService.modifyReview(rDTO);
				
		// 4.첨부파일이 있으면 등록
		if(isUpload==true) {
			// DTO에 담아 넘겨줄 DTO 객체 생성
			ReviewDTO rDTO1 = new ReviewDTO(); 
					
			//	4-1)과거에 업로드한 파일을 삭제
			// 		4-1-1)과거에 업로드한 파일의 정보를 DB에서 가져오기
			ArrayList<ReviewDTO> list =	rService.getFileInfo(rno);
			System.out.println(list);
				if( list!=null && list.size()!=0 ) { 
					for(int i=0; i<list.size(); i++) {
						
					ReviewDTO tempDTO = (ReviewDTO)list.get(i);
					
					// 	4-1-2)해당 파일을 File객체로 만들어
					// path - 저장 경로, DTO에 저장된 이름을 뽑아서 file 객체로 만든다.
					File tempFile = new File(path, tempDTO.getiSaveName());
						
					// 	4-1-3)삭제 (upload 폴더 내에서 삭제함)
					tempFile.delete();
					}
				}
			//  4-2)DB에서 첨부파일정보 삭제(upload 폴더 내에서 삭제했으니 DB에서도 지우자.)
			rService.deleteFileInfo(rno);
			
			//  4-3)DB에    첨부파일정보 등록
			for(int i=0; i<fileList.size(); i++) {
				HashMap tempMap=(HashMap)fileList.get(i);
				
				rDTO1.setrNo(rDTO.getrNo());
				rDTO1.setPath((String)tempMap.get("path"));
				rDTO1.setOriName((String)tempMap.get("oriName"));
				rDTO1.setiSaveName((String)tempMap.get("iSaveName"));
				rDTO1.setLen((Long)tempMap.get("len"));
				
				rService.insertFileInfo(rDTO1);
			}
		}//if끝

		//3.Model & 4.View    상세보기
		ArrayList<PlaceDto> Info = rService.getPlaceInfo(placeNo);
		
		request.setAttribute("placeNo", placeNo);
		request.setAttribute("Info",Info);
		return "redirect:../review/detailView?placeNo="+placeNo;
	}
	
	// 5. 리뷰 삭제 
	@RequestMapping("deleteReview")
	public String deleteReview(ReviewDTO rDTO, HttpServletRequest request) {
		System.out.println("deleteReview() 진입");
		
		// 파라미터 받기 placeNo = 장소 번호, rno = 글 번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		// 비즈니스 로직
		rDTO.setplaceNo(placeNo);
		rDTO.setrNo(rno);
		
		// Model
		rService.deleteReview(rDTO);
		
		// View
		return "redirect:../review/detailView?placeNo="+placeNo;
	}
	// 작성일(String->Date) 타입 변환 메서드
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	// 좋아요 처리
	@RequestMapping("goodcnt")
	public String goodcnt(ReviewDTO rDTO, HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		// 파라미터 받기 placeNo = 장소 번호, rno = 글 번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		String memberID = request.getParameter("memberID");
		System.out.println("placeNo="+placeNo+", rNo="+rNo+", memberID="+memberID);
		
		// DTO에 실어보낼 데이터 세팅
		rDTO.setplaceNo(placeNo);
		rDTO.setrNo(rNo);
		rDTO.setMemberID(memberID); // 현재는 "~~~"로 대체

		// 비즈니스 로직
		int number = rService.goodcheck(rDTO); // 체크	
		System.out.println("number="+number);
		if( number == 0 ){
			rService.goodupdate(rDTO); // 좋아요 +1
		} else {
			rService.gooddelete(rDTO); // 좋아요 삭제
		}

		request.setAttribute("placeNo", placeNo);
		// View
		return "redirect:../review/detailView?placeNo="+placeNo;
	}	
	
	// 좋아요 수 검색
	@RequestMapping("goodcount")
	public void goodcount(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		// 파라미터 받기 placeNo = 장소 번호, rno = 글 번호
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		
		// 비즈니스 로직 수행
		int count = rService.goodgetcnt(rNo); // 좋아요 수 반환
		PrintWriter out = response.getWriter();
		out.println(count);
		out.close();
	}
		
	// 싫어요 처리
	@RequestMapping("badcnt")
	public String badcnt(ReviewDTO rDTO, HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		// 파라미터 받기 placeNo = 장소 번호, rno = 글 번호
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int rno = Integer.parseInt(request.getParameter("rno"));
		String memberID = request.getParameter("memberID");
			
		// DTO에 실어보낼 데이터 세팅
		rDTO.setplaceNo(placeNo);
		rDTO.setrNo(rno);
		rDTO.setMemberID(memberID); // 현재는 "~~~"로 대체

		// 비즈니스 로직
		int number = rService.badcheck(rDTO); // 체크	
		if( number == 0 ){
			rService.badupdate(rDTO); // 싫어요 +1
		} else {
			rService.baddelete(rDTO); // 싫어요 삭제
		}

		request.setAttribute("placeNo", placeNo);
		
		// View
		return "redirect:../review/detailView?placeNo="+placeNo;
	}	
	
	// 싫어요 수 검색
	@RequestMapping("badcount")
	public void badcount(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		// 파라미터 받기 placeNo = 장소 번호, rno = 글 번호
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		// 비즈니스 로직 수행
		int count = rService.badgetcnt(rno); // 싫어요 수 반환
		PrintWriter out = response.getWriter();
		out.println(count);
		out.close();
	}
		
}