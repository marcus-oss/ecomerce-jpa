insert into produto (id,versao, nome, preco, data_criacao, ativo) values (1,0, 'Kindle', 799.0, date_sub(sysdate(), interval 1 day), 'SIM');
insert into produto (id,versao, nome, preco, data_criacao, ativo) values (3,0, 'Câmera GoPro Hero 7', 1500.0, date_sub(sysdate(), interval 1 day), 'SIM');
insert into produto (id,versao, nome, preco, data_criacao, ativo) values (4,0, 'Câmera Canon 80D', 3500.0, sysdate(), 'SIM');
insert into produto (id,versao, nome, preco, data_criacao, ativo) values (5,0, 'Microfone de Lapela', 50.0, sysdate(), 'NAO');


insert into Cliente (id,nome,cpf) values  (1,'Matheus','0001');
insert into Cliente (id,nome,cpf) values (2,'Fernanda','112');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', date_sub(sysdate(), interval 27 year));
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', date_sub(sysdate(), interval 30 year));

insert into pedido (id,versao, cliente_id, data_pedido, total, status) values (1,0, 1, sysdate(), 998.0, "AGUARDANDO");
insert into pedido (id,versao, cliente_id, data_pedido, total, status) values (2,0, 1, sysdate(), 499.0, "AGUARDANDO");
insert into pedido (id,versao, cliente_id, data_criacao, total, status) values (2,0, 1, date_sub(sysdate(), interval 5 day), 499.0, 'AGUARDANDO');
insert into pedido (id,versao, cliente_id, data_criacao, total, status) values (3,0, 1, date_sub(sysdate(), interval 4 day), 3500.0, 'PAGO');
insert into pedido (id,versao, cliente_id, data_criacao, total, status) values (4,0, 2, date_sub(sysdate(), interval 2 day), 499.0, 'PAGO');
insert into pedido (id,versao, cliente_id, data_criacao, total, status) values (6, 0,2, sysdate(), 799.0, 'AGUARDANDO');

insert into item_pedido (pedido_id,versao, produto_id, preco_produto, quantidade) values (1, 1,0, 499, 2);
insert into item_pedido (pedido_id,versao, produto_id, preco_produto, quantidade) values (2, 1,0,499, 1);
insert into item_pedido (pedido_id,versao, produto_id, preco_produto, quantidade) values (2, 1,0,499, 1);
insert into item_pedido (pedido_id,versao, produto_id, preco_produto, quantidade) values (3, 4,0,3500, 1);
insert into item_pedido (pedido_id,versao, produto_id, preco_produto, quantidade) values (4, 1,0,499, 1);
insert into item_pedido (pedido_id,versao, produto_id, preco_produto, quantidade) values (6, 1,0,799, 1);


insert into pagamento (pedido_id,versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (1,0, 'PROCESSANDO', 'cartao', '123', null);
insert into pagamento (pedido_id,versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (2,0, 'RECEBIDO', 'cartao', '0123', null);
insert into pagamento (pedido_id,versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (3,0, 'PROCESSANDO', 'cartao', '4567', null);
insert into pagamento (pedido_id,versao, status, tipo_pagamento, numero_cartao, codigo_barras,data_vencim0,ento) values (4,0,'RECEBIDO', 'boleto', null,'8910',date_sub(sysdate(),interval 2 day);
insert into pagamento (pedido_id,versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (5,0 ,'PROCESSANDO', 'cartao', '1112', null);
insert into pagamento (pedido_id,versao, status, tipo_pagamento, numero_cartao, codigo_barras,data_vencimento) values (6,0, 'PROCESSANDO', 'boleto', null,'45666',date_add(sysdate(),interval 2 day);


insert into categoria (id, versao, nome) values (1, 0, 'Eletrodomésticos');
insert into categoria (id, versao, nome) values (2, 0, 'Livros');
insert into categoria (id, versao, nome) values (3, 0, 'Esportes');
insert into categoria (id, versao, nome) values (4, 0, 'Futebol');
insert into categoria (id, versao, nome) values (5, 0, 'Natação');
insert into categoria (id, versao, nome) values (6, 0, 'Notebooks');
insert into categoria (id, versao, nome) values (7, 0, 'Smartphones');
insert into categoria (id, versao, nome) values (8, 0, 'Câmeras');

insert  into produto_categoria(produto_id, categoria_id) values (1,2)
insert into produto_categoria (produto_id, categoria_id) values (3, 8);
insert into produto_categoria (produto_id, categoria_id) values (4, 8);

insert into produto_loja (id, nome, preco, data_criacao, descricao) values (101, 'Kindle', 799.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto_loja (id, nome, preco, data_criacao, descricao) values (103, 'Câmera GoPro Hero 7', 1500.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');
insert into produto_loja (id, nome, preco, data_criacao, descricao) values (104, 'Câmera Canon 80D', 3500.0, sysdate(), 'O melhor ajuste de foco.');
insert into produto_loja (id, nome, preco, data_criacao, descricao) values (105, 'Microfone de Lapela', 50.0, sysdate(), 'Produto massa');

insert into ecm_produto (prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (201, 'Kindle', 799.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into ecm_produto (prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (203, 'Câmera GoPro Hero 7', 1500.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');
insert into ecm_produto (prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (204, 'Câmera Canon 80D', 3500.0, sysdate(), 'O melhor ajuste de foco.');
insert into ecm_produto (prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (205, 'Microfone de Lapela', 50.0, sysdate(), 'Produto massa');

insert into erp_produto (id, nome, preco, descricao) values (301, 'Kindle', 799.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into erp_produto (id, nome, preco, descricao) values (303, 'Câmera GoPro Hero 7', 1500.0, 'Desempenho 2x melhor.');
insert into erp_produto (id, nome, preco, descricao) values (304, 'Câmera Canon 80D', 3500.0, 'O melhor ajuste de foco.');
insert into erp_produto (id, nome, preco, descricao) values (305, 'Microfone de Lapela', 50.0, 'Produto massa');


insert into nota_fiscal (pedido_id, xml, data_emissao) values (2,'<xml />', sysdate());
