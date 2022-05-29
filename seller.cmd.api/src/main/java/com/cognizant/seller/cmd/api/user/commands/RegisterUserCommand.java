package com.cognizant.seller.cmd.api.user.commands;

import com.cognizant.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class RegisterUserCommand implements Serializable {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;

    public RegisterUserCommand(){

    }
}
