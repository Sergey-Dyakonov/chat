DROP KEYSPACE IF EXISTS chat;

CREATE KEYSPACE chat WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};
--     Replication factor means number of nodes to keep copies of the same data.
--     Since only one machine is used (current), no need to set values greater than 1

USE chat;

CREATE TYPE if not exists message
    (
    id          bigint,
    createdAt   timestamp,
    senderId    bigint,
    content     text
    );

CREATE TABLE if not exists chat
(
    id          uuid PRIMARY KEY,
    createdAt   timestamp,
    name        text,
    description text,
    icon        text,
    creator     bigint,
    content     set<message>
);

BEGIN
    BATCH
    INSERT INTO chat (createdAt, name, description, creator)
    VALUES ('2023-01-01', 'Common chat', 'It is a common chat for each joined user', 1);
    INSERT INTO chat (createdAt, name, description, creator)
    VALUES ('2023-01-02', 'Jane''s chat', 'Jane''s chat for her best friends', 2);
    APPLY BATCH
