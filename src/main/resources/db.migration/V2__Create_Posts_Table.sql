CREATE TABLE posts (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       title VARCHAR(100) NOT NULL,
                       content TEXT NOT NULL,
                       author_id UUID NOT NULL,
                       created_by UUID,
                       created_date BIGINT NOT NULL,
                       modified_by UUID,
                       modified_date BIGINT NOT NULL,

                       CONSTRAINT fk_posts_author FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE INDEX idx_posts_author ON posts(author_id);
CREATE INDEX idx_posts_created_date ON posts(created_date DESC);