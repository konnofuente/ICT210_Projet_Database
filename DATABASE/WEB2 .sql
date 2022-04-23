drop table if exists COURS;

drop table if exists DONNER;

drop table if exists ENSEIGNANT;

drop table if exists SALLE;

drop table if exists SEANCE;

drop table if exists UE;

/*==============================================================*/
/* Table: COURS                                                 */
/*==============================================================*/
create table COURS
(
   IDCOURS              int not null,
   IDSEANCE             int not null,
   IDUE                 int not null,
   ID_SALLE             int not null,
   JOUR                 date,
   primary key (IDCOURS),
   key AK_IDENTIFIER_2 (IDCOURS)
);

/*==============================================================*/
/* Table: DONNER                                                */
/*==============================================================*/
create table DONNER
(
   IDCOURS              int not null,
   IDENSEIGNANT         int not null,
   primary key (IDCOURS, IDENSEIGNANT)
);

/*==============================================================*/
/* Table: ENSEIGNANT                                            */
/*==============================================================*/
create table ENSEIGNANT
(
   IDENSEIGNANT         int not null,
   NOM_ENSEIGNANT       char(30),
   SEXE                 char(1),
   primary key (IDENSEIGNANT),
   key AK_IDENTIFIER_2 (IDENSEIGNANT)
);

/*==============================================================*/
/* Table: SALLE                                                 */
/*==============================================================*/
create table SALLE
(
   ID_SALLE             int not null,
   NOM_SALLE            char(20),
   primary key (ID_SALLE)
);

/*==============================================================*/
/* Table: SEANCE                                                */
/*==============================================================*/
create table SEANCE
(
   IDSEANCE             int not null,
   HEURE_DEBUT          time,
   HEURE_FIN            time,
   primary key (IDSEANCE),
   key AK_IDENTIFIER_2 (IDSEANCE)
);

/*==============================================================*/
/* Table: UE                                                    */
/*==============================================================*/
create table UE
(
   IDUE                 int not null,
   NOM_UE               char(25),
   primary key (IDUE)
);

alter table COURS add constraint FK_CONCERNER foreign key (IDUE)
      references UE (IDUE) on delete restrict on update restrict;

alter table COURS add constraint FK_DEROULER foreign key (ID_SALLE)
      references SALLE (ID_SALLE) on delete restrict on update restrict;

alter table COURS add constraint FK_SE_PASSER foreign key (IDSEANCE)
      references SEANCE (IDSEANCE) on delete restrict on update restrict;

alter table DONNER add constraint FK_DONNER foreign key (IDCOURS)
      references COURS (IDCOURS) on delete restrict on update restrict;

alter table DONNER add constraint FK_DONNER2 foreign key (IDENSEIGNANT)
      references ENSEIGNANT (IDENSEIGNANT) on delete restrict on update restrict;
