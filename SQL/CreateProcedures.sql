/*

VERSION: SM_V2_2
*/

/*
Procedure to Browse all shows(events)
Return All Events from EventInfo
*/
USE FinalProjectTheatre;
DELIMITER //

DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShows//
CREATE PROCEDURE GetShows()
	BEGIN
		SELECT EventID, Title
		FROM EventInfo;

	END; //

/*
Procedure to Browse all shows(events)
Return All Events from EventInfo where search Query contained withing Title
*/

DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsSearch//
CREATE PROCEDURE GetShowsSearch(IN SearchQ VARCHAR(50))
	BEGIN
		SELECT EventID, Title
		FROM EventInfo
        WHERE Title LIKE CONCAT('%',SearchQ,'%'); -- find all Shows containg the search query

	END; //


/*
Procedure to Browse all shows(events)
Return All Events from EventInfo where search Query matches date
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsDate//
CREATE PROCEDURE GetShowsDate(IN SearchQ DATE)
	BEGIN
		SELECT EventInfo.EventID, EventInfo.Title
		FROM EventInfo
        LEFT JOIN Performances
        ON EventInfo.EventID = Performances.EventID
        WHERE PerformanceStart LIKE CONCAT(SearchQ,'%'); -- Query must be in format YYYY-MM-DD STRING

	END; //