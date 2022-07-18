CREATE OR ALTER PROCEDURE sp_checkName
	@param_username VARCHAR(40) = NULL 
AS
	BEGIN 
		IF EXISTS (SELECT TOP 1 1 FROM tb_Students WHERE NAME_STUDENT = @param_username)
			BEGIN 
				SELECT 1 AS 'status';
			END 
		ELSE 
			BEGIN 
				SELECT -1 AS 'status';
			END 
	END