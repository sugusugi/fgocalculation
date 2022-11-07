package com.example.fgocalculation.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ToolNameForm {
    
    @NotEmpty
    private String toolName;
}
