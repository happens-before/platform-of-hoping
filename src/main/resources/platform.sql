id 自增主键
news_id 新闻编号  32位uuid
news_name 新闻名称
news_description  新闻描述
news_content  新闻内容
news_creator  新闻创建者
status 新闻状态
news_count 新闻访问量
published_time  发布时间
create_time 创建时间
update_time 更新时间
create table `news`(
`id` int(11) unsigned not null auto_increment comment '自增主键',
`news_id` varchar(32) not null default '' comment '新闻编号',
`news_name` varchar(128) not null default '' comment '新闻名称',
`news_description` varchar(200) not null default '' comment '新闻描述',
`news_content` varchar(3000) not null default '' comment '新闻内容',
`news_creator` varchar(32) not null default '' comment '新闻创建者',
`valid` tinyint(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT ' 0 1 ',
`status` tinyint(4) unsigned not null default '00' comment '00 未发布 10 已发布 20 暂停 30全部',
`news_count` int(11) unsigned not null default '0' comment '新闻访问量',
`published_time` datetime not null default current_timestamp comment '发布时间',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_news_id` (`news_id`),
index `idx_news_name` (news_name)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='新闻表';

id 自增主键
picture_id 图片编号 唯一索引
news_id 新闻编号
picture_path 图片路径
create_time 创建时间
update_time 更新时间

create table `picture` (
`id` int(11) unsigned not null auto_increment comment '自增主键',
`picture_id` varchar(32) not null default '' comment '图片编号',
`news_id` varchar(32) not null default '' comment '新闻编号',
`picture_path` varchar(32) not null default '' comment '图片路径',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_picture_id` (`picture_id`),
index `idx_news_id` (`news_id`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='新闻图片表';

id  自增主键
comment_id 评论编号 32位uuid
news_id  新闻编号
reviewer 评论者
comment_content 评论内容
create_time 创建时间
update_time 更新时间

create table `comment` (
`id` int(11) unsigned not null auto_increment comment '自增主键',
`comment_id` varchar(32) not null default '' comment '评论编号',
`news_id` varchar(32) not null default '' comment '新闻编号',
`reviewer` varchar(30) not null default '' comment '评论者',
`comment_content` varchar(200) not null default '' comment '评论内容',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_comment_id` (`comment_id`),
index `idx_news_id` (`news_id`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='评论信息表';
id 自增主键
reply_id 追评编号 32位uuid
comment_id  评论编号
replier 追评姓名
reply_content 追评内容
create_time 创建时间
update_time 更新时间
create table `reply` (
`id` int(11) unsigned not null auto_increment comment '自增主键',
`reply_id` varchar(32) not null default '' comment '追评编号',
`comment_id` varchar(32) not null default '' comment '评论编号',
`replier` varchar(30) not null default '' comment '追评姓名',
`reply_content` varchar(200) not null default '' comment '追评内容',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_reply_id` (`reply_id`),
index `idx_comment_id` (`comment_id`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='追评信息表';
id 自增主键
user_id 用户编号 唯一索引
school学校
college 学院
profession 专业
user_sno用户学号
community 社团
minister   是否是社团部长  社团部长才可以申请创建活动
user_name用户姓名
sex用户性别
age 用户年龄
email用户邮件
phone 电话号码
password  密码
create_time 创建时间
update_time 更新时间
create table `user` (
`id` int(11) unsigned not null auto_increment comment '自增主键',
`user_id` varchar(32) not null default '' comment '用户编号',
`school` varchar(20) not null default '' comment '所在学校',
`college` varchar(30) not null default '' comment '所在学院',
`profession` varchar(30) not null default '' comment '所在专业',
`user_sno` varchar(20) not null default '' comment '用户学号',
`community` varchar(30) not null default '' comment '所在社团',
`minister` tinyint(1) not null default '0' comment '0 不是社团部长 1 是社团部长',
`user_name` varchar(30) not null default '' comment '用户姓名',
`sex` varchar(2) not null default '' comment '性别' ,
`age` tinyint(4) not null default '0' comment '年龄',
`email` varchar(20) not null default '' comment '电子邮件',
`phone` varchar(11) not null default '' comment '电话号码',
`password` varchar(20) not null default '123456' comment '密码',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_user_id` (`user_id`),
index `idx_user_name` (`user_name`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='用户信息表';
id 自增主键
organizer_id 组织者编号
organizer_city 组织所在地
organizer_name 组织名称
organizer_phone  组织联系方式
organizer_principal  组织负责人
organizer_description 组织描述
create_time 创建时间
update_time 更新时间
create table `organizer` (
`id` int(11) unsigned not null auto_increment comment '自增主键',
`organizer_id` varchar(32) not null default '' comment '组织者编号',
`organizer_city` varchar(50) not null default '' comment '组织所在地',
`organizer_name` varchar(50) not null default '' comment '组织名称',
`organizer_phone` varchar(11) not null default '' comment '组织联系方式',
`organizer_principal` varchar(32) not null default '' comment '组织负责人',
`organizer_description` varchar(500) not null default '' comment '组织描述',
`password` varchar(20) not null default '123456' comment '密码',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_organizer_id` (`organizer_id`),
index `idx_organizer_name` (`organizer_name`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='组织信息表';

id 自增主键
admin_id 管理员编号 唯一索引
admin_name 管理员姓名
valid 是否可用
create_time 创建时间
update_time 更新时间
create table `admin` (
`id` int(11) unsigned not null auto_increment comment '自增主键',
`admin_id` varchar(32) not null default '' comment '管理员编号',
`admin_name` varchar(32) not null default '' comment '管理员姓名',
`valid` tinyint(1) not null default '0' comment '是否是管理员',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_admin_id` (`admin_id`),
index `idx_admin_name` (`admin_name`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='管理员信息表';
id 自增主键
activity_id 活动编号  唯一索引
promoter 活动发起人 （社团部长）
organizer 活动参与的组织（养老院）
activity_topic 活动主题
activity_content 活动内容
activity_status 活动状态
start_time 开始时间
end_time 结束时间
published_time  发布时间
create_time 创建时间
update_time 更新时间
create table `activity`(
`id` int(11) unsigned not null auto_increment comment '自增主键',
`activity_id` varchar(32) not null default '' comment '活动编号',
`promoter` varchar(32) not null default '' comment '活动发起人 （社团部长）',
`organizer` varchar(50) not null default '' comment '活动参与的组织（养老院）',
`activity_topic` varchar(128) not null default '' comment '活动主题',
`activity_content` varchar(3000) not null default '' comment '活动内容',
`valid` tinyint(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT ' 0 1 ',
`activity_status` tinyint(4) unsigned not null default '00' comment '00 未发布 10 已发布但未开始 20 进行中 30 活动结束',
`start_time` datetime not null default current_timestamp comment '开始时间',
`end_time` datetime not null default current_timestamp comment '结束时间',
`published_time` datetime not null default current_timestamp comment '发布时间',
`create_time` datetime not null default current_timestamp comment '创建时间',
`update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
primary key (`id`),
index `idx_activity_id` (`activity_id`),
index `idx_activity_topic` (`activity_topic`)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='活动信息表';