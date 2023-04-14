--INSERT FlightBooking
INSERT INTO FlightBooking (bookingRefNo) VALUES ('TRABC1');
INSERT INTO FlightBooking (bookingRefNo) VALUES ('TRABC2');

--INSERT FlightPersonInfo tan ah kong
INSERT INTO FlightPersonInfo (bookingRefNo, personName, email, nationality) 
VALUES ('TRABC1', 'Tan Ah Kong', 'tanAh@hotmail.com', 'Singaporean');

--INSERT FlightPersonInfo ah hua family
INSERT INTO FlightPersonInfo (bookingRefNo, personName, email, nationality) 
VALUES ('TRABC2', 'marcus', 'marcus@hotmail.com', 'Singaporean');
INSERT INTO FlightPersonInfo (bookingRefNo, personName, email, nationality) 
VALUES ('TRABC2', 'leonard', 'leonard@hotmail.com', 'Singaporean');
INSERT INTO FlightPersonInfo (bookingRefNo, personName, email, nationality) 
VALUES ('TRABC2', 'daemian', 'daemian@hotmail.com', 'Singaporean');
INSERT INTO FlightPersonInfo (bookingRefNo, personName, email, nationality) 
VALUES ('TRABC2', 'sudha', 'sudha@hotmail.com', 'Singaporean');

--INSERT FlightTravelInfo tan ah kong
INSERT INTO FlightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC1', 'SIN', 'ADL', parseDateTime('10-05-2023', 'dd-MM-yyyy'), '2300', parseDateTime('11-05-2023', 'dd-MM-yyyy'), '0700');
INSERT INTO FlightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC1', 'ADL', 'SIN', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1000', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1800');

INSERT INTO FlightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC2', 'SIN', 'TPE', parseDateTime('11-05-2023', 'dd-MM-yyyy'), '2300', parseDateTime('11-05-2023', 'dd-MM-yyyy'), '0400');
INSERT INTO FlightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC2', 'TPE', 'SIN', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1000', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1400');


--INSERT Country
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','ADL','Adelaide');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','AYQ','Ayers Rock/Uluru');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','BNE','Brisbane');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','CBR','Canberra');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','CNS','Cairns');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','HBA','Hobart');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','LST','Launceston');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','MEL','Melbourne');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','OOL','Gold Coast');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','PER','Perth');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Australia','SYD','Sydney');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Japan','CTS','Sapporo');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Japan','KIX','Osaka');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Japan','NRT','Tokyo');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Taiwan','KHH','Kaohsiung');
INSERT INTO COUNTRY (country, cityCode, airport) VALUES ('Taiwan','TPE','Taipei');