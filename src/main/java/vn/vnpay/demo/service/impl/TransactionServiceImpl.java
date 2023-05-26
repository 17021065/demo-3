package vn.vnpay.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.vnpay.demo.bean.*;
import vn.vnpay.demo.service.TransactionService;
import vn.vnpay.demo.utilities.ObjectUtilities;
import vn.vnpay.demo.utilities.StringUtilities;

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
    public TransactionResponse add(TransactionRequest transactionRequest) {
        log.info("Begin to add transaction with data: {}", transactionRequest);

        if (ObjectUtilities.hasNullOrEmptyOrBlankAttributes(transactionRequest)) {
            log.info("Add transaction failed: Request has null or empty or blank attribute!");
            return response(BaseResponseCategories.INVALID_REQUEST, null);
        }

        String bankCode = transactionRequest.getBankCode();
        BankDTO bank = bankConfigure.findBankByCode(bankCode);
        String privateKey = bank != null ? bank.getPrivateKey() : null;

        if (privateKey == null) {
            log.info("Add transaction failed: Bank code is not exist!");
            return response(BaseResponseCategories.AUTHORIZED, null);
        }
        if (!transactionRequest.isCorrectChecksum(privateKey)) {
            log.info("Add transaction failed: Checksum is incorrect!");
            return response(BaseResponseCategories.SUSPICIOUS_REQUEST, privateKey);
        }

        String hashKey = transactionRequest.getTokenKey();
        String hashValue = ObjectUtilities.convertToJson(transactionRequest);
        log.info("Push data to Redis: {hash:'{}', hashKey:'{}', hashValue:'{}'}", bankCode, hashKey, hashValue);
        redisTemplate.opsForHash().put(bankCode, hashKey, Objects.requireNonNull(hashValue));

        log.info("Add transaction successfully!");
        return response(BaseResponseCategories.SUCCESS, privateKey);
    }

    private TransactionResponse response(BaseResponse baseResponse, String privateKey) {
        String responseId = StringUtilities.generateRandomString(Constant.RESPONSE_ID_LENGTH);
        String responseTime = new SimpleDateFormat(Constant.RESPONSE_TIME_FORMAT).format(new Date());
        String checksumCombine = baseResponse.getCode()
                + baseResponse.getMessage()
                + responseId
                + responseTime
                + privateKey;
        log.info("Response checksum before hex: {}", checksumCombine);

        String checksum = DigestUtils.sha256Hex(checksumCombine);
        log.info("Response checksum after hex: {}", checksum);

        TransactionResponse response = TransactionResponse.builder()
                .code(baseResponse.getCode())
                .message(baseResponse.getMessage())
                .responseId(responseId)
                .responseTime(responseTime)
                .checksum(checksum)
                .build();

        log.info("Final response: {}", response);
        return response;
    }
}
