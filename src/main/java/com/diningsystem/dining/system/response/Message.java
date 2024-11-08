package com.diningsystem.dining.system.response;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {
    private Integer code;
    private String status;
    private String message;
    private T data;
}
