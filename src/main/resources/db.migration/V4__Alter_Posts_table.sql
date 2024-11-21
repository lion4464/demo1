ALTER TABLE "public"."posts"
    DROP CONSTRAINT "fk_posts_author",
    ADD CONSTRAINT "fk_posts_author" FOREIGN KEY ("author_id") REFERENCES "public"."users" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;