DROP DATABASE IF EXISTS `Medical Center`;
CREATE DATABASE IF Not EXISTS MedicalCenter;
SHOW DATABASES;
USE MedicalCenter;

DROP  TABLE IF EXISTS Doctor;
CREATE TABLE IF NOT EXISTS Doctor(
 D_Id VARCHAR (6),
 D_Name VARCHAR (40) NOT NULL DEFAULT 'Unknown',
 D_Address VARCHAR (50),
 D_Speciality VARCHAR (30),
 D_PhoneNo VARCHAR (15),
 D_MBBSNo VARCHAR (15),
 D_Charge DECIMAL(10,2) ,
  CONSTRAINT PRIMARY KEY (D_Id)
);
    SHOW TABLES;
    DESCRIBE Doctor;


DROP  TABLE IF EXISTS `Session`;
CREATE TABLE IF NOT EXISTS `Session`(
 Session_Id VARCHAR (6),
 D_Id VARCHAR (6),
 MaxNoOfPatients int ,
 CurrentAppointmentNo int,
 Session_Date DATE,
 Session_StartTime TIME,
 Session_EndTime TIME,

  CONSTRAINT PRIMARY KEY (Session_Id),
  CONSTRAINT FOREIGN KEY (D_Id) REFERENCES Doctor(D_Id) ON DELETE CASCADE ON UPDATE CASCADE

);
    SHOW TABLES;
    DESCRIBE `Session`;

DROP TABLE IF EXISTS Patient;
CREATE TABLE IF NOT EXISTS Patient(
    P_Id VARCHAR (6),
    P_Name VARCHAR (50) NOT NULL DEFAULT 'Unknown',
    P_Nic VARCHAR(15),
    P_Dob DATE ,
    P_Gender VARCHAR(10),
    P_Address VARCHAR (50),
    P_PhoneNo VARCHAR (15),
        CONSTRAINT PRIMARY KEY (P_Id)

    );
    SHOW TABLES;
    DESCRIBE Patient;




DROP  TABLE IF EXISTS Appointment;
CREATE TABLE IF NOT EXISTS Appointment(
 App_Id VARCHAR (6),
 P_Id VARCHAR (6),
 Session_Id VARCHAR (6),
 App_Number VARCHAR (6),
 App_Fee DECIMAL(10,2),
  CONSTRAINT PRIMARY KEY (App_Id),
  CONSTRAINT FOREIGN KEY (Session_Id) REFERENCES `Session`(Session_Id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (P_Id) REFERENCES Patient(P_Id) ON DELETE CASCADE ON UPDATE CASCADE
);
    SHOW TABLES;
    DESCRIBE Appointment;



DROP TABLE IF EXISTS Drug;
CREATE TABLE IF NOT EXISTS Drug(
    Drug_Id VARCHAR (6),
    Drug_Name VARCHAR (40),
    Drug_TradeName VARCHAR (40),
        CONSTRAINT PRIMARY KEY (Drug_Id)
);
    SHOW TABLES ;
    DESCRIBE Drug;



DROP TABLE IF EXISTS `Supplier`;
CREATE TABLE IF NOT EXISTS Supplier(
   Supplier_Id VARCHAR(6),
   Supplier_Name VARCHAR(50),
        CONSTRAINT PRIMARY KEY (Supplier_Id)

);

     SHOW TABLES ;
     DESCRIBE Supplier;

DROP TABLE IF EXISTS `Stock`;
CREATE TABLE IF NOT EXISTS Stock(
    Stock_Id VARCHAR (6),
    Supply_Date DATE,
        CONSTRAINT PRIMARY KEY (Stock_Id)

);
    SHOW TABLES ;
    DESCRIBE Stock;


DROP TABLE IF EXISTS `DrugHasStock`;
CREATE TABLE IF NOT EXISTS DrugHasStock(
    DrugHasStock_Id VARCHAR(6),
    Drug_Id VARCHAR (6),
    Stock_Id VARCHAR (6),
    PharmacyBill_Id VARCHAR (6),
    cost DECIMAL(10,2),
    SellPrice DECIMAL(10,2),
        CONSTRAINT PRIMARY KEY (DrugHasStock_Id),
        CONSTRAINT FOREIGN KEY (Drug_Id) REFERENCES Drug(Drug_Id) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT FOREIGN KEY (Stock_Id) REFERENCES Stock(Stock_Id) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT FOREIGN KEY (PharmacyBill_Id) REFERENCES PharmacyBill(PharmacyBill_Id) ON DELETE CASCADE ON UPDATE CASCADE

);
    SHOW TABLES ;
    DESCRIBE DrugHasStock;

DROP TABLE IF EXISTS `PharmacyBill`;
CREATE TABLE IF NOT EXISTS PharmacyBill(
    PharmacyBill_Id VARCHAR (6),
    Qty int,
        CONSTRAINT PRIMARY Key(PharmacyBill_Id)

);

        SHOW TABLES ;
        DESCRIBE PharmacyBill;


DROP TABLE IF EXISTS Test;
CREATE TABLE IF NOT EXISTS Test(
    Test_Id VARCHAR (6),
    TestCategoryId VARCHAR (6),
    Test_Type VARCHAR (50),
    Test_Fee DECIMAL(10,2),
    Test_Duration int,
        CONSTRAINT PRIMARY KEY (Test_Id),
        CONSTRAINT FOREIGN KEY (TestCategoryId) REFERENCES TestCategory(TestCategoryId) ON DELETE CASCADE ON UPDATE CASCADE

);
     SHOW TABLES ;
     DESCRIBE Test;

DROP TABLE IF EXISTS TestCategory;
CREATE TABLE IF NOT EXISTS TestCategory(
    TestCategoryId VARCHAR (6),
    TestCategory VARCHAR (50),
        CONSTRAINT PRIMARY KEY (TestCategoryId)

);
     SHOW TABLES ;
     DESCRIBE TestCategory;


DROP TABLE IF EXISTS LabReport;
CREATE TABLE IF NOT EXISTS LabReport(
    LabReport_Id VARCHAR(6),
    Tested_Day DATETIME,
    Amount DECIMAL(10,2),
        CONSTRAINT PRIMARY KEY (LabReport_Id)

);
     SHOW TABLES ;
     DESCRIBE LabReport;



--DROP TABLE IF EXISTS Payment;
--CREATE TABLE IF NOT EXISTS Payment(
--    Payment_Id VARCHAR (6),
--    PharmacyBill_Id VARCHAR (6),
--    App_Id VARCHAR (6),
--    LabReport_Id VARCHAR (6),
--    Payment_Amount DECIMAL(10,2),
--        CONSTRAINT PRIMARY Key(Payment_Id),
--        CONSTRAINT FOREIGN KEY (PharmacyBill_Id) REFERENCES PharmacyBill(PharmacyBill_Id) ON DELETE CASCADE ON UPDATE CASCADE,
--        CONSTRAINT FOREIGN KEY (LabReport_Id) REFERENCES LabReport(LabReport_Id) ON DELETE CASCADE ON UPDATE CASCADE,
--        CONSTRAINT FOREIGN KEY (App_Id) REFERENCES Appointment(App_Id) ON DELETE CASCADE ON UPDATE CASCADE
--
--);
--
--        SHOW TABLES ;
--        DESCRIBE Payment;

DROP TABLE IF EXISTS Login;
CREATE TABLE IF NOT EXISTS Login(
    Log_Id VARCHAR(6),
    D_Id VARCHAR (6),
    User_Id VARCHAR(6),
    Log_Status VARCHAR(15),
        CONSTRAINT PRIMARY KEY(Log_Id),
        CONSTRAINT FOREIGN KEY (User_Id) REFERENCES `User`(User_Id) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT FOREIGN KEY (D_Id) REFERENCES Doctor (D_Id) ON DELETE CASCADE ON UPDATE CASCADE

);
      SHOW TABLES ;
      DESCRIBE Login;

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User`(
    User_Id VARCHAR(6),
    F_Name VARCHAR(20),
    L_Name VARCHAR(20),
    Email VARCHAR(30),
    Contact VARCHAR(15),
    UserName VARCHAR(20),
    Password VARCHAR(15),
    AccessLevel VARCHAR(15),
    Status VARCHAR(10),
        CONSTRAINT PRIMARY KEY(User_Id)

);
        SHOW TABLES ;
        DESCRIBE `User`;



        DROP TABLE IF EXISTS TestLabReport;
        CREATE TABLE IF NOT EXISTS TestLabReport(
            TestLabReportId VARCHAR (6),
            Test_Id VARCHAR (6),
            LabReport_Id VARCHAR (6),
            ReceivedDay DATETIME,
                CONSTRAINT PRIMARY Key(TestLabReportId),
                CONSTRAINT FOREIGN KEY (Test_Id) REFERENCES Test(Test_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                CONSTRAINT FOREIGN KEY (LabReport_Id) REFERENCES LabReport(LabReport_Id) ON DELETE CASCADE ON UPDATE CASCADE

        );

                SHOW TABLES ;
                DESCRIBE TestLabReport;

