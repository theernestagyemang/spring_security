
package com.project.software.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbFile implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String fileName;
    private String transactionId;
    private String fileType;
    private byte[] uploadedFile;

    public DbFile(String fileName, String fileType, byte[] uploadedFile) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadedFile = uploadedFile;
    }
}
