package com.mj.util.mail;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mj.activity.BtcServer;

import java.util.Properties;

public class MailUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(MailUtil.class);

	public static void mail(String mailTitle, String mailContent) {
		try {
			Mail mail = new Mail("mengjia2017@126.com", "zasdsd123");
			String[] toArr = { "mengjia2017@126.com" };
			mail.set_to(toArr);
			mail.set_from("mengjia2017@126.com");
			mail.set_subject(mailTitle);
			mail.setBody(mailContent);
			boolean isSucess = mail.send();
			logger.error("mail  is " + isSucess);
		} catch (Exception e) {
			logger.error("mail error is " + e.getMessage());
		}

	}

	public static void Mailto(String mailTitle, String mailContent) {
		try {
			// 配置发送邮件的环境属性
			final Properties props = new Properties();
			/*
			 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host
			 * / mail.user / mail.from
			 */
			// 表示SMTP发送邮件，需要进行身份验证
			// props.put("mail.host", "mail.mj.com");
			// props.put("mail.smtp.localhost", "mail.mj.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.126.com");
			// 发件人的账号
			props.put("mail.user", "mengjia2017@126.com");
			// 访问SMTP服务时需要提供的密码
			props.put("mail.password", "zasdsd123");
			props.put("mail.debug", "true");// 便于调试

			// 构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名、密码
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			// 使用环境属性和授权信息，创建邮件会话
			Session mailSession = Session.getInstance(props, authenticator);
			// 创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			// 设置发件人
			InternetAddress form = new InternetAddress(
					props.getProperty("mail.user"));
			message.setFrom(form);

			// 设置收件人
			InternetAddress to = new InternetAddress("mengjia2017@126.com");
			message.setRecipient(RecipientType.TO, to);

			// // 设置抄送
			// InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
			// message.setRecipient(RecipientType.CC, cc);
			//
			// // 设置密送，其他的收件人不能看到密送的邮件地址
			// InternetAddress bcc = new InternetAddress("aaaaa@163.com");
			// message.setRecipient(RecipientType.CC, bcc);

			// 设置邮件标题
			message.setSubject(mailTitle);

			// 设置邮件的内容体
			message.setContent(mailContent, "text/html;charset=UTF-8");

			// 发送邮件
			Transport.send(message);
		} catch (Exception e) {
			logger.error("MailUtil error is " + e.getMessage());
		}

	}

}
