package vn.vnpay.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.demo.utilities.ObjectUtilities;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private String bankCode;
    private String privateKey;
    private String ips;

    @Override
    public String toString() {
        return ObjectUtilities.convertToJson(this);
    }
}
