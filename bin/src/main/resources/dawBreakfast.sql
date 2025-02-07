-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mariadb2:3306
-- Tiempo de generación: 27-01-2025 a las 19:01:03
-- Versión del servidor: 11.5.2-MariaDB-ubu2404
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dawBreakfast`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desayuno`
--

CREATE TABLE `desayuno` (
  `id` int(11) NOT NULL,
  `id_establecimiento` int(11) DEFAULT NULL,
  `nombre` varchar(30) NOT NULL,
  `precio` decimal(5,2) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `puntuacion` decimal(3,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Volcado de datos para la tabla `desayuno`
--

INSERT INTO `desayuno` (`id`, `id_establecimiento`, `nombre`, `precio`, `imagen`, `puntuacion`) VALUES
(1, 1, 'Café con tostada', 3.50, 'imagenes/cafe_tostada.jpg', 4.20),
(2, 2, 'Croissant con zumo', 4.00, 'imagenes/croissant_zumo.jpg', 4.60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `establecimiento`
--

CREATE TABLE `establecimiento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `puntuacion` decimal(3,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Volcado de datos para la tabla `establecimiento`
--

INSERT INTO `establecimiento` (`id`, `nombre`, `descripcion`, `ubicacion`, `puntuacion`) VALUES
(1, 'Café Central', 'Un lugar acogedor con café artesanal.', 'Calle Mayor 12, Madrid', 4.50),
(2, 'Panadería La Espiga', 'Pan recién horneado y desayunos caseros.', 'Avenida del Sol 45, Barcelona', 4.80);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_desayuno` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT current_timestamp(),
  `precio` decimal(5,2) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `puntuacion` int(11) DEFAULT NULL CHECK (`puntuacion` between 0 and 5),
  `comentarios` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Volcado de datos para la tabla `review`
--

INSERT INTO `review` (`id`, `id_usuario`, `id_desayuno`, `fecha`, `precio`, `imagen`, `puntuacion`, `comentarios`) VALUES
(1, 1, 1, '2025-01-27 18:49:56', 3.50, 'imagenes/review1.jpg', 5, 'El café estaba excelente, y la tostada bien crujiente.'),
(2, 2, 2, '2025-01-27 18:49:56', 4.00, 'imagenes/review2.jpg', 4, 'El croissant muy bueno, aunque el zumo podría mejorar.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `email`, `password`) VALUES
(1, 'juanperez', 'juan.perez@email.com', 'password123'),
(2, 'maria.gomez', 'maria.gomez@email.com', 'securepass456');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `desayuno`
--
ALTER TABLE `desayuno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_establecimiento` (`id_establecimiento`);

--
-- Indices de la tabla `establecimiento`
--
ALTER TABLE `establecimiento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_desayuno` (`id_desayuno`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `desayuno`
--
ALTER TABLE `desayuno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `establecimiento`
--
ALTER TABLE `establecimiento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `desayuno`
--
ALTER TABLE `desayuno`
  ADD CONSTRAINT `desayuno_ibfk_1` FOREIGN KEY (`id_establecimiento`) REFERENCES `establecimiento` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`id_desayuno`) REFERENCES `desayuno` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
