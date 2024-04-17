package com.project.travel.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.travel.model.t_boardVO;
import com.project.travel.model.impl.mybatis.t_boardService;
import com.project.travel.model.impl.mybatis.t_boardServiceImpl;

@Component
@EnableScheduling
public class t_boardBatchComponent2 {

	private final t_boardService bs;
	
	@Autowired
	public t_boardBatchComponent2(t_boardServiceImpl bs) {
		this.bs = bs;
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void checkCt_d() throws Throwable {
		
		try {
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			List<t_boardVO> boardNum0 = null;
			List<t_boardVO> boardNum1 = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			params.put("today", today);
			
			// 진행중 카테고리에 존재하는 게시글 중에서 기간이 진행종료로 변경된 게시글 체크 및 카테고리 변경
			params.put("ct_d", 0);
			boardNum0 = bs.checkCt_d(params);
			if(!boardNum0.isEmpty()) {
				bs.updateCt_d0to2(boardNum0);
				System.out.println("updateCt_d0to2 success");
			}else {
				System.out.println("checkCt_d0to2 = null");
			}
			
			// 진행예정 카테고리에 존재하는 게시글 중에서 기간이 진행중으로 변경된 게시글 체크 및 카테고리 변경
			params.put("ct_d", 1);
			boardNum1 = bs.checkCt_d(params);
			if(!boardNum1.isEmpty()) {
				bs.updateCt_d1to0(boardNum1);
				System.out.println("updateCt_d1to0 success");
			}else {
				System.out.println("checkCt_d1to0 = null");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
