package com.seoulmate.seoulgo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WordCountService {
	private Logger logger = LoggerFactory.getLogger(WordCountService.class);
	public void ReviewCount(String rootPath, Integer rno) {
		String[] cmd = new String[] {rootPath+"WEB-INF/ep/nlp/wordCount.exe", rno.toString()};
		try {
			Process process = new ProcessBuilder(cmd).start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("wordCount error. rno:"+rno+"\n"+e.getMessage());
		}
	}
}
