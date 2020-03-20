-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2020 at 03:38 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_nusa`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(10) NOT NULL,
  `nama_admin` varchar(40) NOT NULL,
  `nik` varchar(20) NOT NULL,
  `username_admin` varchar(12) NOT NULL,
  `password_admin` varchar(12) NOT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `photo` text NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `no_hp` varchar(14) NOT NULL,
  `email` text NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `nik`, `username_admin`, `password_admin`, `tgl_lahir`, `photo`, `jenis_kelamin`, `no_hp`, `email`, `alamat`) VALUES
(1, 'Jamal', '30019920518', 'admin1', '12345678', '1992-05-18', '', 'Male', '08213457899', 'jamal@nusabank.com', 'Kali Deres'),
(2, 'Putri', '30019920518', 'admin2', '12345678', '1992-01-18', '', 'Female', '08213457899', 'putri@nusabank.com', 'Kali Deres');

-- --------------------------------------------------------

--
-- Table structure for table `nasabah`
--

CREATE TABLE `nasabah` (
  `id_nasabah` int(5) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `nik` int(16) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  `photo` text,
  `jenis_kelamin` varchar(10) NOT NULL,
  `pekerjaan` text NOT NULL,
  `alamat_kantor` text NOT NULL,
  `pendapatan` int(12) NOT NULL,
  `no_hp` varchar(14) NOT NULL,
  `status` varchar(10) NOT NULL,
  `nama_ibu` varchar(40) NOT NULL,
  `username_nasabah` varchar(12) NOT NULL,
  `password_nasabah` varchar(12) NOT NULL,
  `tgl_pembuatan` datetime NOT NULL,
  `id_rekening` int(16) NOT NULL,
  `id_admin` int(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nasabah`
--

INSERT INTO `nasabah` (`id_nasabah`, `nama`, `nik`, `tgl_lahir`, `alamat`, `photo`, `jenis_kelamin`, `pekerjaan`, `alamat_kantor`, `pendapatan`, `no_hp`, `status`, `nama_ibu`, `username_nasabah`, `password_nasabah`, `tgl_pembuatan`, `id_rekening`, `id_admin`) VALUES
(2, 'Deni', 12345678, '1990-01-02', 'Jkt', 'file...', 'Male', 'Programmer', 'Jkt', 1200000, '0812345678', 'Single', 'Dummy Mom', 'dummy1', 'dummy1', '2020-03-16 19:02:00', 6, 0),
(4, 'Ruslan', 1231200976, '1992-01-12', 'Jaksel', 'res/nasabah_photos/abcd1_NusaBank_2020-03-17_16-10.jpg', 'Male', 'Admin', 'Depok', 4000000, '081231231', 'Maried', 'Bsdkja', 'abcd1', '123456', '2020-03-17 16:10:00', 9, NULL),
(5, 'Andi', 201543502, '1991-01-01', 'Jakarta', 'res/nasabah_photos/andi_NusaBank_2020-03-17_16-23.jpg', 'Male', 'Programmer', 'Bekasi', 999999999, '08123456', 'Maried', 'Rumiah', 'andi', '123456', '2020-03-17 16:23:00', 11, NULL),
(6, 'Asep', 12345678, '1992-01-27', 'Bandung', 'res/nasabah_photos/asep1_NusaBank_2020-03-17_16-27.jpg', 'Male', 'Programmer', 'Jakarta', 50000000, '0812345', 'Single', 'Booo', 'asep1', '123456', '2020-03-17 16:27:00', 12, NULL),
(7, 'Ujang', 11223344, '1990-01-01', 'Bogor', 'res/nasabah_photos/ujang1_NusaBank_2020-03-17_19-35.jpg', 'Male', 'Manager', 'Depok', 20000000, '0857123456', 'Single', 'Ujung', 'ujang1', '123456', '2020-03-17 19:35:00', 13, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rekening`
--

CREATE TABLE `rekening` (
  `id_rekening` int(12) NOT NULL,
  `no_rekening` varchar(24) NOT NULL,
  `saldo` int(12) NOT NULL,
  `jenis_rekening` varchar(24) NOT NULL,
  `no_pin` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rekening`
--

INSERT INTO `rekening` (`id_rekening`, `no_rekening`, `saldo`, `jenis_rekening`, `no_pin`) VALUES
(1, '1234567890', 100000, 'Reguler', 123456),
(4, '058159171881', 100000, 'Gold', 123456),
(5, '673159171881', 1000000, 'Reguler', 123456),
(6, '058159171881', 900000, 'Reguler', 123456),
(7, '058159170576', 500000, 'Gold', 123456),
(8, '058159170576', 6000000, 'Gold', 123456),
(9, '259959170576', 1500000, 'Reguler', 123456),
(10, '045359170576', 5000000, 'Gold', 123456),
(11, '045359170576', 5000000, 'Gold', 123456),
(12, '050159170576', 6000000, 'Reguler', 123456),
(13, '175359170576', 50000000, 'Platinum', 123456);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_bank`
--

CREATE TABLE `transaksi_bank` (
  `id_transaksi` int(12) NOT NULL,
  `jenis_transaksi` varchar(10) NOT NULL,
  `nominal` int(12) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `kode_bank` int(3) NOT NULL,
  `rek_tujuan` int(16) NOT NULL,
  `biaya_admin` int(12) NOT NULL,
  `ket_transaksi` text NOT NULL,
  `id_rekening` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_ppob`
--

CREATE TABLE `transaksi_ppob` (
  `id_transaksi` int(12) NOT NULL,
  `jenis_transaksi` varchar(40) NOT NULL,
  `nominal` int(12) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `no_target` int(32) NOT NULL,
  `ket_transaksi` text NOT NULL,
  `id_rekening` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `nasabah`
--
ALTER TABLE `nasabah`
  ADD PRIMARY KEY (`id_nasabah`);

--
-- Indexes for table `rekening`
--
ALTER TABLE `rekening`
  ADD PRIMARY KEY (`id_rekening`);

--
-- Indexes for table `transaksi_bank`
--
ALTER TABLE `transaksi_bank`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `transaksi_ppob`
--
ALTER TABLE `transaksi_ppob`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `nasabah`
--
ALTER TABLE `nasabah`
  MODIFY `id_nasabah` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `rekening`
--
ALTER TABLE `rekening`
  MODIFY `id_rekening` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `transaksi_bank`
--
ALTER TABLE `transaksi_bank`
  MODIFY `id_transaksi` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaksi_ppob`
--
ALTER TABLE `transaksi_ppob`
  MODIFY `id_transaksi` int(12) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
