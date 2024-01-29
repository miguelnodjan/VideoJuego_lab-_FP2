-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-01-2024 a las 01:19:11
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `persona`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
--

CREATE TABLE `partidas` (
  `id` int(100) NOT NULL,
  `nombreReino1` varchar(100) NOT NULL,
  `nombreReino2` varchar(100) NOT NULL,
  `campo` varchar(100) NOT NULL,
  `cantidadArqueros1` int(20) NOT NULL,
  `cantidadArqueros2` int(20) NOT NULL,
  `cantidadCaballeros1` int(20) NOT NULL,
  `cantidadCaballeros2` int(20) NOT NULL,
  `cantidadLanceros1` int(20) NOT NULL,
  `cantidadLanceros2` int(20) NOT NULL,
  `cantidadEspadachines1` int(20) NOT NULL,
  `cantidadEspadachines2` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partidas`
--

INSERT INTO `partidas` (`id`, `nombreReino1`, `nombreReino2`, `campo`, `cantidadArqueros1`, `cantidadArqueros2`, `cantidadCaballeros1`, `cantidadCaballeros2`, `cantidadLanceros1`, `cantidadLanceros2`, `cantidadEspadachines1`, `cantidadEspadachines2`) VALUES
(1, 'Inglaterra', 'Francia', 'Campo', 1, 1, 1, 1, 1, 1, 1, 1),
(2, 'Castilla-Aragon', 'Moros', 'Campo', 2, 1, 3, 2, 2, 1, 1, 3),
(3, 'Francia', 'Castilla-Aragon', 'Desierto', 1, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `id_number` int(11) NOT NULL,
  `id_type` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `state` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `id_number`, `id_type`, `name`, `last_name`, `date_of_birth`, `gender`, `email`, `phone`, `state`) VALUES
(1, 1234, 'Cedula de Ciudadania', 'Juan Luis', 'Guerra', '1994-05-09', 'Masculino', 'Example@gmail.com', '340567', 1),
(2, 1237, 'Cedula de Ciudadania', 'Maria Carolina', 'Perez', '1994-05-09', 'Femenino', 'ExampleT@gmail.com', '876544', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `user` varchar(33) NOT NULL,
  `pass` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `user`, `pass`) VALUES
(1, 'Admin', 0xc91d0f919f23f1d56157b94ba87db805),
(2, 'miguel', 0x3132333435),
(4, 'Yuli', 0x31323334),
(5, 'hola', 0x31323334);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `partidas`
--
ALTER TABLE `partidas`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
