package com.seoulmate.seoulgo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.StatisticsDao;
import com.seoulmate.seoulgo.dto.ReviewStatisticsDto;

@Service
public class StatisticsService {
	private Logger logger = LoggerFactory.getLogger(StatisticsService.class);
	@Autowired
	private StatisticsDao dao;
	
	private void reviewWordCount(HttpServletRequest req, Integer rNo) {
		String[] cmd = new String[] {req.getSession().getServletContext().getRealPath("/")+"WEB-INF/ep/nlp/wordCount.exe", "-k", rNo.toString()};
		try {
			Process process = new ProcessBuilder(cmd).start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("wordCount error. rNo:"+rNo+"\n"+e.getMessage());
		}
	}
	public void insert(HttpServletRequest req, Integer rNo) {
		dao.update(new ReviewStatisticsDto(rNo));
		reviewWordCount(req, rNo);
	}
	public void update(HttpServletRequest req, Integer rNo) {
		dao.update(new ReviewStatisticsDto(rNo));
		reviewWordCount(req, rNo);
	}
	public void delete(HttpServletRequest req, Integer rNo) {
		dao.update(new ReviewStatisticsDto(rNo));
		reviewWordCount(req, rNo);
	}
}
