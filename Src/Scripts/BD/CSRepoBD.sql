PGDMP         0            
    u           csrepo    9.6.5    10.0 8    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �	           1262    16438    csrepo    DATABASE     d   CREATE DATABASE csrepo WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE csrepo;
             score    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             score    false            �	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  score    false    3            �	           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO score;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  score    false    3                        3079    12655    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16473    Author    TABLE     o   CREATE TABLE "Author" (
    "id_Author" integer NOT NULL,
    "name_Author" character varying(255) NOT NULL
);
    DROP TABLE public."Author";
       public         score    false    3            �            1259    16512    AuthorPaper    TABLE     F   CREATE TABLE "AuthorPaper" (
    "id_AuthorPaper" integer NOT NULL
);
 !   DROP TABLE public."AuthorPaper";
       public         score    false    3            �            1259    16541    AuthorPaper_id_AuthorPaper_seq    SEQUENCE     �   CREATE SEQUENCE "AuthorPaper_id_AuthorPaper_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public."AuthorPaper_id_AuthorPaper_seq";
       public       score    false    195    3            �	           0    0    AuthorPaper_id_AuthorPaper_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE "AuthorPaper_id_AuthorPaper_seq" OWNED BY "AuthorPaper"."id_AuthorPaper";
            public       score    false    196            �            1259    16471    Author_id_Author_seq    SEQUENCE     x   CREATE SEQUENCE "Author_id_Author_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public."Author_id_Author_seq";
       public       score    false    3    190            �	           0    0    Author_id_Author_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE "Author_id_Author_seq" OWNED BY "Author"."id_Author";
            public       score    false    189            �            1259    16463 
   Conference    TABLE     �   CREATE TABLE "Conference" (
    "id_Conference" integer NOT NULL,
    "acronym_Conference" character varying(10) NOT NULL,
    "name_Conference" character varying(255) NOT NULL
);
     DROP TABLE public."Conference";
       public         score    false    3            �            1259    16461    Conference_id_Conference_seq    SEQUENCE     �   CREATE SEQUENCE "Conference_id_Conference_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public."Conference_id_Conference_seq";
       public       score    false    188    3            �	           0    0    Conference_id_Conference_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE "Conference_id_Conference_seq" OWNED BY "Conference"."id_Conference";
            public       score    false    187            �            1259    16499    Paper    TABLE       CREATE TABLE "Paper" (
    "id_Paper" integer NOT NULL,
    "title_Paper" character varying(255) NOT NULL,
    "pages_Paper" integer NOT NULL,
    "year_Publication_Paper" integer NOT NULL,
    "first_Page_Paper" integer NOT NULL,
    "last_Page_Paper" integer NOT NULL
);
    DROP TABLE public."Paper";
       public         score    false    3            �            1259    16497    Paper_id_Paper_seq    SEQUENCE     v   CREATE SEQUENCE "Paper_id_Paper_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."Paper_id_Paper_seq";
       public       score    false    3    194            �	           0    0    Paper_id_Paper_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE "Paper_id_Paper_seq" OWNED BY "Paper"."id_Paper";
            public       score    false    193            �            1259    16452 	   Publisher    TABLE     �   CREATE TABLE "Publisher" (
    "id_Publisher" integer NOT NULL,
    "link_Publisher" character varying(255) NOT NULL,
    "name_Publisher" character varying(255) NOT NULL,
    "acronym_Publisher" character varying(10) NOT NULL
);
    DROP TABLE public."Publisher";
       public         score    false    3            �            1259    16450    Publisher_id_Publisher_seq    SEQUENCE     ~   CREATE SEQUENCE "Publisher_id_Publisher_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public."Publisher_id_Publisher_seq";
       public       score    false    186    3            �	           0    0    Publisher_id_Publisher_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE "Publisher_id_Publisher_seq" OWNED BY "Publisher"."id_Publisher";
            public       score    false    185            �            1259    16481    Volume    TABLE     `   CREATE TABLE "Volume" (
    "id_Volume" integer NOT NULL,
    "year_Volume" integer NOT NULL
);
    DROP TABLE public."Volume";
       public         score    false    3            �            1259    16479    Volume_id_Volume_seq    SEQUENCE     x   CREATE SEQUENCE "Volume_id_Volume_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public."Volume_id_Volume_seq";
       public       score    false    3    192            �	           0    0    Volume_id_Volume_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE "Volume_id_Volume_seq" OWNED BY "Volume"."id_Volume";
            public       score    false    191            �           2604    16476    Author id_Author    DEFAULT     l   ALTER TABLE ONLY "Author" ALTER COLUMN "id_Author" SET DEFAULT nextval('"Author_id_Author_seq"'::regclass);
 C   ALTER TABLE public."Author" ALTER COLUMN "id_Author" DROP DEFAULT;
       public       score    false    189    190    190            �           2604    16543    AuthorPaper id_AuthorPaper    DEFAULT     �   ALTER TABLE ONLY "AuthorPaper" ALTER COLUMN "id_AuthorPaper" SET DEFAULT nextval('"AuthorPaper_id_AuthorPaper_seq"'::regclass);
 M   ALTER TABLE public."AuthorPaper" ALTER COLUMN "id_AuthorPaper" DROP DEFAULT;
       public       score    false    196    195            �           2604    16466    Conference id_Conference    DEFAULT     |   ALTER TABLE ONLY "Conference" ALTER COLUMN "id_Conference" SET DEFAULT nextval('"Conference_id_Conference_seq"'::regclass);
 K   ALTER TABLE public."Conference" ALTER COLUMN "id_Conference" DROP DEFAULT;
       public       score    false    188    187    188            �           2604    16502    Paper id_Paper    DEFAULT     h   ALTER TABLE ONLY "Paper" ALTER COLUMN "id_Paper" SET DEFAULT nextval('"Paper_id_Paper_seq"'::regclass);
 A   ALTER TABLE public."Paper" ALTER COLUMN "id_Paper" DROP DEFAULT;
       public       score    false    193    194    194            �           2604    16455    Publisher id_Publisher    DEFAULT     x   ALTER TABLE ONLY "Publisher" ALTER COLUMN "id_Publisher" SET DEFAULT nextval('"Publisher_id_Publisher_seq"'::regclass);
 I   ALTER TABLE public."Publisher" ALTER COLUMN "id_Publisher" DROP DEFAULT;
       public       score    false    186    185    186            �           2604    16484    Volume id_Volume    DEFAULT     l   ALTER TABLE ONLY "Volume" ALTER COLUMN "id_Volume" SET DEFAULT nextval('"Volume_id_Volume_seq"'::regclass);
 C   ALTER TABLE public."Volume" ALTER COLUMN "id_Volume" DROP DEFAULT;
       public       score    false    191    192    192            �	          0    16473    Author 
   TABLE DATA               7   COPY "Author" ("id_Author", "name_Author") FROM stdin;
    public       score    false    190   �;       �	          0    16512    AuthorPaper 
   TABLE DATA               2   COPY "AuthorPaper" ("id_AuthorPaper") FROM stdin;
    public       score    false    195   <       �	          0    16463 
   Conference 
   TABLE DATA               Y   COPY "Conference" ("id_Conference", "acronym_Conference", "name_Conference") FROM stdin;
    public       score    false    188   5<       �	          0    16499    Paper 
   TABLE DATA               �   COPY "Paper" ("id_Paper", "title_Paper", "pages_Paper", "year_Publication_Paper", "first_Page_Paper", "last_Page_Paper") FROM stdin;
    public       score    false    194   �<       �	          0    16452 	   Publisher 
   TABLE DATA               g   COPY "Publisher" ("id_Publisher", "link_Publisher", "name_Publisher", "acronym_Publisher") FROM stdin;
    public       score    false    186   �<       �	          0    16481    Volume 
   TABLE DATA               7   COPY "Volume" ("id_Volume", "year_Volume") FROM stdin;
    public       score    false    192   �<       �	           0    0    AuthorPaper_id_AuthorPaper_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('"AuthorPaper_id_AuthorPaper_seq"', 1, false);
            public       score    false    196            �	           0    0    Author_id_Author_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('"Author_id_Author_seq"', 1, false);
            public       score    false    189            �	           0    0    Conference_id_Conference_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('"Conference_id_Conference_seq"', 2, true);
            public       score    false    187            �	           0    0    Paper_id_Paper_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('"Paper_id_Paper_seq"', 1, false);
            public       score    false    193            �	           0    0    Publisher_id_Publisher_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('"Publisher_id_Publisher_seq"', 1, false);
            public       score    false    185            �	           0    0    Volume_id_Volume_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('"Volume_id_Volume_seq"', 1, false);
            public       score    false    191            	           2606    16548    AuthorPaper AuthorPaper_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY "AuthorPaper"
    ADD CONSTRAINT "AuthorPaper_pkey" PRIMARY KEY ("id_AuthorPaper");
 J   ALTER TABLE ONLY public."AuthorPaper" DROP CONSTRAINT "AuthorPaper_pkey";
       public         score    false    195            	           2606    16478    Author Author_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY "Author"
    ADD CONSTRAINT "Author_pkey" PRIMARY KEY ("id_Author");
 @   ALTER TABLE ONLY public."Author" DROP CONSTRAINT "Author_pkey";
       public         score    false    190            	           2606    16468    Conference Conference_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY "Conference"
    ADD CONSTRAINT "Conference_pkey" PRIMARY KEY ("id_Conference");
 H   ALTER TABLE ONLY public."Conference" DROP CONSTRAINT "Conference_pkey";
       public         score    false    188            	           2606    16504    Paper Paper_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY "Paper"
    ADD CONSTRAINT "Paper_pkey" PRIMARY KEY ("id_Paper");
 >   ALTER TABLE ONLY public."Paper" DROP CONSTRAINT "Paper_pkey";
       public         score    false    194            	           2606    16460    Publisher Publisher_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY "Publisher"
    ADD CONSTRAINT "Publisher_pkey" PRIMARY KEY ("id_Publisher");
 F   ALTER TABLE ONLY public."Publisher" DROP CONSTRAINT "Publisher_pkey";
       public         score    false    186            		           2606    16486    Volume Volume_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY "Volume"
    ADD CONSTRAINT "Volume_pkey" PRIMARY KEY ("id_Volume");
 @   ALTER TABLE ONLY public."Volume" DROP CONSTRAINT "Volume_pkey";
       public         score    false    192            	           2606    16470    Conference name_un 
   CONSTRAINT     U   ALTER TABLE ONLY "Conference"
    ADD CONSTRAINT name_un UNIQUE ("name_Conference");
 >   ALTER TABLE ONLY public."Conference" DROP CONSTRAINT name_un;
       public         score    false    188            	           2606    16549    AuthorPaper id_Author    FK CONSTRAINT        ALTER TABLE ONLY "AuthorPaper"
    ADD CONSTRAINT "id_Author" FOREIGN KEY ("id_AuthorPaper") REFERENCES "Author"("id_Author");
 C   ALTER TABLE ONLY public."AuthorPaper" DROP CONSTRAINT "id_Author";
       public       score    false    195    2311    190            	           2606    16487    Volume id_Conference    FK CONSTRAINT     �   ALTER TABLE ONLY "Volume"
    ADD CONSTRAINT "id_Conference" FOREIGN KEY ("id_Volume") REFERENCES "Conference"("id_Conference");
 B   ALTER TABLE ONLY public."Volume" DROP CONSTRAINT "id_Conference";
       public       score    false    188    192    2307            	           2606    16554    AuthorPaper id_Paper    FK CONSTRAINT     |   ALTER TABLE ONLY "AuthorPaper"
    ADD CONSTRAINT "id_Paper" FOREIGN KEY ("id_AuthorPaper") REFERENCES "Paper"("id_Paper");
 B   ALTER TABLE ONLY public."AuthorPaper" DROP CONSTRAINT "id_Paper";
       public       score    false    194    2315    195            	           2606    16492    Volume id_Publisher    FK CONSTRAINT     ~   ALTER TABLE ONLY "Volume"
    ADD CONSTRAINT "id_Publisher" FOREIGN KEY ("id_Volume") REFERENCES "Publisher"("id_Publisher");
 A   ALTER TABLE ONLY public."Volume" DROP CONSTRAINT "id_Publisher";
       public       score    false    192    186    2305            	           2606    16505    Paper id_Volume    FK CONSTRAINT     s   ALTER TABLE ONLY "Paper"
    ADD CONSTRAINT "id_Volume" FOREIGN KEY ("id_Paper") REFERENCES "Volume"("id_Volume");
 =   ALTER TABLE ONLY public."Paper" DROP CONSTRAINT "id_Volume";
       public       score    false    2313    194    192            �	      x������ � �      �	      x������ � �      �	   g   x���=@0����D�D:XHt�4����M���c3���B5�5�aB�K>ЭRΈ��(6��tb�x�sѥ�Uk�a��k۷L����3q��>�!�Z�g&2>      �	      x������ � �      �	      x������ � �      �	      x������ � �     