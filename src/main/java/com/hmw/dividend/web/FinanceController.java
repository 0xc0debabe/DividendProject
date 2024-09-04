package com.hmw.dividend.web;

import com.hmw.dividend.model.ScrapedResult;
import com.hmw.dividend.service.FinanceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 또는 @NonNull로 표시된 필드만 파라미터로 받는 생성자를 생성
// 만약, 다른 멤버변수가 있으면 생성자 X, AllArgsConstructor 써야됨
//        (멤버변수 : 클래스 안에서 생성됨, 지역변수 : 메소드 안에서 생성됨)
@RequestMapping("/finance")
public class FinanceController {

    private final FinanceService financeService;

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<?> searchFinance(@PathVariable String companyName) {
        ScrapedResult scrapedResult = this.financeService.getDividendByCompanyName(companyName);
        return ResponseEntity.ok(scrapedResult);
    }

}