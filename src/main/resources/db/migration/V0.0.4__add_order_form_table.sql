alter table tbl_evaluate rename column child_fruit_id to product_id;
CREATE TABLE tbl_order_form
(
    id                  VARCHAR(44) NOT NULL COMMENT '订单编号uuid',
    user_id             VARCHAR(44) NOT NULL COMMENT '用户id',
    address             VARCHAR(255) NOT NULL COMMENT '收货地址',
    express             VARCHAR(127) COMMENT '物流信息',
    fruit_id            VARCHAR(44) NOT NULL COMMENT '水果id',
    scenic_id 	    	VARCHAR(44) NOT NULL COMMENT '景区id',
    amount              INT(11) NULL COMMENT '数目',
    payment             DOUBLE NOT NULL COMMENT '实际付款金额',
    order_form_status   SMALLINT  COMMENT '订单状态:未支付-0 已支付-1 待发货-2 已发货-3 已完成-4',
    has_evaluate        SMALLINT  COMMENT '是否已经评价:未评价-0 已评价-1',
    bind_evaluate_id    VARCHAR(44) NOT NULL COMMENT '评价表的id',
    STATUS              SMALLINT COMMENT '状态,0:禁用 1:启用',
    pay_time        BIGINT COMMENT '支付时间',
    create_time     BIGINT COMMENT '创建时间',
    update_time     BIGINT COMMENT '更新时间',
    create_user_id  VARCHAR(44) COMMENT '记录创建人ID',
    update_user_id  VARCHAR(44) COMMENT '记录修改人ID',
    CONSTRAINT tbl_order_form_pk PRIMARY KEY (id),
    CONSTRAINT tbl_order_form_user_id_fk FOREIGN KEY (user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_order_form_create_user_id_fk FOREIGN KEY (create_user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_order_form_update_user_id_fk FOREIGN KEY (update_user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_order_form_bind_evaluate_id_fk FOREIGN KEY (bind_evaluate_id) REFERENCES tbl_evaluate (id),
    CONSTRAINT tbl_order_form_fruit_id_fk FOREIGN KEY (fruit_id) REFERENCES tbl_fruit (id),
    CONSTRAINT tbl_order_form_scenic_id_fk FOREIGN KEY (scenic_id) REFERENCES tbl_scenic (id)
) COMMENT '订单表';



