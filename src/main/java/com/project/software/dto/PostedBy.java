
package com.project.software.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostedBy {

    private String companyName;
    private String email;
    private String requesterContact;
    private String requesterName;
    private String telephone;

}
