package com.wenge.textclassify.consumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.wenge.textclassify.service.TextClassifyService;

public class TextClassifyTest {
	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("consumer.xml");
		context.start();
		TextClassifyService textClassifyService = (TextClassifyService) context.getBean("TextClassifyService");
		ArrayList<String> textList = readtxtfile("./data/test.txt");

		Long startTime = System.currentTimeMillis();
		for(int i =0; i < textList.size();i++) {
			String text = textList.get(i);
			String resu = textClassifyService.classify(text);
			System.out.println(i + "-" + resu);
		}
		Long endTime = System.currentTimeMillis();
		float timeUse = (float)(endTime - startTime)/1000;
		System.out.println("分类时间耗损：" + timeUse + "秒");
	}
	
	public static ArrayList<String> readtxtfile(String txtFilePath) throws IOException {
		File txtFile = new File(txtFilePath);
		if (!txtFile.exists()) {
			System.out.println("文件不存在");
			return null;
		}
		ArrayList<String> textList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(txtFile));
		String line = null;
		while ((line = br.readLine()) != null) {
			textList.add(line);
		}
		br.close();
		return textList;
	}
}
