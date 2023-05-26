package vn.vnpay.demo.bean;

import lombok.*;
import vn.vnpay.demo.utilities.ObjectUtilities;

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
        return ObjectUtilities.convertToJson(this);
    }
}
