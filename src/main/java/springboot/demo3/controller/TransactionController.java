package springboot.demo3.controller;

import lombok.RequiredArgsConstructor;
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
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponse> addTransaction(@RequestBody TransactionRequest request) throws IllegalAccessException {
        TransactionResponse response = transactionService.add(request);

        return ResponseEntity.ok(response);
    }
}
