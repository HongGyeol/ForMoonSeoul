package com.project.travel.model.impl.mybatis;

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
	public int getBoardCount(HashMap<String, Object> params) throws Throwable {
		
		return dao.getBoardCount(params);
	}

	@Override
	public List<t_boardVO> getBoardList(HashMap<String, Object> params) throws Throwable {
		
		return dao.getBoardList(params);
	}

	@Override
	public t_boardVO getBoard(t_boardVO vo) throws Throwable {
		
		return dao.getBoard(vo);
	}

	
	@Override
	public List<t_boardVO> checkCt_d(HashMap<String, Object> params) throws Throwable {
		
		return dao.checkCt_d(params);
	}

	@Override
	public void updateCt_d0to2(List<t_boardVO> boardNum) throws Throwable {
		
		dao.updateCt_d0to2(boardNum);
	}

	@Override
	public void updateCt_d1to0(List<t_boardVO> boardNum) throws Throwable {
		
		dao.updateCt_d1to0(boardNum);
	}

}
