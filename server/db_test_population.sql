DROP TABLE IF EXISTS artist;
CREATE TABLE artist (
  artist_id int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (artist_id)
);

DROP TABLE IF EXISTS album;
CREATE TABLE album (
  album_id int(11) NOT NULL AUTO_INCREMENT,
  artist_id int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (album_id),
  FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
);

DROP TABLE IF EXISTS member;
CREATE TABLE member (
  member_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (member_id)
);

DROP TABLE IF EXISTS playlist;
CREATE TABLE playlist (
  playlist_id int(11) NOT NULL AUTO_INCREMENT,
  member_id int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (playlist_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

DROP TABLE IF EXISTS song;
CREATE TABLE song (
  song_id int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  album_id int(11) NOT NULL,
  artist_id int(11) NOT NULL,
  track_number int(11) NOT NULL,
  PRIMARY KEY (song_id),
  FOREIGN KEY (album_id) REFERENCES album(album_id),
  FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
);

DROP TABLE IF EXISTS playlistsong;
CREATE TABLE playlistsong (
  playlistsong_id int(11) NOT NULL AUTO_INCREMENT,
  playlist_id int(11) NOT NULL,
  song_id int(11) NOT NULL,
  track_number int(11) NOT NULL,
  PRIMARY KEY (playlistsong_id),
  FOREIGN KEY (playlist_id) REFERENCES playlist(playlist_id),
  FOREIGN KEY (song_id) REFERENCES song(song_id)
);



#the comments below are for tracking the auto incremented ID's

insert into member values(NULL, 'zetral', 'nt273');
insert into member values(NULL, 'todfeuer', 'mage');
insert into member values(NULL, 'league', 'legends');
#1-zetral, 2-todfuer, 3-league




#Playlist Classic Rock

insert into artist values(NULL, 'Ten Years After');
insert into artist values(NULL, 'Billy Squire');
insert into artist values(NULL, 'Eddie Money');
#1-Ten years after, 2-Billy Squire, 3-Eddie Money

insert into album values(NULL, 1, "Greatest Hits");
insert into album values(NULL, 2, "Never Say No");
insert into album values(NULL, 3, "The Essential Eddie Money");
#1-Greatest Hits by Ten Years After
#2-Never Say No by Billy Squire
#3-The Essential Eddie Money by Eddie Money

insert into playlist values(NULL, 1, 'Classic Rock');
#playlist 1 is Classic rock

insert into song values(NULL, 'I\'d Love To Change The World', 1, 1, 1);
insert into song values(NULL, 'Lonely Is The Night', 2, 2, 1);
insert into song values(NULL, 'Take Me Home Tonight', 3, 3, 1);
#1- I'd love to change the world, 2- Lonely is the night, 3-Take me home tonight

insert into playlistsong values(NULL, 1, 1, 1);
insert into playlistsong values(NULL, 1, 2, 1);
insert into playlistsong values(NULL, 1, 3, 1);





#Playlist Techno

insert into artist values(NULL, 'Daft Punk');
insert into artist values(NULL, 'Basshunter');
insert into artist values(NULL, 'Cascada');
#4-Ten years after, 5-Billy Squire, 6-Eddie Money

insert into playlist values(NULL, 2, 'Techno');
#Playlist 2 is Techno

insert into album values(NULL, 4, "Around The World");
insert into album values(NULL, 5, "Now Youre Gone");
insert into album values(NULL, 6, "Everytime We Touch");
#4-Around the World by Daft Punk
#5-Now Youre Gone by Basshunter
#6-Everytime We Touch by Cascada

insert into song values(NULL, 'Harder Better Faster Stronger', 4, 4, 1);
insert into song values(NULL, 'DotA', 5, 5, 1);
insert into song values(NULL, 'Everytime We Touch', 6, 6, 1);
#4-harder better faster stronger, 5-dota 6-everytime we touch

insert into playlistsong values(NULL, 2, 4, 1);
insert into playlistsong values(NULL, 2, 5, 1);
insert into playlistsong values(NULL, 2, 6, 1);



#Playlist Party Mix

insert into artist values(NULL, 'Nelly');
insert into artist values(NULL, 'Lady Gaga');
insert into artist values(NULL, 'Kesha');
#7-Nelly, 8-Lady Gaga, 9-Kesha

insert into playlist values(NULL, 3, 'Party Mix');
#playlist 3 is Party Mix

insert into album values(NULL, 7, "Nellyville");
insert into album values(NULL, 8, "Fame Monster");
insert into album values(NULL, 9, "Animal");
#7-Nellyville by Nelly
#8-Fame Monster by Lady Gaga
#9-Animal by Kesha

insert into song values(NULL, 'Hot in Herre', 7, 7, 1);
insert into song values(NULL, 'Alejandro', 8, 8, 1);
insert into song values(NULL, 'Tik Tok', 9, 9, 1);
#7-Hot in Herre 8-Alejandro 9-Tik Tok

insert into playlistsong values(NULL, 3, 7, 1);
insert into playlistsong values(NULL, 3, 8, 1);
insert into playlistsong values(NULL, 3, 9, 1);