CREATE OR ALTER PROCEDURE sp_getCampus
	@param_campusId INT = NULL
AS
	BEGIN
		SET NOCOUNT ON;
		SELECT * FROM tb_Recintos where ID_RECINTO = @param_campusId
		RETURN;
	END

--exec sp_getCampus 1