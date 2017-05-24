INSERT INTO conference (idx, zone, description) VALUES (1, 'C601', '6층 첫번째 회의실');
INSERT INTO conference (idx, zone, description) VALUES (2, 'C602', '6층 2번째 회의실');

INSERT INTO conference_reserve (conference_idx, date, time_zone, content, reserve_name) VALUES (1,'20170505', 'T9', 'R&D회의', '중현');
INSERT INTO conference_reserve (conference_idx, date, time_zone, content, reserve_name, del) VALUES (1,'20170505', 'T10', 'R&D회의', '중현', true);
INSERT INTO conference_reserve (conference_idx, date, time_zone, content, reserve_name) VALUES (2,'20170505', 'T10', 'R&D회의', '중현');
INSERT INTO conference_reserve (conference_idx, date, time_zone, content, reserve_name) VALUES (2,'20170505', 'T11', 'R&D회의', '중현');
INSERT INTO conference_reserve (conference_idx, date, time_zone, content, reserve_name) VALUES (2,'20170505', 'T12', 'R&D회의', '중현');

