package com.greencake.rest.exceptions;

public class WxPayException extends CustomException {
	private static final long serialVersionUID = 1L;

    public WxPayException(String message) {
        super(message);
    }

    public WxPayException(String returnCode, String returnMsg) {
        this("Return Code: " + returnCode + ", Return Msg: " + returnMsg);
    }

    public WxPayException(String returnCode, String returnMsg, String errorCode, String errorDesc) {
        this("Return Code: " + returnCode + ", Return Msg: " + returnMsg + ", Error Code: " + errorCode + ", Error Desc: " + errorDesc);
    }
}
