package com.lld.splitwise.models;

import com.lld.bms.models.User;
import com.lld.splitwise.dtos.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "SPLITWISE_USERS")
public class SplitwiseUser extends BaseModel {
    private String name;
    private String phoneNumber;
    private String hashedPassword;

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(this.name);
        userDTO.setPhoneNumber(this.phoneNumber);
        return toDTO();
    }
}
