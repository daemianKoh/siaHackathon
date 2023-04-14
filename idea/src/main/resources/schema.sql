DROP TABLE IF EXISTS Country; 
DROP TABLE IF EXISTS FlightTravelInfo; 
DROP TABLE IF EXISTS FlightPersonInfo; 
DROP TABLE IF EXISTS FlightBooking;  
 

CREATE TABLE flightBooking (  
bookingRefNo VARCHAR(10) NOT NULL,
PRIMARY KEY (bookingRefNo)
);  

CREATE TABLE flightPersonInfo (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
bookingRefNo VARCHAR(10) NOT NULL,
personName VARCHAR(66) NOT NULL,
email VARCHAR(2000) NOT NULL,
nationality VARCHAR(100) NOT NULL,
visa VARCHAR(2) NOT NULL DEFAULT 'N', 
seat VARCHAR(2) NOT NULL DEFAULT 'N',
baggage VARCHAR(2) NOT NULL DEFAULT 'N',
pcr VARCHAR (2) NOT NULL DEFAULT 'N',
vaccination VARCHAR (2) NOT NULL DEFAULT 'N',
FOREIGN KEY (bookingRefNo) REFERENCES FlightBooking(bookingRefNo) 
);  

CREATE TABLE flightTravelInfo (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
bookingRefNo VARCHAR(10) NOT NULL,
origin VARCHAR(3) NOT NULL,
destination VARCHAR(3) NOT NULL,
departDate Date NOT NULL,
departTime VARCHAR (4) NOT NULL,
arrivalDate Date NOT NULL,
arrivalTime VARCHAR (4) NOT NULL,
FOREIGN KEY (bookingRefNo) REFERENCES FlightBooking(bookingRefNo) 
); 

CREATE TABLE country (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
country VARCHAR (100) NOT NULL,
cityCode VARCHAR (3) NOT NULL,
airport VARCHAR (100) NOT NULL
); 