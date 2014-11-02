
-- 2014-10-02
-- 更新视图
ALTER
VIEW `V_HouseMessage` AS
    select 
        `a`.`id` AS `id`,
        `a`.`userName` AS `userName`,
        `a`.`houseCode` AS `houseCode`,
        `a`.`price` AS `price`,
        `a`.`unit` AS `unit`,
        `a`.`mobilePhone` AS `mobilePhone`,
        `a`.`createTime` AS `messageCreateTime`,
        `b`.`room` AS `room`,
        `b`.`hall` AS `hall`,
        `b`.`toilet` AS `toilet`,
        `b`.`decoration` AS `decoration`,
        `b`.`direction` AS `direction`,
        `b`.`acreage` AS `acreage`,
        `b`.`propertyId` AS `propertyId`,
        `b`.`propertyName` AS `propertyName`,
        `b`.`longitude` AS `longitude`,
        `b`.`latitude` AS `latitude`,
        `b`.`address` AS `address`,
        `b`.`floor` AS `floor`,
        `b`.`totalFloor` AS `totalfloor`,
        `b`.`createUserCode` AS `createUserCode`,
        `b`.`createTime` AS `createTime`,
        `a`.`status` AS `status`,
        `a`.`title` AS `title`,
        `a`.`creator` AS `creator`,
	`a`.`attachName` AS `attachName`
    from
        (`T_HouseMessage` `a`
        join `T_HouseInfo` `b` ON ((`a`.`houseCode` = `b`.`houseCode`)))