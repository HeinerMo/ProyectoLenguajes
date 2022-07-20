CREATE OR ALTER PROCEDURE sp_studentLogin
	@param_carnet VARCHAR(40), @param_password VARCHAR(30)
AS
	BEGIN
		IF EXISTS(SELECT TOP 1 1 FROM tb_Students WHERE id = @param_carnet)
			BEGIN
				IF EXISTS(SELECT TOP 1 1 FROM tb_Students WHERE PASSWORD = @param_password)
					BEGIN
						SELECT 'success' AS 'status';
					END
				ELSE
					BEGIN
						SELECT 'password' AS 'status';
					END
			END
		ELSE
			BEGIN
				SELECT 'carnet' AS 'status';
			END
	END
GO