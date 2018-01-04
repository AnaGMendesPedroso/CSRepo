PGDMP         +                u           csrepo    9.6.5    10.0 7    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
                       false    1            �            1259    16473    author    TABLE     i   CREATE TABLE author (
    id_author integer NOT NULL,
    name_author character varying(255) NOT NULL
);
    DROP TABLE public.author;
       public         score    false    3            �            1259    16471    author_id_author_seq    SEQUENCE     u   CREATE SEQUENCE author_id_author_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.author_id_author_seq;
       public       score    false    190    3            �	           0    0    author_id_author_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE author_id_author_seq OWNED BY author.id_author;
            public       score    false    189            �            1259    16593    authorpaper    TABLE     \   CREATE TABLE authorpaper (
    id_author integer NOT NULL,
    id_paper integer NOT NULL
);
    DROP TABLE public.authorpaper;
       public         score    false    3            �            1259    16463 
   conference    TABLE     �   CREATE TABLE conference (
    id_conference integer NOT NULL,
    acronym_conference character varying(30) NOT NULL,
    name_conference character varying(255) NOT NULL
);
    DROP TABLE public.conference;
       public         score    false    3            �            1259    16461    conference_id_conference_seq    SEQUENCE     ~   CREATE SEQUENCE conference_id_conference_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.conference_id_conference_seq;
       public       score    false    188    3            �	           0    0    conference_id_conference_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE conference_id_conference_seq OWNED BY conference.id_conference;
            public       score    false    187            �            1259    16481    edition    TABLE     �   CREATE TABLE edition (
    id_edition integer NOT NULL,
    year_edition integer NOT NULL,
    conference_edition integer NOT NULL,
    publisher_edition integer NOT NULL
);
    DROP TABLE public.edition;
       public         score    false    3            �            1259    16479    edition_id_edition_seq    SEQUENCE     x   CREATE SEQUENCE edition_id_edition_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.edition_id_edition_seq;
       public       score    false    3    192            �	           0    0    edition_id_edition_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE edition_id_edition_seq OWNED BY edition.id_edition;
            public       score    false    191            �            1259    16499    paper    TABLE     Y  CREATE TABLE paper (
    id_paper integer NOT NULL,
    title_paper character varying(255) NOT NULL,
    pages_paper integer NOT NULL,
    year_publication_paper integer NOT NULL,
    first_page_paper integer NOT NULL,
    last_page_paper integer NOT NULL,
    conference_paper integer,
    publisher_paper integer,
    edition_paper integer
);
    DROP TABLE public.paper;
       public         score    false    3            �            1259    16497    paper_id_paper_seq    SEQUENCE     s   CREATE SEQUENCE paper_id_paper_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.paper_id_paper_seq;
       public       score    false    3    194            �	           0    0    paper_id_paper_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE paper_id_paper_seq OWNED BY paper.id_paper;
            public       score    false    193            �            1259    16452 	   publisher    TABLE     �   CREATE TABLE publisher (
    id_publisher integer NOT NULL,
    link_publisher character varying(255) NOT NULL,
    name_publisher character varying(255) NOT NULL,
    acronym_publisher character varying(10) NOT NULL
);
    DROP TABLE public.publisher;
       public         score    false    3            �            1259    16450    publisher_id_publisher_seq    SEQUENCE     |   CREATE SEQUENCE publisher_id_publisher_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.publisher_id_publisher_seq;
       public       score    false    3    186            �	           0    0    publisher_id_publisher_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE publisher_id_publisher_seq OWNED BY publisher.id_publisher;
            public       score    false    185            �           2604    16476    author id_author    DEFAULT     f   ALTER TABLE ONLY author ALTER COLUMN id_author SET DEFAULT nextval('author_id_author_seq'::regclass);
 ?   ALTER TABLE public.author ALTER COLUMN id_author DROP DEFAULT;
       public       score    false    190    189    190            �           2604    16466    conference id_conference    DEFAULT     v   ALTER TABLE ONLY conference ALTER COLUMN id_conference SET DEFAULT nextval('conference_id_conference_seq'::regclass);
 G   ALTER TABLE public.conference ALTER COLUMN id_conference DROP DEFAULT;
       public       score    false    187    188    188            �           2604    16560    edition id_edition    DEFAULT     j   ALTER TABLE ONLY edition ALTER COLUMN id_edition SET DEFAULT nextval('edition_id_edition_seq'::regclass);
 A   ALTER TABLE public.edition ALTER COLUMN id_edition DROP DEFAULT;
       public       score    false    192    191    192            �           2604    16502    paper id_paper    DEFAULT     b   ALTER TABLE ONLY paper ALTER COLUMN id_paper SET DEFAULT nextval('paper_id_paper_seq'::regclass);
 =   ALTER TABLE public.paper ALTER COLUMN id_paper DROP DEFAULT;
       public       score    false    193    194    194            �           2604    16455    publisher id_publisher    DEFAULT     r   ALTER TABLE ONLY publisher ALTER COLUMN id_publisher SET DEFAULT nextval('publisher_id_publisher_seq'::regclass);
 E   ALTER TABLE public.publisher ALTER COLUMN id_publisher DROP DEFAULT;
       public       score    false    186    185    186            �	          0    16473    author 
   TABLE DATA               1   COPY author (id_author, name_author) FROM stdin;
    public       score    false    190   �;       �	          0    16593    authorpaper 
   TABLE DATA               3   COPY authorpaper (id_author, id_paper) FROM stdin;
    public       score    false    195   �;       �	          0    16463 
   conference 
   TABLE DATA               Q   COPY conference (id_conference, acronym_conference, name_conference) FROM stdin;
    public       score    false    188   �;       �	          0    16481    edition 
   TABLE DATA               [   COPY edition (id_edition, year_edition, conference_edition, publisher_edition) FROM stdin;
    public       score    false    192   .Z       �	          0    16499    paper 
   TABLE DATA               �   COPY paper (id_paper, title_paper, pages_paper, year_publication_paper, first_page_paper, last_page_paper, conference_paper, publisher_paper, edition_paper) FROM stdin;
    public       score    false    194   KZ       �	          0    16452 	   publisher 
   TABLE DATA               ]   COPY publisher (id_publisher, link_publisher, name_publisher, acronym_publisher) FROM stdin;
    public       score    false    186   hZ       �	           0    0    author_id_author_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('author_id_author_seq', 0, true);
            public       score    false    189            �	           0    0    conference_id_conference_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('conference_id_conference_seq', 431, true);
            public       score    false    187            �	           0    0    edition_id_edition_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('edition_id_edition_seq', 1, false);
            public       score    false    191            �	           0    0    paper_id_paper_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('paper_id_paper_seq', 0, true);
            public       score    false    193            �	           0    0    publisher_id_publisher_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('publisher_id_publisher_seq', 1, false);
            public       score    false    185            	           2606    16478    author author_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id_author);
 <   ALTER TABLE ONLY public.author DROP CONSTRAINT author_pkey;
       public         score    false    190            	           2606    16597    authorpaper authorpaper_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY authorpaper
    ADD CONSTRAINT authorpaper_pkey PRIMARY KEY (id_author, id_paper);
 F   ALTER TABLE ONLY public.authorpaper DROP CONSTRAINT authorpaper_pkey;
       public         score    false    195    195             	           2606    16468    conference conference_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY conference
    ADD CONSTRAINT conference_pkey PRIMARY KEY (id_conference);
 D   ALTER TABLE ONLY public.conference DROP CONSTRAINT conference_pkey;
       public         score    false    188            	           2606    16562    edition edition_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY edition
    ADD CONSTRAINT edition_pkey PRIMARY KEY (id_edition);
 >   ALTER TABLE ONLY public.edition DROP CONSTRAINT edition_pkey;
       public         score    false    192            	           2606    24754    author name_author_un 
   CONSTRAINT     P   ALTER TABLE ONLY author
    ADD CONSTRAINT name_author_un UNIQUE (name_author);
 ?   ALTER TABLE ONLY public.author DROP CONSTRAINT name_author_un;
       public         score    false    190            	           2606    16470    conference name_un 
   CONSTRAINT     Q   ALTER TABLE ONLY conference
    ADD CONSTRAINT name_un UNIQUE (name_conference);
 <   ALTER TABLE ONLY public.conference DROP CONSTRAINT name_un;
       public         score    false    188            
	           2606    16504    paper paper_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY paper
    ADD CONSTRAINT paper_pkey PRIMARY KEY (id_paper);
 :   ALTER TABLE ONLY public.paper DROP CONSTRAINT paper_pkey;
       public         score    false    194            �           2606    16460    publisher publisher_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id_publisher);
 B   ALTER TABLE ONLY public.publisher DROP CONSTRAINT publisher_pkey;
       public         score    false    186            	           2606    16598 &   authorpaper authorpaper_id_author_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY authorpaper
    ADD CONSTRAINT authorpaper_id_author_fkey FOREIGN KEY (id_author) REFERENCES author(id_author);
 P   ALTER TABLE ONLY public.authorpaper DROP CONSTRAINT authorpaper_id_author_fkey;
       public       score    false    190    2308    195            	           2606    16603 %   authorpaper authorpaper_id_paper_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY authorpaper
    ADD CONSTRAINT authorpaper_id_paper_fkey FOREIGN KEY (id_paper) REFERENCES paper(id_paper);
 O   ALTER TABLE ONLY public.authorpaper DROP CONSTRAINT authorpaper_id_paper_fkey;
       public       score    false    194    195    2314            	           2606    32949    paper id_conference    FK CONSTRAINT     }   ALTER TABLE ONLY paper
    ADD CONSTRAINT id_conference FOREIGN KEY (conference_paper) REFERENCES conference(id_conference);
 =   ALTER TABLE ONLY public.paper DROP CONSTRAINT id_conference;
       public       score    false    188    2304    194            	           2606    32964    edition id_conference    FK CONSTRAINT     �   ALTER TABLE ONLY edition
    ADD CONSTRAINT id_conference FOREIGN KEY (conference_edition) REFERENCES conference(id_conference);
 ?   ALTER TABLE ONLY public.edition DROP CONSTRAINT id_conference;
       public       score    false    192    2304    188            	           2606    32954    paper id_edition    FK CONSTRAINT     q   ALTER TABLE ONLY paper
    ADD CONSTRAINT id_edition FOREIGN KEY (edition_paper) REFERENCES edition(id_edition);
 :   ALTER TABLE ONLY public.paper DROP CONSTRAINT id_edition;
       public       score    false    194    192    2312            	           2606    32959    paper id_publisher    FK CONSTRAINT     y   ALTER TABLE ONLY paper
    ADD CONSTRAINT id_publisher FOREIGN KEY (publisher_paper) REFERENCES publisher(id_publisher);
 <   ALTER TABLE ONLY public.paper DROP CONSTRAINT id_publisher;
       public       score    false    186    194    2302            	           2606    32969    edition id_publisher    FK CONSTRAINT     }   ALTER TABLE ONLY edition
    ADD CONSTRAINT id_publisher FOREIGN KEY (publisher_edition) REFERENCES publisher(id_publisher);
 >   ALTER TABLE ONLY public.edition DROP CONSTRAINT id_publisher;
       public       score    false    2302    192    186            �	      x������ � �      �	      x������ � �      �	      x��\[w⸖~V~? ���[��$>����䜳��'���N�����dS�V���D{K���U[֙5��AȬ�Wt�Ƨ^r�y�.���)������+��^����wg0�v]f����s�����yΉ���{<��}����Y�Q�wg�.-f]��!:���ߒ�K�DF�>͈U�ֳ>?�ɖ��Ի�<X0����9�8���8HD�_n/���wN{���z����c9cf-f�:�$�^h����&4`Ŭ�	+� ��,ٝ�JW��W���2˵�����p��u�Q+���f�Ngİ<���r:gI�W��mz8\��6��a�%��8���љ�ۀ����-��>�OPi��0�8P/�%�o?��>}���uf�l�+�~$g��*oiVȡ8}[]N��;�Y`p<^�I����3=%�"�܆ȓh�G��-���I�1 �lO��4`·m+����&�$�^��t���Z{�:Y����3��-�!�AZ^.�vm�A�}%�H�Z(k\��6���8ʢ׽�Y����/�J����w��O��}�@>��N�g��g��Up���s�s~�b�4ä:���M˶=IX�[Nz�W�n�B�&�)��@?�q�@_�9�~D03[a�h����^�6Mс�`��<�|�t��P>�)ɏ29r+�kd��1�-�b�0�
^0�_�!��=9�qV?`�
����"%��}�~d�1���@R�B��� n3L�Me��;
~�b�&u����0��.N�D0{Kn�/�W���ܰ�C6�符j�b�a�����.`+��{�����Bx�:��s��7~KB�3�]�'�`�31n�s�`b=|�����>޽ǽErڦ_d�r���ЍQ�X9lj�߁!=�tH'HL¾�c6_�kL�&��ުݨ�$��+�L����r�ZO�B�b�Ɣ��YJ5�n����8����7�O�}%�3f�X͸eqD� �;��vg��f���3�wy��Qd�m-~<d����
�X ̮����Xk ��EI�T��r��K���L����PS����NR�`���ÒAc̞{.Q�ɕx{��99p7��%��̶i�4�%G)L(E�ט�8E�?���H��閖^�[| n�9�4�NfG:}u�4ԅN��a��D�d0�ZPv��y삑S��u�$��A���t.u�f��ϸF{�ȩ��u9���F���^�U�Y���tB<������񎀴˃ë`H�F�̃�����ع2� ���j�m���c)]?��(�Ym�[��!"��
s.{u��KV5�U�,��߷�#�A'�q��3z�DL]���F����o���Cy��8@��l�k8E��@���@V��hq7�� �M���BR�7p���1�UI!L��:1
j&I*�����sJ`��I\ph��$6/pd��L������K�*�B��1!/dJ\&�A�����ر4:!�üލ��}z�a
��ˌ��S:�N#�4Zwc �#3�0-^�@W���&��Եˋ��ND���K�� %�`S����*�(�<���'���x  黵!��
���V�Q�}�`i�$�nb��b]$��x䤰vB6�+�Y:����%Q0s����C�-�y��{�p�#{*�c�%�_� �|~��V++I9&A�vO �R9��_���'�g<��&*t�>�we�Y��@'pie{��TS�(��(��%`D�M�ĮS *X�o���Q� O0)���+��)������8&�;��)�m�?Αb���^/j�@��B��-�M�d�<�T%���J��l�>��j��OGQ��-�K�Q�lF��
�SiGB1�[���+���-l�e�7jegJo��ݿ��@���j)�G�z|�#����N�.%��A��+�r[���RP3P��<`�Ic{>~@\�rf:[,$�R��H��cz�"�����qf�Žg)�������N�
�k�&�!f�Jg{al/L�qY�z^��
�cz�N�Z��!-�=9�̩�T	��~6�~E.ih�� c��3�Qc�yk&�2M��CB��='���F��l��rQq~��i�iC@&�=�G#������Sۄ6z�E)�B؅Ն:�>�@p�y�b8e��	V���nF^1��=u�b��+b�1g�k+����I�d�����̹d��C�L�l1|��umti���M����Y��5��r�f�5ӆ3D ]�t���A�	)�g��b��_���I޳�1��x泼YtY}�>�(#;��r��E�t�����6��� ��6��K��^+�j&���?�ŋ�6*�K�_gLR��"`����|���v�<�����D�ǭɗp�	���A��*w�d1+��l'g,�yN�=D~H}^�/�V}9@����N�=�d���x�Q�}���+i��S��E�B����y�����W����k��9� ��f�����V�@�
M7��A`_��C���C��Q�_$|}ĩ m�d�瞬��d���j������۶��x����� �Vd������ȍ�>Y�{(�9��Dm�$�WSv�X��4�9���ʊ�Vy�/ѻ�j������8+�: ]O�˓!�w�u���V���-�.mKH.e�sm�����/h�5�j/�� Ɋ�_�	��F�&a���׶��н<��d4#�	�i�` ��K*���L<���B�y{w���o�`��ރ{9��*�5̘a��j��9~%8R2K�������Ȣ_�����c�9��+��+N�	2Q�/�L1����9��]-�V���v�(i�f��9r2��/�Xۮ�?�#���o��#�v�dFe��kR\K�nN���)>�?������t��9�){L|�{�P�ke�{�">~�Қ���$GC3g�7���ZSl�������P�c����õ�n��͒6��#�=+�spr�X��x��y�(�;�n��lGo��H������#nbx�6�ı�W\
#���8h��
�j�5A��[Q2�]�Ew��\��79��.��h#����(#0ˉ�4Bw��#��W�C�ZL�? �+�Zv�[�E\a��ʷ6�A0��V��E�66����܋�s�A���2�F�oURĂ�	am�L�u�0�%D���t�G��D��y\�@�k�x�\��.�߇#Ϣ�[Q��������=�t>
4�ʸY����Se���8�΂�6�+��Q�U�5��ɐȗ�r�T"����o죨4.�(;6"Hpщ����U�k�uU�&gf?�<�,����-�����ܤ�Z�x��iX�	!�����m2����֜R#�#�󊰗ߔ�e-�&���~��� 0#��ݙIi�˰mJ�q�YՋ�gw���>o.�(�+5mJ���ߑj��7�r��6%@��>�D�|���nJ賃��t��n�b�M	��n~-E�)At�z�t-%2�3%�.n0�o��)AЙw�7���U���L�%�<pGZ"�UsӐ��N��ȫ���84����j���:#�:��u����Q[!�	o�T��b��O����A7�<�o���f�ևn#2�A˩�H	��ݾ-�K���E2 3«��r���gZ�c���{K�8q�A..��Vq�;!pR�֐���@���&�jn=gu������Z�s܋�KŅ��-��d^QO�[Jl��^dW�lV�-%2��O%as� -K�F��7��C���]u#D:j�ڠ	��n�J��!�ѧ��.�2��5�R91"|��{��=�I�A��#x�pgHe���ԪT�k��?TS��ee��g��B]#����Y�A�����	r��F�
n8��?�O��8qQ,Tz�d1�X�n��Y)�P�mA��`E��K�Ɯ��fށ)��jF��ت�2j��)��˺ɤyW��EMaS���*�y�u��:����|ڟ���x�56��S��F�f�B���>�����H�u"�S]���@䃵i�w��$�f7�! 6  =ǥ���nu��}x���#��dW^GTn ����t(t�������i�F;���<��(��:I�<ז#y5����ث������䆿h�-�'0�w����T]�<��S���Im <��)��|���ɭ�UC"Gb�I6�����@��NP� Zݯ���[�J=���3xvq�����\��a���`I��x�#C� z�F����v�z-�&�1�n�W:)�)���U�u`�Il~Ǔ
n�`���$���+$gm E~h_���<oSW��ė�^D W�dT�T�0��R�(�תʇ�8�`^�*
�F���:���N(����i-o%L #h�l��H�ۊ��	p�U$RK}^` ����1Xh8>e��n�h�U�/T	���P��~ur���QFC��\Od�S"i�)^�]~�Z��z�)T���d�1��<X�j��us{w�!�5�e��aiM)j�328��SyK��5u;K%�2� OKr��!����I�#B�5���,H/珞�F�;�'\
�4�s�->6o�O��4&�S#�T����Z���������,�����tD@�f�z7d^�/^�)��Իu��G���M�X"�x�N5�PY<�
	U�)m�s~�DN����s������Ԋϙ�/�םh���Z��N%/�u��9>	�B_��|i�<2Yy��>=
a��?�ŒΉסm�TQ�[&�Y���l<a?�UI�\(P�1���5xq�l�Eѕj�����L��T���0na�Q�r����;}2dK{Ŗ�L�c�O4�Y����/�v*��{��(?(k����jK�>102t0��q$9�zn;1c����#��x XT%�#�!ͧ����c���$�SOK���9��U�Zo��*�'���tP�ɔy.���N����,}2c�z�._��Sl��/���1a�_�j�%l��uL�����,4����ZL5�����ݪ���ڨ�����	.�A��x��] �_"��^�lTq�	��g�n!5�m�r�S /}N�)�A��F��2_���
< �@�[1��
h�Y�m��U%��UyU���K��4KR�) P�@8�Nx=��_(�S�+�����=��$�ʏ��'������l��"�_��6���
�\4T�8g�L>\�����Ҍ���^ș7`T/����V�Up��e�+0����P+�~�!�L/ő�&�ʾ�F�
�r�x�LP	�RCi}�18��\���//8��A�>��ՃGO�W�x'=o+Р�N�6-�:�����鰢����>0P��l�V��U�m��h�0�CF�h�U�^v��I�����f��ލ���r��'�4O��&	��+�u4�[���S|+�5�̦�i7��w�\������Oٙ��_�S$W��K ���{c8f>}y�*h��}�x�'}HB���	XS�V����7�7c8e�M|�1=�h|۠6|���m��j�R=#Pt�	SC��_r�+l�N����\r34�hC��:���M����=N�QQ���<�f0���<�I�Rp�����euih@�z���b~��W�v�6���AX�&9t���AC��կ4��h��omB3�	�3xtWk���>�XD
��ԟLq��G�����۝3T>��f��'�����;ߚ�=��>d�Q�1�l���$I��klc�+K2�S��T��t��l��%�Z�o�~���'����������LN�v��j����4�0��a�6��b ��{�j
����}��t�-��aT,�z#���ap���}�6�g����#���S.���)�����C�'{��<q��
Cgl�l}&���J����g��	/QA�ܕ]�r�����*0�K6��a�5O2��6�OY�����;���a� >��m��S?7��ly�W��!�C� �t[{�Z�}w��.�eޮX��A5������x��K��=��Gj���Sk������>����'��=:&ˢ͔h��{�͑?�c3�-{�[��G�f9�;Z�s~v�����W�E�h��sn�ơ1-6��eL��� �d|����5��	x6/�E7+,�^<��𗽬�	|:�Ů��)�k*b��p�rDP����/��/�0tLCW�NB��	���17��DVdƧ�/���'68��ȟ6<+��/�_5c/�����&P?X����wYB��Ж���#@��\9���c:O�T���L�ʣ�J�# �y)?ֶ�׎��Gk�Q	���e�J���c��Y�B�楢1�\�_:�$�Ao�⹠(1��̫܋�<�5F _�CJ�j���rC�4]�'���->�WQX?XY<���Z�� �18�.Y>"���� ���rK����?���.-J:�1��Y��a��ji-LU�@<6qCiCc���2Y��8�5r���JW2o�q��5�Mk)���fz<����/p����nzK��.u�1N5��f�k�b�jq��!ʗ��$�m��d��+F[����l7�@�&�����%c�\�e��������S܀+*q��eL4�q����oX��V���3K��B�1�a����D�#�r���~kL���Ю��/��~����ڐ�2�� ��Щ�oPM]S��� ſV�q���2a�/��.?f�wX���O�~�B:�s��䰨��$�<*.�ŋ1����R;e]9��Oq)���0&S:ֆ���x�'ף� ���1���M��9/���W�ݹ3�Cz�Goo��~G�E��4a��B�a-m@"�k��S�=!�x:W��Ƈh���Ak}[aj�'d�ҨF�0�'�ac-���bO'������8�3�ʄdN�ς�k���+>Qz�Q����v�j�O$��>P5��LM�9EK��l�5�S�L��q�_d���\�6�3�%;W�s1
������z�3�A�7�����ޣ����]e`s�3}�deT���٤�w�R�:�9q��~SZ��3�=�h��Ds�|͘��y#
x�w#��u܈=Q��b���E|҅[�<@���@xtZ�B�����l�@`!�W~剨1����L���1���~V��"��m��A��,�T��N�.�̡F�>���	��N{֥���rɭ9�ً���r/ܯw~�b|�A���X�<%1�&#�i��B��L����{�_�ō�,�(���f0&u�'���n��ᄽ8�k;�O��G�i��uQK���B�ԏ!�󞲗G߾��h�Ь�$oE5����ר��MPq-�UE[�,j߮Q|^y�gj �Ku�B?�{׿��>a55�%��SuZ�ߤ�Tj@D��<3���|\&)�X�t���߸�T2M�q�&��*O\O�M��6"^.��~n�>��,!tK���2��'�'���Dj��i��bg�"��%uJ��> 35@j�]J����ȔOQj@�z%)8/U��ZmN*Z8L�[o~
���_�q��w-�Z�¬�ei��1�DJBЁ���K=�m8��U��!pj����ݗO��^����᜔���pӯ�W�W�~��j &����V�C%]RGM��)����
�/5GQ~�=h|)C�2��"��E�הE_�|�wg��8��������?�pF      �	      x������ � �      �	      x������ � �      �	      x������ � �     