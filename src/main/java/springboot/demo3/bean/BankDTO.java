package springboot.demo3.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        return "BankDTO{" +
                "bankCode='" + bankCode + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", ips='" + ips + '\'' +
                '}';
    }
}
