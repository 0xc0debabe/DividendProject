package com.hmw.dividend.service;

import com.hmw.dividend.exception.impl.NoCompanyException;
import com.hmw.dividend.model.Company;
import com.hmw.dividend.model.Dividend;
import com.hmw.dividend.model.ScrapedResult;
import com.hmw.dividend.model.constrants.CacheKey;
import com.hmw.dividend.persist.CompanyRepository;
import com.hmw.dividend.persist.DividendRepository;
import com.hmw.dividend.persist.entity.CompanyEntity;
import com.hmw.dividend.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
    // 조회할 때 캐쉬에 저장
    public ScrapedResult getDividendByCompanyName(String companyName) {
        log.info("serch company -> " + companyName);
        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity companyEntity = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new NoCompanyException());

        // 2. 조회된 회사 ID로 배당금 정보 조회
        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(companyEntity.getId());
        List<Dividend> dividends = dividendEntities.stream()
                                                        .map(e -> new Dividend(e.getDate(), e.getDividend()))
                                                        .collect(Collectors.toList());


        // 3. 결과 조합 후 반환
        return new ScrapedResult(new Company(companyEntity.getTicker(), companyEntity.getName()), dividends);
    }

}
