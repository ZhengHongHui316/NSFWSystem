package cn.itcast.core.exception;

public class ServiceException extends SysException{

	public ServiceException() {
		super("业务操作错误!");
	}

	public ServiceException(String message) {
		super(message);
	}
	
}
