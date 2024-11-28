ALTER TABLE Pagamento
ADD COLUMN produto_id INT,
ADD CONSTRAINT fk_produto
FOREIGN KEY (produto_id) REFERENCES Produto(id);