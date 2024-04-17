package com.project.travel.model.impl.dao;

import java.util.HashMap;
import java.util.List;

import com.project.travel.model.t_boardVO;

public interface t_boardDAO {
	
	// DB와 같이 외부에 접근하는 경우 외부 요인에 의해 문제가 발생할 수 있으므로 예외처리가 필요하다

	void insertBoard(t_boardVO vo) throws Throwable;
	
	void deleteBoard(t_boardVO vo) throws Throwable;
	
	void updateBoard(t_boardVO vo) throws Throwable;
	
	
	int getBoardCount(t_boardVO vo) throws Throwable;
	
	int getBoardCount(t_boardVO vo, String searchKeyword) throws Throwable;
	
	int getBoardCount(t_boardVO vo, String date_start, String date_end) throws Throwable;
	
	
	List<t_boardVO> getBoardList(t_boardVO vo, int startRow, int endRow) throws Throwable;
	
	List<t_boardVO> getBoardList(t_boardVO vo, String searchKeyword, int startRow, int endRow) throws Throwable;
	
	List<t_boardVO> getBoardList(t_boardVO vo, String date_start, String date_end, int startRow, int endRow) throws Throwable;
	
	
	t_boardVO getBoard(t_boardVO vo) throws Throwable;
	
	
	List<Integer> checkNumCt_d0(HashMap<String, String> params) throws Throwable;
	
	List<Integer> checkNumCt_d1(HashMap<String, String> params) throws Throwable;
	
	
	void updateCt_d0to2(List<Integer> boardNum0) throws Throwable;
	
	void updateCt_d1to0(List<Integer> boardNum1) throws Throwable;
}
