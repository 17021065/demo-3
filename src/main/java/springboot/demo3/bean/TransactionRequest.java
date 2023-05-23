package springboot.demo3.bean;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TransactionRequest {
    private String tokenKey;
    private String apiID;
    private String mobile;
    private String bankCode;
    private String accountNo;
    private String payDate;
    private String additionalData;
    private Double debitAmount;
    private String respCode;
    private String respDesc;
    private String traceTransfer;
    private String messageType;
    private String checkSum;
    private String orderCode;
    private String userName;
    private String realAmount;
    private String promotionCode;

    public Boolean isCorrectCheckSum(String privateKey) {
        String correctCheckSum = DigestUtils.sha256Hex(mobile +
                bankCode +
                accountNo +
                payDate +
                debitAmount +
                respCode +
                traceTransfer +
                messageType +
                privateKey
        );

        return checkSum.equals(correctCheckSum);
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "tokenKey='" + tokenKey + '\'' +
                ", apiID='" + apiID + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", payDate='" + payDate + '\'' +
                ", additionalData='" + additionalData + '\'' +
                ", debitAmount=" + debitAmount +
                ", respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                ", traceTransfer='" + traceTransfer + '\'' +
                ", messageType='" + messageType + '\'' +
                ", checkSum='" + checkSum + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", userName='" + userName + '\'' +
                ", realAmount='" + realAmount + '\'' +
                ", promotionCode='" + promotionCode + '\'' +
                '}';
    }
}
