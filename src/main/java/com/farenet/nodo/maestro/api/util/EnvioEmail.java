package com.farenet.nodo.maestro.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage.RecipientType;

import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;

@Component
public class EnvioEmail {
	
	SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
	SimpleDateFormat dtfPeru= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);
	 
	 @Value("${mail.from}")
	 String from;

	 @Value("${mail.from.name}")
	 String fromname;
	 
	 @Value("${mail.replyto}")
	 String replyto;
	 
	 @Value("${mail.replyto.name}")
	 String replytoname;
	 

	 @Value("${mail.smtp}")
	 String smtp;
	 
	 @Value("${mail.from.password}")
	 String password;
		
	 @Autowired
	  private TriggerTaskFactory trigger;
	   
	 @Async
	 //@Scheduled(fixedDelay = 300000)
	 public void execute(String mensaje, String correo) {
		 
		 trigger.launch("", "mail.begin", "ENVIAR POR CORREO OFISIS", "correo:" + mensaje+ " | Mensaje "+mensaje);
		 
		 //se obtuvo la lista de eventos
		 String[] toList = correo.split(",");
		 Email email = new Email();

		email.setFromAddress(fromname, from);
		
		for(String toItem : toList)
		{
			email.addRecipient("", toItem, RecipientType.TO);
		}
		email.setSubject("Detalle de envio a Ofisis "+dtf.format(new Date()));
		email.setReplyToAddress(replytoname, replyto);
		
				
				
		StringBuffer cadenaHTML = new StringBuffer(
				"<!doctype html>\n" + 
				"<html>\n" + 
				"  <head>\n" + 
				"    <meta name='viewport' content='width=device-width'>\n" + 
				"    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n" + 
				"    <title>Simple Transactional Email</title>\n" + 
				"    <style>\n" + 
				"    /* -------------------------------------\n" + 
				"        INLINED WITH htmlemail.io/inline\n" + 
				"    ------------------------------------- */\n" + 
				"    /* -------------------------------------\n" + 
				"        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" + 
				"    ------------------------------------- */\n" + 
				"    @media only screen and (max-width: 620px) {\n" + 
				"      table[class=body] h1 {\n" + 
				"        font-size: 28px !important;\n" + 
				"        margin-bottom: 10px !important;\n" + 
				"      }\n" + 
				"      table[class=body] p,\n" + 
				"            table[class=body] ul,\n" + 
				"            table[class=body] ol,\n" + 
				"            table[class=body] td,\n" + 
				"            table[class=body] span,\n" + 
				"            table[class=body] a {\n" + 
				"        font-size: 16px !important;\n" + 
				"      }\n" + 
				"      table[class=body] .wrapper,\n" + 
				"            table[class=body] .article {\n" + 
				"        padding: 10px !important;\n" + 
				"      }\n" + 
				"      table[class=body] .content {\n" + 
				"        padding: 0 !important;\n" + 
				"      }\n" + 
				"      table[class=body] .container {\n" + 
				"        padding: 0 !important;\n" + 
				"        width: 100% !important;\n" + 
				"      }\n" + 
				"      table[class=body] .main {\n" + 
				"        border-left-width: 0 !important;\n" + 
				"        border-radius: 0 !important;\n" + 
				"        border-right-width: 0 !important;\n" + 
				"      }\n" + 
				"      table[class=body] .btn table {\n" + 
				"        width: 100% !important;\n" + 
				"      }\n" + 
				"      table[class=body] .btn a {\n" + 
				"        width: 100% !important;\n" + 
				"      }\n" + 
				"      table[class=body] .img-responsive {\n" + 
				"        height: auto !important;\n" + 
				"        max-width: 100% !important;\n" + 
				"        width: auto !important;\n" + 
				"      }\n" + 
				"    }\n" + 
				"\n" + 
				"    /* -------------------------------------\n" + 
				"        PRESERVE THESE STYLES IN THE HEAD\n" + 
				"    ------------------------------------- */\n" + 
				"    @media all {\n" + 
				"      .ExternalClass {\n" + 
				"        width: 100%;\n" + 
				"      }\n" + 
				"      .ExternalClass,\n" + 
				"            .ExternalClass p,\n" + 
				"            .ExternalClass span,\n" + 
				"            .ExternalClass font,\n" + 
				"            .ExternalClass td,\n" + 
				"            .ExternalClass div {\n" + 
				"        line-height: 100%;\n" + 
				"      }\n" + 
				"      .apple-link a {\n" + 
				"        color: inherit !important;\n" + 
				"        font-family: inherit !important;\n" + 
				"        font-size: inherit !important;\n" + 
				"        font-weight: inherit !important;\n" + 
				"        line-height: inherit !important;\n" + 
				"        text-decoration: none !important;\n" + 
				"      }\n" + 
				"      .btn-primary table td:hover {\n" + 
				"        background-color: #34495e !important;\n" + 
				"      }\n" + 
				"      .btn-primary a:hover {\n" + 
				"        background-color: #34495e !important;\n" + 
				"        border-color: #34495e !important;\n" + 
				"      }\n" + 
				"    }\n" + 
				"    </style>\n" + 
				"  </head>\n" + 
				"  <body class='' style='background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;'>\n" + 
				"    <table border='0' cellpadding='0' cellspacing='0' class='body' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;'>\n" + 
				"      <tr>\n" + 
				"        <td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>&nbsp;</td>\n" + 
				"        <td class='container' style='font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;'>\n" + 
				"          <div class='content' style='box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;'>\n" + 
				"\n" + 
				"            <!-- START CENTERED WHITE CONTAINER -->\n" + 
				"            <span class='preheader' style='color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;'>"
				+ mensaje 
				+ "</span>\n" + 
				"            <table class='main' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;'>\n" + 
				"\n" + 
				"              <!-- START MAIN CONTENT AREA -->\n" + 
				"              <tr>\n" + 
				"                <td class='wrapper' style='font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;'>\n" + 
				"                  <table border='0' cellpadding='0' cellspacing='0' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;'>\n" + 
				"                    <tr>\n" + 
				"                      <td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>\n");
				StringBuffer eventStr = new StringBuffer();
				eventStr.append("<p style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>");
				eventStr.append("<b><big>").append(mensaje).append("</big></b></p>\n");
				cadenaHTML.append(eventStr.toString());
				
				cadenaHTML.append(
				"                      </td>\n" + 
				"                    </tr>\n" + 
				"                  </table>\n" + 
				"                </td>\n" + 
				"              </tr>\n" + 
				"\n" + 
				"            <!-- END MAIN CONTENT AREA -->\n" + 
				"            </table>\n" + 
				"\n" + 
				"            <!-- START FOOTER -->\n" + 
				"            <div class='footer' style='clear: both; Margin-top: 10px; text-align: center; width: 100%;'>\n" + 
				"              <table border='0' cellpadding='0' cellspacing='0' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;'>\n" + 
				"                <tr>\n" + 
				"                  <td class='content-block' style='font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;'>\n" + 
				"                    <span class='apple-link' style='color: #999999; font-size: 12px; text-align: center;'>FARENET 2018</span>\n" + 
				"                  </td>\n" + 
				"                </tr>\n" + 
				"              </table>\n" + 
				"            </div>\n" + 
				"            <!-- END FOOTER -->\n" + 
				"\n" + 
				"          <!-- END CENTERED WHITE CONTAINER -->\n" + 
				"          </div>\n" + 
				"        </td>\n" + 
				"        <td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>&nbsp;</td>\n" + 
				"      </tr>\n" + 
				"    </table>\n" + 
				"  </body>\n" + 
				"</html>\n" + 
				"");
		
		email.setTextHTML(cadenaHTML.toString());
				
		try{
			
			 new Mailer(smtp, 
					 25, 
					 from, 
					 password, 
					 TransportStrategy.SMTP_TLS)
			 .sendMail(email);
				
			 //actualizamos los enviados
			 
			 trigger.launch("", "mail.enviado", "ENVIAR POR CORREO OFISIS", "Correo:" + correo + " | Mensaje : " + mensaje);
		     
		}catch(Exception e)
		{
			e.printStackTrace();
			trigger.launch("", "mail.error", "ERROR AL ENVIAR",e.getMessage());
			trigger.launch("", "mail.error", "ENVIAR POR CORREO OFISIS", "Correo:" + correo + " | Mensaje : " + mensaje);
		     
		}

		
		 
	 }

}
