package com.INetwork.readwritefile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDto {
    private String errorMessage;
    private Object errorData;
}
