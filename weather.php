<?php
// weather.php - Proxy hacia OpenWeatherMap
// Ubícalo en public_html

// Configura tu API key de OpenWeatherMap
$API_KEY = "c3a766aa374fe4ffad1a644e51e0e225";

// Permitir acceso desde tu dominio (seguridad básica)
$allowed_origins = [
    "https://lightsteelblue-stinkbug-981609.hostingersite.com",
    "http://lightsteelblue-stinkbug-981609.hostingersite.com"
];

if (isset($_SERVER['HTTP_ORIGIN']) && in_array($_SERVER['HTTP_ORIGIN'], $allowed_origins)) {
    header("Access-Control-Allow-Origin: " . $_SERVER['HTTP_ORIGIN']);
}

header("Content-Type: application/json; charset=utf-8");

// Validar parámetro de ciudad
if (!isset($_GET['city']) || trim($_GET['city']) === "") {
    http_response_code(400);
    echo json_encode(["error" => "city_missing"]);
    exit;
}

$city = urlencode(trim($_GET['city']));
$url = "https://api.openweathermap.org/data/2.5/weather?q={$city}&appid={$API_KEY}&units=metric&lang=es";

// --- Cache simple para no gastar demasiadas consultas ---
$cacheDir = __DIR__ . "/cache";
if (!is_dir($cacheDir)) {
    mkdir($cacheDir, 0755, true);
}

$cacheFile = $cacheDir . "/" . md5($url) . ".json";
$cacheTime = 600; // 10 minutos

if (file_exists($cacheFile) && (time() - filemtime($cacheFile) < $cacheTime)) {
    echo file_get_contents($cacheFile);
    exit;
}

// --- Llamada a la API ---
$response = @file_get_contents($url);

if ($response === false) {
    http_response_code(500);
    echo json_encode(["error" => "fetch_failed"]);
    exit;
}

// Guardar en caché
file_put_contents($cacheFile, $response);

// Devolver respuesta
echo $response;
