CREATE TABLE "comm_code" (
	"seq"	serial4		NOT NULL,
	"code"	varchar(10)		NOT NULL,
	"parent"	varchar(10)		NOT NULL,
	"name"	varchar(20)		NOT NULL,
	"desc"	varchar(30)		NULL,
	"depth"	int2		NOT NULL,
	"order"	int2		NOT NULL,
	"use_yn"	char(1)	DEFAULT 'Y'	NOT NULL,
	"etc"	varchar(30)		NULL,
	"indt"	timestamptz	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"in_user"	int4		NOT NULL,
	"updt"	timestamptz	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"up_user"	int4		NOT NULL
);

COMMENT ON COLUMN "comm_code"."seq" IS '공통코드 시퀀스';

COMMENT ON COLUMN "comm_code"."code" IS '코드';

COMMENT ON COLUMN "comm_code"."parent" IS '부모코드';

COMMENT ON COLUMN "comm_code"."name" IS '코드 이름';

COMMENT ON COLUMN "comm_code"."desc" IS '코드 설명';

COMMENT ON COLUMN "comm_code"."depth" IS '코드 뎁스';

COMMENT ON COLUMN "comm_code"."order" IS '코드 순서';

COMMENT ON COLUMN "comm_code"."use_yn" IS '사용여부';

COMMENT ON COLUMN "comm_code"."etc" IS '기타 정보';

COMMENT ON COLUMN "comm_code"."indt" IS '등록일시';

COMMENT ON COLUMN "comm_code"."in_user" IS '등록자 시퀀스';

COMMENT ON COLUMN "comm_code"."updt" IS '수정일시';

COMMENT ON COLUMN "comm_code"."up_user" IS '수정자 시퀀스';

CREATE TABLE "menu" (

);

CREATE TABLE "login" (
	"seq"	serial4		NOT NULL,
	"id"	varchar(100)		NOT NULL,
	"password"	varchar(200)		NOT NULL,
	"join_dt"	timestamptz	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"update_dt"	timestamptz	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"login_count"	integer	DEFAULT 0	NOT NULL,
	"recent_login"	timestamptz		NULL,
	"accessible_ip"	text		NULL
);

COMMENT ON COLUMN "login"."seq" IS '로그인 테이블 시퀀스';

COMMENT ON COLUMN "login"."id" IS '회원 ID (SEED 암호화)';

COMMENT ON COLUMN "login"."password" IS '회원 비밀번호(scrypt 암호화)';

COMMENT ON COLUMN "login"."join_dt" IS '회원가입일시';

COMMENT ON COLUMN "login"."update_dt" IS '정보 수정 일시';

COMMENT ON COLUMN "login"."login_count" IS '로그인 횟수';

COMMENT ON COLUMN "login"."recent_login" IS '최근 로그인 시간';

COMMENT ON COLUMN "login"."accessible_ip" IS '접근가능 IP, \n으로 구분';

CREATE TABLE "user_info" (
	"seq"	serial4		NOT NULL,
	"login_seq"	serial4		NOT NULL,
	"name"	varchar(100)		NULL,
	"ninkname"	varchar(20)		NULL,
	"phone"	varchar(100)		NOT NULL,
	"address"	varchar(500)		NULL,
	"sex"	char(1)		NULL,
	"mailing_yn"	char(1)	DEFAULT 'N'	NOT NULL,
	"sms_yn"	char(1)	DEFAULT 'N'	NOT NULL,
	"email"	varchar(100)		NOT NULL
);

COMMENT ON COLUMN "user_info"."seq" IS '회원테이블 시퀀스';

COMMENT ON COLUMN "user_info"."login_seq" IS '로그인 테이블 시퀀스';

COMMENT ON COLUMN "user_info"."name" IS '회원 이름 (SEED 암호화)';

COMMENT ON COLUMN "user_info"."ninkname" IS '회원 닉네임';

COMMENT ON COLUMN "user_info"."phone" IS '회원 전화번호 (SEED 암호화)';

COMMENT ON COLUMN "user_info"."address" IS '회원 주소, |!|로 구분(SEED 암호화)';

COMMENT ON COLUMN "user_info"."sex" IS '회원 성별, M: 남자, F: 여자';

COMMENT ON COLUMN "user_info"."mailing_yn" IS '이메일 수신 여부';

COMMENT ON COLUMN "user_info"."sms_yn" IS 'SMS 수신 여부';

COMMENT ON COLUMN "user_info"."email" IS '회원 이메일(SEED 암호화)';

CREATE TABLE "login_log" (
	"seq"	serial8		NOT NULL,
	"login_seq"	serial4		NOT NULL,
	"login_dt"	timestamptz	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"login_ip"	varchar(30)		NOT NULL,
	"login_type"	char(1)		NOT NULL
);

COMMENT ON COLUMN "login_log"."seq" IS '로그인 로그 테이블 시퀀스';

COMMENT ON COLUMN "login_log"."login_seq" IS '로그인 테이블 시퀀스';

COMMENT ON COLUMN "login_log"."login_dt" IS '로그인 일시';

COMMENT ON COLUMN "login_log"."login_ip" IS '로그인 IP';

COMMENT ON COLUMN "login_log"."login_type" IS '로그인 타입, 일반: U, 구글: G, 네이버: N. 카카오: K. 애플: A';

CREATE TABLE "access_log" (
	"seq"	serial8		NOT NULL,
	"session_id"	varchar(100)		NOT NULL,
	"access_ip"	varchar(30)		NULL,
	"user_agent"	varchar(200)		NULL,
	"referer"	varcahr(500)		NULL,
	"browser"	varchar(20)		NULL,
	"os"	varchar(20)		NULL,
	"date"	timestamptz	DEFAULT CURRENT_TIMESTAMP	NOT NULL
);

COMMENT ON COLUMN "access_log"."seq" IS '접속로그 시퀀스';

COMMENT ON COLUMN "access_log"."session_id" IS '세션 ID';

COMMENT ON COLUMN "access_log"."access_ip" IS '접속 IP';

COMMENT ON COLUMN "access_log"."user_agent" IS 'User Agent';

COMMENT ON COLUMN "access_log"."referer" IS 'referer';

COMMENT ON COLUMN "access_log"."browser" IS '접속 브라우저';

COMMENT ON COLUMN "access_log"."os" IS '접속 OS';

COMMENT ON COLUMN "access_log"."date" IS '접속일시';

ALTER TABLE "comm_code" ADD CONSTRAINT "PK_COMM_CODE" PRIMARY KEY (
	"seq"
);

ALTER TABLE "login" ADD CONSTRAINT "PK_LOGIN" PRIMARY KEY (
	"seq"
);

ALTER TABLE "user_info" ADD CONSTRAINT "PK_USER_INFO" PRIMARY KEY (
	"seq",
	"login_seq"
);

ALTER TABLE "login_log" ADD CONSTRAINT "PK_LOGIN_LOG" PRIMARY KEY (
	"seq",
	"login_seq"
);

ALTER TABLE "access_log" ADD CONSTRAINT "PK_ACCESS_LOG" PRIMARY KEY (
	"seq"
);

ALTER TABLE "user_info" ADD CONSTRAINT "FK_login_TO_user_info_1" FOREIGN KEY (
	"login_seq"
)
REFERENCES "login" (
	"seq"
);

ALTER TABLE "login_log" ADD CONSTRAINT "FK_login_TO_login_log_1" FOREIGN KEY (
	"login_seq"
)
REFERENCES "login" (
	"seq"
);

