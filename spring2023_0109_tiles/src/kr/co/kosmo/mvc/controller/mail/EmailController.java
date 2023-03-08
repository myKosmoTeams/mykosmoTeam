package kr.co.kosmo.mvc.controller.mail;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/mail")
public class EmailController {
	static final String emailFromRecipient = "kobilinux@naver.com";
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@GetMapping(value="/emailForm")
	public String emailForm() {
		
		return "mail/emailForm";
	}

	
	
	
	
	
	
	
	
	
	
	
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmailToClient(HttpServletRequest request) {
        String emailSubject = request.getParameter("subject");
        String emailMessage = request.getParameter("message");
        String emailToRecipient = request.getParameter("mailTo");
        // ���� ������ �����ϴ� �޼��忡�� �����͸� �Է� 
        mailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");             
                mimeMsgHelperObj.setTo(emailToRecipient);
                mimeMsgHelperObj.setFrom(emailFromRecipient);               
                mimeMsgHelperObj.setText(emailMessage);
                mimeMsgHelperObj.setSubject(emailSubject);
            }
        });
        System.out.println("\n ���� ���� ���� \n");
 
        ModelAndView  mav = new ModelAndView("success","messageObj","��������  ���� ");
        return  mav;   
    }
}
