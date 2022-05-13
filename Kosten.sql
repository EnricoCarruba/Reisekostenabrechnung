-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 23. Okt 2020 um 10:24
-- Server-Version: 10.4.14-MariaDB
-- PHP-Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `Kosten`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abrechnung`
--

CREATE TABLE `abrechnung` (
  `ab_id` int(11) NOT NULL,
  `an_id` int(11) NOT NULL,
  `datum` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `abrechnung`
--

INSERT INTO `abrechnung` (`ab_id`, `an_id`, `datum`) VALUES
(11, 16, '22.10.2020'),
(12, 18, '22.10.2020'),
(13, 19, '23.10.2020');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abrechnungPositionen`
--

CREATE TABLE `abrechnungPositionen` (
  `ab_id` int(11) NOT NULL,
  `ko_id` int(11) NOT NULL,
  `anzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `abrechnungPositionen`
--

INSERT INTO `abrechnungPositionen` (`ab_id`, `ko_id`, `anzahl`) VALUES
(11, 1, 5),
(11, 2, 2),
(11, 3, 12),
(11, 4, 2),
(12, 1, 5),
(12, 2, 2),
(12, 3, 2),
(12, 4, 2),
(13, 1, 2),
(13, 2, 3),
(13, 3, 5),
(13, 4, 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `anwender`
--

CREATE TABLE `anwender` (
  `an_id` int(11) NOT NULL,
  `vorname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `plz` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `anwender`
--

INSERT INTO `anwender` (`an_id`, `vorname`, `name`, `straße`, `plz`) VALUES
(16, 'Enrico', 'Carruba', 'Giebelkamp 7', '30966'),
(17, 'Julia', 'Carruba', 'Giebelkamp 7', '30966'),
(18, 'Enrico', 'Carruba', 'Giebelkamp 7', '30966'),
(19, 'Testvorname', 'Testname', 'Teststraße 1', '11111');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kostenart`
--

CREATE TABLE `kostenart` (
  `ko_id` int(11) NOT NULL,
  `beschreibung` varchar(255) NOT NULL,
  `euro` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `kostenart`
--

INSERT INTO `kostenart` (`ko_id`, `beschreibung`, `euro`) VALUES
(1, 'Tagegeld', 30),
(2, 'Übernachtung', 100),
(3, 'Fahrtkosten', 1),
(4, 'Sonstiges', 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ort`
--

CREATE TABLE `ort` (
  `ort_id` int(11) NOT NULL,
  `plz` varchar(11) NOT NULL,
  `ort` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `ort`
--

INSERT INTO `ort` (`ort_id`, `plz`, `ort`) VALUES
(1, '30966', 'Hemmingen'),
(2, '30966', 'Hemmingen'),
(3, '30966', 'Hemmingen'),
(4, '11111', 'Testhausen');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `abrechnung`
--
ALTER TABLE `abrechnung`
  ADD PRIMARY KEY (`ab_id`);

--
-- Indizes für die Tabelle `abrechnungPositionen`
--
ALTER TABLE `abrechnungPositionen`
  ADD PRIMARY KEY (`ab_id`,`ko_id`);

--
-- Indizes für die Tabelle `anwender`
--
ALTER TABLE `anwender`
  ADD PRIMARY KEY (`an_id`);

--
-- Indizes für die Tabelle `kostenart`
--
ALTER TABLE `kostenart`
  ADD PRIMARY KEY (`ko_id`);

--
-- Indizes für die Tabelle `ort`
--
ALTER TABLE `ort`
  ADD PRIMARY KEY (`ort_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `abrechnung`
--
ALTER TABLE `abrechnung`
  MODIFY `ab_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT für Tabelle `anwender`
--
ALTER TABLE `anwender`
  MODIFY `an_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT für Tabelle `ort`
--
ALTER TABLE `ort`
  MODIFY `ort_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
