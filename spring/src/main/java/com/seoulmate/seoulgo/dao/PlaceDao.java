package com.seoulmate.seoulgo.dao;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.page.PlacePage;

@Repository
public class PlaceDao {
	private Logger logger = LoggerFactory.getLogger(PlaceDao.class);
	
	@Autowired
	SqlSessionTemplate session;

	public int insert(PlaceDto dto) {
		int rv = 0;
		try {
			rv = session.insert("place.insert", dto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			delete(dto.getPlaceNo());
		}
		return rv;
	}

	public int update(PlaceDto dto) {
		int rv = 0;
		try {
			rv = session.update("place.update", dto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public int delete(int placeNo) {
		int rv = 0;
		try {
			rv = session.delete("place.delete", placeNo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public int insertSub(PlaceDto dto) {
		int rv = 0;
		try {
			Integer placeNo = dto.getPlaceNo();
			for (String i : dto.getImageNames()) {
				Map<String, Object> map = new HashMap<>();
				map.put("placeNo", placeNo);
				map.put("iname", i);
				session.insert("place.insertSub", map);
				rv++;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			deleteSub(dto.getPlaceNo());
		}
		return rv;
	}

	public int deleteSub(int placeNo) {
		int rv = 0;
		try {
			rv = session.delete("place.deleteSub", placeNo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public int deleteSubIn(PlaceDto dto) {
		int rv = 0;
		try {
			List<String> deleteList = dto.getImageNames();
			if (deleteList.size() > 0) {
				rv = session.delete("place.deleteSubIn", deleteList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public PlacePage list(PlacePage page) {
		page.setTotalRow(session.selectOne("place.listCount", page));
		page.setContent(session.selectList("place.list", page));
		return page;
	}

//	public int listSubCount(int placeNo) {
//		return session.selectOne("place.listSubCount", placeNo);
//	}

	public PlaceDto detail(int placeNo) {
		return session.selectOne("place.detail", placeNo);
	}
}
