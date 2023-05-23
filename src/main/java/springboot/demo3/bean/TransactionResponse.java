package springboot.demo3.bean;

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
    private String checkSum;

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", responseId='" + responseId + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", checkSum='" + checkSum + '\'' +
                '}';
    }
}
