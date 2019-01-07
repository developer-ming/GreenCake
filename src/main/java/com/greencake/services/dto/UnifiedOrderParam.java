package com.greencake.services.dto;

import java.math.BigDecimal;

public class UnifiedOrderParam {
	    private String title;
	    private String remarks;
	    private BigDecimal payAmt;
	    private String remoteIp;
	    private String openId;
		public String getTitle() {
			return title;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public BigDecimal getPayAmt() {
			return payAmt;
		}
		public String getOpenId() {
			return openId;
		}
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public String getRemoteIp() {
			return remoteIp;
		}
		public void setRemoteIp(String remoteIp) {
			this.remoteIp = remoteIp;
		}
}
