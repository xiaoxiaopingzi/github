package cn.wcmc.test;

import java.io.File; 
import java.util.HashMap; 
import java.util.Map; 
import org.junit.Test;

/** * 重命名规则类 * @author jack */ 
class ReplacementChain{ 
	private Map<String,String> map = new HashMap<String, String>(); 
	public Map<String, String> getMap() { 
		return map; 
	} 
	// 添加新的替换规则(字符串替换) 
	public ReplacementChain addRegulation(String oldStr , String newStr){ 
		this.map.put(oldStr, newStr); 
		return this; 
		}
}
	
	

/** * 重命名类 * @author Jack */ 
public class Rename { 
	/** * 批量重命名 * 
	 * @param path 文件夹路径
	 * @param replacementChain  重命名规则
	 **/ 
	public static void multiRename(String path,ReplacementChain replacementChain)
	{ 	
		File file = new File(path); 
		boolean isDirectory = file.isDirectory(); 
		/** 如果不是文件夹，就返回* */ 
		if(!isDirectory){ 
			System.out.println(path + "不是一个文件夹！"); 
			return; 
		} 
		String[] files = file.list(); 
		File f = null; 
		String oldFileName = null; //之前的名字 
		/** 循环遍历所有文件* */ 
		for(String fileName : files){ 
			oldFileName = fileName; //记住原始的文件名
			Map<String, String> map = replacementChain.getMap(); 
			for (Map.Entry<String, String> entry : map.entrySet()) {   //用for循环来替换文件名中多个不连续的字符串
				fileName = fileName.replace(entry.getKey(), entry.getValue()); 
			} 
			f = new File(path + "\\" + oldFileName); //输出地址和原路径保持一致 
			f.renameTo(new File(path + "\\" + fileName)); //renameTo()方法用于替换文件名
		} 
		System.out.println("恭喜，批量重命名成功！"); 
	} 
	
	/**
	 * 去除文件名中指定位置后的那部分的文件名
	 * @param path  文件路径
	 * @param fileNameEndIndex 需要保留的文件名
	 * @param fileNameSuffix 需要更改文件名的文件的后缀名
	 */
	public static void multiDelNameByIndex(String path,String fileNameEndIndex)
	{ 	
		File file = new File(path); 
		/** 如果不是文件夹，就返回* */ 
		if(!file.isDirectory()){ 
			System.out.println(path + "不是一个文件夹！"); 
			return; 
		} 
		String[] files = file.list(); 
		//获取需要保留的文件名最后的一个字符的位置
		int endIndex = fileNameEndIndex.length(); 
		File f = null; 
		String oldFileName = null; //之前的名字 
		/** 循环遍历所有文件* */ 
		for(String fileName : files){ //得到的文件名会带上文件的后缀名
			oldFileName = fileName; //记住原始的文件名
			int index = fileName.lastIndexOf("."); //获取文件名的后缀中"."号最后一次出现的位置
			String fileSuffix = fileName.substring(index);//获取文件名的后缀名
			fileName = fileName.substring(0, endIndex) + fileSuffix;//得到的子串需要加上文件的后缀名
			f = new File(path + "\\" + oldFileName); //输出地址和原路径保持一致 
			f.renameTo(new File(path + "\\" + fileName)); //renameTo()方法用于替换文件名
		} 
		System.out.println("恭喜，批量重命名成功！");  
	} 
	

	/**
	 * 在文件名的前面和后面添加指定的文件名
	 * @param path  文件夹路径
	 * @param addFileName_Front  需要在文件名前面添加的文件名
	 * @param addFileName_Behind  需要在文件名后面添加的文件名
	 */
	public static void multiAddName(String path,String addFileName_Front,String addFileName_Behind)
	{ 	
		File file = new File(path); 
		/** 如果不是文件夹，就返回* */ 
		if(!file.isDirectory()){ 
			System.out.println(path + "不是一个文件夹！"); 
			return; 
		} 
		String[] files = file.list(); 
		File f = null; 
		String oldFileName = null; //原本的文件名 
		String fileNameNoSuffix = null; //去除后缀的文件名 
		/** 循环遍历所有文件* */ 
		for(String fileName : files){ //得到的文件名会带上文件的后缀名
			oldFileName = fileName; //记住原始的文件名
			if(addFileName_Behind != null){
				int index = fileName.lastIndexOf("."); //获取文件名的后缀中"."号最后一次出现的位置
				String fileSuffix = fileName.substring(index);//获取文件名的后缀名
				fileNameNoSuffix = fileName.substring(0, index);//获取去除后缀名后的文件名
				fileName = addFileName_Front + fileNameNoSuffix + addFileName_Behind + fileSuffix;
			}
			else{
				fileName = addFileName_Front + fileName;
			}
			f = new File(path + "\\" + oldFileName); //输出地址和原路径保持一致 
			f.renameTo(new File(path + "\\" + fileName)); //renameTo()方法用于替换文件名
		} 
		System.out.println("恭喜，批量重命名成功！");  
	} 
	
	//测试multiRename()方法
	@Test
	public void test(){
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("韩顺平.2011版.struts视频教程.", "");//將文件中"测试"用""代替
		replacementChain.addRegulation("[BDRIP][X264_AAC][1280X720]", "");//將文件中"再次测试"用""代替
		multiRename("F:\\BaiduYunDownload\\韩顺平struts视频", replacementChain);
	}
	
	//测试multiDelNameByIndex()方法
	@Test
	public void test2(){
		multiDelNameByIndex("F:\\迅雷下载\\攻壳机动队\\攻壳机动队TV版第一季与第二季", "攻壳机动队TV版第二季[01]");
	}
	
	//测试multiAddName()方法
	@Test
	public void test3(){
		multiAddName("G:\\迅雷下载\\鬼吹灯之精绝古城", "鬼吹灯之精绝古城[","]");
	}
}
