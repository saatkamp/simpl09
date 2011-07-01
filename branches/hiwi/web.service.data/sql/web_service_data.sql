CREATE TABLE webservicedata (
   id SERIAL PRIMARY KEY,
   size int,
   data bytea,
   UNIQUE (id)
);

