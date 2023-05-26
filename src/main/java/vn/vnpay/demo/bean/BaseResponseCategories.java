package vn.vnpay.demo.bean;

public enum BaseResponseCategories implements BaseResponse {
    SUCCESS("00", "Success"),
    INVALID_REQUEST("01", "Invalid request"),
    AUTHORIZED("02", "Not authorized"),
    SUSPICIOUS_REQUEST("03", "Suspicious request"),
    ;

    BaseResponseCategories(String code, String message) {
        this.code = code;
        this.message = message;
    }
    private final String code;
    private final String message;
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public  String getMessage() {
        return message;
    }
}
