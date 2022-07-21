CREATE OR ALTER PROCEDURE sp_checkCarnet
	@param_carnet VARCHAR(7) = NULL 
AS
	BEGIN 
		IF EXISTS (SELECT TOP 1 1 FROM tb_Students WHERE id = @param_carnet)
			BEGIN 
				SELECT 1 AS 'status';
			END 
		ELSE 
			BEGIN 
				SELECT -1 AS 'status';
			END 
	END