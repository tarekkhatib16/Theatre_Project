/* Test Population Of tables via INSERT
ADD end / from above INSERT to run, remove to comment out
VERSION: SM_V2_0
*/

/**
INSERT INTO EventInfo(Title,EventType,Descrip,EventDurationMM,PricePence)
VALUES('Manns Bands Stands','Musical','Mr Manns Band and the Hand lead the show with gusto and Glee. Join them for a merry laugh of love, life and lemers. Presented in English.', 120, 2700);
/**/

/**
INSERT INTO Performances(EventID, PerformanceStart, AvailabilityOfTickets)
VALUES(1, '2022-06-01 12:00:00', 200);
/**/

/**
INSERT INTO Purchasers(PurchaserName, DoB, AddressHouseNumber, AddressStreet, AddressCity,AddressCounty,AddressPostcode, CreditCard)
VALUES('Mikey Martel Martinson III', '1978-05-01', 'The Mikey Mansion','Makenbut Road', 'Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch', 'Wasles Somewhere', 'WA345TT','1234123412341234');
/**/

/**
INSERT INTO Bookings(PerformanceID, PurchaserID, Concessionary)
VALUES(1,1,FALSE);
/**/

-- SELECT * FROM EventInfo;
-- SELECT * FROM Performances;
-- SELECT * FROM Purchasers;
-- SELECT * FROM Bookings;