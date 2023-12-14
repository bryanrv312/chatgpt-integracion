package com.chatgpt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
	
	//inyectamos la dependencia
	private final ChatgptService chatgptService;
	
	@GetMapping("/send")
	public String enviar(@RequestParam String message) {
		System.err.println("Mensaje = " + message);
		String responseMessage = chatgptService.sendMessage(message);
		return responseMessage;
	}

	@GetMapping("/chat")
	public String chatWith(@RequestParam String message) {
		System.err.println("Mensaje = " + message);
		return chatgptService.sendMessage(message);

	}
	
	//prompts: forma inteligente de preguntar a una IA
	@GetMapping("/prompt")
	public ChatResponse prompt(@RequestParam String message) {
		
		//parametros para ajustar la respuesta
		Integer maxTokens = 300;
		String model = "text-davinci-003";
		Double temperature = 0.5;
		Double topP = 1.0;
		
		ChatRequest chatRequest = new ChatRequest(model, message, maxTokens, temperature, topP);
		ChatResponse res = chatgptService.sendChatRequest(chatRequest);
		System.err.println("Response was; " + res.toString());
		
		return res;
		
		
	}
}
