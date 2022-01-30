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