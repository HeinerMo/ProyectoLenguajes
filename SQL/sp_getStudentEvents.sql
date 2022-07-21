CREATE OR ALTER PROCEDURE sp_getStudentEvents
	@param_studentId varchar(7) = NULL
AS
	BEGIN
		
		SET NOCOUNT ON;

		WITH usedSpots 
			(eventId
			,quantity)
		AS 
			(SELECT
					ID_EVENT
					,COUNT(se.studentId) AS used
				FROM tb_Events AS e
					FULL OUTER JOIN tb_event_student AS se
						ON e.ID_EVENT = se.eventId
				WHERE se.studentId = @param_studentId
				GROUP BY e.ID_EVENT)

		SELECT
			e.ID_EVENT
			,e.NAME_EVENT
			,r.NAME_RECINTO
			,e.TIME_START_EVENT
			,e.TIME_END_EVENT
			,e.TAQUILLA
			,a.ID_ARTIST
			,a.FAMOUS_SONG
			,a.NAME_ARTIST
			,a.PHOTO_ARTIST
			,(e.TAQUILLA - us.quantity) AS AVAIBLE
			
		FROM tb_Events AS e
			INNER JOIN tb_Events_Artists AS ea
				ON e.ID_EVENT = ea.ID_EVENT
					INNER JOIN tb_Artists AS a
						ON a.ID_ARTIST = ea.ID_ARTIST
							INNER JOIN usedSpots AS us
								ON us.eventId = e.ID_EVENT
									INNER JOIN tb_Recintos AS r
										ON r.ID_RECINTO = e.ID_RECINTO
		WHERE e.TIME_END_EVENT >= GETDATE()



		RETURN;
	END

--exec sp_getEvents