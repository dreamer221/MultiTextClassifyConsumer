package com.wenge.textclassify.consumer;

import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.wenge.textclassify.service.TextClassifyService;

public class TextClassifyConsumer {

	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("consumer.xml");
		context.start();
		
		String textContent = "汉十高铁汉江特大桥主桥建设进入“百米冲刺” 7月14日在湖北襄阳拍摄的汉十高铁汉江特大桥施工现场（无人机拍摄）。日前，汉十高铁汉江特大桥主桥连续刚构施工已完成220米，进入到最后的“百米冲刺”阶段，跨度300米的主桥预计将于今年10月合龙。    汉江特大桥是汉十高铁的关键控制性工程，大桥计划于2019年年底建设完成。汉（武汉）十（十堰）高铁于2015年底开工，全长391.5公里，设计时速350公里";
		
		TextClassifyService firstService = (TextClassifyService) context.getBean("TextClassifyService");
		String firstClassifyResult = firstService.classify(textContent);
		System.out.println("分类器分类结果：" + firstClassifyResult);
	}
}
