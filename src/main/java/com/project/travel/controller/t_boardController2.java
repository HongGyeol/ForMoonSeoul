package com.project.travel.controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.travel.model.t_boardVO;
import com.project.travel.model.impl.mybatis.t_boardService;
import com.project.travel.model.impl.mybatis.t_boardServiceImpl;

@Controller
public class t_boardController2 {

	private final t_boardService bs;
	
	@Autowired
	public t_boardController2(t_boardServiceImpl bs) {
		this.bs = bs;
	}
	
	/*
		메인 페이지
	*/
	@RequestMapping(value="/index.traveldo")
	public ModelAndView indexPage(ModelAndView mav) {
		
		mav.setViewName("index");
		
		return mav;
	}
	
	/*
		게시글 목록 페이지
	*/
	@RequestMapping(value="/t_list.traveldo")
	public ModelAndView getBoardList(
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String searchKeyword,
			@RequestParam(value="date_start", defaultValue="", required=false) String date_start,
			@RequestParam(value="date_end", defaultValue="", required=false) String date_end,
			@RequestParam(value="pageNum", defaultValue="1", required=false) String pageNum,
			t_boardVO vo, ModelAndView mav) throws Throwable {
		
		// 현재 페이지
		int currentPage = Integer.parseInt(pageNum);
		
		// 한 페이지에 출력되는 게시글 최대 개수
		int boardSize = 4;
		
		// 해당 페이지에 출력되는 게시글의 첫번째 행
		int startRow = (currentPage-1) * boardSize + 1;
		
		// 해당 페이지에 출력되는 게시글의 마지막 행
		int endRow = currentPage * boardSize;
		
		// 출력되는 게시글의 총 개수
		int count = 0;
		
		// DB로부터 가져오는 게시글 목록을 저장할 변수
		List<t_boardVO> boardList = null;
		
		// mybatis sql mapper xml파일에 파라미터로 넘어갈 HashMap타입의 params 선언
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		// params에 vo, startRow, endRow 저장
		params.put("vo", vo);
		params.put("startRow", startRow);
		params.put("endRow", endRow);
		
		if(vo.getCt_u()==0) {
			
			// 기간 검색 처리
			if(!date_start.equals("")) {
				String searchDate_s = date_start;
				mav.addObject("searchDate_s", searchDate_s);
				
				date_start = date_start.substring(0,10);
			}
			
			if(!date_end.equals("")) {
				String searchDate_e = date_end;
				mav.addObject("searchDate_e", searchDate_e);
				
				date_end = date_end.substring(0,10);
			}
			
			mav.addObject("date_start", date_start);
			mav.addObject("date_end", date_end);
			
			// DB로부터 게시글 목록과 게시글 총 개수 받아서 변수에 저장 
			if(searchKeyword.equals("")) {
				if(date_start.equals("") && date_end.equals("")) {
					count = bs.getBoardCount(params);
					System.out.println("default list count = " + count);
					boardList = bs.getBoardList(params);
					System.out.println("default list = " + boardList.isEmpty());
					
				}else {
					params.put("date_start", date_start);
					params.put("date_end", date_end);
					
					count = bs.getBoardCount(params);
					System.out.println("date list count = " + count);
					boardList = bs.getBoardList(params);
					System.out.println("date list = " + boardList.isEmpty());
				}
			}else {
				params.put("searchKeyword", searchKeyword);
				
				count = bs.getBoardCount(params);
				System.out.println("keyword list count = " + count);
				boardList = bs.getBoardList(params);
				System.out.println("keyword list = " + boardList.isEmpty());
			}
		}else {
			// DB로부터 게시글 목록 및 게시글 총 개수 받아서 변수에 저장
			if(searchKeyword.equals("")) {
				count = bs.getBoardCount(params);
				System.out.println("default list count = " + count);
				boardList = bs.getBoardList(params);
				System.out.println("default list = " + boardList.isEmpty());
			}else {
				params.put("searchKeyword", searchKeyword);
				
				count = bs.getBoardCount(params);
				System.out.println("keyword list count = " + count);
				boardList = bs.getBoardList(params);
				System.out.println("keyword list = " + boardList.isEmpty());
			}
			
		}
		
		// ModelAndView 객체에 데이터 저장
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("currentPage", currentPage);
		mav.addObject("boardSize", boardSize);
		mav.addObject("count", count);
		mav.addObject("boardList", boardList);
		
		mav.addObject("ct_u", vo.getCt_u());
		mav.addObject("ct_d", vo.getCt_d());
		
		// view name 설정
		if(vo.getCt_u()==0) {
			mav.setViewName("travel/t_list0");
		}else if(vo.getCt_u()==1) {
			mav.setViewName("travel/t_list1");
		}else {
			mav.setViewName("travel/t_list2");
		}
		
		return mav;
	}

	/*
		게시글 상세 페이지
	*/
	@RequestMapping(value="/t_detail.traveldo")
	public ModelAndView getBoard(
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String searchKeyword,
			@RequestParam(value="date_start", defaultValue="", required=false) String date_start,
			@RequestParam(value="date_end", defaultValue="", required=false) String date_end,
			@RequestParam(value="pageNum", defaultValue="1", required=false) String pageNum,
			t_boardVO vo, ModelAndView mav) throws Throwable {
		
		// DB로부터 해당 게시글 데이터 가져오기
		t_boardVO board = bs.getBoard(vo);
		
		// ModelandVeiw 객체에 게시글 데이터 저장
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("date_start", date_start);
		mav.addObject("date_end", date_end);
		mav.addObject("pageNum", pageNum);
		
		mav.addObject("num", vo.getNum());
		mav.addObject("ct_u", vo.getCt_u());
		mav.addObject("ct2", vo.getCt_d());
		mav.addObject("ct_d", board.getCt_d());
		mav.addObject("title", board.getTitle());
		mav.addObject("content", board.getContent().replace("\n", "<br>"));
		mav.addObject("zipcode", board.getZipcode());
		mav.addObject("address", board.getAddress());
		mav.addObject("traffic", board.getTraffic().replace("\n", "<br>"));
		mav.addObject("open_day", board.getOpen_day().replace("\n", "<br>"));
		mav.addObject("tel", board.getTel());
		mav.addObject("write_day", board.getWrite_day());
		mav.addObject("update_day", board.getUpdate_day());
		mav.addObject("filepath", board.getFilepath());
		
		if(board.getCt_u()!=2) {
			mav.addObject("uri", board.getUri());
			mav.addObject("price", board.getPrice().replace("\n", "<br>"));
		}else {
			mav.addObject("bestmenu", board.getBestmenu().replace("\n", "<br>"));
		}
		
		if(board.getCt_u()==0) {
			mav.addObject("time_s", board.getTime_s().toString().substring(0,10));
			mav.addObject("time_e", board.getTime_e().toString().substring(0,10));
		}
		
		// view name 설정
		mav.setViewName("travel/t_detail");
		
		return mav;
	}
	
	/*
		게시글 등록 페이지
	*/
	@RequestMapping(value="/t_write.traveldo")
	public ModelAndView getWriteForm(t_boardVO vo, ModelAndView mav) {
		
		// ModelAndView 객체에 데이터 저장
		mav.addObject("ct_u", vo.getCt_u());
		mav.addObject("ct_d", vo.getCt_d());
		
		// view name 설정
		if(vo.getCt_u()==0) {
			mav.setViewName("travel/t_write0");
		}else if(vo.getCt_u()==1) {
			mav.setViewName("travel/t_write1");
		}else {
			mav.setViewName("travel/t_write2");
		}
		
		return mav;
	}

	/*
		게시글 등록 처리
	*/
	@RequestMapping(value="/t_write_proc.traveldo")
	public ModelAndView insertBoard(t_boardVO vo, ModelAndView mav, HttpServletRequest request) throws Throwable {
		
		// 상위 카테고리 설정
		int ct1 = 0;
		
		// 하위 카테고리 설정
		int ct2 = 9;
		
		// 업로드 경로 설정 - 웹애플리케이션 상대경로 src/main/webapp
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		// 업로드 파일 크기
		int size = 10 * 1024 * 1024;
		
		// 업로드 경로에 파일이 업로드 될 때 지정되는 이름
		String fileName = "";
		
		try {
			// DefaultFileRenamePolicy() - 업로드 경로에 동일한 파일명이 존재하느 경우 숫자를 붙여 처리
			MultipartRequest multi =
					new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
			
			// getFilenames() - type="file"인 input 태그의 name속성값을 이뉴머레이션(객체들의 집합)형태로 반환
			Enumeration files = multi.getFileNames();
			
			// nextElement() - 이뉴머레이션의 요소들에 순차적으로 접근해 데이터를 Object타입으로 반환
			String file = (String)files.nextElement();
			
			System.out.println("file = " + file);
			
			// getFilesystemName() - 실제 업로드경로에 파일이 업로드 될 때 지정되는 파일명을 반환
			fileName = multi.getFilesystemName(file);
			
			ct1 = Integer.parseInt(multi.getParameter("ct_u"));
			
			vo.setCt_u(ct1);
			vo.setTitle(multi.getParameter("title"));
			vo.setContent(multi.getParameter("content"));
			vo.setZipcode(multi.getParameter("zipcode"));
			vo.setAddress(multi.getParameter("address"));
			vo.setTraffic(multi.getParameter("traffic"));
			vo.setOpen_day(multi.getParameter("open_day"));
			vo.setTel(multi.getParameter("tel"));
			vo.setWriter(multi.getParameter("writer"));
			vo.setFilepath("/resources/upload/"+fileName);
			
			if(ct1!=2) {
				vo.setUri(multi.getParameter("uri"));
				vo.setPrice(multi.getParameter("price"));
			}else {
				vo.setBestmenu(multi.getParameter("bestmenu"));
			}
			
			if(ct1==0) {
				Timestamp time_s = Timestamp.valueOf(multi.getParameter("start_datetime").substring(0,10) + " " +
												     multi.getParameter("start_datetime").substring(11) + ":00");
				Timestamp time_e = Timestamp.valueOf(multi.getParameter("end_datetime").substring(0,10) + " " +
												     multi.getParameter("end_datetime").substring(11) + ":00");
				vo.setTime_s(time_s);
				vo.setTime_e(time_e);
				
				int start = Integer.parseInt(multi.getParameter("start_datetime").substring(0,4) +
											 multi.getParameter("start_datetime").substring(5,7) +
											 multi.getParameter("start_datetime").substring(8,10));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				int today = Integer.parseInt(sdf.format(new Date()));
				
				if(start>today) {
					ct2 = 1;
				}else {
					ct2 = 0;
				}
			}else {
				ct2 = Integer.parseInt(multi.getParameter("ct_d"));
			}
			
			vo.setCt_d(ct2);
			
			bs.insertBoard(vo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// ModelAndView 객체에 데이터 저장
		mav.addObject("ct_u", ct1);
		mav.addObject("ct_d", ct2);
		
		// view name 설정
		mav.setViewName("redirect:t_list.traveldo");
		
		return mav;
	}
	
	/*
		게시글 수정 페이지
	*/
	@RequestMapping(value="/t_update.traveldo")
	public ModelAndView getUpdateForm(
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String searchKeyword,
			@RequestParam(value="date_start", defaultValue="", required=false) String date_start,
			@RequestParam(value="date_end", defaultValue="", required=false) String date_end,
			@RequestParam(value="pageNum", defaultValue="1", required=false) String pageNum,
			t_boardVO vo, ModelAndView mav) throws Throwable {
		
		// 해당 게시글의 데이터를 가져온다.
		t_boardVO board = bs.getBoard(vo);
		
		// ModelAndView객체에 데이터 저장 및 view name 설정
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("date_start", date_start);
		mav.addObject("date_end", date_end);
		mav.addObject("pageNum", pageNum);
		
		mav.addObject("num", vo.getNum());
		mav.addObject("ct_u", vo.getCt_u());
		mav.addObject("ct2", vo.getCt_d());
		mav.addObject("ct_d", board.getCt_d());
		mav.addObject("title", board.getTitle());
		mav.addObject("content", board.getContent());
		mav.addObject("zipcode", board.getZipcode());
		mav.addObject("address", board.getAddress());
		mav.addObject("traffic", board.getTraffic());
		mav.addObject("open_day", board.getOpen_day());
		mav.addObject("tel", board.getTel());
		mav.addObject("writer", board.getWriter());
		mav.addObject("p_filepath", board.getFilepath());
		mav.addObject("p_filename", board.getFilepath().substring(18));
		
		if(board.getCt_u()==0) {
			mav.addObject("uri", board.getUri());
			mav.addObject("price", board.getPrice());
			mav.addObject("time_s", board.getTime_s());
			mav.addObject("time_e", board.getTime_e());
			
			mav.setViewName("travel/t_update0");
		}else if(board.getCt_u()==1) {
			mav.addObject("uri", board.getUri());
			mav.addObject("price", board.getPrice());
			
			mav.setViewName("travel/t_update1");
		}else {
			mav.addObject("bestmenu", board.getBestmenu());
			
			mav.setViewName("travel/t_update2");
		}
		
		return mav;
	}
	
	/*
		게시글 수정 처리
	*/
	@RequestMapping(value="/t_update_proc.traveldo")
	public ModelAndView updateBoard(t_boardVO vo, ModelAndView mav, HttpServletRequest request) throws Throwable {
		
		// 게시글 번호
		int num = 0;
		
		// 상위 카테고리
		int ct1 = 0;
		
		// 하위 카테고리
		int ct2 = 9;
		
		// 검색어 설정
		String searchKeyword = "";
		
		// 검색 기간 설정
		String date_start = "";
		
		String date_end = "";
		
		// 페이지 설정
		String pageNum = "1";
		
		// 업로드 경로 - 웹 애플리케이션 절대경로 src/main/webapp
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		// 업로드 파일 크기
		int size = 10 * 1024 * 1024;
		
		// 업로드 경로에 파일이 업로드 될 때 지정되는 이름
		String fileName = "";
		
		try {
			// DefaultFileRenamePolicy() - 업로드경로에 동일한 파일명이 존재하는 경우 파일명에 숫자를 붙여 처리
			MultipartRequest multi =
					new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
			
			// getFileNames() - type="file"인 input태그의 name속성값들을 이뉴머레이션(객체들의집합)형태로 반환
			Enumeration files = multi.getFileNames();
			
			// nextElement() - 이뉴머레이션 객체의 요소들에 순차적으로 접근해 데이터를 Object타입으로 반환
			String file = (String)files.nextElement();
			
			// getFilesystemName(file) - 해당 name속성의 파일이 업로드 경로에 업로드될 때 지정되는 이름
			if(multi.getFilesystemName(file)!=null) {
				fileName = multi.getFilesystemName(file); 
			}
			
			num = Integer.parseInt(multi.getParameter("num"));
			ct1 = Integer.parseInt(multi.getParameter("ct_u"));
			
			vo.setNum(num);
			vo.setCt_u(ct1);
			vo.setTitle(multi.getParameter("title"));
			vo.setContent(multi.getParameter("content"));
			vo.setZipcode(multi.getParameter("zipcode"));
			vo.setAddress(multi.getParameter("address"));
			vo.setTraffic(multi.getParameter("traffic"));
			vo.setOpen_day(multi.getParameter("open_day"));
			vo.setTel(multi.getParameter("tel"));
			vo.setUpdater(multi.getParameter("updater"));
			
			if(fileName.equals("")) {
				vo.setFilepath(fileName);
			}else {
				File img = new File(uploadPath + multi.getParameter("p_filepath").substring(17));
				
				if(img.exists()) {
					img.delete();
				}
				
				vo.setFilepath("/resources/upload/" + fileName);
			}
			
			if(ct1!=2) {
				vo.setUri(multi.getParameter("uri"));
				vo.setPrice(multi.getParameter("price"));
			}else {
				vo.setBestmenu(multi.getParameter("bestmenu"));
			}
			
			if(ct1==0) {
				Timestamp time_s = Timestamp.valueOf(multi.getParameter("start_datetime").substring(0,10) + " " +
													 multi.getParameter("start_datetime").substring(11) + ":00");
				Timestamp time_e = Timestamp.valueOf(multi.getParameter("end_datetime").substring(0,10) + " " +
													 multi.getParameter("end_datetime").substring(11) + ":00");
				
				vo.setTime_s(time_s);
				vo.setTime_e(time_e);
				
				int start = Integer.parseInt(multi.getParameter("start_datetime").substring(0,4) +
						  					 multi.getParameter("start_datetime").substring(5,7) +
						  					 multi.getParameter("start_datetime").substring(8,10));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				int today = Integer.parseInt(sdf.format(new Date()));
				
				if(start>today) {
					ct2 = 1;
				}else {
					ct2 = 0;
				}
				
				date_start = multi.getParameter("date_start");
				date_end = multi.getParameter("date_end");
				
				mav.addObject("date_start", date_start);
				mav.addObject("date_end", date_end);
				
			}else {
				ct2 = Integer.parseInt(multi.getParameter("ct_d"));
			}
			
			vo.setCt_d(ct2);
			
			bs.updateBoard(vo);
			
			ct2 = Integer.parseInt(multi.getParameter("ct2"));
			searchKeyword = multi.getParameter("searchKeyword");
			pageNum = multi.getParameter("pageNum");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// ModelAndView 객체에 데이터 저장
		mav.addObject("num", num);
		mav.addObject("ct_u", ct1);
		mav.addObject("ct_d", ct2);
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("pageNum", pageNum);
		
		// view name 설정
		mav.setViewName("redirect:t_detail.traveldo");
		
		return mav;
	}
	
	/*
		게시글 삭제 처리
	*/
	@RequestMapping(value="/t_delete_proc.traveldo")
	public ModelAndView deleteBoard(t_boardVO vo, ModelAndView mav) throws Throwable{
		
		// 게시글 삭제 메서드 호출
		bs.deleteBoard(vo);
		
		// ModelAndView객체에 데이터 저장
		mav.addObject("ct_u", vo.getCt_u());
		mav.addObject("ct_d", vo.getCt_d());
		
		// view name 설정
		mav.setViewName("redirect:t_list.traveldo");
		
		return mav;
	}
	
	
}
