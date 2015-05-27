package com.xcode.whz.util;
//package com.whz.util;
//
//import java.io.BufferedWriter;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.sql.ResultSet;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Random;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////import org.apache.commons.logging.Log;
////import org.apache.commons.logging.LogFactory;
////
////import org.springframework.context.MessageSource;
////import org.springframework.validation.BindException;
////import org.springframework.validation.ObjectError;
////import org.springframework.web.context.WebApplicationContext;
////import org.springframework.web.context.support.WebApplicationContextUtils;
//
//
///**
// * 平台工具类
// * 
// * @version 1.0 Jul 16, 2008
// * @author xiaobin
// */
//public class GlueUtil {
//	public static final String commandName = "Glue";
//	public static final String GLUE_EDITABLE_PARAMETERS_ERROR = "error.editableParameters";
//	
//
//	public static boolean isEmpty(String str) {
//		return str == null || str.trim().length() == 0;
//	}
//
//	public static boolean isNotEmpty(String str) {
//		return !isEmpty(str);
//	}
//
//	public static String getTabs(int i) {
//		if (i < 0) {
//			return "";
//		}
//		String tmp = "";
//		for (int j = 0; j < i; j++) {
//			tmp += "\t";
//		}
//		return tmp;
//	}
//
//	public static String upperFirstChar(String str) {
//		String first = str.substring(0, 1);
//		String last = str.substring(1);
//		return first.toUpperCase() + last;
//	}
//
//	public static String getServerTime(String fmt) {
//		SimpleDateFormat format = new SimpleDateFormat(fmt);
//		return format.format(new Date());
//	}
//
//
//	@SuppressWarnings("unchecked")
//	private static void saveError(HttpServletRequest request, String error) {
//		List errors = (List) request.getSession().getAttribute("errors");
//		if (errors == null) {
//			errors = new ArrayList();
//		}
//		errors.add(error);
//		request.getSession().setAttribute("errors", errors);
//	}
//
//
//	
//	public static String getRemoteAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}
//
//	
//	
//	public static List getList(Map map, Object key) {
//		List l = (List) map.get(key);
//		if (l == null) {
//			l = new ArrayList();
//			map.put(key, l);
//		}
//		return l;
//	}
//	
////	/**
////	 * Map转化为字符串存储
////	 * @param mapIn
////	 * @return
////	 */
////	public static String Map2String(Map mapIn)
////    {
////    	if(mapIn!=null)
////    	{
////    		HashMap map = new HashMap();
////    		if(mapIn instanceof BaseCommand)
////    		{
////    			try {
////					map.putAll(((BaseCommand) mapIn).model2Map());
////		    		map.remove(GlueKey.SESSION_MODEL_NAME);	//删除Session域
////		    		map.remove(GlueConstants.LOCALE_KEY);	//删除国际化域
////		    		map.remove("baseException");			//删除异常域
////    			} catch (Exception e) {
////    				log.error(e);
////				}
////    		}
////    		else
////        	{
////    			map.putAll(mapIn);
////        	}
////
////
////    		
////	        byte[] ret=map2Bin(mapIn);
////	    	byte[] result = Base64.encode(ret);
////	        return new String(result);
////    	}
////    	else
////    	{
////            return null;
////    	}
////    }
////    
////    /**
////     * 字符串转化为Map
////     * @param inStr
////     * @return
////     */
////	public static Map String2Map(String inStr)
////    {
////    	if(inStr!=null)
////    	{
////            byte[] result = Base64.decode(inStr.getBytes());
////            return bin2Map(result);
////    	}
////    	else
////    	{
////            return null;
////    	}
////    }
//    
//    /**
//     * Map转化为2进制字节
//     * @param mapIn
//     * @return
//     */
//	public  static byte[] map2Bin(Map mapIn){
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		try {
//            ObjectOutputStream out = new ObjectOutputStream(bos);
//        	out.writeObject(mapIn);
//            out.close();
//        } catch (IOException e1) {
//        	log.error("反序列化错误："+e1.getMessage());
//            return null;
//        }
//        return bos.toByteArray();
//    }
//	
//	/**
//	 * 二进制字节转化为Map
//	 * @param btin
//	 * @return
//	 */
//	public static Map bin2Map(byte[] btin){
//        Object ob = null;
//        ByteArrayInputStream is = new ByteArrayInputStream(btin);
//        try {
//            ObjectInputStream in = new ObjectInputStream(is);
//        	ob = in.readObject();
//            in.close();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return null;
//        }
//        return (Map)ob;
//    }
//
//	/**
//	 * ognlScript 测试
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception 
//	{
////		Map map =new HashMap();
////		map.put("a",1);
////		map.put("b", 2);
////		
////		Map map1 =new HashMap();
////		map1.put("a",1);
////		map1.put("b", 2);
////		map.put("c", map1);
////		
////		System.out.println(ognlScript("c.a==a",map));
////		System.out.println(getInetAddress());
//		System.out.println(genRandomNum(6));
//		
//		
//	}
//	
//	
//
//	
//	
//	
//	/**
//	 * 验证数据总线
//	 * @param model 总线 
//	 * @param validator 验证名称
//	 * @throws Exception 验证异常
//	 */
////	public static void validate( BaseCommand model,String validator) throws Exception 
////	{
////			List fields = new ArrayList();
////			Iterator keyIte =  model.keySet().iterator();
////			while ( keyIte.hasNext() )
////			{
////				String key = (String) keyIte.next();
////				fields.add( key );
////			}
////
////			BaseCommand wrapper = BaseModelWrapperFactory.getWrapper( validator, fields );
////			wrapper.setMetaData( model.getMetaDataDefine() );
////
////			Iterator entrys = model.entrySet().iterator();
////			while ( entrys.hasNext() )
////			{
////				Map.Entry entry = (Map.Entry) entrys.next();
////				try
////				{
////					wrapper.setValueAt( (String) entry.getKey(), entry.getValue() );
////				}
////				catch ( Exception e )
////				{
////
////				}
////			}
//
////			Validator validatorBean = (Validator) GlueUtil.getBean( Config.get( Config.BEAN_VALIDATOR ) );
////
////			BindException errors = new BindException( new BeanPropertyBindingResult(
////					wrapper,
////					"input" ) );
////			ValidationUtils.invokeValidator( validatorBean, wrapper, errors );
////
////			if ( errors.getErrorCount() != 0 )
////			{
////				throw errors;
////			}
////	}
//	
//	/**
//	 * 字符串转换为日期时间类型
//	 * 
//	 * @param date
//	 * @return
//	 */
//	public static Date str2Date( String str,String format)
//	{
//		if((str==null)||(format==null))
//		{
//			return null;
//		}
//		else
//		{
//			DateFormat timeFormat = new SimpleDateFormat( format );
//			try {
//				return timeFormat.parse(str);
//			} catch (ParseException e) {
//				return null;
//			}
//			
//		}
//	}
//	
//	/**
//	 * 日期时间类型转换为字符串
//	 * 
//	 * @param date
//	 * @return
//	 */
//	public static String date2Str( Date date,String format)
//	{
//		if((date==null)||(format==null))
//		{
//			return null;
//		}
//		else
//		{
//			DateFormat timeFormat = new SimpleDateFormat( format );
//			return timeFormat.format(date);
//			
//		}
//	}
//	
//	/**
//	 * 将Locale对象转换成如"zh_CN"、"en"、"zh_TW"等的字符串
//	 * 
//	 * @param locale
//	 * @return
//	 */
//	public static String getLocaleStr( Locale locale )
//	{
//		String language = locale.getLanguage();
//		String country = locale.getCountry();
//
//		if ( "".equals( country ) )
//		{
//			return language;
//		}
//
//		return language + "_" + country;
//	}
//	
//	
//	
//	/**
//	 * 获取客户端IP
//	 * @param request
//	 * @return
//	 */
//	public static String getUserIpAddr(HttpServletRequest request)
//	{
//		String ip = request.getHeader("x-forwarded-for");
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	 }
//	
//
//	public static String getPreUrI(HttpServletRequest request)
//	{
//		
//		String requestURI =request.getRequestURI();
//		requestURI = requestURI.substring(request.getContextPath().length()+1);
//		StringBuffer subFirstDir=new StringBuffer();
//
//		int index = requestURI.indexOf("/");
//		while(index>=0)
//		{
//			String tmp = requestURI.substring(0,index);
//			requestURI = requestURI.substring(index+1);
//			subFirstDir.append(subFirstDir).append("/").append(tmp);
//			index=requestURI.indexOf("/");
//		}
//		return subFirstDir.toString();
//	}
//	/**
//	 * 获取系统的IP地址
//	 * @return
//	 */
//	public static String getInetAddress()
//	{
//		String netAddress="127.0.0.1";
//		try {
//			InetAddress[]   inetAdds   =   InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
//			if(inetAdds.length>0)
//			{
//				netAddress = inetAdds[0].getHostAddress();
//			}
//			if(inetAdds.length>1)
//			{
//				netAddress = netAddress+";";
//				inetAdds[1].getHostAddress();
//			}
//			if(inetAdds.length>2)
//			{
//				netAddress = netAddress+";";
//				inetAdds[2].getHostAddress();
//			}
//			
//			if(netAddress.indexOf(";")!=-1){
//				netAddress = inetAdds[0].getHostAddress();
//			}
//		} catch (UnknownHostException e) {
//			log.error("getInetAddress is error:"+e);
//		}
//		return netAddress;
//		
//	}
//	
//	/**
//	 * 根据参数将页面跳转到错误页面,错误页面需要为交易处理器,这样可以将错误页面处理为同目录级别的页面
//	 * 使用Session作为错误信息传递的页面
//	 * @param request
//	 * @param response
//	 * @param sessionErrorID session的存储ID,获取后需要删除session中的errorid
//	 * @param errorMsg 存储的错误消息
//	 * @param sendPageUrl 错误页面的URL
//	 * @param backPageURL 错误页面的返回页面
//	 * @throws ServletException 
//	 * @throws IOException
//	 */
//	public static void sendRedirect(HttpServletRequest request, HttpServletResponse response,String sendPageUrl) throws ServletException, IOException 
//	{
//		/**
//		 * 对于response方式的直接跳转，需要做以下设置，否则WebSphere 6.1对跳转后出现的页面有字符集问题，需要增加下列语句
//		 */
//		response.setLocale( request.getLocale() );
//		if(sendPageUrl.startsWith("/"))
//		{
//			sendPageUrl = request.getContextPath()+sendPageUrl;
//		}
//		else
//		{
//			sendPageUrl = request.getContextPath()+"/"+sendPageUrl;
//		}
//		if(log.isDebugEnabled())
//		{
//			log.debug("response sendRedirect url:"+sendPageUrl);
//		}
//		response.sendRedirect(response.encodeRedirectURL(sendPageUrl));
//
//	}
//	
//	/**
//	 * 根据参数将页面跳转到错误页面,错误页面需要为交易处理器,这样可以将错误页面处理为同目录级别的页面
//	 * 使用Session作为错误信息传递的页面
//	 * @param request
//	 * @param response
//	 * @param sessionErrorID session的存储ID,获取后需要删除session中的errorid
//	 * @param errorMsg 存储的错误消息
//	 * @param errorPageUrl 错误页面的URL
//	 * @param backPageURL 错误页面的返回页面
//	 * @throws ServletException 
//	 * @throws IOException
//	 */
//	public static void errorSendRedirect(HttpServletRequest request, HttpServletResponse response,String sessionErrorID,String errorMsg,String errorPageUrl,String backPageURL) throws ServletException, IOException 
//	{
//		/**
//		 * 对于response方式的直接跳转，需要做以下设置，否则WebSphere 6.1对跳转后出现的页面有字符集问题，需要增加下列语句
//		 */
//		response.setLocale( request.getLocale() );
//		request.getSession().setAttribute(sessionErrorID, errorMsg);
//		String subFirstDir=getPreUrI(request);
//
//		if((backPageURL!=null)&&(!backPageURL.equals("")))
//		{
//			request.getSession().setAttribute("backPageURL", backPageURL);
//		}
//
//		errorPageUrl = subFirstDir+errorPageUrl;
//		if(errorPageUrl.startsWith("/"))
//		{
//			errorPageUrl = request.getContextPath()+errorPageUrl;
//		}
//		else
//		{
//			errorPageUrl = request.getContextPath()+"/"+errorPageUrl;
//		}
//		if(log.isDebugEnabled())
//		{
//			log.debug("response sendRedirect url:"+errorPageUrl);
//		}
//		response.sendRedirect(response.encodeRedirectURL(errorPageUrl));
//
//	}
//
//	/**
//	 * @param workflowName 流程名称
//	 * @param flowAccessAdvice
//	 * @param map传近来的Map中的Key必须在子流程中全面定义
//	 * @return 运行结束后的数据总线
//	 * @throws Exception
//	 */
////	public static Map execute(String workflowName,FlowAccessAdvice flowAccessAdvice,Map map) throws Exception
////	{
////		try
////		{
////			long time = System.currentTimeMillis();
////			log.info( "\n-----========== Start executeProcessflow. flowName " + workflowName );
////			int idx = workflowName.indexOf( "." );
////			if ( idx == -1 )
////			{
////				throw new GlueRuntimeException( "Error flowName. must be 'fileName.flowName'. flowName = "
////						+ workflowName );
////			}
////
////			String opName = workflowName.substring( 0, idx );
////			String flowName1 = workflowName.substring( idx + 1, workflowName.length() );
////
////			OperationDescriptor opDescriptor = (OperationDescriptor) GlueConstants.webApplicationContext
////					.getBean( opName + ".biz" );
////			Processflow workflow = opDescriptor.getProcessflow();
////
////			BaseCommand model = new BaseCommand();
////			model.setMetaData( opDescriptor.getInputData() );
////			model.putAll(map);
////			if ( flowAccessAdvice != null && flowAccessAdvice.check( model, null ) )
////			{
////				flowAccessAdvice.begin( model, null );
////			}
////
////			try
////			{
////				workflow.executeProcessflow(
////						opName + "_" + flowName1,
////						model,
////						GlueConstants.webApplicationContext );
////			}
////			finally
////			{
////				if ( flowAccessAdvice != null && flowAccessAdvice.check( model, null ) )
////				{
////					flowAccessAdvice.end( model, null );
////				}
////			}
////			log.info( "\n-----========== End executeProcessflow. flowName " + workflowName
////					+ ". times = " + (System.currentTimeMillis() - time) + " ms\n" );
////			return model;
////		}
////		catch ( Exception e )
////		{
////			log.error( "\n-----========== executeProcessflow error. flowName = "
////					+ workflowName, e );
////			throw e;
////		}
////
////	}
//
//	
//
//	
//	
//
////	public static void checkMap( BaseCommand dist, Map orig ) throws DataAccessException
////	{
////		Iterator origKeys = orig.entrySet().iterator();
////		while ( origKeys.hasNext() )
////		{
////			Map.Entry entry = (Map.Entry)origKeys.next();
////			String origKey = (String) entry.getKey();
////			if ( !dist.containsKey( origKey ) )
////			{
////				throw new DataAccessException(
////						"The metadata of dist map is not match the orig map. origKey = " + origKey );
////			}
////			else
////			{
////				Class clazz = (Class) dist.getMetaData( origKey );
////				Object origValue = entry.getValue();
////				if ( clazz == BaseListModel.class )
////				{
////
////				}
////				else
////				{
////					if ( origValue != null && !clazz.isAssignableFrom( origValue.getClass() ) )
////					{
////						throw new DataAccessException(
////								"The metadata of dist map is not match the orig map. origKey = "
////										+ origKey + ", distClass = " + clazz + ", origClass = "
////										+ origValue.getClass() );
////					}
////				}
////			}
////		}
////	}
//	
//	
//	
//	
//	
//	/**
//	 * 线程池日志输出
//	 * @param fileName
//	 * @param loginfo
//	 * @param size 	文件大小；小于等于0：是追加；否则为，超过该大小则覆盖
//	 */
//	public static void writeLog(String fileName,String loginfo,double size){
//		File filePath = new File(fileName); 
//		if(filePath.exists()){
//			double fileSize = (double)(filePath.length()/1024);
//			log.info("fileName size is "+filePath.length()+" KB"); 
//			BufferedWriter out = null;
//			try {  
//				if(fileSize>size && size > 0){
//					//这种情况就是覆盖的
//			       out = new BufferedWriter(new FileWriter(fileName));
//			       out.write(loginfo);
//			       out.close();
//				}else{
//					//这种情况是添加
//			       out = new BufferedWriter(new FileWriter(fileName, true));
//			       out.write(loginfo);
//			       out.close();
//				}
//			} catch (IOException ei) {  
//				ei.printStackTrace();  
//			}  
//		}else{
//			FileOutputStream fileoutputstream = null;
//			try {
//				fileoutputstream = new FileOutputStream(fileName);
//				fileoutputstream.write(loginfo.getBytes());
//				fileoutputstream.flush();
//				fileoutputstream.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	/**
//	 * 线程池日志输出
//	 * @param fileName
//	 * @param loginfo
//	 */
//	public static void writeLog(String fileName,String loginfo){
//		File filePath = new File(fileName); 
//		if(filePath.exists()){
//			try {  
//				//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
//				FileWriter writer = new FileWriter(fileName, true);
//				writer.write(loginfo);  
//				writer.close();  
//			} catch (IOException ei) {  
//				ei.printStackTrace();  
//			}  
//		}else{
//			FileOutputStream fileoutputstream = null;
//			try {
//				fileoutputstream = new FileOutputStream(fileName);
//				fileoutputstream.write(loginfo.getBytes());
//				fileoutputstream.flush();
//				fileoutputstream.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 将字符转换为utf-8格式
//	 * @param s
//	 */
//	public static String toUtf8String(String s){   
//        StringBuffer sb = new StringBuffer();   
//          for (int i=0;i<s.length();i++){   
//             char c = s.charAt(i);   
//             if (c >= 0 && c <= 255){sb.append(c);}   
//           else{   
//           byte[] b;   
//           try { b = Character.toString(c).getBytes("utf-8");}   
//            catch (Exception ex) {   
//                System.out.println(ex);   
//                     b = new byte[0];   
//            }   
//               for (int j = 0; j < b.length; j++) {   
//                int k = b[j];   
//                 if (k < 0) k += 256;   
//                sb.append("%" + Integer.toHexString(k).toUpperCase());   
//                }   
//        }   
//     }   
//     return sb.toString();   
//   }
//	
//	
//	 /**
//	  * 生成随即密码
//	  * @param pwd_len 生成的密码的总长度
//	  * @return  密码的字符串
//	  */
//	 public static String genRandomNum(int pwd_len) {
//		//35是因为数组是从0开始的，26个字母+10个数字
//		final int maxNum = 36;
//		int i; //生成的随机数
//		int count = 0; //生成的密码的长度
//		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
//				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
//				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
//
//		StringBuffer pwd = new StringBuffer("");
//		Random r = new Random();
//		while (count < pwd_len) {
//			//生成随机数，取绝对值，防止生成负数，
//
//			i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
//
//			if (i >= 0 && i < str.length) {
//				pwd.append(str[i]);
//				count++;
//			}
//		}
//		return pwd.toString();
//	}
//
//}
