package springboot.demo3.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "bank-config")
@Slf4j
public class BankConfigure {

    private List<BankDTO> banks;

    public BankDTO findBankByCode(String bankCode) {
        Optional<BankDTO> filterBank = banks.stream().filter(i -> i.getBankCode().equals(bankCode)).findFirst();

        return filterBank.orElse(null);
    }

    public boolean isContain(String bankCode) {
        return banks.stream().anyMatch(i -> i.getBankCode().equals(bankCode));
    }
}
