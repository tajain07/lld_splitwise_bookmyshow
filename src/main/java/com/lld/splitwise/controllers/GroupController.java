package com.lld.splitwise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lld.splitwise.dtos.ResponseDTO;
import com.lld.splitwise.dtos.ResponseStatus;
import com.lld.splitwise.dtos.SettleUpGroupRequestDTO;
import com.lld.splitwise.dtos.SettleUpResponseDTO;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.services.GroupService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GroupController {

    private GroupService groupService;

    /**
     * Returns the list of transactions when made will settle up every memeber in
     * the group with every other member of the group for all the expenses that were
     * made in the group
     * 
     * Sample Output:
     * 1 -> 2 : 300
     * 1 -> 4 : 500
     * 4 -> 3 : 21
     * 
     * @param request
     * @return
     */
    public ResponseDTO<SettleUpResponseDTO> settleUp(SettleUpGroupRequestDTO request) {
        List<Transaction> transactions = groupService.settleUp(request.getGroupId());

        SettleUpResponseDTO responseDTO = new SettleUpResponseDTO();
        responseDTO.setTransactions(transactions);

        ResponseDTO<SettleUpResponseDTO> response = new ResponseDTO<SettleUpResponseDTO>(
                ResponseStatus.SUCCESS, responseDTO);
        return response;

    }
}
