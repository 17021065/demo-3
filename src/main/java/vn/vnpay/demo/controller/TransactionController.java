package vn.vnpay.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vnpay.demo.bean.TransactionRequest;
import vn.vnpay.demo.bean.TransactionResponse;
import vn.vnpay.demo.service.TransactionService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponse> addTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.add(request);

        return ResponseEntity.ok(response);
    }
}
