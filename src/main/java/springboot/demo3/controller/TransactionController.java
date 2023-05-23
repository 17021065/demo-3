package springboot.demo3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.demo3.bean.TransactionRequest;
import springboot.demo3.bean.TransactionResponse;
import springboot.demo3.service.TransactionService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponse> addTransaction(@RequestBody TransactionRequest request) throws IllegalAccessException {
        log.info("Request received via URL '/api/v1/transactions'");
        TransactionResponse response = transactionService.add(request);

        return ResponseEntity.ok(response);
    }
}
