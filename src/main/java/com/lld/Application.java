package com.lld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lld.splitwise.controllers.GroupController;
import com.lld.splitwise.dtos.ResponseDTO;
import com.lld.splitwise.dtos.SettleUpGroupRequestDTO;
import com.lld.splitwise.dtos.SettleUpResponseDTO;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private GroupController groupController;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SettleUpGroupRequestDTO requestDTO = new SettleUpGroupRequestDTO();
		requestDTO.setGroupId(1L);
		ResponseDTO<SettleUpResponseDTO> settleUp = groupController.settleUp(requestDTO);

	}

}
