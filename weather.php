<?php
// weather.php - Proxy hacia OpenWeatherMap con cURL

$API_KEY = "c3a766aa374fe4ffad1a644e51e0e225";

// Permitir acceso desde tu dominio (CORS opcional)
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=utf-8");

// Validar parámetro city
if (!isset($_GET['city']) || trim($_GET['city']) === "") {
    http_response_code(400);
    echo json_encode(["error" => "city_missing"]);
    exit;
}

$city = urlencode(trim($_GET['city']));
$url = "https://api.openweathermap.org/data/2.5/weather?q={$city}&appid={$API_KEY}&units=metric&lang=es";

// Cache simple para reducir llamadas
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

// --- Llamada a la API usando cURL ---
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 5);
curl_setopt($ch, CURLOPT_TIMEOUT, 10);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, true);

$response = curl_exec($ch);
$httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
$curlError = curl_error($ch);
curl_close($ch);

if ($response === false || $httpCode !== 200) {
    http_response_code($httpCode ?: 500);
    echo json_encode([
        "error" => "fetch_failed",
        "http_code" => $httpCode,
        "curl_error" => $curlError
    ]);
    exit;
}

// Guardar en caché
file_put_contents($cacheFile, $response);

// Devolver respuesta
echo $response;
