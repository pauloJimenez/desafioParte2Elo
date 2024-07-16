package com.desafio.pedidos.domain.service;

import com.desafio.pedidos.domain.entity.Pedido;

public interface PedidosService {
	Pedido processarPedido(String numeroPedido);
}
