package Exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_CONFIRM_PASSWORD(1000,"Mật khẩu xác nhận không trùng khớp. Vui lòng thử lại !",HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_SYNTAX(1001,"Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường,1 chữ số và 1 kí tự đặc biệt(@,#,$,..)",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    VERIFY_LINK_EXPIRED(1002, "Link xác thực hết hạn!", HttpStatus.BAD_REQUEST);


    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
