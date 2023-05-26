package vn.vnpay.demo.service;

import vn.vnpay.demo.bean.TransactionRequest;
import vn.vnpay.demo.bean.TransactionResponse;

public interface TransactionService {
    TransactionResponse add(TransactionRequest transactionRequest);
}
