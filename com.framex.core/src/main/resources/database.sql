DROP TABLE
IF EXISTS `framex_config`;

CREATE TABLE `framex_config` (
	`rowGuid` VARCHAR (50) PRIMARY KEY,
	`configName` VARCHAR (50),
	`configValue` VARCHAR (50)
);

DROP TABLE
IF EXISTS `framex_module`;

CREATE TABLE `framex_module` (
	`rowGuid` VARCHAR (50) PRIMARY KEY,
	`moduleName` VARCHAR (50),
	`moduleUri` VARCHAR (50),
	`moduleIp` VARCHAR (50),
	`modulePort` VARCHAR (50)

);

ALTER TABLE `framex_module` ADD moduleVersion VARCHAR(50);
ALTER TABLE `framex_module` ADD servicePackageName VARCHAR(50);


DROP TABLE
IF EXISTS `framex_zookeeper`;

CREATE TABLE `framex_zookeeper` (
	`rowGuid` VARCHAR (50) PRIMARY KEY,
	`moduleName` VARCHAR (50),
	`moduleUri` VARCHAR (50),
	`moduleIp` VARCHAR (50),
	`modulePort` VARCHAR (50)

);
