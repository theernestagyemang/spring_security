
package com.project.software.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sms {

    private BigDecimal rate;
    private String messageId;
    private String status;
    private String networkId;
}

