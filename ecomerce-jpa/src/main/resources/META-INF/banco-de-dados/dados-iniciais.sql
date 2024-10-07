insert into Produto (id,nome,preco,data_criacao) values (1,"Kindle", 600.0, date_sub(sysdate(),interval 1 day));
insert into Produto (id, nome, preco,data_criacao) values (3, "Câmera GoPro Hero 7", 1400.0,date_sub(sysdate(),interval 1 day));


insert into Cliente (id,nome,cpf) values  (1,'Matheus','0001');
insert into Cliente (id,nome,cpf) values (2,'Fernanda','112');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', date_sub(sysdate(), interval 27 year));
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', date_sub(sysdate(), interval 30 year));

insert into pedido (id, cliente_id, data_pedido, total, status) values (1, 1, sysdate(), 998.0, "AGUARDANDO");
insert into pedido (id, cliente_id, data_pedido, total, status) values (2, 1, sysdate(), 499.0, "AGUARDANDO");
insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 1, date_sub(sysdate(), interval 5 day), 499.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (3, 1, date_sub(sysdate(), interval 4 day), 3500.0, 'PAGO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (4, 2, date_sub(sysdate(), interval 2 day), 499.0, 'PAGO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (6, 2, sysdate(), 799.0, 'AGUARDANDO');


insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (3, 4, 3500, 1);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (4, 1, 499, 1);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (6, 1, 799, 1);

insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 'PROCESSANDO', 'cartao', '123', null);
insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (3, 'RECEBIDO', 'cartao', '0123', null);
insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (4, 'PROCESSANDO', 'cartao', '4567', null);
insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (5, 'RECEBIDO', 'boleto', null, '8910');
insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras,data_vencimento) values (6, 'PROCESSANDO', 'boleto', null,'45666',date_add(sysdate(),interval 2 day);


insert  into categoria(nome) values ('Eletrodomesticos');
insert into categoria (nome) values ('Livros');
insert into categoria (nome) values ('Esportes');
insert into categoria (nome) values ('Futebol');
insert into categoria (nome) values ('Futsal');
insert into categoria (nome) values ('Volei');
insert into categoria (id, nome) values (6, 'Notebooks');
insert into categoria (id, nome) values (7, 'Smartphones');
insert into categoria (id, nome) values (8, 'Câmeras');

insert  into produto_categoria(produto_id, categoria_id) values (1,2)
insert into produto_categoria (produto_id, categoria_id) values (3, 8);
insert into produto_categoria (produto_id, categoria_id) values (4, 8);


insert into nota_fiscal (pedido_id, xml, data_emissao) values (2,'<xml />', sysdate());
