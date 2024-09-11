insert into Produto (id,nome,preco,data_criacao) values (1,"Kindle", 600.0, date_sub(sysdate(),interval 1 day));
insert into Produto (id, nome, preco,data_criacao) values (3, "Câmera GoPro Hero 7", 1400.0,date_sub(sysdate(),interval 1 day));


insert into Cliente (id,nome,cpf) values  (1,'Matheus','0001');
insert into Cliente (id,nome,cpf) values (2,'Fernanda','112');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', date_sub(sysdate(), interval 27 year));
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', date_sub(sysdate(), interval 30 year));

insert into pedido (id, cliente_id, data_pedido, total, status) values (1, 1, sysdate(), 998.0, "AGUARDANDO");
insert into pedido (id, cliente_id, data_pedido, total, status) values (2, 1, sysdate(), 499.0, "AGUARDANDO");

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);


insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 'PROCESSANDO', 'cartao', '123', null);

insert  into categoria(id,nome) values (1,"Eletrodomesticos");
