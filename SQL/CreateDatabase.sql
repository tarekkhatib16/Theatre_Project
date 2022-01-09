/*
Stuart McClymont
VERSION: SM_V2_2_2 : performance DateTime changed to UNIQUE
*/

-- Create Database
DROP DATABASE IF EXISTS FinalProjectTheatre;
CREATE DATABASE FinalProjectTheatre;
USE FinalProjectTheatre;

-- Create a customer user for database
DROP USER IF EXISTS 'customer'@'localhost';
CREATE USER 'customer'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT ON finalprojecttheatre.* TO 'customer'@'localhost'; -- Allow to Search tables
GRANT EXECUTE ON finalprojecttheatre.* TO 'customer'@'localhost'; -- Allow to Run Procedures
-- *? will INSERT be needed to add to bookings? need to test

/* -- Create EventInfo Table
	Table for Details of Events Held at the Theatre
*/
DROP TABLE IF EXISTS EventInfo;
CREATE TABLE EventInfo(
	EventID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(50) NOT NULL, -- Name of Show
	EventType VARCHAR(50) NOT NULL, -- Type of Show (Theatre, Musical, Opera, Concert)
    LiveMusic BOOL NOT NULL, -- indicate if live music is present at event
    PerformerInfo VARCHAR(100), -- fill in details only if live music is present
    Descrip VARCHAR(500) NOT NULL, -- Description of Show
    Lang VARCHAR(50), -- Language of Performance
    EventDurationMM INT NOT NULL, -- Length of Event in Minutes
    PricePenceStall INT NOT NULL, -- Could use DECIMAL(6,2) to list as £, using INT for easier conversion to Java for now
    PricePenceCircle INT NOT NULL, -- Could use DECIMAL(6,2) to list as £, using INT for easier conversion to Java for now
    
    CONSTRAINT chk_EventType CHECK ( EventType IN ('Theatre', 'Musical', 'Opera', 'Concert')) -- Event Type limited to one of four values
);

/* -- Create Performaces Table

*/
DROP TABLE IF EXISTS Performances;
CREATE TABLE Performances(
PerformanceID INT PRIMARY KEY AUTO_INCREMENT,
EventID INT NOT NULL,
PerformanceStart DATETIME NOT NULL UNIQUE, -- Format 'YYYY-MM-DD HH:MM:SS' AS STRING
AvailabilityOfTickets INT NOT NULL,
PerformanceListed DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Auto Generated when Performance listed, for data analytics
FOREIGN KEY(EventID) REFERENCES EventInfo(EventID)
);

/* -- Create Table Purchasers

*/
DROP TABLE IF EXISTS Purchasers;
CREATE TABLE Purchasers(
PurchaserID INT PRIMARY KEY AUTO_INCREMENT,
PurchaserName VARCHAR(100),
DoB DATE, -- Format 'YYYY-MM-DD' AS STRING
AddressHouseNumber VARCHAR(50), -- VARCHAR(50) to cover instances where house has name, flat B, 21A etc
AddressStreet VARCHAR(60), -- 
AddressCity VARCHAR (60), -- To cover exceedingly long place names like Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch (58 Char)
AddressCounty VARCHAR(60), --
AddressPostcode VARCHAR(7), -- Postcodes between 5 and 7 AlphaNumeric, remove spaces before putting into column
CreditCard VARCHAR(19) -- Credit card numbers between 16-19 digits long, remove spaces before putting into column
);

/* -- Create Bookings Table

*/
DROP TABLE IF EXISTS Bookings;
CREATE TABLE Bookings(
BookingID INT PRIMARY KEY AUTO_INCREMENT,
PerformanceID INT NOT NULL,
PurchaserID INT NOT NULL,
Concessionary BOOLEAN NOT NULL, 
SeatNumber INT NOT NULL,
TicketPricePence INT NOT NULL, -- Log price of ticket, in pence, in case ticket price changes after purchase
BookingTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Automatically add current date time to booking for easier lookups and legal
FOREIGN KEY(PerformanceID) REFERENCES Performances(PerformanceID),
FOREIGN KEY(PurchaserID) REFERENCES Purchasers(PurchaserID)
);