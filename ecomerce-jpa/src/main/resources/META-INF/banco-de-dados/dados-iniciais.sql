insert into Produto (id,nome,preco) values (1,"Kindle", 600.0);
insert into Produto (id, nome, preco) values (3, 'Câmera GoPro Hero 7', 1400.0);


insert into Cliente (id,nome) values  (1,'Matheus');
insert into Cliente (id,nome) values (2,'Fernanda');

insert into pedido ( id ,cliente_id,data_pedido, total, status) values (1, 1, sysdate(),  100.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_pedido, total, status) values (2, 1, sysdate(), 499.0, "AGUARDANDO");

insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 5.0, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);

insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 'PROCESSANDO', 'cartao', '123', null);


insert  into categoria(id,nome) values (1,'Eletronicos');
