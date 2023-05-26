package vn.vnpay.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "bank-config")
@Slf4j
public class BankConfigure {
    private List<BankDTO> banks;

    public BankDTO findBankByCode(String bankCode) {
        for (BankDTO bank : banks) {
            if (bank.getBankCode().equals(bankCode)) {
                return bank;
            }
        }
        return null;
    }
}
