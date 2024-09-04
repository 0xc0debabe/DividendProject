package com.hmw.dividend.scraper;

import com.hmw.dividend.model.Company;
import com.hmw.dividend.model.ScrapedResult;

public interface Scraper {

    Company scrapCompanyByTicker(String ticker);

    ScrapedResult scrap(Company company);

}
