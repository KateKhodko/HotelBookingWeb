-- select all locales
SELECT locale from i18n;
-- WHERE locale = "ru";
--------------------------------------------------

-- select all localization by locale
-- return array room_types
SELECT * from room_type 
	LEFT JOIN room_type_i18n on room_type_i18n.room_type_id = room_type.id
	WHERE room_type_i18n.locale = "ru" OR room_type_i18n.locale IS NULL;

-- select by room_type.id all localization by locale
-- return room_type	
SELECT * from room_type 
	LEFT JOIN room_type_i18n on room_type_i18n.room_type_id = room_type.id
	WHERE room_type_i18n.locale = "ru" OR room_type_i18n.locale IS NULL
		AND room_type.id = 3;
		
-- select all room_types
-- return array room_types    
SELECT * FROM room_type
	LEFT JOIN amenity_room_type ON room_type.id = amenity_room_type.room_type_id
	LEFT JOIN amenity ON amenity.id = amenity_room_type.amenity_id;		

-- FIND_ALL_ROOMTYPES_I18N_JOIN	
-- select all room_types with localization
-- return array room_types    
-- i18n.id = 1 (England) - default id. Всегда запрашивается вместе с требуемым языком. Обязательная локализация!
SELECT * FROM room_type
	LEFT JOIN room_type_i18n ON room_type_i18n.room_type_id = room_type.id
	LEFT JOIN amenity_room_type ON room_type.id = amenity_room_type.room_type_id
	LEFT JOIN amenity ON amenity.id = amenity_room_type.amenity_id	
	LEFT JOIN amenity_i18n ON amenity_i18n.amenity_id = amenity.id
	WHERE (room_type_i18n.i18n_id = 3 OR (room_type_i18n.i18n_id != 3 AND room_type_i18n.i18n_id == 1) OR room_type_i18n.i18n_id IS NULL)
	AND (amenity_i18n.i18n_id = 3 OR (amenity_i18n.i18n_id != 3 AND amenity_i18n.i18n_id == 1) OR amenity_i18n.i18n_id IS NULL );
--	WHERE room_type_i18n.i18n_id IN (1, 3) AND amenity_i18n.i18n_id IN (1, 3);
-- 		AND room_type.id = 3;	-- select by room_type.id
----------------------------------------------------------------------------

-- select amenities with localization		
SELECT * FROM amenity
	LEFT JOIN amenity_i18n ON amenity_i18n.amenity_id = amenity.id
	AND amenity_i18n.locale = "ru" OR amenity_i18n.locale IS NULL;	
-- 	AND amenity.id = 3;	-- select by amenity.id
		
-- select amenities by room_type.id
-- return array amenities
-- use select by id room_types with localization (выше)
SELECT amenity.id, amenity.name, amenity.price FROM amenity
	JOIN amenity_room_type ON amenity.id = amenity_room_type.amenity_id
	JOIN room_type ON room_type.id = amenity_room_type.room_type_id
	WHERE room_type.id = 3;

------------------------------------------------------------------------------	
	
-- select bookings with limit 10 and offset 0
-- return array bookings	
SELECT * FROM booking
    LEFT JOIN room_type ON booking.room_type_id = room_type.id
    LEFT JOIN user ON booking.user_id = user.id
    LIMIT 10 OFFSET 0;	

-- with localization    
SELECT * FROM booking
    LEFT JOIN room_type ON booking.room_type_id = room_type.id
    LEFT JOIN room_type_i18n ON room_type_i18n.room_type_id = room_type.id
    LEFT JOIN user ON booking.user_id = user.id
    WHERE (room_type_i18n.locale = "ru" OR room_type_i18n.locale IS NULL)
    LIMIT 10 OFFSET 0;	    
 
------------------------------------------------------------------------------    
-- select all users
-- retutn array users	
SELECT * FROM user
	LEFT JOIN user_role ON user.id = user_role.user_id
	LEFT JOIN role ON role.id = user_role.role_id;