/*
Procedures used in data analytics
V1: initial creation
*/
USE FinalProjectTheatre;
DELIMITER //

/*
Procedure to display Events by most sold tickets as Average
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.DA_AvgTicketsEvent//
CREATE PROCEDURE DA_AvgTicketsEvent()
	BEGIN
		SELECT Performances.EventID, EventInfo.Title, AVG(200 - Performances. AvailabilityOfTickets) AS avgSold
        FROM Performances
        LEFT JOIN EventInfo
        ON Performances.EventID = EventInfo.EventID
        GROUP BY EventID
        ORDER BY AvgSold;
    END; //

/*
Procedure to event most sold tickets by performance
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.DA_TicketsPerformance//
CREATE PROCEDURE DA_TicketsPerformance()
	BEGIN
		SELECT Performances.EventID, EventInfo.EventID, EventInfo.Title, Performances.PerformanceStart, 200 - Performances. AvailabilityOfTickets AS Sold
        FROM Performances
		LEFT JOIN EventInfo
        ON Performances.EventID = EventInfo.EventID
        GROUP BY Performances.PerformanceID
		ORDER BY Sold;
    END; //
    
/*
Procedure to shows Performances(event) that sold tickets closest to annoucement of performace
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.DA_TicketSoldQuick//
CREATE PROCEDURE DA_TicketSoldQuick()
	BEGIN
		SELECT Performances.PerformanceID, Performances.PerformanceListed AS DateListed, Bookings.BookingTime AS BookingCreated
        FROM Performances
        LEFT JOIN Bookings
        ON Performances.PerformanceID = Bookings.PerformanceID
        ORDER BY ABS(TIMESTAMPDIFF(SECOND, DateListed, BookingCreated)) DESC;
    END; //

/*
Procedure to show Events type popularity, most sold tickets as average
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.DA_EventTypePopularity//
CREATE PROCEDURE DA_EventTypePopularity()
	BEGIN
    
    
    END; //
    
/*
Procedure to show Bookings demographics, amount of bookings into age, all bookings
COMMENT: Need to have unique purchaserID? need to create and SUM age groups?
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.DA_DemographicAgeAllBooking//
CREATE PROCEDURE DA_DemographicAgeAllBooking()
	BEGIN
		SELECT Purchasers.PurchaserName, TIMESTAMPDIFF(YEAR, Purchasers.DoB, Bookings.BookingTime) AS age
        FROM Purchasers
        LEFT JOIN Bookings
        ON Purchasers.PurchaserID = Bookings.PurchaserID
        ORDER BY @age DESC;
    END; //

/*
Procedure to show Bookings demographics, amount of bookings into age, each event
*/
-- DROP PROCEDURE IF EXISTS finalprojecttheatre.DA_DemographicAgeByEvent//

DELIMITER ;