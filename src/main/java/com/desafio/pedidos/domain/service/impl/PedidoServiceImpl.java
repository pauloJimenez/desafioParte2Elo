package com.desafio.pedidos.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.pedidos.domain.entity.Pedido;
import com.desafio.pedidos.domain.enums.StatusEnum;
import com.desafio.pedidos.domain.repository.PedidoRepository;
import com.desafio.pedidos.domain.service.PedidosService;

@Service
public class PedidoServiceImpl implements PedidosService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido processarPedido(String numeroPedido) {
		Pedido pedidoConsultado = pedidoRepository.findByNumeroPedido(numeroPedido);
		pedidoConsultado.setStatus(StatusEnum.ENVIADO_TRANSPORTADORA);
		return pedidoRepository.save(pedidoConsultado);
	}

}
