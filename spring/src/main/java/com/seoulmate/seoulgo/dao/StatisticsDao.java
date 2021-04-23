package com.seoulmate.seoulgo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsDao {
	private Logger logger = LoggerFactory.getLogger(StatisticsDao.class);
	@Autowired
	SqlSessionTemplate session;
	
	public void update(com.seoulmate.seoulgo.dto.ReviewStatisticsDto dto) {
		try {
			session.update("statistics.updateReview", dto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
