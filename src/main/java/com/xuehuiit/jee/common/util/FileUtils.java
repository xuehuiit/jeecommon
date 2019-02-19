package com.xuehuiit.jee.common.util;

//
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import com.xuehuiit.jee.common.util.xml.CommentFilter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

/**
 * @author wing.feng
 */
public class FileUtils {
	
	/** DOCUMENT ME! */
	//private static Log log = LogFactory.getLog(FileUtils.class);
	private static final Logger log = Logger.getLogger(NumberUtil.class);


	private static final String IMAGETYPE = "gif,jpg,jpeg,png,bmp";

	/**
	 * 用于对页面的代码进行处理，例如对页面中的框架进行代码抽取并组合等
	 * 
	 * @param content
	 * @return
	 */
	public static String contentchange(String content) {
		//
		String str_return = content;
		return str_return;

	}

	/**
	 * 
	 * @param filename
	 *            the full file name.
	 * @param fullPath
	 *            the aim file path.
	 * @return the operator result.
	 */
	public static boolean copy(String filename, String fullPath) {

		File file = new File(filename);
		File fileDir = new File(fullPath);
		log.info("start copy file....");
		log.info("resource file name:" + file.getName());
		log.info("aim path:" + fullPath);

		String fileName = file.getName();

		int pos = fileName.lastIndexOf(File.separator);
		if (pos > 0) {
			fileName = fileName.substring(pos + 1, fileName.length());
		}
		fileDir.mkdirs();
		String lastPath = fullPath + File.separator + fileName;
		File objFile = new File(lastPath);
		if (objFile.exists() && !objFile.delete()) { // 如果存在则删除
			log.info("delete file fail");
			return false;
		}
		// 开始拷贝
		try {
			objFile.createNewFile();
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(objFile);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
		} catch (IOException ie) {
			ie.printStackTrace();
			log.error("", ie);
			return false;
		}
		return true;
	}
	
	/**
	 * 复制文件并重命名
	 * @param filename
	 *            the full file name.
	 * @param fullPath
	 *            the aim file path.
	 * @return the operator result.
	 */
	public static boolean copyrename(String filename, String fullPath,String rename) {

		File file = new File(filename);
		File fileDir = new File(fullPath);
		log.info("start copy file....");
		log.info("resource file name:" + file.getName());
		log.info("aim path:" + fullPath);

		String fileName = file.getName();

		int pos = fileName.lastIndexOf(File.separator);
		if (pos > 0) {
			fileName = fileName.substring(pos + 1, fileName.length());
		}
		fileDir.mkdirs();
		String lastPath = fullPath + File.separator + rename;
		File objFile = new File(lastPath);
		if (objFile.exists() && !objFile.delete()) { // 如果存在则删除
			log.info("delete file fail");
			return false;
		}
		// 开始拷贝
		try {
			objFile.createNewFile();
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(objFile);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
		} catch (IOException ie) {
			ie.printStackTrace();
			log.error("", ie);
			return false;
		}
		return true;
	}
	/**
	 * 
	 * 将文件移动到相应的目录
	 * 
	 * @param moveFileName   需要移动的文件
	 * @param moveDir		  文件移动的目录
	 */
	public static void moveFile(String moveFileName , String moveDir){
		
		 if( copy(moveFileName,moveDir) ){
			 File file = new File(moveFileName);
			 file.delete();
		 }else{
			 
			 log.error("copy file error!");
		 }
			 
		
		
	}
	
	/**
	 * copy 文件夹 
	 * 
	 * @param oldPath  被拷贝的文件夹 
	 * @param newPath  新位置 
	 * @param clear    过滤关键字  考虑中
	 *  
	 */
	public static void copyFolder(String oldPath, String newPath  , String clear) {      
	     
		
	    try {      
	    	
	        (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹      
	        
	        File a = new File(oldPath);      
	        String[] file = a.list();      
	        File temp = null;      
	        
	        for (int i = 0; i < file.length; i++) {      
	        	
	            if (oldPath.endsWith(File.separator)) {
	            	
	                temp = new File(oldPath + file[i]);
	                
	            } else {      
	                
	            	temp = new File(oldPath + File.separator + file[i]);
	            	
	            }
	            
	            if (temp.isFile()) {      
	                
	            	FileInputStream input = new FileInputStream(temp);      
	                FileOutputStream output = new FileOutputStream(newPath + File.separatorChar + (temp.getName()).toString());      
	                byte[] b = new byte[1024 * 5];      
	                int len;
	                
	                while ((len = input.read(b)) != -1) {      
	                    output.write(b, 0, len);      
	                }
	                
	                output.flush();      
	                output.close();      
	                input.close();      
	            }      
	            
	            
	            
	            if ( temp.isDirectory() ) {// 如果是子文件夹\
	            	
	            	String name = temp.getName();
	            	//System.out.println("目录名称 ：  "+name);
	            	if( !name.equals(clear) )
	                  copyFolder(oldPath + File.separatorChar + file[i], newPath + File.separatorChar + file[i] , clear);      
	            }      
	        }      
	    } catch (Exception e) {      
	        //System.out.println("复制整个文件夹内容操作出错");      
	        e.printStackTrace();      
	    }      
	}    

	
	
	/**
	 * 将相应目录项目的子目录拷贝到目标目录中
	 * 
	 * @param aimDir
	 *            the aim Directiory
	 * @param sourceDir
	 *            the source directiry
	 * @param childdir
	 *            原文件夹下面需要拷贝到目的文件夹的子文件名字，如果是多个 文件夹，他们之间的名字用“,”分隔。比如：“a,b,c,d”
	 * @throws IOException
	 */
	public static void copyDirectiory(String aimDir, String sourceDir,
			String childdir) throws IOException {

		String fileName;

		(new File(aimDir)).mkdirs();
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileInputStream input = new FileInputStream(file[i]);
				FileOutputStream output = new FileOutputStream(aimDir + "/"
						+ file[i].getName());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (file[i].isDirectory()) {
				fileName = file[i].getName();
				if (childdir.indexOf(fileName) < 0)
					continue;
				copyDirectiory(aimDir + "/" + file[i].getName(), sourceDir
						+ "/" + file[i].getName(), childdir);
			}

		}

	}
	
	/**
	 * 
	 * 创建目录
	 * 
	 * @param dir
	 * 
	 */
	public static void createDir(String data_dir){
		
		File file =new File(data_dir);  
		if  (!file .exists()  && !file .isDirectory())      {       
		    System.out.println("//不存在");  
		    file .mkdir();    
		} else   {  
		    System.out.println("//目录存在");  
		}  
		
		
	}

	/**
	 * Create multilevel directory
	 * 
	 * @param aParentDir
	 *            String
	 * @param aSubDir
	 *            Start in "/"
	 * @return boolean the operator reulst
	 */
	public static boolean createDirs(String aParentDir, String aSubDir) {
		File aFile = new File(aParentDir);
		if (aFile.exists()) {
			File aSubFile = new File(aParentDir + aSubDir);
			if (!aSubFile.exists()) {
				return aSubFile.mkdirs();
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param url
	 * @param file
	 */
	public static String downLoadFile(final String url, final UploadFile file) {
		URL uri = null;
		String message = null;
		try {
			uri = new URL(url);
			file.setInputStream(uri.openStream());
			writeFile(file);
			message = "success";
		} catch (MalformedURLException e) {
			log.error("downLoadFile error: " + e);
			message = "failure";
		} catch (IOException e) {
			log.error("downLoadFile error: " + e);
			message = "failure";
		}finally {
			return message;
		}
	}

	/**
	 * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
	 * 
	 * @param baseDir
	 *            java.lang.String 根目录
	 * @param realFileName
	 *            java.io.File 实际的文件名
	 * @return 相对文件名
	 */
	public static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else {
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param baseDir
	 * @param absFileName
	 * @return
	 */
	public static File getRealFileName(String baseDir, String absFileName) {

		int count;
		String retSer = null;
		StringTokenizer stringToken = new StringTokenizer(absFileName, "/");
		count = stringToken.countTokens();
		String[] dirs = new String[count];

		for (int i = 0; i < count; i++) {
			retSer = stringToken.nextToken();
			dirs[i] = retSer;
		}

		// System.out.println(dirs.length);
		File ret = new File(baseDir);
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				ret = new File(ret, dirs[i]);
			}
		}
		if (!ret.exists()) {
			ret.mkdirs();
		}
		ret = new File(ret, dirs[dirs.length - 1]);
		return ret;
	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static String[] getSubFileNames(String baseDir) {
		
		File file = new File(baseDir);
		File subfile;
		//return getSubFiles(file);
		List files = getSubFiles(file);
		String[] fileNames = new String[files.size()];
		for( int i = 0 ; i < files.size() ; i++)
		{
			subfile = (File)files.get(i);
			fileNames[i] = subfile.getName();
		}
			
		return fileNames;
		
	}

    
	/**
	 * 取得指定目录下的所有文件列表，垠{I8供!垠管fa的4软[包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List<String> getSubFileNamesLoup(File baseDir) {
		List<String> ret = new ArrayList<String>();
		// File base=new File(baseDir);
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i].getPath());
			}
			if (tmp[i].isDirectory() && !tmp[i].isHidden() ) {
				ret.addAll(getSubFileNamesLoup(tmp[i]));
			}
		}
		return ret;
	}
	
	
	/**
	 * 取得指定目录下的所有文件列表，包含子文件夹中的目录
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List<String> getSubFileNamesLoup(String baseDir) {
		File file = new File(baseDir);
		return getSubFileNamesLoup(file);
	}
	
	
	/**
	 * 取得指定目录下的所有文件列表，垠{I8供!垠管fa的4软[包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List getSubFilesLoup(File baseDir) {
		List<File> ret = new ArrayList<File>();
		// File base=new File(baseDir);
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
				
			if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}
	
	
	/**
	 * 取得指定目录下的所有文件列表，垠{I8供!垠管fa的4软[包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List getSubFilesLoup(String baseDir) {
		File file = new File(baseDir);
		return getSubFilesLoup(file);
	}
	
	
	/**
	 * 取得指定目录下的所有文件列表，垠{I8供!垠管fa的4软[包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List getSubFiles(File baseDir) {
		List<File> ret = new ArrayList<File>();
		// File base=new File(baseDir);
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
			/*if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}*/
		}
		return ret;
	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List<File> getSubDirs(String baseDir) {
		File file = new File(baseDir);
		return getSubDirs(file);
	}

	
	/**
	 * 取得指定目录下的所有文件列表，垠{I8供!垠管fa的4软[包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List<File> getSubDirs(File baseDir) {
		List<File> ret = new ArrayList<File>();
		// File base=new File(baseDir);
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			/*if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}*/
			if (tmp[i].isDirectory()) {
				ret.add(tmp[i]);
			}
		}
		return ret;
	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	public static List getSubFiles(String baseDir) {
		File file = new File(baseDir);
		return getSubFiles(file);
	}
	
	
	/**
	 * DOCUMENT ME!
	 * 
	 * @param dir
	 *            DOCUMENT ME!
	 */
	public static void removeDir(String dir) {
		File f = new File(dir);
		File[] tmps = f.listFiles();

		if (f.exists()) {
			for (int i = tmps.length; i-- > 0;) {
				tmps[i].delete();
			}

			f.delete();
		}
	}



	
	/**
	 * 抓取网页地址并生成网页文件
	 * 
	 * @param web_url
	 *            抓取页面的地址
	 * @param coding
	 *            抓取页面的编码
	 * @param file
	 *            生成后的文件名 （实际文件地址）
	 * @return
	 */
	public static String snatch(String web_url, String coding, String file) {

		String sCurrentLine = "";
		String return_msg = "";

		InputStream l_urlStream;

		FileWriter fw;
		try {
			String sTotalString = "";
			URL l_url = new URL(web_url);
			java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url
					.openConnection();
			l_connection.connect();
			l_urlStream = l_connection.getInputStream();
			java.io.InputStreamReader read = new InputStreamReader(l_urlStream,
					coding);
			java.io.BufferedReader l_reader = new java.io.BufferedReader(read);

			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}

			sTotalString = contentchange(sTotalString);
			String path = file;

			File fileName = new File(path);
			if (fileName.exists()) {
				// 删除File.txt档
				// fileName.delete();
			} else {
				// 在目前的目录下建立一个名为File.txt的文字档
				fileName.createNewFile();
				fileName.createNewFile();
			}

			fw = new FileWriter(path);
			// 将字串写入文件
			fw.write(sTotalString);
			fw.close();
			return_msg = "操作完毕，页面已生成！";
			return return_msg;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return "操作失败，页面未生成！";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "操作失败，页面未生成！";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "操作失败，页面未生成！";
		}
	}
	
	
	/**
	 * 解压zip包
	 * @param filePath
	 * @param zipFile
	 */
	public static void unzip(String filePath, String zipFile) {
		int BUFFER = 2048;
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;
		ZipEntry entry = null;
		ZipFile zipfile = null;
		Enumeration enu = null;
		
		try {
			zipfile = new ZipFile(zipFile);
			enu = zipfile.entries();
			while (enu.hasMoreElements()) {
				entry = (ZipEntry) enu.nextElement();
				// logger.debug("extracting file name is" + entry.getName());
				if (entry.isDirectory()) {
					new File(filePath + entry.getName()).mkdirs();
					continue;
				}
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];

				FileOutputStream fos = new FileOutputStream(filePath
						+ entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);

				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		} finally {
			try {
				if (dest != null) {
					dest.flush();
					dest.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 解压zip包
	 * @param zin
	 * @param s
	 * @throws IOException
	 */
	public static void unzip(ZipInputStream zin, String s) throws IOException {
		System.out.println("unzipping " + s);
		FileOutputStream out = new FileOutputStream(s);
		byte[] b = new byte[512];
		int len = 0;
		while ((len = zin.read(b)) != -1) {
			out.write(b, 0, len);
		}
		out.close();
	}
	

	/**
	 * 
	 * @param fileName
	 * @param fileContant
	 * @throws IOException 
	 */
	public static void witerFile(String fileName , StringBuffer fileContant) throws IOException{
		
		File file = new File(fileName);
		
		org.apache.commons.io.FileUtils.writeStringToFile(file, fileContant.toString(), "UTF-8");
		
		
	}
	
	/**
	 * 
	 * @param fileName
	 * @param fileContant
	 * @throws IOException 
	 */
	public static void witerFile(String fileName , String fileContant) throws IOException{
		
		File file = new File(fileName);
		
		org.apache.commons.io.FileUtils.writeStringToFile(file, fileContant, "UTF-8");
		
		
	}

	
	/**
	 * DOCUMENT ME!
	 *
	 */
	public static void writeFile(UploadFile file) {
		log.info("write file");

		OutputStream os = null;

		if (file.getDirect() != null) {
			File f = new File(file.getDirect());

			if (!f.exists()) {
				f.mkdirs();   
			}
		}

		try {
			log.info("write file" + file.getDirect() + file.getFileName());
			os = new FileOutputStream(file.getDirect() + file.getFileName());

			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			while ((bytesRead = file.getInputStream().read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			log.error("write file error: file not found \n" + e);
		} catch (IOException e) {
			log.error("write file error: " + e);
		}
	}

	/**
	 * 
	 * @param inputFileName
	 * @throws Exception
	 */
	public static void zip(String inputFileName) throws Exception {
		String zipFileName = inputFileName;// 打包后文件名字
		System.out.println(zipFileName);
		zip(zipFileName, inputFileName);

	}
	
	
	/**
	 * 
	 * @param zipFileName
	 * @param outfileName
	 * @throws Exception
	 */
	public static void zip(String zipFileName, String outfileName)
			throws Exception {

		File inputFile = new File(outfileName);

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		// System.out.println("zip done");
		out.close();
	}
	
	/**
	 * 
	 * @param out
	 * @param f
	 * @param base
	 * @throws Exception
	 */
	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		/*if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			// System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}*/
	}
	

	
	/**
	 * 复制文件
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
   public static void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	
   /**
    * 将一个目录下面的所有文件和目录复制到其他的目录
    * @param srcDir
    * @param dstDir
    * @throws IOException
    */
   public static void copyDirectory(File srcDir, File dstDir) throws IOException {
       if (srcDir.isDirectory()) {
           if (!dstDir.exists()) {
               dstDir.mkdir();
           }
   
           String[] children = srcDir.list();
           for (int i=0; i<children.length; i++) {
               copyDirectory(new File(srcDir, children[i]),
                                    new File(dstDir, children[i]));
           }
       } else {
           // This method is implemented in e1071 Copying a File
    	   copy(srcDir, dstDir);
       }
   }

   
   public static String readFile4Classpath(String path){
	   
	   return readFile4Classpath(path,null);
   }
   
   
   
   public static String readFile4Classpath(String path,String charSetName){
	   
	  
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		
		 ClassLoader  loader  =  FileUtils.class.getClassLoader();  
		 URL  url  =  loader.getResource(path);  
		
		try {
		
			
          if( null == charSetName )
              br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url.getFile())),"UTF-8"));
          else
       	       br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url.getFile())),charSetName));

          String s;

           while ((s = br.readLine() )!=null) {

          	    sb.append(s);

            }


			//return fr.toString();
			
		} catch (FileNotFoundException fnfEx) {
			
			log.error("读取文件时发生错误",fnfEx);
			
		} catch (IOException ioEx) {
			log.error("读取文件时发生错误",ioEx);
		}finally{
			
			
				try {
					
					if( null!=br )
						br.close();
			
				} catch (IOException ioEx) {
					log.error("文件关闭时发生错误",ioEx);
				}
			
		}
		
		return sb.toString();
	   
	   
   }
	
   
   /**
    * 读取一个文件，返回改文件的字符串，并可以设定程序的字符集
    * @param file  文件名
    * @param charSetName  字符集 （utf-8,gb2321）
    * @return
    */
   public static List<String> readFile2List(String file,String charSetName){
		
		FileReader fr = null;
		 List<String>  sb = new ArrayList<String>();
		BufferedReader br = null;
		try {
			
			fr = new FileReader(file);
			
           if( null == charSetName )
               br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),"utf-8"));
           else
        	   br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),charSetName));

           String s;

            while ((s = br.readLine() )!=null) {

            	sb.add(s);

             }


			//return fr.toString();
			
		} catch (FileNotFoundException fnfEx) {
			
			log.error("读取文件时发生错误",fnfEx);
			
		} catch (IOException ioEx) {
			log.error("读取文件时发生错误",ioEx);
		}finally{
			
			
				try {
					
					if( null!=br )
						br.close();
					if( null!=fr )
					    fr.close();
			
				} catch (IOException ioEx) {
					log.error("文件关闭时发生错误",ioEx);
				}
			
		}
		
		return sb;
		
	}
   
   /**
    * 读取一个文件，返回改文件的字符串，并可以设定程序的字符集
    * @param file  文件名
    * @param charSetName  字符集 （utf-8,gb2321）
    * @return
    */
   public static Map<String, String>  readFile2Map(String file,String charSetName){
		
		FileReader fr = null;
		Map<String, String>  sb = new HashMap<String, String>();
		BufferedReader br = null;
		try {
			
			fr = new FileReader(file);
			
           if( null == charSetName )
               br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),"utf-8"));
           else
        	   br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),charSetName));

           String s;

            while ((s = br.readLine() )!=null) {

            	sb.put(s,"1");

             }


			//return fr.toString();
			
		} catch (FileNotFoundException fnfEx) {
			
			log.error("读取文件时发生错误",fnfEx);
			
		} catch (IOException ioEx) {
			log.error("读取文件时发生错误",ioEx);
		}finally{
			
			
				try {
					
					if( null!=br )
						br.close();
					if( null!=fr )
					    fr.close();
			
				} catch (IOException ioEx) {
					log.error("文件关闭时发生错误",ioEx);
				}
			
		}
		
		return sb;
		
	}
   
   
   
   /**
    * 读取一个文件，返回改文件的字符串，并可以设定程序的字符集
    * @param file  文件名
    * @param charSetName  字符集 （utf-8,gb2321）
    * @return
    */
   public static String readFile2String(String file,String charSetName){
		
		FileReader fr = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			
			fr = new FileReader(file);
			
           if( null == charSetName )
               br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),"utf-8"));
           else
        	   br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),charSetName));

           String s;

            while ((s = br.readLine() )!=null) {

           	    sb.append(s+"\n\r");

             }


			//return fr.toString();
			
		} catch (FileNotFoundException fnfEx) {
			
			log.error("读取文件时发生错误",fnfEx);
			
		} catch (IOException ioEx) {
			
			log.error("读取文件时发生错误",ioEx);
			
		}finally{
			
			
				try {
					
					if( null!=br ){
						br.close();
						br=null;
					}
					if( null!=fr ){
					    fr.close();
					    fr = null;
					}
			
				} catch (IOException ioEx) {
					log.error("文件关闭时发生错误",ioEx);
				}
			
		}
		
		return sb.toString();
		
	}
	
   
   /**
    * 读取一个文件，返回改文件的字符串，并可以设定程序的字符集
    * @param file  文件名
    * @param charSetName  字符集 （utf-8,gb2321）
    * @return
    */
   public static String readFile2String4selfslty(String file,String charSetName){
		
		FileReader fr = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			
			fr = new FileReader(file);
			
			if( null == charSetName )
        	   br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),"utf-8"));
            else
        	   br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),charSetName));

            String s;

            while ((s = br.readLine() )!=null) {

           	    sb.append(s+"\n");

             }


			//return fr.toString();
			
		} catch (FileNotFoundException fnfEx) {
			
			log.error("读取文件时发生错误",fnfEx);
			
		} catch (IOException ioEx) {
			log.error("读取文件时发生错误",ioEx);
		}finally{
			
			
				try {
					
					if( null!=br )
						br.close();
					if( null!=fr )
					    fr.close();
			
				} catch (IOException ioEx) {
					log.error("文件关闭时发生错误",ioEx);
				}
			
		}
		
		return sb.toString();
		
	}
    
   /**
    * 读取一个文件，返回改文件的字符串
    * @param file
    * @return
    */
   public static String readFile2String(String file){
		
		return readFile2String(file,null);
		
	}
   
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// CompressBook zip = new CompressBook();
		try {
			
			

			//copyFolder("F:/wingfeng/project/appfactory/book/client/book/book","F:/temp/ttt/j2meddd",".svn");

			
			
			//downloadFileHttpclient("http://b.nearmobile.cn/appfactory/up/other/152/320X480.png", "f:/tttt.png");
			
			
			/*copyFolder("G:/temp/android/res","F:/temp/ttt/res","");
			
			
			String dd = ">                                        \t                    \t   <"*/;
			
			
			//System.out.println(clearXML(dd));
			
			/*String ba = downloadFile2Base64("http://img06.taobaocdn.com/bao/uploaded/i6/T1Dt0tXjNCXXb1upjX_40x40.jpg");
			
			ImageUtil.convertBase64image( ba , "d:/test1111.jpg");
			
			System.out.println( ba );*/
			
			
			//zip("D:/temp/checkform.zip", "D:/temp/checkform");
			 //unzip("D:/addir/producttype1/product5/ad172/","D:/addir/producttype1/product5/ad172/1.zip");
			//copyDirectiory("E:\\taoxin\\website\\doimf\\WebRoot\\addir\\producttype1\\product51temp\\adplan\\", "E:\\taoxin\\website\\doimf\\WebRoot\\addir\\producttype1\\product51\\","ad402");
			
			//String[] filenames = getSubFileNames("E:/wingfeng/website/are/are\config\background\spring");
			
			/*StringBuffer cp = new StringBuffer();
			List files = getSubFiles("E:/wingfeng/website/doimf/doimf/bin/background/lib");
			for(int i = 0 ; i<files.size() ; i++ ){
				
				File file = (File)files.get(i);
				if (file.getName().indexOf(".svn-base")>-1)
					continue;
				cp.append("$cpdir/");
				cp.append(file.getName());
				cp.append(":");
			}
			
			System.out.println(cp.toString());*/
			
			
			/*zip("E:/temp/ddaf/spring/appContext.zip","E:/temp/ddaf/spring");*/
			
			/*FileForm fileForm = new FileForm();
			
			fileForm.setRemoteDir("/var/www/html/are/1");
			fileForm.setRemoteServer("192.168.0.40");
			fileForm.setRemoteUser("root");
			fileForm.setRemotePasswd("linkwise");
			
			String[] files = {"E:/temp/ad/5/tu2.htm","E:/temp/ad/5/tu.htm"}; 
			
			fileForm.setFileNames(files);
			//
			sendFileToRemoteServer4SSH(fileForm);
			sendFileAndCreDirToRemoteServer4SSH("chmod -R 777 /var/www/html/are/1",fileForm);
			*/
			//moveFile("C://TDDOWNLOAD//hibernate_reference.pdf","E:/wingfeng/java/opensource/hibernate");
			
			/*FtpFileForm ftpFileForm = new FtpFileForm();
			
			ftpFileForm.setFileName("A_FETIONOpenCloseUser_20071110_000001.Z");
			ftpFileForm.setRemoteDir("");
			ftpFileForm.setRemotePasswd("Hgvvbq46");
			ftpFileForm.setRemoteServer("221.130.46.130");
			ftpFileForm.setRemoteUser("IDP_ZJ");
			ftpFileForm.setLocalDir("d://");
			*/
			//File file = getFile4ApacleCommonNet(ftpFileForm);
			
			unzip("d://","F:/wingfeng/project/nearmobile/jeestudy/source/epub/2512462827788.epub");
			
			/*List<String> filenames = getSubFileNamesLoup("E:/wingfeng/project/nearmobile/taobao2/server/taobao2_server/WebRoot/tpl");
			
			for (String string : filenames) {
				System.out.println(string);
			}*/
			
			
			
		} catch (Exception ex) {
			// zip.zip("");
			ex.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param xml
	 * @return
	 */
	public static String clearXML(String xml){
		
		if( null == xml || xml.length() == 0 )
			return null;
		
		String  out = CommentFilter.delComments(xml);
		//xml = xml.replaceAll(regex, replacement);
/*		out = out.replaceAll("//t", "");
		out = xml.replaceAll(">//s+<", "><");
		out = out.replaceAll(">//s+//t+<", "><");
		out = out.replaceAll(">//t+<", "><");
		out = out.replaceAll(">//t+//s+<", "><");*/
		
		out = out.replaceAll("//t", "");
		out = out.replaceAll(">//s+<", "><");
		out = out.replaceAll(">//s+#", ">#");
		
		//String out = xml.replaceAll(">//s+<", "><");
		//System.out.println(out);
		
		
		
		return out;
		
		
		
	}
	
	
	
	
	
	  public static int downloadFileHttpclient(String urlString, String filename) throws Exception {
		  
		  HttpClient client = new HttpClient();  
		  //client.setTimeout(newTimeoutInMilliseconds)
		  client.getHttpConnectionManager().getParams().setConnectionTimeout(500000);
		  GetMethod get = new GetMethod();  
		  get.setURI(new URI(urlString,false,"GB2312"));
		  try {
			client.executeMethod(get);
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.info("下载时出错:"+e.getMessage());
		}  
		  File storeFile = new File(filename);  
		  FileOutputStream output = new FileOutputStream(storeFile);  
		  //得到网络资源的字节数组,并写入文件  
		  
		 // System.out.println("code:"+ get.getStatusCode());
		  if(get.getStatusCode()==200)//下载资源成功
		  {
			  InputStream resStream = get.getResponseBodyAsStream();
			 
			  int b;
			  while((b=resStream.read())!=-1){ 
				//System.out.write(b); 
				//System.out.print((char)i);
				output.write(b);
			  } 
			  output.close(); 
			  return  get.getStatusCode();
		  }else//下载资源失败
		  {
			  output.close(); 
			  return  get.getStatusCode();
		  }
		  	  
		  //output.write(get.getResponseBodyAsStream());  
		
		     
		}   
	  
	  
	/**
	   * 下载文件到本地
	   *
	   * @param urlString
	   *          被下载的文件地址
	   * @param filename
	   *          本地文件名
	   * @throws Exception
	   *           各种异常
	   */
	public static void downloadFile(String urlString, String filename) throws Exception {
		
		log.info("url"+urlString +"  localpath:"+filename);
		
	    // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    con.setConnectTimeout(30000);
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[10240];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    OutputStream os = new FileOutputStream(filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	    
	    
	}   


	
	
	/**
	 * 是否图片
	 * @return
	 */
	private static Boolean isImage(String type){
		
		boolean flag = false;
		
		type = type.toLowerCase();
		
		if(IMAGETYPE.indexOf(type) != -1){
			return true;
		}
		
		
		return flag;
	}
	
	/**   
     * 删除目录（文件夹）以及目录下的文件   
     * @param   dir 被删除目录的文件路径   
     * @return  目录删除成功返回true,否则返回false   
     */   
    public static boolean deleteDirectory(String dir){    
        //如果dir不以文件分隔符结尾，自动添加文件分隔符    
        if(!dir.endsWith(File.separator)){    
            dir = dir+File.separator;    
        }    
        File dirFile = new File(dir);    
        //如果dir对应的文件不存在，或者不是一个目录，则退出    
        if(!dirFile.exists() || !dirFile.isDirectory()){    
        //	log.info("删除目录失败"+dir+"目录不存在！");    
            return false;    
        }    
        boolean flag = true;    
        //删除文件夹下的所有文件(包括子目录)    
        File[] files = dirFile.listFiles();    
        for(int i=0;i<files.length;i++){    
            //删除子文件    
            if(files[i].isFile()){    
                flag = deleteFile(files[i].getAbsolutePath());    
                if(!flag){    
                    break;    
                }    
            }    
            //删除子目录    
            else{    
                flag = deleteDirectory(files[i].getAbsolutePath());    
                if(!flag){    
                    break;    
                }    
            }    
        }    
            
        if(!flag){    
        	log.info("删除目录失败");    
            return false;    
        }    
            
        //删除当前目录    
        if(dirFile.delete()){    
        	//log.info("删除目录"+dir+"成功！");    
            return true;    
        }else{    
        	log.info("删除目录"+dir+"失败！");    
            return false;    
        }    
    }
    
    

	public static void del(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录

			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					delFile[j].delete();
				}
			}
		}
	}

    
    /**   
     * 删除单个文件   
     * @param   fileName    被删除文件的文件名   
     * @return 单个文件删除成功返回true,否则返回false   
     */   
    public static boolean deleteFile(String fileName){    
        File file = new File(fileName);    
        if(file.isFile() && file.exists()){    
            file.delete();    
           // log.info("删除单个文件"+fileName+"成功！");    
            return true;    
        }else{    
        	log.info("删除单个文件"+fileName+"失败！");    
            return false;    
        }    
    }    

	
	

}
