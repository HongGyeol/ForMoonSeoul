package com.project.travel.model.impl.mybatis;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.travel.model.t_boardVO;

@Repository
public class t_boardDAOImpl implements t_boardDAO {

	//@Resource(name="SqlSessionTemplate")
	private final SqlSessionTemplate mybatis;
	
	@Autowired
	public t_boardDAOImpl(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@Override
	public void insertBoard(t_boardVO vo) throws Throwable {
		
		mybatis.insert("t_board.insertBoard", vo);
	}

	@Override
	public void deleteBoard(t_boardVO vo) throws Throwable {
		
		mybatis.update("t_board.deleteBoard", vo);
	}

	@Override
	public void updateBoard(t_boardVO vo) throws Throwable {
		
		mybatis.update("t_board.updateBoard", vo);
	}

	
	@Override
	public int getBoardCount(HashMap<String, Object> params) throws Throwable {
		
		return mybatis.selectOne("t_board.getBoardCount", params);
	}

	@Override
	public List<t_boardVO> getBoardList(HashMap<String, Object> params) throws Throwable {
		
		return mybatis.selectList("t_board.getBoardList", params);
	}

	@Override
	public t_boardVO getBoard(t_boardVO vo) throws Throwable {
		
		return mybatis.selectOne("t_board.getBoard", vo);
	}

	
	@Override
	public List<t_boardVO> checkCt_d(HashMap<String, Object> params) throws Throwable {
		
		return mybatis.selectList("t_board.checkCt_d", params);
	}

	@Override
	public void updateCt_d0to2(List<t_boardVO> boardNum) throws Throwable {
		
		mybatis.update("t_board.updateCt_d0to2", boardNum);
	}

	@Override
	public void updateCt_d1to0(List<t_boardVO> boardNum) throws Throwable {
		
		mybatis.update("t_board.updateCt_d1to0", boardNum);
	}

}
