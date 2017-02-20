import java.io.File; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry; 

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
	 * @param path *
	 *  @param replacementChain */ 
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
		String filename = null; 
		String oldFileName = null; //之前的名字 
		/** 循环遍历所有文件* */ 
		for(String fileName : files){ 
			oldFileName = fileName; //記住原始的文件名
			Map<String, String> map = replacementChain.getMap(); 
			for (Map.Entry<String, String> entry : map.entrySet()) {   //用for循环来替换文件名中多个不连续的字符串
				fileName = fileName.replace(entry.getKey(), entry.getValue()); 
			} 
			f = new File(path + "\\" + oldFileName); //输出地址和原路径保持一致 
			f.renameTo(new File(path + "\\" + fileName)); //renameTo()方法用於替換文件名
		} 
		System.out.println("恭喜，批量重命名成功！"); 
	} 
	public static void main(String[] args) {
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("11957杨毅《全国计算机等级》四级操作系统原理", "计算机四级操作系统原理");//將文件中"测试"用""代替
	//	replacementChain.addRegulation(".720p.chs-eng.luckydag", "");//將文件中"再次测试"用""代替
		multiRename("F:\\BaiduYunDownload\\计算机四级网络工程师\\操作系统\\讲解视频", replacementChain);
	} 
}