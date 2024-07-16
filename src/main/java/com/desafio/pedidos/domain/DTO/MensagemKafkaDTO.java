package com.desafio.pedidos.domain.DTO;

import lombok.Data;

@Data
public class MensagemKafkaDTO {
	private String numeroPedido;

	public MensagemKafkaDTO() {
	}
}
