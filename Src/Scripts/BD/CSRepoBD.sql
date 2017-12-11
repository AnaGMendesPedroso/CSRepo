PGDMP         "                u           csrepo    9.6.5    10.0 7    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
       public         score    false    3            �            1259    16471    author_id_author_seq    SEQUENCE     v   CREATE SEQUENCE author_id_author_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
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
            public       score    false    187            �            1259    16481    edition    TABLE     ]   CREATE TABLE edition (
    id_edition integer NOT NULL,
    year_edition integer NOT NULL
);
    DROP TABLE public.edition;
       public         score    false    3            �            1259    16479    edition_id_edition_seq    SEQUENCE     x   CREATE SEQUENCE edition_id_edition_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.edition_id_edition_seq;
       public       score    false    192    3            �	           0    0    edition_id_edition_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE edition_id_edition_seq OWNED BY edition.id_edition;
            public       score    false    191            �            1259    16499    paper    TABLE       CREATE TABLE paper (
    id_paper integer NOT NULL,
    title_paper character varying(255) NOT NULL,
    pages_paper integer NOT NULL,
    year_publication_paper integer NOT NULL,
    first_page_paper integer NOT NULL,
    last_page_paper integer NOT NULL
);
    DROP TABLE public.paper;
       public         score    false    3            �            1259    16497    paper_id_paper_seq    SEQUENCE     t   CREATE SEQUENCE paper_id_paper_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.paper_id_paper_seq;
       public       score    false    194    3            �	           0    0    paper_id_paper_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE paper_id_paper_seq OWNED BY paper.id_paper;
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
       public       score    false    194    193    194            �           2604    16455    publisher id_publisher    DEFAULT     r   ALTER TABLE ONLY publisher ALTER COLUMN id_publisher SET DEFAULT nextval('publisher_id_publisher_seq'::regclass);
 E   ALTER TABLE public.publisher ALTER COLUMN id_publisher DROP DEFAULT;
       public       score    false    186    185    186            �	          0    16473    author 
   TABLE DATA               1   COPY author (id_author, name_author) FROM stdin;
    public       score    false    190   �:       �	          0    16593    authorpaper 
   TABLE DATA               3   COPY authorpaper (id_author, id_paper) FROM stdin;
    public       score    false    195   �:       �	          0    16463 
   conference 
   TABLE DATA               Q   COPY conference (id_conference, acronym_conference, name_conference) FROM stdin;
    public       score    false    188   �:       �	          0    16481    edition 
   TABLE DATA               4   COPY edition (id_edition, year_edition) FROM stdin;
    public       score    false    192   �X       �	          0    16499    paper 
   TABLE DATA               w   COPY paper (id_paper, title_paper, pages_paper, year_publication_paper, first_page_paper, last_page_paper) FROM stdin;
    public       score    false    194   Y       �	          0    16452 	   publisher 
   TABLE DATA               ]   COPY publisher (id_publisher, link_publisher, name_publisher, acronym_publisher) FROM stdin;
    public       score    false    186   6Y       �	           0    0    author_id_author_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('author_id_author_seq', 1, false);
            public       score    false    189            �	           0    0    conference_id_conference_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('conference_id_conference_seq', 427, true);
            public       score    false    187            �	           0    0    edition_id_edition_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('edition_id_edition_seq', 1, false);
            public       score    false    191            �	           0    0    paper_id_paper_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('paper_id_paper_seq', 1, false);
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
       public       score    false    2308    195    190            	           2606    16603 %   authorpaper authorpaper_id_paper_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY authorpaper
    ADD CONSTRAINT authorpaper_id_paper_fkey FOREIGN KEY (id_paper) REFERENCES paper(id_paper);
 O   ALTER TABLE ONLY public.authorpaper DROP CONSTRAINT authorpaper_id_paper_fkey;
       public       score    false    195    2314    194            	           2606    16568    edition id_conference    FK CONSTRAINT     y   ALTER TABLE ONLY edition
    ADD CONSTRAINT id_conference FOREIGN KEY (id_edition) REFERENCES conference(id_conference);
 ?   ALTER TABLE ONLY public.edition DROP CONSTRAINT id_conference;
       public       score    false    188    192    2304            	           2606    24755    paper id_conference    FK CONSTRAINT     u   ALTER TABLE ONLY paper
    ADD CONSTRAINT id_conference FOREIGN KEY (id_paper) REFERENCES conference(id_conference);
 =   ALTER TABLE ONLY public.paper DROP CONSTRAINT id_conference;
       public       score    false    188    2304    194            	           2606    16573    paper id_edition    FK CONSTRAINT     l   ALTER TABLE ONLY paper
    ADD CONSTRAINT id_edition FOREIGN KEY (id_paper) REFERENCES edition(id_edition);
 :   ALTER TABLE ONLY public.paper DROP CONSTRAINT id_edition;
       public       score    false    192    194    2312            	           2606    16563    edition id_publisher    FK CONSTRAINT     v   ALTER TABLE ONLY edition
    ADD CONSTRAINT id_publisher FOREIGN KEY (id_edition) REFERENCES publisher(id_publisher);
 >   ALTER TABLE ONLY public.edition DROP CONSTRAINT id_publisher;
       public       score    false    2302    192    186            	           2606    32944    paper id_publisher    FK CONSTRAINT     r   ALTER TABLE ONLY paper
    ADD CONSTRAINT id_publisher FOREIGN KEY (id_paper) REFERENCES publisher(id_publisher);
 <   ALTER TABLE ONLY public.paper DROP CONSTRAINT id_publisher;
       public       score    false    2302    194    186            �	      x������ � �      �	      x������ � �      �	      x��\�z⺒�V���i|��q�����d��͍N�ـ3�+���/��6����$��:�d�Yo���}E�m|�%Ǟ����`��]/H�ο�,����{w�l�e��[�='_q�N������s��'������E�{w&���b���3x���-y�d@�a��ӌX�o=��s�l�/O��ȃ�,���ўs��D@������o�{�|��񡷈O�;�3f�"`�s�H2�v�.{��nBV�:��B��yȒ݉�t�����)�\�,Z��q�^����,_�`��tF�!��.�s�4x��>ަ���XkCP��Y�!z����iQ�(ޏ	��m�#���,S���]�������o�^g�ʆ���Gr��.��f��ӷ����Ӏ�U �����|>�Sr9` ��m�<�~�Mޒmo���32��$K�N&|۶N�+(jO��%9AW����������?�,��y�R<���!~`����WB�T���f���k�K~��,z�˚U-��8 ���D��>9�q��$���g���t�!�|&Al|��Z�~O9�>g�g)vqJ3L��kٴlۓd�ź���~��6,Tk��L���
D�U���G3�&�6�Q���%n��
�+�S�W�nIWJ峜��(�#�b:�FfO��b(�c�������E��ߓcg�ց��_+�)R�N���G����
$u�)�����2�1���TH�Ӂ�P��(*aR<�I�
����TL���f��}��~�۝1ds{�j���!�-L{`hl�����8��8ڟ?*D�ǝ���;w�x�G�$�;à�ޕ�|"�8��?&����yL���{�[$�m�E6�(�9*�e�>��æ���3N�t��$�۝1f��D nr��ڍ:M�
�Ʉ����/'��t*�(��oL���Ts�6�8�3��<�}c��T�W�<c��Ռ[G�
Ҽ��qi�q��i��;y��E�����C}~@
��܁��
/YA :]��b�Z�T=AUj-��`��dz�����5u0���$��	�)?,4�����\���wٟ�wc��_b��l��H�]r�RT_q���St�s�A��/�ni�Ŏ�����cLsк�dv��W'PNC]�L6.NdO#�e珘�.9�HX'Lr��@�JK�R�l& h��k����
�_g�Þ/n�-����[��8�0O'��߹�h�H{�<8�
�сm�<�Ϭ�>�y��+�
pyX���j{�1���'�SQ9����Ż"2�p�0�Qw�daUsZ��"��}�;ht2��8��L�ԅo�Onx��k���(0�7H�� 4\϶��S��ľ0X\d� Y��w��	�ڤ�i)$��q7�A_cZ��T�x���f���B�kn|lp:�f_������Mb�7A�)ϴ�� �Ț�į�q-���B��e�� L�
��K��=����ڧ������ Z>���4�M�u7�<2S��t���n�xA]������DD�˻ԟ	P�
6uo���R�"�Σ�M|���~� ��[�.Ϩp@Xpm����FJ"�&_,�E"��GN
Ka'�a�a�r��c�=�7\3��m�+>����k�G�W9��";X�E
���g��`����c�i�p)��X�U�>~�{Ƴ�j�Bw���qWf�e<tבV&���L5���Ҟ{QBFT���J�:��u��N-e
���bY���Q�//��~�3`r�������k�)v�Q��p���fĭ-D������L&�SJUr;+�d����g�S��f,�t��N�b��X%�f�̯p;�v$#�Ū��2��N�¶\v}�Vv��6�������ܭ�bD���w�9�>� ��D�RҘ��� �2+��<^/5�@5x���4���ĕ/g����AR-Eۋ$z?�'>+�	)1i�g[�{����^Y�.�t�`�Vh2Abv��q�����t�%��e���0:���� A��ޓ3ɜJN�p��L�gcA�W䒆6�2v�:C5�@��fr,�T�0�!$m��s��/j4�� ��^�*��ڜƝ6d�܃|4R�KX�h?q8�MhC�'X�2)�]XQm����gX�w)�S֮�`%k��f���S,��q�"�s���x �[�tH�@�<�K��K/�p9ɔ��'X�F��Y.`ٔ9��^�Oh!�)a&[3m8�@�%IJW
x�$k��Bf�)Vl���H��=ks��g>�{�E��W�Ï2�C�*�n�]4Mg���0�.��l#�q�`�_iC���t��f�IX���Z��i�R��u�$�9(r��z�Ϸ�Jo��sp��K�LD~ܚ|	�� ����.�rW )0A�RK�v�p����t�C�����n՗�ʛi:���H��ˊ��G[(����h:0JZT/j�Μ'��qzz�Nμ)���<mֈ/�q�a%pT��t���U�<Dٙ;Īq�E��G�
�f�@�{��:��O���֘늠�ٽm{�>�����BiEF\mϋ`�ܘ��!���lKԦ�A�z�1e��%�O3��S^=��xn��������f�ZA�(�������T�<�{�Z7��j����}��b��V��RF<�fQy�����v}Ps��B!H������`Io�jF��~m�L��ShM�A3"���F1	p�/�`��2qK����ӿ�= 䱛�w������f��=��8�2YÌ�9����W�#%�D��!{X0�z�,�%HM�=<��c��b=�℘ ����n��a��S�X��iu��Yi�����k���#�!��Q������9b�����="lWKfTF8�&�e�t��=Ο����#�J`���pNg̜3���ķ����\+3��#�����$_PW'9Ҙ9����՚b����4��\<�b���<׌g��t��o����A�Y��������R��Cx��[Dэ���`�tk�e;�x�uF2�%�^��p�C�)%����:�R�G߶��AS�T�W�ј:�ߊ�ф�.������ɡ4u	��@��7�F�YN\��N�'�
2�b2,��_i�ղ��".�
�T���������'P/j�����7�^��>*�疉7|�⠐"Lpk�`R��x�q/!����<o� �g��Z*\C�c��Fwa��>yeߊJ�'����h��)��Q���}P�ͪԧĜ*s���!w��X�݌�������L�D�������?�5N~cE�qGٱA��N\6�o��_+���7183���g��mQG�& �=��&]�b<�kwH��L�v� ��Gh�	'���"�a�W�����,k�5�F8������A���LJ3^�mSB�;Ϊ^��8�˶͔�ys�Eq\)�iSB����T�L�Y����)��o��%R���$��t�SB�������/wkmJ�\t�k)�L	���k)�)��)auq��|˧M	�μ{���g���0�P_Pg�/��;�)�������v��F^U/�����8��l��WS$���	n��,%u�ڒ�Nx����k-~r�nF�}����Ŵ6#�>t;���ZN�@J�}��my�]z&�.��^�� @��?#к�N��[�xƉ�rq)�G<������	y�������\�r��X5!Uؐp�9��/��V�j��^T\*.�Wo��&�ʈz:�Rb���� ��f��n)�a~�(	���iY�7�E�9F>��!�Q��}H]wW��	�>�w�1���i�ʉ����{^��N�ڽ��;C*��ȧV�r]#�Y��B��(+s�?Ӡ�A�FXx��B�b7η�O��5�Tp�I��9~Z��y��b���'�����u���J���Fh�}F +z��^�6�~�0�L��W3j��V��ܐQ��4H	|]�M&ͻ���gȠ(j
�".��P�ͳ�]�	�O^7����E<�ˮ�T��7:5;�������g�DE����ꪅ7]"�Mýl'�6���9. #  ��u�t���|e�0=&��:�r9$d��@���m�H�,N7H�4�qM��q�E�\ׁHj湶ɫ��&@���^Ս�(��M'7�E�<h=�ټ�����:��ٕ��og�Nj���Mw��+ė�Hn-�9+M���4P�x� w�J� ��~M���JV���n����3��@_����j���\K��ś���7d �M�k�k�5��9u����I�O�Mt��� �Ob�;�T`p�+�N&�D�]!9k(�C�͵�y�ڸ
�&���"�B�& �⥂�	H��F��VU>����WQ0 5��fЙD�p�uB�e��Nky+aA�dk��E��V��M�#XX�"�ZX���O�}���B��)���dusFíz�J���_�Z�������x�2��z"ӜI� N����$�D֛N����$�����JU����ۻ�0��](�\KkJQ;���97|��[*З�i��Y*ї!xp�l\�+,���K�EMJR�i�fAz9��7J�i<�R��ٝ�o�yK|�5�1�����v~��M}D�6�,]eYO~ȥƦ#:5cջ!���N�7�ޭ;�.8��Fn��I���t�9����UH�Ni���[$Bp��W���U|�Xſ�V|� }���D�}����u�(y�3�D��)�I(�Jt �K#��ʃ���Qc��a/��pN�m�����2y͢L�g�	�8�Jr�B����-���3gK,��T㌷��]fz��4n�q[��� �6@��![�+�L�e�+�x�1ϲV��K}��S����0@�AY���h%U[*�������d�#��sۉɨ���h|������(q1�h>}�%���=&��zZ���0��.ި�z+V!<q�e��� N��s���wZ��^f���sw�bm�b���x�n��	��"V�.a�m�c:�LDUg�IL�V���b�q^��w8U�V���F}��֞Op��_��0.�)������d��N�=3u�)o�ßx�s�OI��|0*����z�'V�@2܊A|W@#̂�l�4�*�e�ʫ�HDX]*֠�Y��N���¹�uB���1��BA�^����G��A��#Y�T~<\>���|��� �g���r�����VV�䢡ҍ�9�e��:��/l�f,��Bμ�z�������k�.[_��V��Z��(q`z)��7�T�e6U��sĻf�J8�J���!����Vޠxy�����ل�<z⼢�;�y[�EuJ�i9�ٔ���O��޶^����f3���n��8�E�12�.@ۮ���C�|HrGt /�/6�|�nu���s�?a�y
�m5I��_Q��14�z?@��[��!e6�Nc��|����t@����X��� ��_P����1���VA;������c=�CJ��N����bο���)�m�s���aE����3�/l+dW�����M�����S_a#u��O�咛�iDZ��!�|�`h:X/�G�q�򈢰���14������L����4��U�(��KC��K���S�^�������Ѐ�b��7ɡ������~��|E��~k��M؟���rXKl���1�"R ,��d�Kv?��D��|��|�����6c>Y��$M���D�5�!�X/�J��gk��'�'I:]]c�XY�Q����D�3�f��/�(��"}C7�[��>y��D�\����?�`r� �[�W{lէ��&#��wh����VS }�6��=�;n��bi����|����Df��Y<k�D����r�WMMA5�|�?�CL�k�W:c��g��3)T����?�O�x�
�?��җN5��T�9^��]��y��6p�!�"w�W���i|4���o����i�e�k����
�yz�������j�#����ev�,�vŢ��1��uǅ����^�T�!�>Rs�%�0&�2X� ޿�]�Y���?Yg��1�[5h�D�_�[��h��aØ�`n��Z�?*��0�q�Ѫl�C��',ݨ��,E��X �s�5�i�/c�,�-��%��ŵ��@����M��yy �-�YaY���H����e5L�ӱ,v�MO��XSsD�K�#����t)M|�퀡c���u�M��dO��	�9�f �* 3>�x�O�?��ɧD���YywQ�B�{ѝ����0����zğYB�Ud	C[�?�sg� Egs�:.�@�<IRUV�3�+��*���<��\X�$_;���O|D%@�Z���*Tz@��NgYT
�����Xs=����!�}�炢�T2�2p/Fs��|�)5�� ��b�@tԟ�涶�`_E`�|`e��Sk��0��X�d��p /��6���u|X,�>���Rۻ�(�`ƀ�gm�s��I�0U5������B�d�����+]ɼ1����x7��x�'���f\s�������C��-A[��5�8�����=�ũ'�|(_2N�� ����=�m��Wc���,՛�O�[���	8p-��[�_�²Oq��\�E��1ш��-�W�B�aJZ�w���,��i��x++������F��1�B����df�UZ�jC��cX�
B�:�A5uM�jS�Z�� �WJ˄���:�����a�s��?i�1�p��'�â⇒��8�/�d�B�K�u��C<ťH��ØLY�X;⥟\�����(�d�B4�_缼�^�^�t�ΘY�Y������q�3/Є�����8��/cLu�����\U��1���l���ɞ�!J�a��tĞ����p��=�x���~�GO����+�9E>���'��D�YjD�K�g*�	��+>�x��@՘N�35��-�F��֘N�3ݳ�Ʃ~�YFr�ۘΈ��\I��X(�������x6�߈zjӋ{��g^0^ct�9��e��ْe�Q-��Wd�R�IK��L���#�MiF�����>Q̽�E4cf��(�ߍ(�q#�D���
��In� ��f E��i	
9��V_|���P^��'���\2����l�^,�Y1@ʊ���Ks8ܲhSY�2:m�H2�����&l��9�Y��K[�M$��Pg/�
>�ʽp���Պy�1�1�Rc�������	n�2��G�%~�7��|�(�㆛���I�lL�J�2��℮�k>�p��a`�YD-�®�S?�Xx �{�^}��ڣ�B��G._�Zk75@ŵ<Wm-��}�F�y�-��<.�U
��6�p�]�Zϫ���� ��JO�Uh-��S����̔��q���b�Ӎ�c~��R�45 �����<q=�75�ڈx���o��y�_�� ���-u>��t*�<Ԟ��f�p��yO����������)ٟ���� �5|w)�B�ۦ#S>E�Y땤�T}�Rk�9�h�0u o��)d�O���7h03ܵ�k)����!4Ƅ)	Av�/�����Vq4?���&ߓv_>Q�zU"�ƇsR>�M���^E_����6����f<r[��t1H	5i����b.
*
��E�}����˜��BOw��_www��Z:�      �	      x������ � �      �	      x������ � �      �	      x������ � �     