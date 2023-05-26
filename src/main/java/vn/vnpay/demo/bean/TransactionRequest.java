package vn.vnpay.demo.bean;

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
    private String checksum;
    private String orderCode;
    private String userName;
    private String realAmount;
    private String promotionCode;

    public boolean isCorrectChecksum(String privateKey) {
        String checksumCombine = mobile +
                bankCode +
                accountNo +
                payDate +
                debitAmount +
                respCode +
                traceTransfer +
                messageType +
                privateKey;
        log.info("Request checksum before hex: {}", checksumCombine);

        String correctChecksum = DigestUtils.sha256Hex(checksumCombine);
        log.info("Request checksum after hex: {}", correctChecksum);

        return checksum.equalsIgnoreCase(correctChecksum);
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
                ", checksum='" + checksum + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", userName='" + userName + '\'' +
                ", realAmount='" + realAmount + '\'' +
                ", promotionCode='" + promotionCode + '\'' +
                '}';
    }
}
