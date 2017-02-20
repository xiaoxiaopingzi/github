import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
class ChineseCharEncodingList
{  
	private static final int MIN_INDEX = 19968;  
	private static final int MAX_INDEX = 40869;  
	private static final String CR = "\r\n";  
	private static final String TAB = "\t";  
	public void execute(String fileName) throws IOException
	{  
	   File f = new File(fileName);  
	   FileWriter fw = new FileWriter(f);  
	   fw.write("�ַ�"+TAB+"Unicodeʮ����"+TAB+"Unicodeʮ������"+TAB+TAB+"GBKʮ����"+TAB+"GBKʮ������"+CR);  
	   fw.write("=================================================================================="+CR);  
	   int GBKCode;  
	   for(int i=MIN_INDEX;i<=MAX_INDEX;i++)
	   {  
			GBKCode = getGBKCode(i);  
			fw.write((char)i+TAB+i+TAB+TAB+Integer.toHexString(i)+TAB+TAB+TAB+GBKCode+TAB+TAB+Integer.toHexString(GBKCode)+CR);  
	   }  
	   fw.flush();  
	   System.out.println("Done!");  
	}  
	
	private int getGBKCode(int unicodeCode) throws UnsupportedEncodingException
	{  
	   char c = (char) unicodeCode;  
	   byte[] bytes = (c+"").getBytes("GBK");  
	   return ((bytes[0]&255)<<8) + (bytes[1]&255);  
	} 
}
public class ChineseCharEncoding
{
	public static void main(String[] args) throws Exception
	{  
		new ChineseCharEncodingList().execute("e:\\���ֱ���һ����.txt");  
	}  
} 