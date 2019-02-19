/**
 * 
 */
package com.xuehuiit.jee.common.util.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Administrator
 *
 */
public  class Request implements HttpServletRequest {
    private String requestURI;
    private Map<String, String[]> parameterMap;

    public Request() {
      this("");
    }

    public Request(String requestURI) {
      this.requestURI = requestURI;
      parameterMap = new HashMap<String, String[]>();
    }

    public Request(String requestURI, Map<String, String[]> parameterMap) {
      this.requestURI = requestURI;
      this.parameterMap = parameterMap;
    }

    /**
     * 获得指定名称的参数
     * @param name
     * @return
     */
    public String getParameter(String name) {
      String[] values = parameterMap.get(name);
      if (values != null && values.length > 0) {
        return values[0];
      }
      return null;
    }

    /**
     * 获得所有的参数名称
     * @return
     */
    public Enumeration<String> getParameterNames() {
      return Collections.enumeration(parameterMap.keySet());
    }

    /**
     * 获得指定名称的参数值(多个)
     * @param name
     * @return
     */
    public String[] getParameterValues(String name) {
      return parameterMap.get(name);
    }

    /**
     * 获得请求的url地址
     * @return
     */
    public String getRequestURI() {
      return requestURI;
    }

    /**
     * 获得 参数-值Map
     * @return
     */
    public Map<String, String[]> getParameterMap() {
      return parameterMap;
    }

    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("{");
      buf.append("\n  url = ").append(this.requestURI);
      buf.append("\n  paramsMap = {");
      if (this.parameterMap.size() > 0) {
        for (Map.Entry<String, String[]> e : this.parameterMap.entrySet()) {
          buf.append(e.getKey()).append("=").append(Arrays.toString(e.getValue())).append(",");
        }
        buf.deleteCharAt(buf.length() - 1);
      }
      buf.append("}\n}");
      return buf.toString();
    }
   //  剩下的函数,自己根据需求实现了,一般返回0或者null即可
   //  这里就不贴了,HttpServletRequest的接口方法也太多了

	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}


	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}


	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getHeader(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public Enumeration getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}


	public Enumeration getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}


	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}


	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}


	public HttpSession getSession(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
		return false;
	}

	public void login(String s, String s1) throws ServletException {

	}

	public void logout() throws ServletException {

	}

	public Collection<Part> getParts() throws IOException, ServletException {
		return null;
	}

	public Part getPart(String s) throws IOException, ServletException {
		return null;
	}


	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}


	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ServletContext getServletContext() {
		return null;
	}

	public AsyncContext startAsync() throws IllegalStateException {
		return null;
	}

	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
		return null;
	}

	public boolean isAsyncStarted() {
		return false;
	}

	public boolean isAsyncSupported() {
		return false;
	}

	public AsyncContext getAsyncContext() {
		return null;
	}

	public DispatcherType getDispatcherType() {
		return null;
	}


	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}


	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}


	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}


	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}


	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}


	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}
  }
