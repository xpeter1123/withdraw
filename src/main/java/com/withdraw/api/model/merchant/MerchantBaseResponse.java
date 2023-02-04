package com.withdraw.api.model.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.internal.joptsimple.internal.Strings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MerchantBaseResponse {
    private String errorCode;
    private String errorDesc;
    private String errorData;

    private String content = Strings.EMPTY;
}
