--
--
-- `CATEGORIA`
--
--
CREATE TABLE `categoria` (
  `idCategoria` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);
 
--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` bigint(20) NOT NULL AUTO_INCREMENT;



--
--
-- `COMPRADOR`
--
--
CREATE TABLE `comprador` (
  `idComprador` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `comprador`
--
ALTER TABLE `comprador`
  ADD PRIMARY KEY (`idComprador`);
 
--
-- AUTO_INCREMENT for table `comprador`
--
ALTER TABLE `comprador`
  MODIFY `idComprador` bigint(20) NOT NULL AUTO_INCREMENT;


--
--
-- `VENDEDOR`
--
--
CREATE TABLE `vendedor` (
  `idVendedor` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`idVendedor`);
 
--
-- AUTO_INCREMENT for table `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `idVendedor` bigint(20) NOT NULL AUTO_INCREMENT;

--
--
-- `PRODUTO`
--
--
CREATE TABLE `produto` (
  `idProduto` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL, 
  `descricao` varchar(1000) NOT NULL,
  `idCategoria` bigint(20),
  `dataCriacao` datetime NOT NULL,
  `score` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`idProduto`);

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `idProduto` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- FOREIGN_KEY for `categoria`
--
ALTER TABLE `produto` ADD CONSTRAINT `fk_categoria` FOREIGN KEY ( `idCategoria` ) REFERENCES `categoria` ( `idCategoria` );

--
--
-- `VENDA`
--
--
CREATE TABLE `venda` (
  `idVenda` bigint(20) NOT NULL,
  `idVendedor` bigint(20), 
  `idComprador` bigint(20), 
  `dataVenda` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`idVenda`);

--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `idVenda` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- FOREIGN_KEY for `vendendor`, `comprador`
--
ALTER TABLE `venda` ADD CONSTRAINT `fk_vendedor` FOREIGN KEY ( `idVendedor` ) REFERENCES `vendedor` ( `idVendedor` );
ALTER TABLE `venda` ADD CONSTRAINT `fk_comprador` FOREIGN KEY ( `idComprador` ) REFERENCES `comprador` ( `idComprador` );

  
--
--
-- `ITEMVENDA`
--
--
CREATE TABLE `itemVenda` (
  `idItemVenda` bigint(20) NOT NULL,
  `idVenda` bigint(20), 
  `idProduto` bigint(20), 
  `quantidade` int, 
  `avaliacao` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `itemVenda`
--
ALTER TABLE `itemVenda`
  ADD PRIMARY KEY (`idItemVenda`);

--
-- AUTO_INCREMENT for table `itemVenda`
--
ALTER TABLE `itemVenda`
  MODIFY `idItemVenda` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- FOREIGN_KEY for `venda`, `produto`
--
ALTER TABLE `itemVenda` ADD CONSTRAINT `fk_venda` FOREIGN KEY ( `idVenda` ) REFERENCES `venda` ( `idVenda` );
ALTER TABLE `itemVenda` ADD CONSTRAINT `fk_produto` FOREIGN KEY ( `idProduto` ) REFERENCES `produto` ( `idProduto` ) ;


  
--
--
-- `USUARIO`
--
--
CREATE TABLE `usuario` (
  `idUsuario` bigint(20) NOT NULL,
  `login` varchar(255) NOT NULL, 
  `senha` varchar(255) NOT NULL,
  `perfil` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);
 
 
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` bigint(20) NOT NULL AUTO_INCREMENT;
  
INSERT INTO `usuario` (`idUsuario`, `login`,`senha`,`perfil`) VALUES (1, 'admin','$2a$10$NSn0FqMKCikXljNYDHiiFeNAyfsExD2ccIuj6GE62liodPu/Pelgm','ROLE_ADMIN');
INSERT INTO `usuario` (`idUsuario`, `login`,`senha`,`perfil`) VALUES (2, 'usuario_01','$2a$10$NSn0FqMKCikXljNYDHiiFeNAyfsExD2ccIuj6GE62liodPu/Pelgm','ROLE_USUARIO');