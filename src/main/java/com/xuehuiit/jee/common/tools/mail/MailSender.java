/********************************************************************
 * 项目名称				：<b>一个专业的茶叶网站平台</b>			<br/>
 * 
 * Copyright 2005-2006 Wuhua. All rights reserved
 ********************************************************************/
package com.xuehuiit.jee.common.tools.mail;


/**
 * <b>类名：MailSender.java</b> </br> 
 * 编写日期: 2007-1-22 <br/>
 * 程序功能描述： <br/>
 * Demo: <br/>
 * Bug: <br/>
 * 
 * 程序变更日期 ：<br/> 
 * 变更作者 ：<br/> 
 * 变更说明 ：<br/>
 * 
 * @author wuhua </br> <a href="mailto:rrq12345@163.com">rrq12345@163.com</a>
 */
public final class MailSender {
	
	/**
	 * 发送邮件
	 * @param to
	 * @param subject
	 * @param body
	 * @throws Exception
	 */
	public static void send(String to, String subject, String body) throws Exception{
		Mailer mail = new Mailer(ManageConfig.SMTP);
		mail.setNamePass(ManageConfig.HOST_MAIL_NAME, ManageConfig.HOST_MAIL_NAME);
		mail.setFrom(ManageConfig.HOST_MAIL_NAME);
		mail.setSubject(subject);
		mail.setBody(body, false);
		mail.setTo(to);
		mail.setNeedAuth(true);
		mail.sendout();
	}
}
