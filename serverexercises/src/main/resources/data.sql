-- add initial employee test data
INSERT INTO Employee (Title,FirstName,LastName,PhoneNo,Email)
 VALUES ('Mr.','Bigshot','Smartypants','(555)555-5551','bs@abc.com');
INSERT INTO Employee (Title,FirstName,LastName,PhoneNo,Email)
 VALUES ('Mrs.','Penny','Pincher','(555)555-5551','pp@abc.com');
INSERT INTO Employee (Title,FirstName,LastName,PhoneNo,Email)
 VALUES ('Mr.','Smoke','Andmirrors','(555)555-5552','sa@abc.com');
INSERT INTO Employee (Title,FirstName,LastName,PhoneNo,Email)
 VALUES ('Mr.','Sam','Slick','(555)555-5552','ng@abc.com');
INSERT INTO Employee (Title,FirstName,LastName,PhoneNo,Email)
 VALUES ('Mr.','Sloppy','Joe','(555)555-5553','sj@abc.com');
INSERT INTO Employee (Title,FirstName,LastName,PhoneNo,Email)
 VALUES ('Mr.','Brenden','Abbott','(289)233-1498','ba@abc.com');
 -- add expense categories
INSERT INTO Expense_Category (Id, Description) VALUES ('BSM','Business Meetings');
INSERT INTO Expense_Category (Id, Description) VALUES ('ENT','Entertainment');
INSERT INTO Expense_Category (Id, Description) VALUES ('PARK','Parking');
INSERT INTO Expense_Category (Id, Description) VALUES ('LDG','Lodging');
INSERT INTO Expense_Category (Id, Description) VALUES ('TRAV','Travel');
INSERT INTO Expense_Category (Id, Description) VALUES ('MEAL','Meals');
INSERT INTO Expense_Category (Id, Description) VALUES ('TUI','Tuition');
INSERT INTO Expense_Category (Id, Description) VALUES ('MISC','Miscealleous');
INSERT INTO Expense_Category (Id, Description) VALUES ('OTH','OTHER');

-- add some expenses to seed the table
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (1,'PARK','Parking for Convention',true,'2023-04-13',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (1,'LDG','Hotel for Convention',true,'2023-04-15',219.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (1,'MEAL','Food at Convention',true,'2023-04-13',39.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (2,'TUI','Tuition for Cobol course',true,'2023-05-19',29.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (2,'MISC','Bought widgets for the office',true,'2023-05-20',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (2,'OTH','Donation for Cancer Society',true,'2023-05-21',20.00);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (3,'PARK','Parking for Convention',true,'2023-04-13',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (3,'LDG','Hotel for Convention',true,'2023-04-15',219.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (3,'MEAL','Food at Convention',true,'2023-04-13',39.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (4,'TUI','Tuition for Cobol course',true,'2023-05-19',29.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (4,'MISC','Bought widgets for the office',true,'2023-05-20',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (4,'OTH','Donation for Cancer Society',true,'2023-05-21',20.00);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (1,'PARK','Parking for Convention',true,'2023-04-13',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (1,'LDG','Hotel for Convention',true,'2023-04-15',219.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (1,'MEAL','Food at Convention',true,'2023-04-13',39.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (2,'TUI','Tuition for Cobol course',true,'2023-05-19',29.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (2,'MISC','Bought widgets for the office',true,'2023-05-20',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (2,'OTH','Donation for Cancer Society',true,'2023-05-21',20.00);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (3,'PARK','Parking for Convention',true,'2023-04-13',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (3,'LDG','Hotel for Convention',true,'2023-04-15',219.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (3,'MEAL','Food at Convention',true,'2023-04-13',39.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (4,'TUI','Tuition for Cobol course',true,'2023-05-19',29.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (4,'MISC','Bought widgets for the office',true,'2023-05-20',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (4,'OTH','Donation for Cancer Society',true,'2023-05-21',20.00);

INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (6,'MEAL','Food at Convention',true,'2023-04-13',39.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (6,'TUI','Tuition for Cobol course',true,'2023-05-19',29.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (6,'MISC','Bought widgets for the office',true,'2023-05-20',19.99);
INSERT INTO Expense (EmployeeId,CategoryId,Description,Receipt,DateIncurred,Amount)
VALUES (6,'OTH','Donation for Cancer Society',true,'2023-05-21',20.00);