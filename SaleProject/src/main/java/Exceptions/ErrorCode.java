package Exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_CONFIRM_PASSWORD(1000,"The confirmation password does not match. Please try again!",HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_SYNTAX(1001,"Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường,1 chữ số và 1 kí tự đặc biệt(@,#,$,..)",HttpStatus.BAD_REQUEST),
    PASS_IS_DUPLICATED(1002, "Mật khẩu mới không được giống mất khẩu gần nhất", HttpStatus.BAD_REQUEST),
    OLD_PASS_IS_INVALID(1002, "Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST),
    NEW_PASS_IS_NOT_CONFIRMED(1002, "Mật khẩu xác nhận mới không chính xác", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "This email already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User is not existed", HttpStatus.BAD_REQUEST),
    USER_BANNED(1003, "Người dùng đã bị cấm", HttpStatus.BAD_REQUEST),
    OTP_EXISTED(1002, "OTP vừa gửi còn hiệu lực, vui lòng nhập OTP", HttpStatus.BAD_REQUEST),
    INVALID_OTP(1002, "OTP không tồn tại hoặc không còn hiệu lực", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Your password is incorrect", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1005, "Product not exists", HttpStatus.BAD_REQUEST),
    PRODUCT_IMAGES_NOT_ENOUGH(1005, "Cần tải lên ít nhất 4 ảnh", HttpStatus.BAD_REQUEST),
    CART_NOT_EXISTED(1002, "Giỏ hàng không tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_RATING(1002, "Hãy nhập đúng rating", HttpStatus.BAD_REQUEST),
    NOT_FOUND_MESSAGES(1002, "Chưa có đoạn tin nhắn!", HttpStatus.NOT_FOUND),
    CAN_NOT_RATING(1002, "Không được để trống rating", HttpStatus.BAD_REQUEST),
    USER_NOT_ENABLED(1003, "Your account is not verified yet. New verify link have been sent, pls check your email", HttpStatus.BAD_REQUEST),
    USER_NOT_ENABLED_NOT_EXPIRED(1003, "Your account is not verified yet. Pls check your mail to get verify link", HttpStatus.BAD_REQUEST),
    VERIFY_LINK_EXPIRED(1050, "Verify authenticate is expired!", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1005, "Category not exists", HttpStatus.BAD_REQUEST);


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
