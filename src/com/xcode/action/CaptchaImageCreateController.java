package com.xcode.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class CaptchaImageCreateController {
//	<div >
//    验证码：        
//     <input name="kaptcha" type="text"  onchange="checkCha(this);" id="kaptcha" maxlength="4" class="chknumber_input" />             
//     <img id="y00" alt="" src="images/action_check.png" style="display: none;">
//     <img id="n00" alt="" src="images/hx.png" style="display: none;">
//      <img src="captcha-image.asp" width="55" height="22" id="kaptchaImage"  style="margin-bottom: -3px"/> 
//     <script type="text/javascript">    
//      $(function(){         
//          $('#kaptchaImage').click(function () { 
//         	 $(this).hide().attr('src', 'captcha-image.asp?' + Math.floor(Math.random()*100) ).fadeIn(); });    
//                }); 
//      
//      function checkCha(kaptcha){
//   	   alert(kaptcha.value);
//   	   var url='checkcaptcha.asp?code='+kaptcha.value;
//   	   if(kaptcha.value.length==4){
//   	   $.ajax({
//   	   url:url,
//   	   data:'',
//   	   type:'get',
//   	   dataType:'json',
//   	   contentType:'application/json;charset=utf-8',
//   	   cache:false,
//   	   success:function(data) {
//   		   if(data==1){
//   			   alert(1);
//   			   document.getElementById("y00").style.display="block";
//   			   document.getElementById("n00").style.display="none";
//   		   }
//   		   if(data==0){
//   			   document.getElementById("y00").style.display="none";
//   			   document.getElementById("n00").style.display="block";
//   		   }
//   	   },
//   	   error:function(xhr) { alert('失败');}
//   	    }); 
//   	   }
//      }
//     </script> 
//   </div>
	private Producer captchaProducer = null;

	@Autowired
	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	@RequestMapping(value="captcha-image")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
	@RequestMapping(value="checkcaptcha")
	public void checkcaptcha(String code,HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = null; 
		String sessioncode=(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		response.setContentType("text/html;charset=utf-8"); try {
			out = response.getWriter(); 
			if(code.equals(sessioncode)){
				out.print("1"); 
			}else{
				out.print("0"); 
			}
			out.flush();
			} catch (IOException e) { e.printStackTrace();
			}finally { out.close();}
		}
}