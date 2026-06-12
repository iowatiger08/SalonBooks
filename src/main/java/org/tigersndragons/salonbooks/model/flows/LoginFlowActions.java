package org.tigersndragons.salonbooks.model.flows;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginFlowActions implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
