
----クラス情報
DROP TABLE IF EXISTS class_type_list CASCADE;

CREATE TABLE IF NOT EXISTS class_type_list(
classtype_name VARCHAR(100) NOT NULL,
atk_rate NUMERIC NOT NULL,
enemy_np_rate NUMERIC NOT NULL,
enemy_np_rate_special NUMERIC NOT NULL,
PRIMARY KEY (classtype_name)
);

--カード情報
DROP TABLE IF EXISTS card_type_list CASCADE;

CREATE TABLE IF NOT EXISTS card_type_list(
cardType_name VARCHAR(100) NOT NULL,
atk_rate NUMERIC NOT NULL,
np_rate NUMERIC NOT NULL,
PRIMARY KEY (cardtype_name)
);

--サーバント情報
DROP TABLE IF EXISTS servant_list CASCADE;

CREATE TABLE IF NOT EXISTS servant_list(
number SMALLINT NOT NULL,
servant_name VARCHAR(255) NOT NULL,
servant_name_read VARCHAR(255) NOT NULL,
classType_name VARCHAR(100) NOT NULL,
rarity SMALLINT NOT NULL,
atk INT NOT NULL,
atk_lv100 INT NOT NULL,
atk_lv120 INT NOT NULL,
phantasm_card_type VARCHAR(100) NOT NULL,
phantasm_range VARCHAR(100) NOT NULL,
phantasm_rate_Lv1 NUMERIC NOT NULL,
phantasm_rate_Lv2 NUMERIC NOT NULL,
phantasm_rate_Lv3 NUMERIC NOT NULL,
phantasm_rate_Lv4 NUMERIC NOT NULL,
phantasm_rate_Lv5 NUMERIC NOT NULL,
phantasm_hit SMALLINT NOT NULL,
np_rate_attack NUMERIC NOT NULL,
phantasm_rate_1hit INT DEFAULT 0,
phantasm_rate_2hit INT DEFAULT 0,
phantasm_rate_3hit INT DEFAULT 0,
phantasm_rate_4hit INT DEFAULT 0,
phantasm_rate_5hit INT DEFAULT 0,
phantasm_rate_6hit INT DEFAULT 0,
phantasm_rate_7hit INT DEFAULT 0,
phantasm_rate_8hit INT DEFAULT 0,
phantasm_rate_9hit INT DEFAULT 0,
phantasm_rate_10hit INT DEFAULT 0,
phantasm_rate_11hit INT DEFAULT 0,
phantasm_rate_12hit INT DEFAULT 0,
phantasm_rate_13hit INT DEFAULT 0,
phantasm_rate_14hit INT DEFAULT 0,
phantasm_rate_15hit INT DEFAULT 0,

PRIMARY KEY (number),
FOREIGN KEY (classtype_name) references class_type_list(classtype_name),
FOREIGN KEY (phantasm_card_type) references card_type_list(cardtype_name)
);

GRANT ALL PRIVILEGES ON DATABASE fgocalculation TO fgomaster;
