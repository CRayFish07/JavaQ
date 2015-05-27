package com.xcode.whz.alipay.config;

import org.apache.commons.httpclient.HttpException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* *
 *绫诲悕锛欻ttpProtocolHandler
 *鍔熻兘锛欻ttpClient鏂瑰紡璁块棶
 *璇︾粏锛氳幏鍙栬繙绋婬TTP鏁版嵁
 *鐗堟湰锛�3.3
 *鏃ユ湡锛�2012-08-17
 *璇存槑锛�
 *浠ヤ笅浠ｇ爜鍙槸涓轰簡鏂逛究鍟嗘埛娴嬭瘯鑰屾彁渚涚殑鏍蜂緥浠ｇ爜锛屽晢鎴峰彲浠ユ牴鎹嚜宸辩綉绔欑殑闇�瑕侊紝鎸夌収鎶�鏈枃妗ｇ紪鍐�,骞堕潪涓�瀹氳浣跨敤璇ヤ唬鐮併��
 *璇ヤ唬鐮佷粎渚涘涔犲拰鐮旂┒鏀粯瀹濇帴鍙ｄ娇鐢紝鍙槸鎻愪緵涓�涓弬鑰冦��
 */

public class HttpProtocolHandler {

    private static String              DEFAULT_CHARSET                     = "GBK";

    /** 杩炴帴瓒呮椂鏃堕棿锛岀敱bean factory璁剧疆锛岀己鐪佷负8绉掗挓 */
    private int                        defaultConnectionTimeout            = 8000;

    /** 鍥炲簲瓒呮椂鏃堕棿, 鐢眀ean factory璁剧疆锛岀己鐪佷负30绉掗挓 */
    private int                        defaultSoTimeout                    = 30000;

    /** 闂茬疆杩炴帴瓒呮椂鏃堕棿, 鐢眀ean factory璁剧疆锛岀己鐪佷负60绉掗挓 */
    private int                        defaultIdleConnTimeout              = 60000;

    private int                        defaultMaxConnPerHost               = 30;

    private int                        defaultMaxTotalConn                 = 80;

    /** 榛樿绛夊緟HttpConnectionManager杩斿洖杩炴帴瓒呮椂锛堝彧鏈夊湪杈惧埌鏈�澶ц繛鎺ユ暟鏃惰捣浣滅敤锛夛細1绉�*/
    private static final long          defaultHttpConnectionManagerTimeout = 3 * 1000;

    /**
     * HTTP杩炴帴绠＄悊鍣紝璇ヨ繛鎺ョ鐞嗗櫒蹇呴』鏄嚎绋嬪畨鍏ㄧ殑.
     */
    private HttpConnectionManager      connectionManager;

    private static HttpProtocolHandler httpProtocolHandler                 = new HttpProtocolHandler();

    /**
     * 宸ュ巶鏂规硶
     * 
     * @return
     */
    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }

    /**
     * 绉佹湁鐨勬瀯閫犳柟娉�
     */
    private HttpProtocolHandler() {
        // 鍒涘缓涓�涓嚎绋嬪畨鍏ㄧ殑HTTP杩炴帴姹�
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(defaultIdleConnTimeout);

        ict.start();
    }

    /**
     * 鎵цHttp璇锋眰
     * 
     * @param request 璇锋眰鏁版嵁
     * @param strParaFileName 鏂囦欢绫诲瀷鐨勫弬鏁板悕
     * @param strFilePath 鏂囦欢璺緞
     * @return 
     * @throws HttpException, IOException 
     */
    public HttpResponse execute(HttpRequest request, String strParaFileName, String strFilePath) throws HttpException, IOException {
        HttpClient httpclient = new HttpClient(connectionManager);

        // 璁剧疆杩炴帴瓒呮椂
        int connectionTimeout = defaultConnectionTimeout;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

        // 璁剧疆鍥炲簲瓒呮椂
        int soTimeout = defaultSoTimeout;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

        // 璁剧疆绛夊緟ConnectionManager閲婃斁connection鐨勬椂闂�
        httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
        HttpMethod method = null;

        //get妯″紡涓斾笉甯︿笂浼犳枃浠�
        if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
            method = new GetMethod(request.getUrl());
            method.getParams().setCredentialCharset(charset);

            // parseNotifyConfig浼氫繚璇佷娇鐢℅ET鏂规硶鏃讹紝request涓�瀹氫娇鐢≦ueryString
            method.setQueryString(request.getQueryString());
        } else if(strParaFileName.equals("") && strFilePath.equals("")) {
        	//post妯″紡涓斾笉甯︿笂浼犳枃浠�
            method = new PostMethod(request.getUrl());
            ((PostMethod) method).addParameters(request.getParameters());
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
        }
        else {
        	//post妯″紡涓斿甫涓婁紶鏂囦欢
            method = new PostMethod(request.getUrl());
            List<Part> parts = new ArrayList<Part>();
            for (int i = 0; i < request.getParameters().length; i++) {
            	parts.add(new StringPart(request.getParameters()[i].getName(), request.getParameters()[i].getValue(), charset));
            }
            //澧炲姞鏂囦欢鍙傛暟锛宻trParaFileName鏄弬鏁板悕锛屼娇鐢ㄦ湰鍦版枃浠�
            parts.add(new FilePart(strParaFileName, new FilePartSource(new File(strFilePath))));
            
            // 璁剧疆璇锋眰浣�
            ((PostMethod) method).setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]), new HttpMethodParams()));
        }

        // 璁剧疆Http Header涓殑User-Agent灞炴��
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        HttpResponse response = new HttpResponse();

        try {
            httpclient.executeMethod(method);
            if (request.getResultType().equals(HttpResultType.STRING)) {
                response.setStringResult(method.getResponseBodyAsString());
            } else if (request.getResultType().equals(HttpResultType.BYTES)) {
                response.setByteResult(method.getResponseBody());
            }
            response.setResponseHeaders(method.getResponseHeaders());
        } catch (UnknownHostException ex) {

            return null;
        } catch (IOException ex) {

            return null;
        } catch (Exception ex) {

            return null;
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * 灏哊ameValuePairs鏁扮粍杞彉涓哄瓧绗︿覆
     * 
     * @param nameValues
     * @return
     */
    protected String toString(NameValuePair[] nameValues) {
        if (nameValues == null || nameValues.length == 0) {
            return "null";
        }

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < nameValues.length; i++) {
            NameValuePair nameValue = nameValues[i];

            if (i == 0) {
                buffer.append(nameValue.getName() + "=" + nameValue.getValue());
            } else {
                buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
            }
        }

        return buffer.toString();
    }
}
