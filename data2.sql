INSERT INTO brand(id,brand_name) VALUES(1,"RIDEX" );
INSERT INTO brand(id,brand_name) VALUES(2,"TAB" );
INSERT INTO brand(id,brand_name) VALUES(3,"HANKOOK" );
INSERT INTO brand(id,brand_name) VALUES(4,"RIDEXES" );
INSERT INTO brand(id,brand_name) VALUES(5,"FEBI BILSTEIN" );

INSERT INTO brand(id,brand_name) VALUES(6,"RIDER" );
INSERT INTO brand(id,brand_name) VALUES(7,"WALSER" );
INSERT INTO brand(id,brand_name) VALUES(8,"K2" );
INSERT INTO brand(id,brand_name) VALUES(9,"Maxxis" );
INSERT INTO brand(id,brand_name) VALUES(10,"VICTOR REINZ" );


INSERT INTO supplier VALUES(1,100,"12345@gmail.com","13132","115648","Magna International Inc.");
INSERT INTO supplier VALUES(2,100,"12345@gmail.com","13132","115648","Continental Automotive Systems U.S. Inc");
INSERT INTO supplier VALUES(3,100,"12345@gmail.com","13132","115648","ZF North America Inc.");
INSERT INTO supplier VALUES(4,100,"12345@gmail.com","13132","115648","Denso International America Inc.");
INSERT INTO supplier VALUES(5,100,"12345@gmail.com","13132","115648","Robert Bosch");

INSERT INTO supplier VALUES(6,100,"12345@gmail.com","13132","115648","Lear Corp.");
INSERT INTO supplier VALUES(7,100,"12345@gmail.com","13132","115648","Flex-N-Gate Corp.");
INSERT INTO supplier VALUES(8,100,"12345@gmail.com","13132","115648","Panasonic Automotive Systems Co. of America");
INSERT INTO supplier VALUES(9,100,"12345@gmail.com","13132","115648","Aisin World Corp. of America");
INSERT INTO supplier VALUES(10,100,"12345@gmail.com","13132","115648","American Axle & Manufacturing Holdings Inc.");

/*
INSERT INTO user VALUES (1, 'password', 0, 'sharon');
INSERT INTO user VALUES (2, 'password', 0, 'xinyue');
INSERT INTO user VALUES (3, 'password', 0, 'huiling');
INSERT INTO user VALUES (4, 'password', 1, 'kevin');
INSERT INTO user VALUES (5, 'password', 1, 'keqian');
INSERT INTO user VALUES (6, 'password', 1, 'lirang');
INSERT INTO user VALUES (7, 'password', 1, 'dongyang');
INSERT INTO user VALUES (8, 'password', 1, 'huaizhong');
*/

INSERT INTO user VALUES (1, 'darren.wangdongyang@gmail.com', 'password', "ADMIN", 'sharon');
INSERT INTO user VALUES (2, 'darren.wangdongyang@gmail.com', 'password', "ADMIN", 'xinyue');
INSERT INTO user VALUES (3, 'gaokeqian97@gmail.com', 'password', "MECHANIC", 'keqian');

INSERT INTO inventory VALUES(1,42,45,300,30,38);
INSERT INTO inventory VALUES(2,108,117,300,30,99);
INSERT INTO inventory VALUES(3,132,143,300,30,121);
INSERT INTO inventory VALUES(4,43,46,300,30,39);
INSERT INTO inventory VALUES(5,12,13,300,30,11);
INSERT INTO inventory VALUES(6,28,31,300,30,26);
INSERT INTO inventory VALUES(7,24,26,300,30,22);
INSERT INTO inventory VALUES(8,18,19,300,30,16);
INSERT INTO inventory VALUES(9,102,110,300,30,93);
INSERT INTO inventory VALUES(10,40,44,300,30,37);





INSERT INTO product VALUES(1,"Brake",  "Brake pad, Rear Axle, prepared for wear indicator",   "RIDEX Brake Pad","Brake pad","Car",1,1,1);
INSERT INTO product VALUES(2,"Engine","Lead-acid battery, Capacity: 50Ah","TAB Starter Battery","Battery","Car",2,2,2);

INSERT INTO product VALUES(3,"Tyre","Passenger Car Tyre, 135/70 TR15 TL 70T HANK H740 KINERGY 4S", "Hankook Kinergy 4S H740 Tyre","All season","Car",3,3,3);

INSERT INTO product VALUES(4,"Damping" , "Rear Axle, Gas Pressure, Twin-Tube, Top pin, Bottom Pin","RIDEX Shock Absorber","Shock Absorber","Car",4,4,4);
INSERT INTO product VALUES(5,"Interior","Brake pedal Pad,75/60/12mm","FEBI BILSTEIN Brake Pedal Pad","Pedal","Car",5,5,5);
INSERT INTO product VALUES(6,"Engine"," Active sensor, Hall Sensor","RIDEX Sensor, camshaft position","Senor","Car",6,6,6);

INSERT INTO product VALUES(7,"Interior","Seat Cover, Black/Grey, Polyester, Front ","WALSER Seat cover","Car Seat Cover","Car",7,7,7);


INSERT INTO product VALUES(8,"Engine","5W-30, 1l, Part Synthetic Oil ","K2 TEXAR, FUEL ECONOMY Engine Oil","Engine Oil","Motorcycle",8,8,8);
INSERT INTO product VALUES(9,"Tyre ","Truck tyre, Maxxis Mecotra 3 ME3 155/65 R14 75T","Maxxis Mecotra 3  Tyre","All season","Truck",9,9,9);
INSERT INTO product VALUES(10,"Engine","Crankshaft, Timing End, FPM (fluoride rubber)","REINZ Shaft Seal, crankshaft","Crankshaft","Truck",10,10,10);


INSERT INTO `inventory`.`customer` VALUES (1, 98765431, 'Esther');
INSERT INTO `inventory`.`customer` VALUES (2, 98765432, 'Suria');
INSERT INTO `inventory`.`customer` VALUES (3, 98765433, 'Tin');
INSERT INTO `inventory`.`customer` VALUES (4, 98765434, 'Cher Wah');
INSERT INTO `inventory`.`customer` VALUES (5, 98765435, 'Yuan Kwan');
INSERT INTO `inventory`.`customer` VALUES (6, 98765436, 'Liu Fan');