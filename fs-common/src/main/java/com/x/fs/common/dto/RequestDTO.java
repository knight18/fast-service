package com.x.fs.common.dto;

import java.io.Serializable;

/**
 * 入参对象
 * @author x
 */
public class RequestDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tenantId;

	/**
	 * 用户token
	 */
	private String userToken;

	/**
	 * 用户ID
	 */
	private String operatorCode;

	/**
	 * 客户端IP
	 */
	private String ip;

	/**
	 * 页码
	 */
	private Integer pageNo = 0;

	/**
	 * 页长
	 */
	private Integer pageSize = 0;

	/**
	 * 是否需要分页
	 */
	private Boolean needPage = true;

	/**
	 * 业务对象
	 */
	private T params;

	private String userName;

	private String systemFlag;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUserToken() {
		return userToken;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getNeedPage() {
		return needPage;
	}

	public void setNeedPage(Boolean needPage) {
		this.needPage = needPage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	@Override
	public String toString() {
		return "RequestDTO{" +
				"tenantId='" + tenantId + '\'' +
				", userToken='" + userToken + '\'' +
				", operatorCode='" + operatorCode + '\'' +
				", ip='" + ip + '\'' +
				", pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", needPage=" + needPage +
				", params=" + params +
				", userName='" + userName + '\'' +
				", systemFlag='" + systemFlag + '\'' +
				'}';
	}
}
