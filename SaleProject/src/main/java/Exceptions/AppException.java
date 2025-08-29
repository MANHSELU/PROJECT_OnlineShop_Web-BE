package Exceptions;

public class AppException extends RuntimeException{
        private final ErrorCode errorCode;

    public AppException( ErrorCode errorCode) {
        super(errorCode.getMessage()); // truyền messasage cho RuntimeException để khi mà try catch thì e.getMessage là lấy được lỗi từ ErrorCode
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
