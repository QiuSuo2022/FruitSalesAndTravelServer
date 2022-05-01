CREATE TABLE tbl_goods
(
    id                  VARCHAR(44) NOT NULL COMMENT '商品uuid',
    order_id            VARCHAR(44) NOT NULL COMMENT '订单编号',
    name                varchar (64) COMMENT '商品名',
    price               INT(11) NULL COMMENT '商品单价',
    fruit_id            VARCHAR(44)  COMMENT '水果id',
    scenic_id 	    	VARCHAR(44)  COMMENT '景区id',
    amount              INT(11) NULL COMMENT '数目',
    pay_status          SMALLINT  NOT NULL COMMENT '订单状态:未支付-0 已支付-1 待发货-2 已发货-3 已完成-4 已退款-5',
    STATUS              SMALLINT COMMENT '状态,0:禁用 1:启用',
    create_time     BIGINT COMMENT '创建时间',
    update_time     BIGINT COMMENT '更新时间',
    create_user_id  VARCHAR(44) NULL COMMENT '记录创建人ID',
    update_user_id  VARCHAR(44) NULL COMMENT '记录修改人ID',
    CONSTRAINT tbl_goods_pk PRIMARY KEY (id),
    CONSTRAINT tbl_goods_create_user_id_fk FOREIGN KEY (create_user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_goods_update_user_id_fk FOREIGN KEY (update_user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_goods_order_id_fk FOREIGN KEY(order_id) REFERENCES tbl_order_form (id),
    CONSTRAINT tbl_goods_fruit_id_fk FOREIGN KEY(fruit_id) REFERENCES tbl_fruit (id),
    CONSTRAINT tbl_goods_scenic_id_fk FOREIGN KEY (scenic_id) REFERENCES tbl_scenic(id)
) COMMENT '商品表';