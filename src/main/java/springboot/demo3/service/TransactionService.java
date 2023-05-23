package springboot.demo3.service;

import springboot.demo3.bean.TransactionRequest;
import springboot.demo3.bean.TransactionResponse;

public interface TransactionService {
    TransactionResponse add(TransactionRequest transactionDetails) throws IllegalAccessException;
}
