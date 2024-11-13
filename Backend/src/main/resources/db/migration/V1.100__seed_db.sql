CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO profile (id,
                     created_at,
                     updated_at,
                     email,
                     full_name,
                     password,
                     phone_number,
                     profile_type)
VALUES (gen_random_uuid(),
        NOW(),
        NOW(),
        'filimon.mark@gmail.com',
        'Márk Filimon',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG',
        '+36703334444',
        'ADMIN');

INSERT INTO profile (id, created_at, updated_at, email, full_name, password, phone_number, profile_type)
VALUES (gen_random_uuid(), NOW(), NOW(), 'johndoe@example.com', 'John Doe',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36201234567', 'USER'),
       (gen_random_uuid(), NOW(), NOW(), 'janedoe@example.com', 'Jane Doe',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36302345678', 'WAITER'),
       (gen_random_uuid(), NOW(), NOW(), 'michaelsmith@example.com', 'Michael Smith',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36203456789', 'USER'),
       (gen_random_uuid(), NOW(), NOW(), 'sarahjohnson@example.com', 'Sarah Johnson',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36704567890', 'USER'),
       (gen_random_uuid(), NOW(), NOW(), 'emilydavis@example.com', 'Emily Davis',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36305678901', 'WAITER'),
       (gen_random_uuid(), NOW(), NOW(), 'davidwilson@example.com', 'David Wilson',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+3616789012', 'USER'),
       (gen_random_uuid(), NOW(), NOW(), 'danielbrown@example.com', 'Daniel Brown',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36207890123', 'WAITER'),
       (gen_random_uuid(), NOW(), NOW(), 'lisamartin@example.com', 'Lisa Martin',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36708901234', 'USER'),
       (gen_random_uuid(), NOW(), NOW(), 'oliviaanderson@example.com', 'Olivia Anderson',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+3619012345', 'WAITER'),
       (gen_random_uuid(), NOW(), NOW(), 'noahthomas@example.com', 'Noah Thomas',
        '$2b$12$SV.C1MSsU4yE/WzuCZxq3uabhgXJRKYqxt72PkeNbc3UcEKSAIIHG', '+36202345678', 'USER');


INSERT INTO Food (id, name, description, price, created_at, updated_at)
VALUES (gen_random_uuid(), 'Pizza', 'Olívás és paradicsomos pizza', 1299, NOW(), NOW()),
       (gen_random_uuid(), 'Hamburger', 'Klasszikus marhahúsos hamburger', 899, NOW(), NOW()),
       (gen_random_uuid(), 'Sushi', 'Friss lazac és avokádó sushi', 1550, NOW(), NOW()),
       (gen_random_uuid(), 'Tészta', 'Olasz tészta paradicsomszósszal', 1100, NOW(), NOW()),
       (gen_random_uuid(), 'Saláta', 'Vegyes saláta dresszinggel', 750, NOW(), NOW()),
       (gen_random_uuid(), 'Steak', 'Szaftos marhasteak körettel', 2500, NOW(), NOW()),
       (gen_random_uuid(), 'Leves', 'Meleg zöldségleves', 550, NOW(), NOW()),
       (gen_random_uuid(), 'Szendvics', 'Sonkás-sajtos szendvics', 600, NOW(), NOW()),
       (gen_random_uuid(), 'Jégkrém', 'Vaníliafagylalt csokireszelékkel', 400, NOW(), NOW()),
       (gen_random_uuid(), 'Kávé', 'Frissen főzött kávé', 350, NOW(), NOW()),
       (gen_random_uuid(), 'Gulyásleves', 'Hagyományos magyar gulyásleves marhahússal', 1200, NOW(), NOW()),
       (gen_random_uuid(), 'Lángos', 'Sajtos-tejfölös magyar lángos', 600, NOW(), NOW()),
       (gen_random_uuid(), 'Túrós csusza', 'Túróval és szalonnával készített tészta', 900, NOW(), NOW()),
       (gen_random_uuid(), 'Halászlé', 'Friss pontyból készült halászlé', 1400, NOW(), NOW()),
       (gen_random_uuid(), 'Rántott hús', 'Rántott sertéshús krumplipürével', 1100, NOW(), NOW()),
       (gen_random_uuid(), 'Pörkölt', 'Marhapörkölt galuskával', 1300, NOW(), NOW()),
       (gen_random_uuid(), 'Töltött káposzta', 'Savanyú káposztával készült töltött káposzta', 1000, NOW(), NOW()),
       (gen_random_uuid(), 'Palacsinta', 'Házi lekváros palacsinta', 500, NOW(), NOW()),
       (gen_random_uuid(), 'Somlói galuska', 'Tejszínes, csokis somlói galuska', 700, NOW(), NOW()),
       (gen_random_uuid(), 'Kürtőskalács', 'Cukrozott kürtőskalács', 800, NOW(), NOW());

INSERT INTO Seating (id, name, person_count, created_at, updated_at)
VALUES (gen_random_uuid(), 'Erkély', 2, NOW(), NOW()),
       (gen_random_uuid(), 'Főasztal', 8, NOW(), NOW()),
       (gen_random_uuid(), 'Családi asztal', 6, NOW(), NOW()),
       (gen_random_uuid(), 'Nagyterem', 20, NOW(), NOW()),
       (gen_random_uuid(), 'Kis sarok', 2, NOW(), NOW()),
       (gen_random_uuid(), 'Panoráma', 4, NOW(), NOW()),
       (gen_random_uuid(), 'Kerthelyiség', 10, NOW(), NOW()),
       (gen_random_uuid(), 'Romantikus asztal', 2, NOW(), NOW()),
       (gen_random_uuid(), 'Bárpult', 1, NOW(), NOW()),
       (gen_random_uuid(), 'Társalgó', 15, NOW(), NOW());

INSERT INTO Allergen (id, name, created_at, updated_at)
VALUES (gen_random_uuid(), 'Glutén', NOW(), NOW()),
       (gen_random_uuid(), 'Rákfélék', NOW(), NOW()),
       (gen_random_uuid(), 'Tojás', NOW(), NOW()),
       (gen_random_uuid(), 'Hal', NOW(), NOW()),
       (gen_random_uuid(), 'Földimogyoró', NOW(), NOW()),
       (gen_random_uuid(), 'Szójabab', NOW(), NOW()),
       (gen_random_uuid(), 'Tej', NOW(), NOW()),
       (gen_random_uuid(), 'Diófélék', NOW(), NOW()),
       (gen_random_uuid(), 'Zeller', NOW(), NOW()),
       (gen_random_uuid(), 'Mustár', NOW(), NOW());

INSERT INTO foods_allergens (food_id, allergen_id)
SELECT f.id AS food_id,
       a.id AS allergen_id
FROM Food f
         JOIN
     Allergen a ON random() < 0.2
ORDER BY f.id, a.id;

INSERT INTO reservation (id, created_at, updated_at, reservation_start, reservation_end, reserved_by_profile_id)
VALUES (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() - INTERVAL '1 day') + INTERVAL '30 minute',
        date_trunc('hour', NOW() - INTERVAL '1 day') + INTERVAL '2 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 0)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() + INTERVAL '30 minute'),
        date_trunc('hour', NOW() + INTERVAL '30 minute') + INTERVAL '2 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 1)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() + INTERVAL '1 hour'),
        date_trunc('hour', NOW() + INTERVAL '1 hour') + INTERVAL '4 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 2)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() - INTERVAL '2 day') + INTERVAL '30 minute',
        date_trunc('hour', NOW() - INTERVAL '2 day') + INTERVAL '3 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 3)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() + INTERVAL '2 hour'),
        date_trunc('hour', NOW() + INTERVAL '2 hour') + INTERVAL '3 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 4)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() - INTERVAL '1 day') + INTERVAL '30 minute',
        date_trunc('hour', NOW() - INTERVAL '1 day') + INTERVAL '2 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 5)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() + INTERVAL '30 minute'),
        date_trunc('hour', NOW() + INTERVAL '30 minute') + INTERVAL '1 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 6)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() + INTERVAL '1 hour'),
        date_trunc('hour', NOW() + INTERVAL '1 hour') + INTERVAL '2 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 7)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() - INTERVAL '1 day') + INTERVAL '30 minute',
        date_trunc('hour', NOW() - INTERVAL '1 day') + INTERVAL '1 hour 30 minute',
        (SELECT id FROM profile LIMIT 1 OFFSET 8)),

       (gen_random_uuid(), NOW(), NOW(),
        date_trunc('hour', NOW() + INTERVAL '3 hour'),
        date_trunc('hour', NOW() + INTERVAL '3 hour') + INTERVAL '4 hour',
        (SELECT id FROM profile LIMIT 1 OFFSET 9));



INSERT INTO foods_reservations (food_id, reservation_id)
SELECT f.id AS food_id,
       r.id AS reservation_id
FROM Food f
         JOIN
     reservation r ON random() < 0.2
ORDER BY f.id, r.id;

INSERT INTO seatings_reservations (seating_id, reservation_id)
SELECT s.id AS seating_id,
       r.id AS reservation_id
FROM seating s
         JOIN
     reservation r ON true
ORDER BY random()
LIMIT 10;

DELETE
FROM foods_reservations
WHERE foods_reservations.reservation_id NOT IN (SELECT reservation_id
                                                FROM seatings_reservations);

DELETE
FROM reservation
WHERE id NOT IN (SELECT reservation_id
                 FROM seatings_reservations);
