package com.project.travel.model.impl.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.travel.model.t_boardVO;



@Service
public class t_boardServiceImpl implements t_boardService {
	
	private final t_boardDAO dao;
	
	@Autowired
	public t_boardServiceImpl(t_boardDAOImpl dao) {
		this.dao = dao;
	}
	

	@Override
	public void insertBoard(t_boardVO vo) throws Throwable {
		
		dao.insertBoard(vo);
	}

	@Override
	public void deleteBoard(t_boardVO vo) throws Throwable {
		
		dao.deleteBoard(vo);
	}

	@Override
	public void updateBoard(t_boardVO vo) throws Throwable {
		
		dao.updateBoard(vo);
	}

	@Override
	public int getBoardCount(t_boardVO vo) throws Throwable {
		
		return dao.getBoardCount(vo);
	}

	@Override
	public int getBoardCount(t_boardVO vo, String searchKeyword) throws Throwable {
		
		return dao.getBoardCount(vo, searchKeyword);
	}

	@Override
	public int getBoardCount(t_boardVO vo, String date_start, String date_end) throws Throwable {

		return dao.getBoardCount(vo, date_start, date_end);
	}

	@Override
	public List<t_boardVO> getBoardList(t_boardVO vo, int startRow, int endRow) throws Throwable {
		
		return dao.getBoardList(vo, startRow, endRow);
	}

	@Override
	public List<t_boardVO> getBoardList(t_boardVO vo, String searchKeyword, int startRow, int endRow) throws Throwable {
		
		return dao.getBoardList(vo, searchKeyword, startRow, endRow);
	}

	@Override
	public List<t_boardVO> getBoardList(t_boardVO vo, String date_start, String date_end, int startRow, int endRow) throws Throwable {
		
		return dao.getBoardList(vo, date_start, date_end, startRow, endRow);
	}

	@Override
	public t_boardVO getBoard(t_boardVO vo) throws Throwable {
		
		return dao.getBoard(vo);
	}


	@Override
	public List<Integer> checkNumCt_d0(HashMap<String, String> params) throws Throwable {
		
		return dao.checkNumCt_d0(params);
	}


	@Override
	public List<Integer> checkNumCt_d1(HashMap<String, String> params) throws Throwable {
		
		return dao.checkNumCt_d1(params);
	}


	@Override
	public void updateCt_d0to2(List<Integer> boardNum0) throws Throwable {
		
		dao.updateCt_d0to2(boardNum0);
	}
	
	
	@Override
	public void updateCt_d1to0(List<Integer> boardNum1) throws Throwable {
		
		dao.updateCt_d1to0(boardNum1);
	}

}
