package com.lld.splitwise.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lld.splitwise.dtos.RegisterUserRequestDTO;
import com.lld.splitwise.dtos.RegisterUserResponseDTO;
import com.lld.splitwise.dtos.ResponseDTO;
import com.lld.splitwise.dtos.ResponseStatus;
import com.lld.splitwise.dtos.SettleUpResponseDTO;
import com.lld.splitwise.dtos.SettleUpUserRequestDTO;
import com.lld.splitwise.dtos.UserDTO;
import com.lld.splitwise.models.SplitwiseUser;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.services.SplitwiseUserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
    private SplitwiseUserService splitwiseUserService;

    public ResponseDTO<RegisterUserResponseDTO> registerUser(RegisterUserRequestDTO request) {
        SplitwiseUser registerUser = splitwiseUserService.registerUser(request.getName(), request.getPassword(),
                request.getPhoneNumber());

        UserDTO userDTO = new UserDTO();
        userDTO.setName(registerUser.getName());
        userDTO.setPhoneNumber(registerUser.getPhoneNumber());

        ResponseDTO<RegisterUserResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(new RegisterUserResponseDTO());
        responseDTO.getData().setUser(userDTO);
        return responseDTO;
    }

    public ResponseDTO<SettleUpResponseDTO> settleUp(SettleUpUserRequestDTO requestDTO) {
        List<Transaction> transactions = splitwiseUserService.settleUp(requestDTO.getUserId());

        SettleUpResponseDTO responseDTO = new SettleUpResponseDTO();
        responseDTO.setTransactions(transactions);

        ResponseDTO<SettleUpResponseDTO> response = new ResponseDTO<SettleUpResponseDTO>(
                ResponseStatus.SUCCESS, responseDTO);
        return response;
    }
}
