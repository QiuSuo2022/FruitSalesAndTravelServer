create table tbl_user
(
    id                varchar(44)  not null comment 'uuid',
    user_name         varchar(32)  null comment '用户名',
    real_name         varchar(32)  null comment '真实姓名',
    gender            varchar(16)  null comment '性别',
    password          varchar(128) null comment '密码',
    phone             varchar(13)  null comment '手机号',
    email             varchar(32)  null comment 'email',
    id_card           varchar(32)  null comment '身份证号',
    avatar_url        varchar(255) null comment '头像地址',
    token             varchar(32)  null,
    openid            varchar(32)  null comment '微信用户openId',
    role_id           varchar(44)  null,
    status            smallint     null comment '状态，0：不正常；1：正常',
    create_time       bigint       null comment '创建时间',
    update_time       bigint       null comment '最后更新时间',
    create_user_id    varchar(44)  null comment '增加此条数据的用户id',
    update_user_id    varchar(44)  null comment '最后更新此条数据的用户id',
    constraint tbl_user_pk primary key (id)
)
    comment '用户表';

create unique index tbl_user_user_name_uindex
    on tbl_user (user_name);


create table tbl_fruit
(
    id                varchar(44)    not null comment 'uuid',
    fruit_name        varchar(64)    null comment '水果名称',
    fruit_price       varchar(32)    null comment '水果价钱(一个范围)',
    description       varchar(1024)  null comment '水果描述',
    departure_point   varchar(32)    null comment '发货地点',
    delivery_cost     int(11)        null comment '快递费',
    status            smallint       null comment '状态,0: 无效 1: 有效',
    create_time       bigint         null comment '创建时间',
    update_time       bigint         null comment '最后更新时间',
    create_user_id    varchar(44)    null comment '增加此条数据的用户id',
    update_user_id    varchar(44)    null comment '最后更新此条数据的用户id',
    constraint tbl_fruit_pk primary key (id),
    constraint tbl_fruit_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_fruit_updated_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '水果商品表';


create table tbl_child_fruit
(
    id             varchar(44)    not null comment 'uuid',
    fruit_id       varchar(44)    null comment '水果商品id',
    fruit_name     varchar(64)    null comment '水果名称',
    fruit_price    int(11)        null comment '水果价钱(一个数字)',
    stock          int(11)        null comment '库存数量',
    image_url      varchar(255)   null comment '图片存储路径',
    status         smallint       null comment '状态,0: 无效 1: 有效',
    create_time    bigint         null comment '创建时间',
    update_time    bigint         null comment '最后更新时间',
    create_user_id varchar(44)    null comment '增加此条数据的用户id',
    update_user_id varchar(44)    null comment '最后更新此条数据的用户id',
    constraint tbl_child_fruit_pk primary key (id),
    constraint tbl_child_fruit_id_fk foreign key (fruit_id) references tbl_fruit (id),
    constraint tbl_child_fruit_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_child_fruit_updated_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '水果商品子项表';


create table tbl_image_file
(
    id              varchar(44)     not null comment 'uuid',
    product_id      varchar(44)     null comment '用于绑定景区/水果',
    image_name      varchar(255)    null comment '图片名',
    type            smallint        null comment '图片类别: 0--轮播图, 1--水果商品图, 2--景区图, 3--商品评价图',
    image_size      smallint        null comment '文件大小',
    image_url       varchar(255)    null comment '文件存储路径',
    remark          varchar(255)    null comment '备注',
    status          smallint        null comment '状态, 0: 无效 1: 有效',
    create_time     bigint          null comment '创建时间',
    update_time     bigint          null comment '最后更新时间',
    create_user_id  varchar(44)     null comment '增加此条数据的用户id',
    update_user_id  varchar(44)     null comment '最后更新此条数据的用户id',
    constraint tbl_image_file_pk primary key (id),
    constraint tbl_image_file_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_image_file_updated_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '图库表';


create table tbl_cart
(
    id                varchar(44)     not null comment 'uuid',
    user_id           varchar(44)     null comment '用户id',
    child_fruit_id    varchar(44)     null comment '水果商品子项id',
    quantity          int(11)         null comment '购买数量',
    status            smallint        null comment '状态, 0: 无效 1: 有效',
    create_time       bigint          null comment '创建时间',
    update_time       bigint          null comment '最后更新时间',
    create_user_id    varchar(44)     null comment '增加此条数据的用户id',
    update_user_id    varchar(44)     null comment '最后更新此条数据的用户id',
    constraint tbl_cart_pk primary key (id),
    constraint tbl_cart_user_id_fk foreign key (user_id) references tbl_user (id),
    constraint tbl_cart_child_fruit_id_fk foreign key (child_fruit_id) references tbl_child_fruit (id),
    constraint tbl_cart_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_cart_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '购物车表';

create table tbl_evaluate
(
    id                 varchar(44)     not null comment 'uuid',
    user_id            varchar(44)     null comment '用户id',
    product_id         varchar(44)     null comment '产品id',
    evaluate_id        varchar(44)     null comment '评价id',
    detail             varchar(512)    null comment '评价详情',
    grade              smallint        null comment '对商品的整体评价, 0-5星评分',
    type               smallint        null comment '0-评价水果 1评价景区 2-对评价的评论',
    status             smallint        null comment '状态, 0: 无效 1: 有效',
    create_time        bigint          null comment '创建时间',
    update_time        bigint          null comment '最后更新时间',
    create_user_id     varchar(44)     null comment '增加此条数据的用户id',
    update_user_id     varchar(44)     null comment '最后更新此条数据的用户id',
    constraint tbl_evaluate_pk primary key (id),
    constraint tbl_evaluate_user_id_fk foreign key (user_id) references tbl_user (id),
    constraint tbl_evaluate_evaluate_id_fk foreign key (evaluate_id) references tbl_evaluate (id),
    constraint tbl_evaluate_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_evaluate_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '评价/评论表';

create table tbl_scenic
(
    id                 varchar(44)     not null comment 'uuid',
    scenic_name        varchar(64)     null comment '景点名称',
    location           varchar(64)     null comment '地理位置',
    opening_hours      varchar(64)     null comment '开放时间',
    description        varchar(512)    null comment '景区介绍',
    type               smallint        null comment '景区类型, 1-文化古迹, 2-园林花园, 3-古镇古村, 4-城堡教堂',
    status             smallint        null comment '状态, 0: 无效 1: 有效',
    create_time        bigint          null comment '创建时间',
    update_time        bigint          null comment '最后更新时间',
    create_user_id     varchar(44)     null comment '增加此条数据的用户id',
    update_user_id     varchar(44)     null comment '最后更新此条数据的用户id',
    constraint tbl_scenic_primary_key primary key (id),
    constraint tbl_scenic_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_scenic_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
)
    comment '景点表';

create table tbl_ticket
(
    id                 varchar(44)     not null comment 'uuid',
    scenic_id          varchar(44)     null comment '景点id',
    type               smallint        null comment '门票类型, 1--成人票, 2--儿童票, 3--老人票',
    price              int(11)         null comment '门票价格',
    description        varchar(512)    null comment '门票描述',
    status             smallint        null comment '状态, 0: 无效 1: 有效',
    create_time        bigint          null comment '创建时间',
    update_time        bigint          null comment '最后更新时间',
    create_user_id     varchar(44)     null comment '增加此条数据的用户id',
    update_user_id     varchar(44)     null comment '最后更新此条数据的用户id',
    constraint tbl_ticket_pk primary key (id),
    constraint tbl_ticket_scenic_id_fk foreign key (scenic_id) references tbl_scenic (id),
    constraint tbl_ticket_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_ticket_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
)
    comment '景点门票表';


create table tbl_reservation
(
    id                 varchar(44)     not null comment 'uuid',
    user_id            varchar(44)     null comment '用户id',
    ticket_id          varchar(44)     null comment '门票id',
    reserve_time       bigint          null comment '门票预订的时间',
    quantity           int(11)         null comment '购买数量',
    status             smallint        null comment '状态, 0: 无效 1: 有效',
    create_time        bigint          null comment '创建时间',
    update_time        bigint          null comment '最后更新时间',
    create_user_id     varchar(44)     null comment '增加此条数据的用户id',
    update_user_id     varchar(44)     null comment '最后更新此条数据的用户id',
    constraint tbl_reservation_pk primary key (id),
    constraint tbl_reservation_user_id_fk_id foreign key (user_id) references tbl_user (id),
    constraint tbl_reservation_ticket_id_fk foreign key (ticket_id) references tbl_ticket (id),
    constraint tbl_reservation_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_reservation_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
)
    comment '门票预订表';


# rbac 权限
create table tbl_role
(
    id                 varchar(44)      not null comment 'uuid',
    name               varchar(128)     null comment '角色名称',
    status             smallint         null comment '状态, 0:禁用 1:启用',
    create_time        bigint           null comment '创建时间',
    update_time        bigint           null comment '最后更新时间',
    create_user_id     varchar(44)      null comment '增加此条数据的用户id',
    update_user_id     varchar(44)      null comment '最后更新此条数据的用户id',
    constraint tbl_role_pk primary key (id),
    constraint tbl_role_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_role_updated_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '角色表';


create table tbl_permission
(
    id              varchar(44)     not null comment 'uuid',
    name            varchar(128)    null comment '权限名称',
    api_path        varchar(255)    null comment 'API URL',
    method          varchar(32)     null comment '请求方式，GET,PUT,POST,DELETE',
    route_path      varchar(255)    null comment '路由 URL',
    type            smallint        null comment '权限类型；0：路由，1：API',
    status          SMALLINT        null comment '状态,0:禁用 1:启用',
    create_time    BIGINT          null comment '创建时间',
    update_time    BIGINT          null comment '更新时间',
    create_user_id VARCHAR(44)     null comment '记录创建人ID',
    update_user_id VARCHAR(44)     null comment '记录修改人ID',
    constraint tbl_permission_pk primary key (id),
    constraint tbl_permission_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_permission_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '权限表';

create table tbl_role_permission
(
    id              varchar(44) not null comment 'uuid',
    role_id         varchar(44) not null comment '角色id',
    permission_id   varchar(44) not null comment '权限id',
    status          SMALLINT comment '状态,0:禁用 1:启用',
    create_time    BIGINT comment '创建时间',
    update_time    BIGINT comment '更新时间',
    create_user_id VARCHAR(44) comment '记录创建人ID',
    update_user_id VARCHAR(44) comment '记录修改人ID',
    constraint tbl_role_permission_pk primary key (id),
    constraint tbl_role_permission_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_role_permission_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '角色权限表';
create table tbl_user_role
(
    id              varchar(44) not null comment 'uuid',
    role_id         varchar(44) not null comment '角色id',
    user_id         varchar(44) not null comment '用户id',
    status          SMALLINT comment '状态,0:禁用 1:启用',
    create_time     BIGINT comment '创建时间',
    update_time     BIGINT comment '更新时间',
    create_user_id  VARCHAR(44) comment '记录创建人ID',
    update_user_id  VARCHAR(44) comment '记录修改人ID',
    constraint tbl_user_role_pk primary key (id),
    constraint tbl_user_role_role_id_fk foreign key (role_id) references tbl_role (id),
    constraint tbl_user_role_user_id_fk foreign key (user_id) references tbl_user (id),
    constraint tbl_user_role_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_user_role_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '角色权限表';
CREATE TABLE tbl_order_form
(
    id                  VARCHAR(44) NOT NULL COMMENT '订单编号uuid',
    address             VARCHAR(255)  COMMENT '收货地址',
    express             VARCHAR(127) COMMENT '物流信息',
    fee                 INT(11) NULL COMMENT '实际付款金额(单位:分)',
    pay_status          SMALLINT  NOT NULL COMMENT '订单状态:未支付-0 已支付-1 待发货-2 已发货-3 已完成-4 已退款-5',
    has_evaluate        SMALLINT  COMMENT '是否已经评价:未评价-0 已评价-1',
    bind_evaluate_id    VARCHAR(44)  COMMENT '评价表的id',
    STATUS              SMALLINT COMMENT '状态,0:禁用 1:启用',
    pay_time        BIGINT COMMENT '支付时间',
    create_time     BIGINT COMMENT '创建时间',
    update_time     BIGINT COMMENT '更新时间',
    create_user_id  VARCHAR(44) NULL COMMENT '记录创建人ID',
    update_user_id  VARCHAR(44) NULL COMMENT '记录修改人ID',
    CONSTRAINT tbl_order_form_pk PRIMARY KEY (id),
    CONSTRAINT tbl_order_form_create_user_id_fk FOREIGN KEY (create_user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_order_form_update_user_id_fk FOREIGN KEY (update_user_id) REFERENCES tbl_user (id),
    CONSTRAINT tbl_order_form_bind_evaluate_id_fk FOREIGN KEY (bind_evaluate_id) REFERENCES tbl_evaluate (id)
) COMMENT '订单表';

create table tbl_deliveryInfo
(
    id                  varchar(44)  not null comment 'uuid',
    user_id             varchar(44)  not null comment '用户id',
    delivery_name       varchar(32)  null comment '收货人姓名',
    delivery_phone      varchar(32)  null comment '收货人电话',
    delivery_address    varchar(128) null comment '收货人地址',
    status              smallint     null comment '状态，0：不启用；1：启用',
    create_time         bigint       null comment '创建时间',
    update_time         bigint       null comment '最后更新时间',
    create_user_id      varchar(44)  null comment '增加此条数据的用户id',
    update_user_id      varchar(44)  null comment '最后更新此条数据的用户id',
    constraint tbl_deliveryInfo_pk primary key (id),
    constraint tbl_deliveryInfo_user_id_fk foreign key (user_id) references tbl_user (id),
    constraint tbl_deliveryInfo_create_user_id_fk foreign key (create_user_id) references tbl_user (id),
    constraint tbl_deliveryInfo_update_user_id_fk foreign key (update_user_id) references tbl_user (id)
) comment '收货地址信息表';

