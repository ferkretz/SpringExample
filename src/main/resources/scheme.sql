CREATE TABLE Activities (
    id              int(10)      unsigned NOT NULL auto_increment,
    slug            varchar(64)           NOT NULL default '',
    name            varchar(64)           NOT NULL default '',
    description     text,
    icon            varchar(255)          NOT NULL default '',
    minPosts        smallint(5)  unsigned NOT NULL default '0',
    PRIMARY KEY (id),
    UNIQUE  KEY (slug),
            KEY (name)
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Roles (
    id              int(10)      unsigned NOT NULL auto_increment,
    slug            varchar(64)           NOT NULL default '',
    name            varchar(64)           NOT NULL default '',
    description     text,
    color           varchar(6)            NOT NULL default '',
    icon            varchar(255)          NOT NULL default '',
    PRIMARY KEY (id),
    UNIQUE  KEY (slug),
            KEY (name)
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Users (
    id              int(10)      unsigned NOT NULL auto_increment,
    username        varchar(32)           NOT NULL default '',
    password        varchar(64)           NOT NULL default '',
    firstName       varchar(32)           NOT NULL default '',
    lastName        varchar(32)           NOT NULL default '',
    displayName     varchar(64)           NOT NULL default '',
    email           varchar(255)          NOT NULL default '',
    timeZone        varchar(255)          NOT NULL default '',
    locale          varchar(255)          NOT NULL default '',
    avatar          varchar(255)          NOT NULL default '',
    signature       varchar(255)          NOT NULL default '',
    birthDate       date                  NOT NULL default '0000-00-00',
    registeredDate  datetime              NOT NULL default '0000-00-00 00:00:00',
    lastLoginDate   datetime              NOT NULL default '0000-00-00 00:00:00',
    posts           mediumint(8) unsigned NOT NULL default '0',
    messages        smallint(5)  unsigned NOT NULL default '0',
    activityId      int(10)      unsigned NOT NULL default '1',
    unreadMessages  smallint(5)  unsigned NOT NULL default '0',
    expirationDate  date                  NOT NULL default '0000-00-00',
    enabled         boolean               NOT NULL default '1',
    PRIMARY KEY (id),
    UNIQUE  KEY (username),
    UNIQUE  KEY (email),
            KEY (activityId),
    FOREIGN KEY (activityId) REFERENCES Activities(id) ON DELETE RESTRICT
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE UserRoleRelations (
    userId          int(10)      unsigned NOT NULL default '0',
    roleId          int(10)      unsigned NOT NULL default '0',
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (roleId) REFERENCES Roles(id) ON DELETE CASCADE
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

insert into Roles (slug, name, color) values ('administrator', 'Administrator', 'FF0000');
insert into Roles (slug, name, color) values ('moderator', 'Moderator', '00FF00');
insert into Roles (slug, name, color) values ('member', 'Member', '0000FF');

insert into Activities (slug, name, minPosts) values ('newbie', 'Newbie', '0');
insert into Activities (slug, name, minPosts) values ('jr_member', 'Jr. Member', '50');
insert into Activities (slug, name, minPosts) values ('sr_member', 'Sr. Member', '100');
insert into Activities (slug, name, minPosts) values ('hero_member', 'Hero Member', '500');
insert into Activities (slug, name, minPosts) values ('stalwart', 'Stalwart', '1000');
insert into Activities (slug, name, minPosts) values ('king_of_the_yackers', 'King of the Yackers', '5000');
insert into Activities (slug, name, minPosts) values ('yackadoo', 'Yackadoo', '10000');
insert into Activities (slug, name, minPosts) values ('lepus_singularis', 'Lepus Singularis', '20000');
