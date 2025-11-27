package Exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_CONFIRM_PASSWORD(1000,"Mật khẩu xác nhận không trùng khớp. Vui lòng thử lại !",HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_SYNTAX(1001,"Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường,1 chữ số và 1 kí tự đặc biệt(@,#,$,..)",HttpStatus.BAD_REQUEST),
    PASS_IS_DUPLICATED(1002, "Mật khẩu mới không được giống mất khẩu gần nhất", HttpStatus.BAD_REQUEST),
    OLD_PASS_IS_INVALID(1002, "Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST),
    NEW_PASS_IS_NOT_CONFIRMED(1002, "Mật khẩu xác nhận mới không chính xác", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "Người dùng không tồn tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_ENABLED(1003, "Người dùng không tồn tồn tại", HttpStatus.BAD_REQUEST),
    OTP_EXISTED(1002, "OTP vừa gửi còn hiệu lực, vui lòng nhập OTP", HttpStatus.BAD_REQUEST),
    INVALID_OTP(1002, "OTP không tồn tại hoặc không còn hiệu lực", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Mật khẩu không đúng", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1005, "Sản phẩm không tồn tại", HttpStatus.BAD_REQUEST),
    PRODUCT_IMAGES_NOT_ENOUGH(1005, "Cần tải lên ít nhất 4 ảnh", HttpStatus.BAD_REQUEST),
    CART_NOT_EXISTED(1002, "Giỏ hàng không tồn tại", HttpStatus.BAD_REQUEST),
    VERIFY_LINK_EXPIRED(1050, "Link xác thực hết hạn!", HttpStatus.BAD_REQUEST);


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
