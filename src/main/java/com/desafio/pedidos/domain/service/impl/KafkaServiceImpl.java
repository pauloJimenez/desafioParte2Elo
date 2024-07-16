package com.desafio.pedidos.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.desafio.pedidos.domain.DTO.MensagemKafkaDTO;
import com.desafio.pedidos.domain.entity.Pedido;
import com.desafio.pedidos.domain.service.KafkaService;
import com.desafio.pedidos.domain.service.PedidosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

	@Autowired
	private PedidosService pedidoService;

	@Override
	@KafkaListener(topics = "${kafka.topic.pedidos}", groupId = "${kafka.groupId.pedidos}")
	public void lerMensagem(String mensagemKafka) {
		System.out.println("Mensagem recebida: " + mensagemKafka);
		Pedido pedidoProcessado = pedidoService.processarPedido(lerNumeroPedidoDaMensagemKafka(mensagemKafka));
		System.out.println("Mensagem processada: " + pedidoProcessado);
	}

	private String lerNumeroPedidoDaMensagemKafka(String mensagemKafka) {		
		MensagemKafkaDTO mensagemKafkaDTO = new MensagemKafkaDTO();
		try {
			mensagemKafkaDTO = new ObjectMapper().readValue(mensagemKafka, MensagemKafkaDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mensagemKafkaDTO.getNumeroPedido();
	}
}
