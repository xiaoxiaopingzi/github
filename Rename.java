import java.io.File; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry; 

/** * ������������ * @author jack */ 
class ReplacementChain{ 
	private Map<String,String> map = new HashMap<String, String>(); 
	public Map<String, String> getMap() { 
		return map; 
	} 
	// ����µ��滻����(�ַ����滻) 
	public ReplacementChain addRegulation(String oldStr , String newStr){ 
		this.map.put(oldStr, newStr); 
		return this; 
		} 
	} 

/** * �������� * @author Jack */ 
public class Rename { 
	/** * ���������� * 
	 * @param path *
	 *  @param replacementChain */ 
	public static void multiRename(String path,ReplacementChain replacementChain)
	{ 	
		File file = new File(path); 
		boolean isDirectory = file.isDirectory(); 
		/** ��������ļ��У��ͷ���* */ 
		if(!isDirectory){ 
			System.out.println(path + "����һ���ļ��У�"); 
			return; 
		} 
		String[] files = file.list(); 
		File f = null; 
		String filename = null; 
		String oldFileName = null; //֮ǰ������ 
		/** ѭ�����������ļ�* */ 
		for(String fileName : files){ 
			oldFileName = fileName; //ӛסԭʼ���ļ���
			Map<String, String> map = replacementChain.getMap(); 
			for (Map.Entry<String, String> entry : map.entrySet()) {   //��forѭ�����滻�ļ����ж�����������ַ���
				fileName = fileName.replace(entry.getKey(), entry.getValue()); 
			} 
			f = new File(path + "\\" + oldFileName); //�����ַ��ԭ·������һ�� 
			f.renameTo(new File(path + "\\" + fileName)); //renameTo()���������Q�ļ���
		} 
		System.out.println("��ϲ�������������ɹ���"); 
	} 
	public static void main(String[] args) {
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("11957���㡶ȫ��������ȼ����ļ�����ϵͳԭ��", "������ļ�����ϵͳԭ��");//���ļ���"����"��""����
	//	replacementChain.addRegulation(".720p.chs-eng.luckydag", "");//���ļ���"�ٴβ���"��""����
		multiRename("F:\\BaiduYunDownload\\������ļ����繤��ʦ\\����ϵͳ\\������Ƶ", replacementChain);
	} 
}