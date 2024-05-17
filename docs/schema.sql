
ALTER TABLE tUserProject DROP FOREIGN KEY fkUserProjectContent1;
DROP TABLE tUser;
CREATE TABLE tUser(
    nUserSeq        INT             PRIMARY KEY AUTO_INCREMENT,
    sEmail          VARCHAR(40)     NOT NULL    UNIQUE,
    sPassword       VARCHAR(40)     NOT NULL,
    sNickName       VARCHAR(20)     NOT NULL    UNIQUE,
    sName           VARCHAR(20)     NOT NULL,
    sPhone          VARCHAR(20)     NOT NULL    UNIQUE,
    sImage          VARCHAR(500),
    sIntroduce      VARCHAR(400),
    dtCreatedAt     TIMESTAMP       NOT NULL    DEFAULT NOW(),
    dtModifiedAt    TIMESTAMP       NOT NULL    DEFAULT NOW()
);

ALTER TABLE tUserProject DROP FOREIGN KEY fkUserProjectContent2;
ALTER TABLE tProjectSkill DROP FOREIGN KEY fkProjectSkillContent1;
DROP TABLE tProject;
CREATE TABLE tProject(
    nProjectSeq     INT             PRIMARY KEY AUTO_INCREMENT,
    sTitle          VARCHAR(20)     NOT NULL,
    sDescription    VARCHAR(400),
    dtStart         DATE            NOT NULL,
    dtEnd           DATE            NOT NULL,
    bIsProceeding   TINYINT(1)      NOT NULL    DEFAULT 0,
    nHeadcount      SMALLINT        NOT NULL    DEFAULT 1,
    sArchitecture   VARCHAR(500),
    sErd            VARCHAR(500),
    sMainFunction   VARCHAR(1000),
    sInterest       VARCHAR(1000),
    sGithub         VARCHAR(500),
    bIsPublic       TINYINT(1)      NOT NULL    DEFAULT 1,
    dtCreatedAt     TIMESTAMP       NOT NULL    DEFAULT NOW(),
    dtModifiedAt    TIMESTAMP       NOT NULL    DEFAULT NOW()
);


DROP TABLE tUserProject;
CREATE TABLE tUserProject(
    nUserProjectSeq     INT         PRIMARY KEY AUTO_INCREMENT,
    nUserSeq            INT         NOT NULL,
    nProjectSeq         INT         NOT NULL,
    sMyPart             VARCHAR(1000)
);

ALTER TABLE tUserProject ADD CONSTRAINT fkUserProjectContent1
FOREIGN KEY(nUserSeq) REFERENCES tUser(nUserSeq) ON DELETE CASCADE;
ALTER TABLE tUserProject ADD CONSTRAINT fkUserProjectContent2
FOREIGN KEY(nProjectSeq) REFERENCES tProject(nProjectSeq) ON DELETE CASCADE;


ALTER TABLE tProjectSkill DROP FOREIGN KEY fkProjectSkillContent2;
DROP TABLE tSkill;
CREATE TABLE tSkill(
    nSkillSeq       INT             PRIMARY KEY AUTO_INCREMENT,
    sName           VARCHAR(40)     UNIQUE
);

DROP TABLE tProjectSkill;
CREATE TABLE tProjectSkill(
    nProjectSkillSeq    INT         PRIMARY KEY AUTO_INCREMENT,
    nProjectSeq         INT         NOT NULL,
    nSkillSeq           INT         NOT NULL,
    sVersion            VARCHAR(10) NOT NULL DEFAULT ''
);

ALTER TABLE tProjectSkill ADD CONSTRAINT fkProjectSkillContent1
FOREIGN KEY(nProjectSeq) REFERENCES tProject(nProjectSeq) ON DELETE CASCADE;

ALTER TABLE tProjectSkill ADD CONSTRAINT fkProjectSkillContent2
FOREIGN KEY(nSkillSeq) REFERENCES tSkill(nSkillSeq) ON DELETE CASCADE;
