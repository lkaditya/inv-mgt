INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (1, 'password', 0, 'sharon');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (2, 'password', 0, 'xinyue');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (3, 'password', 0, 'huiling');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (4, 'password', 1, 'kevin');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (5, 'password', 1, 'keqian');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (6, 'password', 1, 'lirang');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (7, 'password', 1, 'dongyang');
INSERT INTO `inventory`.`user` (`id`, `password`, `role`, `user_name`) VALUES (8, 'password', 1, 'huaizhong');

INSERT INTO brand(id,brand_name) VALUES(1,"Acura" );
INSERT INTO brand(id,brand_name) VALUES(2,"Alfa Romeo" );
INSERT INTO brand(id,brand_name) VALUES(3,"Audi" );
INSERT INTO brand(id,brand_name) VALUES(4,"BMW" );
INSERT INTO brand(id,brand_name) VALUES(5,"Bentley" );

INSERT INTO brand(id,brand_name) VALUES(6,"Buick" );
INSERT INTO brand(id,brand_name) VALUES(7,"Cadillac" );
INSERT INTO brand(id,brand_name) VALUES(8,"Chevrolet" );
INSERT INTO brand(id,brand_name) VALUES(9,"Chrysler" );
INSERT INTO brand(id,brand_name) VALUES(10,"Dodge" );


INSERT INTO supplier VALUES(1,100,"1235@gmail.com","13132","115648"," Magna International Inc.");
INSERT INTO supplier VALUES(2,100,"1235@gmail.com","13132","115648"," Continental Automotive Systems U.S. Inc");
INSERT INTO supplier VALUES(3,100,"1235@gmail.com","13132","115648","  ZF North America Inc.");
INSERT INTO supplier VALUES(4,100,"1235@gmail.com","13132","115648"," Denso International America Inc.");
INSERT INTO supplier VALUES(5,100,"1235@gmail.com","13132","115648"," Robert Bosch");

INSERT INTO supplier VALUES(6,100,"1235@gmail.com","13132","115648","  Lear Corp.");
INSERT INTO supplier VALUES(7,100,"1235@gmail.com","13132","115648","  Flex-N-Gate Corp.");
INSERT INTO supplier VALUES(8,100,"1235@gmail.com","13132","115648","  Panasonic Automotive Systems Co. of America");
INSERT INTO supplier VALUES(9,100,"1235@gmail.com","13132","115648","  Aisin World Corp. of America");
INSERT INTO supplier VALUES(10,100,"1235@gmail.com","13132","115648","  American Axle & Manufacturing Holdings Inc.");


INSERT INTO inventory VALUES(1,150,200,300,30,100);
INSERT INTO inventory VALUES(2,250,300,300,30,200);
INSERT INTO inventory VALUES(3,350,400,300,30,300);
INSERT INTO inventory VALUES(4,450,500,300,30,400);
INSERT INTO inventory VALUES(5,550,600,300,30,500);
INSERT INTO inventory VALUES(6,650,700,300,30,600);
INSERT INTO inventory VALUES(7,750,800,300,30,700);
INSERT INTO inventory VALUES(8,850,900,300,30,800);
INSERT INTO inventory VALUES(9,9500,1000,300,30,9000);
INSERT INTO inventory VALUES(10,1050,1100,300,30,1000);

INSERT INTO product VALUES(1,"TYRES","Tyres for SUVs and crossovers are intended to ensure safety and driving comfort on any type of road surface or in the off-road conditions.","Double coin DASP+XL","WinterTyres","Off-Road/4x4/SUV",1,1,NULL,1);
INSERT INTO product VALUES(2,"TYRES","They have a durable design and a special tread pattern that guarantees high manoeuvrability in harsh conditions. ","Ziarelli MS45 175/65 R14 107H",NULL,"Off-Road/4x4/SUV",2,2,NULL,2);

INSERT INTO product VALUES(3,"Brake system","A braking system comprises several components designed to ensure a controlled stop of a vehicle at a particular moment.","V20-92-0001","Abs ring","Abs ring",3,3,NULL,3);

INSERT INTO product VALUES(4,"Engine","An engine is a unit which produces the torque required to move the vehicle.","Double coin DASP+XL ver2.0",NULL,"Crankshaft seal",4,4,NULL,4);
INSERT INTO product VALUES(5,"Engine","along with the cylinder head and crankcase, this makes up the body of the power unit. ","Engine block ",NULL,"Oil drain plug gasket",5,5,NULL,5);
INSERT INTO product VALUES(6,"Engine"," this covers the cylinders from the side where the piston crowns are.","Cylinder head ",NULL,"Mounting kit",6,6,NULL,6);

INSERT INTO product VALUES(7,"Engine","this seals the joint between the cylinder head and engine block. ","Head gasket ",NULL,"Tappet",7,7,NULL,7);
INSERT INTO product VALUES(8,"Engine","this is attached to the bottom of the engine block.","Crankcase",NULL,"Timing chain guides",8,8,NULL,8);
INSERT INTO product VALUES(9,"Engine","this consists of pistons that are connected to the crankshaft via connecting rods and set it in motion. ","Rotating assembly",NULL,"Turbocharger gasket",9,9,NULL,9);
INSERT INTO product VALUES(10,"Engine","this includes a camshaft with valves and their actuators, pulleys, and a chain or a belt.","Valvetrain",NULL,"Valve stem seals ",10,10,NULL,10);

INSERT INTO `inventory`.`customer` VALUES (1, 98765431, 'Esther');
INSERT INTO `inventory`.`customer` VALUES (2, 98765432, 'Suria');
INSERT INTO `inventory`.`customer` VALUES (3, 98765433, 'Tin');
INSERT INTO `inventory`.`customer` VALUES (4, 98765434, 'Cher Wah');
INSERT INTO `inventory`.`customer` VALUES (5, 98765435, 'Yuan Kwan');
INSERT INTO `inventory`.`customer` VALUES (6, 98765436, 'Liu Fan');


