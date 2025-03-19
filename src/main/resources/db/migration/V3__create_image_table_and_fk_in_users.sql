DROP TABLE IF EXISTS images;

CREATE TABLE images (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    url TEXT NOT NULL
);

ALTER TABLE users
ADD COLUMN profile_pic UUID;

ALTER TABLE users
ADD CONSTRAINT users_to_images_fk
FOREIGN KEY (profile_pic)
REFERENCES images(id)
ON DELETE SET NULL;