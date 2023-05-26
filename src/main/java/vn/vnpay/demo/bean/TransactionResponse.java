package vn.vnpay.demo.bean;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String code;
    private String message;
    private String responseId;
    private String responseTime;
    private String checksum;

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", responseId='" + responseId + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", checksum='" + checksum + '\'' +
                '}';
    }
}
