package com.project.travel.model.impl.mybatis;

import java.util.HashMap;
import java.util.List;

import com.project.travel.model.t_boardVO;

public interface t_boardService {

	void insertBoard(t_boardVO vo) throws Throwable;
	
	void deleteBoard(t_boardVO vo) throws Throwable;
	
	void updateBoard(t_boardVO vo) throws Throwable;
	
	
	int getBoardCount(HashMap<String, Object> params) throws Throwable;
	
	List<t_boardVO> getBoardList(HashMap<String, Object> params) throws Throwable;
	
	t_boardVO getBoard(t_boardVO vo) throws Throwable;
	
	
	List<t_boardVO> checkCt_d(HashMap<String, Object> params) throws Throwable;
	
	void updateCt_d0to2(List<t_boardVO> boardNum) throws Throwable;
	
	void updateCt_d1to0(List<t_boardVO> boardNum) throws Throwable;
	
}
