--
--
-- `CATEGORIA`
--
--
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);
 
--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;



--
--
-- `COMPRADOR`
--
--
CREATE TABLE `comprador` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `comprador`
--
ALTER TABLE `comprador`
  ADD PRIMARY KEY (`id`);
 
--
-- AUTO_INCREMENT for table `comprador`
--
ALTER TABLE `comprador`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


--
--
-- `VENDEDOR`
--
--
CREATE TABLE `vendedor` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`id`);
 
--
-- AUTO_INCREMENT for table `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
--
-- `PRODUTO`
--
--
CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL, 
  `descricao` varchar(1000) NOT NULL,
  `id_categoria` bigint(20),
  `data_criacao` datetime NOT NULL,
  `score` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- FOREIGN_KEY for `categoria`
--
ALTER TABLE `produto` ADD CONSTRAINT `fk_categoria` FOREIGN KEY ( `id_categoria` ) REFERENCES `categoria` ( `id` );

--
--
-- `VENDA`
--
--
CREATE TABLE `venda` (
  `id` bigint(20) NOT NULL,
  `id_vendedor` bigint(20), 
  `id_comprador` bigint(20), 
  `data_venda` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- FOREIGN_KEY for `vendendor`, `comprador`
--
ALTER TABLE `venda` ADD CONSTRAINT `fk_vendedor` FOREIGN KEY ( `id_vendedor` ) REFERENCES `vendedor` ( `id` );
ALTER TABLE `venda` ADD CONSTRAINT `fk_comprador` FOREIGN KEY ( `id_comprador` ) REFERENCES `comprador` ( `id` );

  
--
--
-- `ITEMVENDA`
--
--
CREATE TABLE `item_venda` (
  `id` bigint(20) NOT NULL,
  `id_venda` bigint(20), 
  `id_produto` bigint(20), 
  `quantidade` int, 
  `avaliacao` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `itemVenda`
--
ALTER TABLE `item_venda`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `itemVenda`
--
ALTER TABLE `item_venda`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- FOREIGN_KEY for `venda`, `produto`
--
ALTER TABLE `item_venda` ADD CONSTRAINT `fk_venda` FOREIGN KEY ( `id_venda` ) REFERENCES `venda` ( `id` );
ALTER TABLE `item_venda` ADD CONSTRAINT `fk_produto` FOREIGN KEY ( `id_produto` ) REFERENCES `produto` ( `id` ) ;


  
--
--
-- `USUARIO`
--
--
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) NOT NULL, 
  `senha` varchar(255) NOT NULL,
  `perfil` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);
 
 
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
  
INSERT INTO `usuario` (`id`, `login`,`senha`,`perfil`) VALUES (1, 'admin','$2a$10$NSn0FqMKCikXljNYDHiiFeNAyfsExD2ccIuj6GE62liodPu/Pelgm','ROLE_ADMIN');
INSERT INTO `usuario` (`id`, `login`,`senha`,`perfil`) VALUES (2, 'usuario_01','$2a$10$NSn0FqMKCikXljNYDHiiFeNAyfsExD2ccIuj6GE62liodPu/Pelgm','ROLE_USUARIO');