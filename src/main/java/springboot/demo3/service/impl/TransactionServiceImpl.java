package springboot.demo3.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springboot.demo3.bean.*;
import springboot.demo3.service.TransactionService;
import springboot.demo3.utilities.ObjectUtilities;
import springboot.demo3.utilities.StringUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final BankConfigure bankConfigure;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public TransactionResponse add(TransactionRequest transactionRequest) throws IllegalAccessException {
        log.info("Begin to add transaction");
        log.info(String.valueOf(transactionRequest));

        String bankCode = transactionRequest.getBankCode();
        String privateKey = null;
        for (BankDTO bank : bankConfigure.getBanks()) {
            if (bank.getBankCode().equals(bankCode)) {
                privateKey = bank.getPrivateKey();
            }
        }

        if (ObjectUtilities.hasNullOrEmptyOrBlankAttributes(transactionRequest)) {
            log.info("Add transaction failed: Request has null or empty or blank attribute!");
            return response(BaseResponseCategories.INVALID_REQUEST, privateKey);
        }
        if (privateKey == null) {
            log.info("Add transaction failed: Bank code is not exist!");
            return response(BaseResponseCategories.AUTHORIZED, null);
        }
        if (!transactionRequest.isCorrectCheckSum(privateKey)) {
            log.info("Add transaction failed: Check sum is incorrect!");
            return response(BaseResponseCategories.SUSPICIOUS_REQUEST, privateKey);
        }

        redisTemplate.opsForHash().put(bankCode,
                transactionRequest.getTokenKey(),
                Objects.requireNonNull(ObjectUtilities.convertToJson(transactionRequest))
        );

        log.info("Add transaction successfully!");
        return response(BaseResponseCategories.SUCCESS, privateKey);
    }

    private TransactionResponse response(BaseResponse baseResponse, String privateKey) {
        String responseId = StringUtilities.generateRandomString(Constant.responseIdLength);
        String responseTime = new SimpleDateFormat(Constant.responseTimeFormat).format(new Date());
        String checkSum = DigestUtils.sha256Hex(baseResponse.getCode()
                + baseResponse.getMessage()
                + responseId
                + responseTime
                + privateKey
        );

        TransactionResponse response = TransactionResponse.builder()
                .code(baseResponse.getCode())
                .message(baseResponse.getMessage())
                .responseId(responseId)
                .responseTime(responseTime)
                .checkSum(checkSum)
                .build();

        log.info(String.valueOf(response));
        return response;
    }
}
