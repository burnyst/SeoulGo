package com.seoulmate.seoulgo.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seoulmate.seoulgo.dao.PlaceDao;
import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.page.PlacePage;

@Service
public class PlaceService {
	
	private Logger logger = LoggerFactory.getLogger(PlaceService.class);
	
	@Autowired
	PlaceDao dao;
	
	private boolean uploadFile(MultipartFile srcFile, File uploadFolder, String fileName) {
		boolean rv = false;
		File destFile = new File(uploadFolder, fileName);
		try {
			srcFile.transferTo(destFile);
			rv = true;
		} catch (IllegalStateException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return rv;
	}
	
	private boolean uploadImageFiles(MultipartFile[] srcFiles, File uploadFolder, List<String> uploadedFileNames) {
		String fileNameBase = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss_"));
		for (MultipartFile srcFile : srcFiles) {
			String fileType = srcFile.getContentType().split("/")[0];
			if (fileType.equals("image")) {
				String fileName = fileNameBase+UUID.randomUUID();
				if (uploadFile(srcFile, uploadFolder, fileName) == false) {
					deleteUploadFiles(uploadFolder, uploadedFileNames);
					return false;
				}
				uploadedFileNames.add(fileName);
			} else {
				logger.info("invalid file type. file type:"+fileType);
			}
		}
		return true;
	}

	private void deleteUploadFiles(File uploadFolder, List<String> fileNames) {
		for (String fileName : fileNames) {
			File file = new File(uploadFolder, fileName);
			if (file.exists()) {
				if (file.delete() == false) {
					logger.error(uploadFolder.getPath()+fileName+" delete error.");
				}
			}
		}
	}
	
	public boolean insert(PlaceDto dto, MultipartFile[] files, String rootPath) {
		boolean rv = false;
		File uploadFolder = new File(rootPath+"resources/", "img/place");
		if (uploadFolder.exists() == false) {
			uploadFolder.mkdirs();
		}
		dto.setImageNames(new ArrayList<>());
		List<String> uploadedFileNames = dto.getImageNames();
		if (uploadImageFiles(files, uploadFolder, uploadedFileNames)) {
			if (dao.insert(dto) == 1) {
				if (dao.insertSub(dto) == uploadedFileNames.size()) {
					rv = true;
				} else {
					deleteUploadFiles(uploadFolder, uploadedFileNames);
				}
			} else {
				deleteUploadFiles(uploadFolder, uploadedFileNames);
			}
		}
		
		return rv;
	}

	public boolean update(PlaceDto dto, MultipartFile[] files, String rootPath) {
		boolean rv = false;
		File uploadFolder = new File(rootPath+"resources/", "img/place");
		if (uploadFolder.exists() == false) {
			uploadFolder.mkdirs();
		}
		int placeNo = dto.getPlaceNo();
		PlaceDto beforeDto = dao.detail(placeNo);
		if (beforeDto != null) {
			List<String> remainImageNames = dto.getImageNames();
			if (remainImageNames == null) {
				dto.setImageNames(new ArrayList<>());
			} else {
				beforeDto.getImageNames().removeAll(remainImageNames);
				remainImageNames.clear();
			}
			dao.deleteSubIn(beforeDto);
			deleteUploadFiles(uploadFolder, beforeDto.getImageNames());
			List<String> uploadedFileNames = dto.getImageNames();
			if (uploadImageFiles(files, uploadFolder, uploadedFileNames)) {
				if (dao.update(dto) == 1) {
					if (dao.insertSub(dto) == uploadedFileNames.size()) {
						rv = true;
					} else {
						deleteUploadFiles(uploadFolder, uploadedFileNames);
					}
				} else {
					deleteUploadFiles(uploadFolder, uploadedFileNames);
				}
			}
		}
		return rv;
	}
	
	public PlacePage list(PlacePage page) {
		return dao.list(page);
	}
	
	public PlaceDto detail(int placeNo) {
		return dao.detail(placeNo);
	}

	public boolean delete(int placeNo) {
		boolean rv = false;
		if (dao.listSubCount(placeNo) == dao.deleteSub(placeNo)) {
			if (dao.delete(placeNo) > 0) {
				rv = true;
			}
		}
		return rv;
	}
}
