CREATE TABLE if NOT EXISTS notification_task(
id bigserial PRIMARY KEY,
chat_id bigint,
message_text VARCHAR(150),
local_date_time TIMESTAMP
)