/* Test Population Of tables via INSERT
ADD end / from above INSERT to run, remove to comment out
VERSION: SM_V2_4 : added more dummy data for events and performaces
*/

/*
Performances Dummy Data
*/

INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('Manns Bands Stands','Musical', TRUE, 'Mr Manns Band and the Hand', 'Lead the show with gusto and Glee. Join them for a merry laugh of love, life and lemers.' ,' English.', 120, 2700,2700);
-- Theatre
INSERT INTO EventInfo(Title,EventType,LiveMusic,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('TA','Theatre',FALSE,'TA Description','French',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('TB','Theatre',FALSE,'TB Description','English',120,1500,1500);
INSERT INTO EventInfo(Title,EventType,LiveMusic,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('TC','Theatre',FALSE,'TC Description','Spainish',120,1500,1500);
INSERT INTO EventInfo(Title,EventType,LiveMusic,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('TD','Theatre',FALSE,'TD Description','English',120,1800,1800);
-- musical
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('MA','Musical',TRUE,'MA Band','TMA Description','French',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('MB','Musical',TRUE,'MB Band','MB Description','English',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('MC','Musical',FALSE,'MC Description','Latin',120,1200,1200);
-- opera
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('OA','Opera',TRUE,'OA Singers','OA Description','French',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('OB','Opera',TRUE,'OB Singers','OA Description','Hebrew',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,Descrip,Lang,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('OC','Opera',FALSE,'OA Description','English',120,1200,1200);
-- concert
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('CA','Concert',TRUE,'CA Band','CA Description',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('CB','Concert',TRUE,'CB Band','CB Description',120,1200,1200);
INSERT INTO EventInfo(Title,EventType,LiveMusic,PerformerInfo,Descrip,EventDurationMM,PricePenceStall,PricePenceCircle)
VALUES('CC','Concert',TRUE,'CC Band','CC Description',120,1200,1200);


INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(1, '2022-06-01 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(1, '2022-06-01 18:00:00', 120, 80);
--
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(2, '2022-06-02 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(2, '2022-06-02 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(2, '2022-06-03 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(3, '2022-06-03 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(4, '2022-06-04 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(5, '2022-06-04 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(6, '2022-06-05 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(6, '2022-06-05 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(7, '2022-06-06 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(7, '2022-06-06 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(7, '2022-06-07 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(8, '2022-06-07 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(9, '2022-06-08 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(10, '2022-06-08 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(10, '2022-06-09 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(11, '2022-06-09 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(11, '2022-06-10 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(11, '2022-06-10 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(12, '2022-06-11 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(13, '2022-06-11 18:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(13, '2022-06-12 12:00:00', 120, 80);
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets)
VALUES(14, '2022-06-12 18:00:00', 120, 80);


-- SELECT * FROM EventInfo;
-- SELECT * FROM Performances;
-- SELECT * FROM Purchasers;
-- SELECT * FROM Bookings;