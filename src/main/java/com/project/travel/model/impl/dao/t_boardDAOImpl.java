package com.project.travel.model.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.travel.model.t_boardVO;
import com.project.travel.model.common.ConnUtil;

@Repository
public class t_boardDAOImpl implements t_boardDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	@Override
	public void insertBoard(t_boardVO vo) throws Throwable {
		
		try {
			conn = ConnUtil.getConnection();
			
			if(vo.getCt_u()==0) {
				stmt = conn.prepareStatement("INSERT INTO T_BOARD(NUM, CT_U, CT_D, TITLE, CONTENT, ZIPCODE, ADDRESS, TRAFFIC, PRICE, TIME_S, TIME_E, OPEN_DAY, TEL, URI, WRITER, FILEPATH) "
						                   + "VALUES(T_BOARD_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, vo.getCt_u());
				stmt.setInt(2, vo.getCt_d());
				stmt.setString(3, vo.getTitle());
				stmt.setString(4, vo.getContent());
				stmt.setString(5, vo.getZipcode());
				stmt.setString(6, vo.getAddress());
				stmt.setString(7, vo.getTraffic());
				stmt.setString(8, vo.getPrice());
				stmt.setTimestamp(9, vo.getTime_s());
				stmt.setTimestamp(10, vo.getTime_e());
				stmt.setString(11, vo.getOpen_day());
				stmt.setString(12, vo.getTel());
				stmt.setString(13, vo.getUri());
				stmt.setString(14, vo.getWriter());
				stmt.setString(15, vo.getFilepath());
			}else if(vo.getCt_u()==1) {
				stmt = conn.prepareStatement("INSERT INTO T_BOARD(NUM, CT_U, CT_D, TITLE, CONTENT, ZIPCODE, ADDRESS, TRAFFIC, PRICE, OPEN_DAY, TEL, URI, WRITER, FILEPATH) "
						                   + "VALUES(T_BOARD_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, vo.getCt_u());
				stmt.setInt(2, vo.getCt_d());
				stmt.setString(3, vo.getTitle());
				stmt.setString(4, vo.getContent());
				stmt.setString(5, vo.getZipcode());
				stmt.setString(6, vo.getAddress());
				stmt.setString(7, vo.getTraffic());
				stmt.setString(8, vo.getPrice());
				stmt.setString(9, vo.getOpen_day());
				stmt.setString(10, vo.getTel());
				stmt.setString(11, vo.getUri());
				stmt.setString(12, vo.getWriter());
				stmt.setString(13, vo.getFilepath());
			}else {
				stmt = conn.prepareStatement("INSERT INTO T_BOARD(NUM, CT_U, CT_D, TITLE, CONTENT, ZIPCODE, ADDRESS, TRAFFIC, OPEN_DAY, TEL, BESTMENU, WRITER, FILEPATH) "
						                   + "VALUES(T_BOARD_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, vo.getCt_u());
				stmt.setInt(2, vo.getCt_d());
				stmt.setString(3, vo.getTitle());
				stmt.setString(4, vo.getContent());
				stmt.setString(5, vo.getZipcode());
				stmt.setString(6, vo.getAddress());
				stmt.setString(7, vo.getTraffic());
				stmt.setString(8, vo.getOpen_day());
				stmt.setString(9, vo.getTel());
				stmt.setString(10, vo.getBestmenu());
				stmt.setString(11, vo.getWriter());
				stmt.setString(12, vo.getFilepath());
			}
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(stmt, conn);
		}
		
	}

	@Override
	public void deleteBoard(t_boardVO vo) throws Throwable {
		
		try {
			conn = ConnUtil.getConnection();
			
			stmt = conn.prepareStatement("UPDATE T_BOARD SET DELETE_YN=?, DELETE_DAY=SYSDATE WHERE NUM=?");
			stmt.setString(1, "Y");
			stmt.setInt(2, vo.getNum());
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(stmt, conn);
		}
		
	}

	@Override
	public void updateBoard(t_boardVO vo) throws Throwable {
		
		try {
			conn = ConnUtil.getConnection();
			
			if(vo.getFilepath().equals("")) {
				if(vo.getCt_u()==0) {
					stmt = conn.prepareStatement("UPDATE T_BOARD "
											   + "SET CT_D=?, TITLE=?, CONTENT=?, ZIPCODE=?, ADDRESS=?, TRAFFIC=?, PRICE=?, TIME_S=?, TIME_E=?, OPEN_DAY=?, TEL=?, URI=?, UPDATER=?, UPDATE_DAY=SYSDATE "
											   + "WHERE NUM=?");
					stmt.setInt(1, vo.getCt_d());
					stmt.setString(2, vo.getTitle());
					stmt.setString(3, vo.getContent());
					stmt.setString(4, vo.getZipcode());
					stmt.setString(5, vo.getAddress());
					stmt.setString(6, vo.getTraffic());
					stmt.setString(7, vo.getPrice());
					stmt.setTimestamp(8, vo.getTime_s());
					stmt.setTimestamp(9, vo.getTime_e());
					stmt.setString(10, vo.getOpen_day());
					stmt.setString(11, vo.getTel());
					stmt.setString(12, vo.getUri());
					stmt.setString(13, vo.getUpdater());
					stmt.setInt(14, vo.getNum());
				}else if(vo.getCt_u()==1) {
					stmt = conn.prepareStatement("UPDATE T_BOARD "
							                   + "SET CT_D=?, TITLE=?, CONTENT=?, ZIPCODE=?, ADDRESS=?, TRAFFIC=?, PRICE=?, OPEN_DAY=?, TEL=?, URI=?, UPDATER=?, UPDATE_DAY=SYSDATE "
							                   + "WHERE NUM=?");
					stmt.setInt(1, vo.getCt_d());
					stmt.setString(2, vo.getTitle());
					stmt.setString(3, vo.getContent());
					stmt.setString(4, vo.getZipcode());
					stmt.setString(5, vo.getAddress());
					stmt.setString(6, vo.getTraffic());
					stmt.setString(7, vo.getPrice());
					stmt.setString(8, vo.getOpen_day());
					stmt.setString(9, vo.getTel());
					stmt.setString(10, vo.getUri());
					stmt.setString(11, vo.getUpdater());
					stmt.setInt(12, vo.getNum());
				}else {
					stmt = conn.prepareStatement("UPDATE T_BOARD "
							                   + "SET CT_D=?, TITLE=?, CONTENT=?, ZIPCODE=?, ADDRESS=?, TRAFFIC=?, OPEN_DAY=?, TEL=?, BESTMENU=?, UPDATER=?, UPDATE_DAY=SYSDATE "
							                   + "WHERE NUM=?");
					stmt.setInt(1, vo.getCt_d());
					stmt.setString(2, vo.getTitle());
					stmt.setString(3, vo.getContent());
					stmt.setString(4, vo.getZipcode());
					stmt.setString(5, vo.getAddress());
					stmt.setString(6, vo.getTraffic());
					stmt.setString(7, vo.getOpen_day());
					stmt.setString(8, vo.getTel());
					stmt.setString(9, vo.getBestmenu());
					stmt.setString(10, vo.getUpdater());
					stmt.setInt(11, vo.getNum());
				}
			}else {
				if(vo.getCt_u()==0) {
					stmt = conn.prepareStatement("UPDATE T_BOARD "
							                   + "SET CT_D=?, TITLE=?, CONTENT=?, ZIPCODE=?, ADDRESS=?, TRAFFIC=?, PRICE=?, TIME_S=?, TIME_E=?, OPEN_DAY=?, TEL=?, URI=?, UPDATER=?, UPDATE_DAY=SYSDATE, FILEPATH=? "
							                   + "WHERE NUM=?");
					stmt.setInt(1, vo.getCt_d());
					stmt.setString(2, vo.getTitle());
					stmt.setString(3, vo.getContent());
					stmt.setString(4, vo.getZipcode());
					stmt.setString(5, vo.getAddress());
					stmt.setString(6, vo.getTraffic());
					stmt.setString(7, vo.getPrice());
					stmt.setTimestamp(8, vo.getTime_s());
					stmt.setTimestamp(9, vo.getTime_e());
					stmt.setString(10, vo.getOpen_day());
					stmt.setString(11, vo.getTel());
					stmt.setString(12, vo.getUri());
					stmt.setString(13, vo.getUpdater());
					stmt.setString(14, vo.getFilepath());
					stmt.setInt(15, vo.getNum());
				}else if(vo.getCt_u()==1) {
					stmt = conn.prepareStatement("UPDATE T_BOARD "
							                   + "SET CT_D=?, TITLE=?, CONTENT=?, ZIPCODE=?, ADDRESS=?, TRAFFIC=?, PRICE=?, OPEN_DAY=?, TEL=?, URI=?, UPDATER=?, FILEPATH=?, UPDATE_DAY=SYSDATE "
							                   + "WHERE NUM=?");
					stmt.setInt(1, vo.getCt_d());
					stmt.setString(2, vo.getTitle());
					stmt.setString(3, vo.getContent());
					stmt.setString(4, vo.getZipcode());
					stmt.setString(5, vo.getAddress());
					stmt.setString(6, vo.getTraffic());
					stmt.setString(7, vo.getPrice());
					stmt.setString(8, vo.getOpen_day());
					stmt.setString(9, vo.getTel());
					stmt.setString(10, vo.getUri());
					stmt.setString(11, vo.getUpdater());
					stmt.setString(12, vo.getFilepath());
					stmt.setInt(13, vo.getNum());
				}else {
					stmt = conn.prepareStatement("UPDATE T_BOARD "
							                   + "SET CT_D=?, TITLE=?, CONTENT=?, ZIPCODE=?, ADDRESS=?, TRAFFIC=?, OPEN_DAY=?, TEL=?, BESTMENU=?, UPDATER=?, FILEPATH=?, UPDATE_DAY=SYSDATE "
							                   + "WHERE NUM=?");
					stmt.setInt(1, vo.getCt_d());
					stmt.setString(2, vo.getTitle());
					stmt.setString(3, vo.getContent());
					stmt.setString(4, vo.getZipcode());
					stmt.setString(5, vo.getAddress());
					stmt.setString(6, vo.getTraffic());
					stmt.setString(7, vo.getOpen_day());
					stmt.setString(8, vo.getTel());
					stmt.setString(9, vo.getBestmenu());
					stmt.setString(10, vo.getUpdater());
					stmt.setString(11, vo.getFilepath());
					stmt.setInt(12, vo.getNum());
				}
			}
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(stmt, conn);
		}
		
	}

	@Override
	public int getBoardCount(t_boardVO vo) throws Throwable {
		
		int count = 0;
		
		try {
			conn = ConnUtil.getConnection();
			
			if(vo.getCt_d()!=9) {				
				stmt = conn.prepareStatement("SELECT COUNT(*) FROM T_BOARD "
						+ "WHERE CT_U=? AND CT_D=? AND DELETE_YN=?");
				stmt.setInt(1, vo.getCt_u());
				stmt.setInt(2, vo.getCt_d());
				stmt.setString(3, "N");
			}else {
				stmt = conn.prepareStatement("SELECT COUNT(*) FROM T_BOARD "
						                   + "WHERE CT_U=? AND DELETE_YN=?");
				stmt.setInt(1, vo.getCt_u());
				stmt.setString(2, "N");
			}
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return count;
	}

	@Override
	public int getBoardCount(t_boardVO vo, String searchKeyword) throws Throwable {
		
		int count = 0;
		
		try {
			conn = ConnUtil.getConnection();
			
			if(vo.getCt_d()!=9) {
				stmt = conn.prepareStatement("SELECT COUNT(*) FROM T_BOARD "
						                   + "WHERE CT_U=? AND CT_D=? AND DELETE_YN=? "
						                   + "AND TITLE LIKE ?");
				stmt.setInt(1, vo.getCt_u());
				stmt.setInt(2, vo.getCt_d());
				stmt.setString(3, "N");
				stmt.setString(4, "%"+searchKeyword+"%");
			}else {
				stmt = conn.prepareStatement("SELECT COUNT(*) FROM T_BOARD "
										   + "WHERE CT_U=? AND DELETE_YN=? "
										   + "AND TITLE LIKE ?");
				stmt.setInt(1, vo.getCt_u());
				stmt.setString(2, "N");
				stmt.setString(3, "%"+searchKeyword+"%");
			}
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return count;
	}

	@Override
	public int getBoardCount(t_boardVO vo, String date_start, String date_end) throws Throwable {
		
		int count = 0;
		
		try {
			conn = ConnUtil.getConnection();
			
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM T_BOARD "
					                   + "WHERE CT_U=? AND CT_D=? AND DELETE_YN=? "
					                   + "AND (?<=TO_CHAR(TIME_S,'YYYY-MM-DD') OR ?>=TO_CHAR(TIME_E,'YYYY-MM-DD'))");
			stmt.setInt(1, vo.getCt_u());
			stmt.setInt(2, vo.getCt_d());
			stmt.setString(3, "N");
			stmt.setString(4, date_start);
			stmt.setString(5, date_end);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return count;
	}

	@Override
	public List<t_boardVO> getBoardList(t_boardVO vo, int startRow, int endRow) throws Throwable {
		
		List<t_boardVO> boardList = null;
		
		try {
			conn = ConnUtil.getConnection();
			
			if(vo.getCt_d()!=9) {
				if(vo.getCt_u()==0) {					
					stmt = conn.prepareStatement("SELECT * FROM "
							                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, TIME_S, TIME_E, FILEPATH FROM "
							                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND CT_D=? AND DELETE_YN=? ORDER BY NUM DESC)) "
							                   + "WHERE RNUM>=? AND RNUM<=?");
					stmt.setInt(1, vo.getCt_u());
					stmt.setInt(2, vo.getCt_d());
					stmt.setString(3, "N");
					stmt.setInt(4, startRow);
					stmt.setInt(5, endRow);
				}else {
					stmt = conn.prepareStatement("SELECT * FROM "
							                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, FILEPATH FROM "
							                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND CT_D=? AND DELETE_YN=? ORDER BY NUM DESC)) "
							                   + "WHERE RNUM>=? AND RNUM<=?");
					stmt.setInt(1, vo.getCt_u());
					stmt.setInt(2, vo.getCt_d());
					stmt.setString(3, "N");
					stmt.setInt(4, startRow);
					stmt.setInt(5, endRow);
				}
			}else {
				stmt = conn.prepareStatement("SELECT * FROM "
						                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, FILEPATH FROM "
						                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND DELETE_YN=? ORDER BY NUM DESC)) "
						                   + "WHERE RNUM>=? AND RNUM<=?");
				stmt.setInt(1, vo.getCt_u());
				stmt.setString(2, "N");
				stmt.setInt(3, startRow);
				stmt.setInt(4, endRow);
			}
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<t_boardVO>(endRow - startRow +1);
				
				do {
					t_boardVO board = new t_boardVO();
					
					if(vo.getCt_u()==0) {
						board.setNum(rs.getInt("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setTime_s(rs.getTimestamp("time_s"));
						board.setTime_e(rs.getTimestamp("time_e"));
						board.setFilepath(rs.getString("filepath"));
					}else {
						board.setNum(rs.getInt("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setFilepath(rs.getString("filepath"));
					}
					
					boardList.add(board);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return boardList;
	}

	@Override
	public List<t_boardVO> getBoardList(t_boardVO vo, String searchKeyword, int startRow, int endRow) throws Throwable {
		
		List<t_boardVO> boardList = null;
		
		try {
			conn = ConnUtil.getConnection();
			
			if(vo.getCt_d()!=9) {
				if(vo.getCt_u()==0) {
					stmt = conn.prepareStatement("SELECT * FROM "
							                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, TIME_S, TIME_E, FILEPATH FROM "
							                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND CT_D=? AND DELETE_YN=? "
							                   + "AND TITLE LIKE ? ORDER BY NUM DESC)) "
							                   + "WHERE RNUM>=? AND RNUM<=?");
					stmt.setInt(1, vo.getCt_u());
					stmt.setInt(2, vo.getCt_d());
					stmt.setString(3, "N");
					stmt.setString(4, "%"+searchKeyword+"%");
					stmt.setInt(5, startRow);
					stmt.setInt(6, endRow);
				}else {
					stmt = conn.prepareStatement("SELECT * FROM "
							                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, FILEPATH FROM "
							                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND CT_D=? AND DELETE_YN=? "
							                   + "AND TITLE LIKE ? ORDER BY NUM DESC)) "
							                   + "WHERE RNUM>=? AND RNUM<=?");
					stmt.setInt(1, vo.getCt_u());
					stmt.setInt(2, vo.getCt_d());
					stmt.setString(3, "N");
					stmt.setString(4, "%"+searchKeyword+"%");
					stmt.setInt(5, startRow);
					stmt.setInt(6, endRow);
				}
			}else {
				stmt = conn.prepareStatement("SELECT * FROM "
						                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, FILEPATH FROM "
						                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND DELETE_YN=? "
						                   + "AND TITLE LIKE ? ORDER BY NUM DESC)) "
						                   + "WHERE RNUM>=? AND RNUM<=?");
				stmt.setInt(1, vo.getCt_u());
				stmt.setString(2, "N");
				stmt.setString(3, "%"+searchKeyword+"%");
				stmt.setInt(4, startRow);
				stmt.setInt(5, endRow);
			}
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<t_boardVO>(endRow - startRow +1);
				
				do {
					t_boardVO board = new t_boardVO();
					
					if(vo.getCt_u()==0) {
						board.setNum(rs.getInt("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setTime_s(rs.getTimestamp("time_s"));
						board.setTime_e(rs.getTimestamp("time_e"));
						board.setFilepath(rs.getString("filepath"));
					}else {
						board.setNum(rs.getInt("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setFilepath(rs.getString("filepath"));
					}
					
					boardList.add(board);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return boardList;
	}

	@Override
	public List<t_boardVO> getBoardList(t_boardVO vo, String date_start, String date_end, int startRow, int endRow)
			throws Throwable {
		
		List<t_boardVO> boardList = null;
				
		try {
			conn = ConnUtil.getConnection();
			
			stmt = conn.prepareStatement("SELECT * FROM "
					                   + "(SELECT ROWNUM RNUM, NUM, TITLE, CONTENT, TIME_S, TIME_E, FILEPATH FROM "
					                   + "(SELECT * FROM T_BOARD WHERE CT_U=? AND CT_D=? AND DELETE_YN=? "
					                   + "AND (?<=TO_CHAR(TIME_S,'YYYY-MM-DD') OR ?>=TO_CHAR(TIME_E,'YYYY-MM-DD')) ORDER BY NUM DESC)) "
					                   + "WHERE RNUM>=? AND RNUM<=?");
			stmt.setInt(1, vo.getCt_u());
			stmt.setInt(2, vo.getCt_d());
			stmt.setString(3, "N");
			stmt.setString(4, date_start);
			stmt.setString(5, date_end);
			stmt.setInt(6, startRow);
			stmt.setInt(7, endRow);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<t_boardVO>(endRow - startRow +1);
				
				do {
					t_boardVO board = new t_boardVO();
					
					board.setNum(rs.getInt("num"));
					board.setTitle(rs.getString("title"));
					board.setContent(rs.getString("content"));
					board.setTime_s(rs.getTimestamp("time_s"));
					board.setTime_e(rs.getTimestamp("time_e"));
					board.setFilepath(rs.getString("filepath"));
					
					boardList.add(board);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return boardList;
	}

	@Override
	public t_boardVO getBoard(t_boardVO vo) throws Throwable {
		
		t_boardVO board = null;
		
		try {
			conn = ConnUtil.getConnection();
			
			stmt = conn.prepareStatement("SELECT * FROM T_BOARD WHERE NUM=?");
			stmt.setInt(1, vo.getNum());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new t_boardVO();
				
				board.setCt_u(rs.getInt("ct_u"));
				board.setCt_d(rs.getInt("ct_d"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setZipcode(rs.getString("zipcode"));
				board.setAddress(rs.getString("address"));
				board.setTraffic(rs.getString("traffic"));
				board.setPrice(rs.getString("price"));
				board.setTime_s(rs.getTimestamp("time_s"));
				board.setTime_e(rs.getTimestamp("time_e"));
				board.setOpen_day(rs.getString("open_day"));
				board.setTel(rs.getString("tel"));
				board.setUri(rs.getString("uri"));
				board.setBestmenu(rs.getString("bestmenu"));
				board.setWriter(rs.getString("writer"));
				board.setWrite_day(rs.getDate("write_day"));
				board.setUpdate_day(rs.getDate("update_day"));
				board.setFilepath(rs.getString("filepath"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return board;
	}

	/*
		진행중 카테고리에 있는 게시글 중에서 기간이 진행완료로 변경된 게시글 번호 출력
	*/
	@Override
	public List<Integer> checkNumCt_d0(HashMap<String, String> params) throws Throwable {
		
		List<Integer> boardNum = null;
		
		try {
			
			conn = ConnUtil.getConnection();
			
			stmt = conn.prepareStatement("SELECT NUM FROM T_BOARD "
									   + "WHERE CT_D=? AND DELETE_YN=? AND "
									   + "?>TO_CHAR(TIME_E, 'YYYY-MM-DD')");
			stmt.setInt(1, Integer.parseInt(params.get("ct_d")));
			stmt.setString(2, "N");
			stmt.setString(3, params.get("today"));
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				boardNum = new ArrayList<Integer>();
				
				do {
					
					boardNum.add(rs.getInt(1));	
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return boardNum;
		
	}

	/*
		진행예정 카테고리에 있는 게시글 중에서 기간이 진행중으로 변경된 게시글 번호 출력
	*/
	@Override
	public List<Integer> checkNumCt_d1(HashMap<String, String> params) throws Throwable {
		
		List<Integer> boardNum = null;
		
		try {
			conn = ConnUtil.getConnection();
			stmt = conn.prepareStatement("SELECT NUM FROM T_BOARD "
									   + "WHERE CT_D=? AND DELETE_YN=? AND "
									   + "?>=TO_CHAR(TIME_S, 'YYYY-MM-DD')");
			stmt.setInt(1, Integer.parseInt(params.get("ct_d")));
			stmt.setString(2, "N");
			stmt.setString(3, params.get("today"));
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				boardNum = new ArrayList<Integer>();
				
				do {
					
					boardNum.add(rs.getInt(1));
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(rs, stmt, conn);
		}
		
		return boardNum;
		
	}

	/*
		진행중 카테고리에 있는 게시글 중에서 기간이 진행완료로 변경된 게시글 카테고리값 변경
	*/
	@Override
	public void updateCt_d0to2(List<Integer> boardNum0) throws Throwable {
		
		try {
			
			conn = ConnUtil.getConnection();
			
			String str = "UPDATE T_BOARD SET CT_D=? WHERE NUM IN (";
			String num = "?";
			
			for(int i=1; i<boardNum0.size(); i++) {
				num += ",?";
			}
			
			stmt = conn.prepareStatement(str+num+")");
			
			stmt.setInt(1, 2);
			
			for(int i=0; i<boardNum0.size(); i++) {
				stmt.setInt(i+2, boardNum0.get(i));
			}
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(stmt, conn);
		}
		
	}
	
	/*
		진행예정 카테고리에 있는 게시글 중에서 기간이 진행중으로 변경된 게시글 카테고리값 변경
	*/
	@Override
	public void updateCt_d1to0(List<Integer> boardNum1) throws Throwable {
		
		try {
			
			conn = ConnUtil.getConnection();
			
			String str = "UPDATE T_BOARD SET CT_D=? WHERE NUM IN (";
			String num = "?";
			
			for(int i=1; i<boardNum1.size(); i++) {
				num += ",?";
			}
			
			stmt = conn.prepareStatement(str+num+")");
			
			stmt.setInt(1, 0);
			
			for(int i=0; i<boardNum1.size(); i++) {
				stmt.setInt(i+2, boardNum1.get(i));
			}
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(stmt, conn);
		}
		
	}

}
