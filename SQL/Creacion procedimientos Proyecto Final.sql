USE IF4101_Proyecto_B87581_B85042

--Procedimiento almacenado que se encarga de reistrar eventos y artistas
CREATE OR ALTER PROCEDURE sp_AddEvent
@param_Nombre VARCHAR(40) = NULL 
,@param_Recinto VARCHAR(25) = NULL 
,@param_Time_Start_Event DATETIME = NULL
,@param_Time_End_Event DATETIME = NULL
,@param_Taquilla INT = NULL
,@param_CancionFamosa VARCHAR(50) = NULL 
,@param_NombreArtista VARCHAR(40) = NULL 
,@param_Imagen VARCHAR(80) = NULL 
AS
BEGIN
	DECLARE @error INT

	IF(@param_Time_End_Event>@param_Time_Start_Event)
		BEGIN
			SET @error=0
			DECLARE @recinto INT

			SET @recinto = (SELECT ID_RECINTO FROM tb_Recintos WHERE NAME_RECINTO=@param_Recinto)

			INSERT INTO tb_Events
				(NAME_EVENT,
				ID_RECINTO,
				TIME_START_EVENT,
				TIME_END_EVENT,
				TAQUILLA)
			VALUES(@param_Nombre,@recinto,@param_Time_Start_Event ,@param_Time_End_Event,@param_Taquilla);
	
			INSERT INTO tb_Artists
				(FAMOUS_SONG,
				NAME_ARTIST,
				PHOTO_ARTIST)
			VALUES(@param_CancionFamosa,@param_NombreArtista,@param_Imagen);

			DECLARE @idEvent INT
			DECLARE @idArtist INT
			SET @idEvent =(SELECT MAX(ID_EVENT) FROM tb_Events)

			SET @idArtist =(SELECT MAX(ID_ARTIST) FROM tb_Artists)

			INSERT INTO tb_Events_Artists
				(ID_EVENT,
				ID_ARTIST)
			VALUES(@idEvent,@idArtist)
		END
	ELSE 
		BEGIN
			SET @error=1
		END
	SELECT @error AS ERROR 
END

--Procedimiento almacenado que se encarga de listar los recintos
CREATE OR ALTER PROCEDURE sp_ListarRecintos
AS
BEGIN

	SELECT 
		NAME_RECINTO 
	FROM tb_Recintos

END

--Procedimiento almacenado que se encarga de listar los evevntos
CREATE OR ALTER PROCEDURE sp_ListarEventos
AS
BEGIN
	SELECT 
        EV.ID_EVENT
        ,EV.NAME_EVENT
        ,R.NAME_RECINTO
        ,EV.TIME_START_EVENT
        ,EV.TIME_END_EVENT
        ,EV.TAQUILLA
        ,A.ID_ARTIST
        ,A.NAME_ARTIST
        ,A.FAMOUS_SONG
        ,A.PHOTO_ARTIST 
        ,IIF (EV.TIME_END_EVENT < GETDATE(), 'Vencido', 'Disponible') AS FINISHED
    FROM tb_Events AS EV
        LEFT JOIN tb_Events_Artists AS EA
            ON EV.ID_EVENT=EA.ID_EVENT
                LEFT JOIN tb_Artists AS A
                    ON EA.ID_ARTIST=A.ID_ARTIST
                        JOIN tb_Recintos AS R
                            ON EV.ID_RECINTO=R.ID_RECINTO
END

--Procedimiento almacenado que se encarga de buscar los eventos mediante diferentes filtros
CREATE OR ALTER PROCEDURE sp_BuscarEventos
@param_Nombre VARCHAR(25) = NULL 
,@param_NombreRecinto VARCHAR(25)= NULL
,@param_NombreArtista VARCHAR(25)= NULL
AS
BEGIN
		DECLARE @local_query VARCHAR(MAX) = 'SELECT * FROM tb_Events AS EV LEFT JOIN tb_Events_Artists AS EA
																				ON EV.ID_EVENT=EA.ID_EVENT
																				LEFT JOIN tb_Artists AS A
																				ON EA.ID_ARTIST=A.ID_ARTIST
																				JOIN tb_Recintos AS R
																				ON EV.ID_RECINTO=R.ID_RECINTO' 
		DECLARE @local_where VARCHAR(MAX) = ' WHERE'
		DECLARE @local_hasWhere BIT = 0

		--dates
		IF (@param_NombreArtista IS NOT NULL AND @param_NombreArtista NOT LIKE '')
			BEGIN 
				SET @local_where = @local_where + ' (A.NAME_ARTIST LIKE ''' 
				+ CAST(@param_NombreArtista AS VARCHAR) + ''')'
				SET @local_hasWhere = 1
			END
		IF (@param_NombreRecinto IS NOT NULL AND @param_NombreRecinto NOT LIKE '')
			BEGIN
				IF (@local_hasWhere = 1)
					BEGIN
						SET @local_where = @local_where + ' AND (R.NAME_RECINTO LIKE ''' + CAST(@param_NombreRecinto AS VARCHAR) + ''')'
					END
				ELSE
					BEGIN
						SET @local_where = @local_where + ' (R.NAME_RECINTO LIKE ''' + CAST(@param_NombreRecinto AS VARCHAR) + ''')'
					END
				SET @local_hasWhere = 1
			END
			IF (@param_Nombre IS NOT NULL AND @param_Nombre NOT LIKE '')
			BEGIN
				IF (@local_hasWhere = 1)
					BEGIN
						SET @local_where = @local_where + ' AND (EV.NAME_EVENT	LIKE ''' + CAST(@param_Nombre AS VARCHAR) + ''')'
					END
				ELSE
					BEGIN
						SET @local_where = @local_where + ' (EV.NAME_EVENT LIKE ''' + CAST(@param_Nombre AS VARCHAR) + ''')'
					END
				SET @local_hasWhere = 1
			END

		IF (@local_hasWhere = 1)
			BEGIN
				SET @local_query = @local_query + ' ' + @local_where
			END
			PRINT @local_query
		EXEC(@local_query)
END

--Procedimiento almacenado que se encarga de buscar eventos
CREATE OR ALTER PROCEDURE sp_BuscarEvento
@param_Id INT = NULL 
AS
BEGIN

	SELECT * FROM tb_Events AS EV
	LEFT JOIN tb_Events_Artists AS EA
	ON EV.ID_EVENT=EA.ID_EVENT
	LEFT JOIN tb_Artists AS A
	ON EA.ID_ARTIST=A.ID_ARTIST
	JOIN tb_Recintos AS R
	ON EV.ID_RECINTO=R.ID_RECINTO
	WHERE EV.ID_EVENT LIKE @param_Id

END

--Procedimiento almacenado que se encarga de actualizar los eventos
CREATE OR ALTER PROCEDURE sp_ActualizarEvento
@param_Id INT = NULL 
,@param_Nombre VARCHAR(40) = NULL 
,@param_Recinto VARCHAR(25) = NULL 
,@param_Time_Start_Event DATETIME = NULL
,@param_Time_End_Event DATETIME = NULL
,@param_Taquilla INT = NULL
,@param_CancionFamosa VARCHAR(50) = NULL 
,@param_NombreArtista VARCHAR(40) = NULL 
,@param_Imagen VARCHAR(80) = NULL AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION

			UPDATE tb_Events 
				SET NAME_EVENT=@param_Nombre 
			where ID_EVENT=@param_Id

			UPDATE tb_Events 
				SET ID_RECINTO=(SELECT ID_RECINTO FROM tb_Recintos WHERE NAME_RECINTO=@param_Recinto)
			where ID_EVENT=@param_Id

			IF(@param_Time_End_Event>@param_Time_Start_Event)
			BEGIN
				UPDATE tb_Events 
					SET TIME_START_EVENT=@param_Time_Start_Event 
				where ID_EVENT=@param_Id

				UPDATE tb_Events 
					SET TIME_END_EVENT=@param_Time_End_Event 
				where ID_EVENT=@param_Id
			END

			IF(@param_Taquilla> (SELECT TAQUILLA FROM tb_Events WHERE ID_EVENT=@param_Id))
			BEGIN
				UPDATE tb_Events 
					SET TAQUILLA = TAQUILLA + (@param_Taquilla-TAQUILLA) 
				where ID_EVENT=@param_Id
			END

			UPDATE tb_Artists 
				SET NAME_ARTIST=@param_NombreArtista
			where ID_ARTIST=@param_Id

			UPDATE tb_Artists 
				SET FAMOUS_SONG=@param_CancionFamosa
			where ID_ARTIST=@param_Id

			IF (@param_Imagen NOT LIKE '0')
			BEGIN 
			UPDATE tb_Artists 
				SET PHOTO_ARTIST=@param_Imagen
			where ID_ARTIST=@param_Id
			END

		COMMIT
	END TRY
	BEGIN CATCH
		PRINT @@ERROR	
		ROLLBACK
	END CATCH
END

--Procedimiento almacenado que se encarga de verificar los datos para acceder a la página
CREATE OR ALTER PROCEDURE sp_Login
@param_Name VARCHAR(40) = NULL 
,@param_Password VARCHAR(30) = NULL 
AS
BEGIN
	DECLARE @local INT 
		IF (@param_Name LIKE (SELECT NAME_ADMIN FROM [tb_Administradores]) AND @param_Password LIKE (SELECT PASSWORD_ADMIN FROM [tb_Administradores]))
		BEGIN 
			SET @local=1
		END
		ELSE
		BEGIN
			SET @local=0
		END
	SELECT @local AS LOGIN
END

CREATE OR ALTER PROCEDURE sp_ListarComentarios
AS
BEGIN
	SELECT 
		student_name, 
		NAME_ARTIST, 
		COMMENT 
	FROM tb_Comment_Artist_Student AS TCAS
		LEFT JOIN tb_Artists AS TC
			ON TCAS.ID_ARTIST=TC.ID_ARTIST
				JOIN tb_Students AS TS
					ON TCAS.ID_STUDENT=TS.id
END

CREATE OR ALTER PROCEDURE sp_EliminarEvento
@param_Id INT = NULL 
AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION
			DELETE FROM tb_Events_Artists WHERE ID_EVENT= @param_Id
			DELETE FROM tb_Events WHERE ID_EVENT= @param_Id
			DELETE FROM tb_Comment_Artist_Student WHERE ID_ARTIST= @param_Id
			DELETE FROM tb_Artists WHERE ID_ARTIST= @param_Id
			DELETE FROM tb_event_student WHERE eventId=@param_Id

		COMMIT
	END TRY
	BEGIN CATCH
		PRINT @@ERROR	
		ROLLBACK
	END CATCH
END
