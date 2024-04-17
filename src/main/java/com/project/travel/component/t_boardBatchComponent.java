package com.project.travel.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.travel.model.impl.dao.t_boardService;
import com.project.travel.model.impl.dao.t_boardServiceImpl;

//@Component
//@EnableScheduling
public class t_boardBatchComponent {

	private final t_boardService bs;
	
	@Autowired
	public t_boardBatchComponent(t_boardServiceImpl bs) {
		this.bs = bs;
	}
	
	// 초 분 시 일 월 요일
	// * - 모든
	// ? - 월, 요일에 사용. 신경안씀이라는 의미
	// 월은 1 - 12
	// 요일은 1(일) - 7(토). ,(컴마)로 복수지정가능. 예)월수금 - 2,4,6
	// 숫자1/숫자2의 경우 숫자1에서 시작하여 매 숫자2마다 실행. 예) 분에 0/5이면 0분부터 5분마다 실행
	// 일에서 L은 달의 마지막날. W는 지정일자가 휴일(토, 일)이면 인접한 평일에 수행.
	// 예) 25W인경우 25일이 일요일이면 26일 월요일 실행.
	// LW는 마지막 평일
	// 요일에서 숫자1#숫자2의경우 숫자2번째 주의 숫자1번 요일에 실행.
	
	/*
		배치 컴포넌트를 이용하려면 pom.xml에 CGLIB <dependency> 추가해야 한다!!!
	*/
	// @Scheduled(cron = "*/20 * * * * *")
	public void checkCt_d() throws Throwable{
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("today", today);
			
			// 진행중 카테고리에 있는 게시글의 기간이 진행완료로 바뀐다면
			params.put("ct_d", "0");
			List<Integer> boardNum0 = bs.checkNumCt_d0(params);
			
			if(!boardNum0.isEmpty()) {
				bs.updateCt_d0to2(boardNum0);
				System.out.println("updateCt_d0to2 success");
			} else {
				System.out.println("checkNumCt_d0 = null");
			}
			
			
			// 진행예정 카테고리에 있는 게시글의 기간이 진행중으로 바뀐다면
			params.put("ct_d", "1");
			List<Integer> boardNum1 = bs.checkNumCt_d1(params);
			
			if(!boardNum1.isEmpty()) {
				bs.updateCt_d1to0(boardNum1);
				System.out.println("updateCt_d1to0 success");
			}else {
				System.out.println("checkNumCt_d1 = null");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
