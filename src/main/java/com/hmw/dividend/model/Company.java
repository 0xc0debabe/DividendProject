package com.hmw.dividend.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    private String ticker;
    private String name;

}
