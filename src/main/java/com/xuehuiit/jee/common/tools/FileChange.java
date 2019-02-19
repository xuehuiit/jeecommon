/**
 * Copyright (c) linkwise 2007-2009 corporation.  
 * All rights reserved
 */
package com.xuehuiit.jee.common.tools;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

import org.apache.log4j.Logger;

import com.xuehuiit.jee.common.util.DateUtil;
import com.xuehuiit.jee.common.util.FileUtils;

/**
 * Deploy the project, this class will help deploy project, it can find the diffiect between source dir
 * and target dir.
 * @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
public class FileChange {

	private static Logger logger = Logger.getLogger(FileChange.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String sourceFileDir=null;
		String targetFileDir=null;
		String outputFileDir=null;
		String projectName=null;
		String arg;
		
		try {
			if( null != args && args.length > 0 )
			{
				
				for( int i = 0 ; i < args.length ; i++ )
				{
					arg = args[i];
					if ( "-sourceDir".equals(arg)){
						sourceFileDir = args[i+1];
						System.out.println("sourceDir  ----"+  sourceFileDir);
					}	
					if ( "-targetDir".equals(arg)){
						targetFileDir = args[i+1];
						System.out.println("targetDir  ----"+  targetFileDir);
					}
					if ( "-outputDir".equals(arg)){
						outputFileDir = args[i+1];
						System.out.println("outputDir  ----"+  outputFileDir);
					}
					if ( "-projectName".equals(arg)){
						
						projectName = args[i+1];
						System.out.println("projectName  ----"+  projectName);
					}
					         
				}
				
				initChange(outputFileDir+File.separator+projectName);
				changerFile(sourceFileDir,targetFileDir,outputFileDir,projectName);
				zipRelaceFile(outputFileDir+File.separator+projectName,projectName,outputFileDir);
			}else{
				initChange("E:/wingfeng/website/test/realsse/order");
				changerFile("E:/wingfeng/website/order/deploy/order","E:/wingfeng/website/test/order","E:/wingfeng/website/test/realsse","order");
				zipRelaceFile("E:/wingfeng/website/test/realsse/order","order","E:/wingfeng/website/test/realsse");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		 	
		//File file = new File("E:/wingfeng/website/order/deploy/order/ddd");
		//System.out.println(file.mkdirs());

	}
	
	/**
	 * 
	 * @param relaceFileName
	 */
	 private static void initChange(String relaceFileName){
	
		 File relaceFile = new File(relaceFileName);
		 
		 if ( !relaceFile.mkdirs())
			 FileUtils.removeDir(relaceFileName);
	 }
	
	 
	 private static void zipRelaceFile(String zipdir,String projectname,String outputdir ){
		 Date currentDate = new Date();
		 String zipfile = outputdir+File.separator+DateUtil.dateToString(currentDate, "yyyyMMdd_HHmmss")+ "_"+projectname+".zip";
		 try {
			FileUtils.zip(zipfile, zipdir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
	 }
	
	/**
	 * Change
	 * @param sourceFile
	 * @param targerFile
	 * @param outputFileDir
	 * @param zipFileName
	 */
	public static void changerFile(String sourceFile,String targerFileDir ,String outputFileDir ,String  projectName){
		
		File file = new File(sourceFile);
		//String[] dirs = file.list();
		
		FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
            
                File file =
                        new File(dir.getAbsolutePath() + File.separator + name);
               if ( file.getName().equals(".svn"))
            	   return false;
               else
            	   return true;
               
            }
        };
        
        File[] dirs = file.listFiles(filter);
        File filetemp ;
        File outDir;
        File targetFile;
        
        File newSourceDir;
        File newTargetDir;
		for ( int i = 0 ; i < dirs.length ; i++)
		{
			
			filetemp = dirs[i];
			if( !filetemp.isDirectory()  )
			{
			
				targetFile = new File(targerFileDir + File.separator + filetemp.getName());
				
				if (   filetemp.lastModified() > targetFile.lastModified()){
					
					
					FileUtils.copy(sourceFile+File.separator+filetemp.getName(), outputFileDir+File.separator+projectName);
				}
				
				//System.out.println(filetemp.getName() + "---" + filetemp.lastModified());
			}
			else
			{
				newSourceDir = new File(sourceFile+File.separator+filetemp.getName());
				newTargetDir = new File( targerFileDir+File.separator+filetemp.getName());
				
				if ( newSourceDir.lastModified() > newTargetDir.lastModified())
					newTargetDir.mkdirs();
				
				changerFile(sourceFile+File.separator+filetemp.getName(), targerFileDir+File.separator+filetemp.getName() , outputFileDir ,  projectName+File.separator+filetemp.getName());
			}
				
			
			
		}
		
	}
	
	

}
