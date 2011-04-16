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
  playlist_name varchar(255) NOT NULL,
  PRIMARY KEY (playlist_id)
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
