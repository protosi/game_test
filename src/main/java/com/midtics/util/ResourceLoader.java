package com.midtics.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.math.R.Rsession;

import com.midtics.util.RConnector;
/**
 * 
 * @author proto_000
 * 리소스 폴더에 있는 파일을 읽기 위한 함수
 * R스크립트 파일을 java 프로젝트에서 관리하기 위해 사용
 */
public class ResourceLoader {

	private static ResourceLoader instance = null;

	public static synchronized ResourceLoader getInstance()
	{
		if(instance == null)
		{
			instance = new ResourceLoader();
		}
		return instance;
	}
	public String getResource(String fileName)
	{
		StringBuilder result = new StringBuilder("");
		
		//Get file from resources folder
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
