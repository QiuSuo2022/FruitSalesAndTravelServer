ALTER TABLE tbl_image_file ADD fk_id VARCHAR(44) comment '用于绑定景区/水果';
alter table tbl_evaluate rename column child_fruit_id to product_id;
