
CREATE TABLE 'pemeriksaan_vaksin' (
  `no_rawat` varchar(41) NOT NULL,
  `tgl_perawatan` date NOT NULL,
  `jam_rawat` time NOT NULL,
  `kd_vaksin` varchar(45) NOT NULL,
  `imun_ke` enum('-','1','2','3','4','5','6','7','8','10','11','12','13') DEFAULT NULL,
  `suhu_tubuh` char(15) DEFAULT NULL,
  `tensi` char(15) NOT NULL,
  `nadi` char(15) DEFAULT NULL,
  `respirasi` char(15) DEFAULT NULL,
  `tinggi` char(15) DEFAULT NULL,
  `berat` char(15) DEFAULT NULL,
  `gcs` varchar(10) DEFAULT NULL,
  `lingkar_dada` varchar(45) DEFAULT NULL,
  `alergi` varchar(225) DEFAULT NULL,
  `subject` mediumtext DEFAULT NULL,
  `object` mediumtext DEFAULT NULL,
  `nip` varchar(20) NOT NULL,
  PRIMARY KEY (`no_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;
