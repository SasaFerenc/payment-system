INSERT INTO firm(firm_name) VALUES ('FirmaA');
INSERT INTO firm(firm_name) VALUES ('FirmaB');

INSERT INTO account(count_number, reserved, total, firm_id) VALUES ('1', 0, 100, (SELECT firm_id FROM firm WHERE firm_name='FirmaA'));
INSERT INTO account(count_number, reserved, total, firm_id) VALUES ('2', 0, 100, (SELECT firm_id FROM firm WHERE firm_name='FirmaB'));