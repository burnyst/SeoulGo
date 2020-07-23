package com.seoulmate.seoulgo.util;

import java.io.File;
// * 	이 클래스는 파일 업로드를 할때 생기는 문제점을 해결하기 위한 각종 함수를 만들어 놓는 클래스이다.
public class FileUtil {
	//	//	업로드할 폴더가 혹시 없으면?
	public static void makeFolder(String path) {
		//	방법
		//	그 경로를 이용해서 File 클래스를 만든다.		File	file = new File(path);
		File	file = new File(path);
		file.mkdirs();
	}
	
	//	2.	혹시 같은 이름의 파일이 존재하면 현재 파일의 이름을 바꿔줌으로 덮어쓰기가 되지 않도록 방지하는 함수
	public static String renameTo(String path, String name) {
		//	어떤 폴더(path)에 무슨이름(name)으로 저장할 예정인데... 이름이 같으면 바꿔 달라.
		
		//	이름을 바꾸기 전에 혹시 현재 저장할 폴더가 없으면 만들고 시작하자.
		makeFolder(path);
		
		//	만약 같은 이름의 파일이 존재하면 현재 파일이름 다음에 _번호	를 붙여서 이름을 바꾸도록 한다.
		String	tempName = name;	//	현재 이름을 기억
		int		count = 0;			//	옆에 붙일 번호를 기억할 변수
		
		//	현재 저장할 이름을 File 클래스로 바꿔놓고
		File	file = new File(path, tempName);
		
		//	이런 파일 존재하니?
		while(file.exists()) {
			//	파일의 이름과 확장자를 분리
			int	index = name.lastIndexOf(".");
			String	fileN = name.substring(0, index);		//	sam
			String	extN = name.substring(index + 1);		//	txt
			
			//	붙일 번호를 하나 증가한다.
			count = count + 1;
			
			//	파일 이름 뒤에 이 번호를 강제로 붙인다.
			fileN = fileN + "_" + count;					//	sam_1
			
			//	이 결과에 확장자까지 붙여서 완전한 파일 이름을 다시 만든다.
			tempName = fileN + "." + extN;					//	sam_1.txt
			
			//	이름이 또 있을 수 있으므로 다시검사
			file = new File(path, tempName);
		}
		return tempName;
	}
}









