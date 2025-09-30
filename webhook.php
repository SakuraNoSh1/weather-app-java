<?php
// Seguridad básica (opcional: validar token de GitHub)
$payload = file_get_contents('php://input');
if (!$payload) {
    http_response_code(400);
    exit("No payload");
}

// Ruta de tu proyecto en el hosting
$repo_dir = '/files/public_html/';

// Ejecuta el pull
exec("cd $repo_dir && git pull origin main 2>&1", $output, $return_var);

// Devuelve respuesta
header('Content-Type: application/json');
echo json_encode([
    "status" => $return_var === 0 ? "success" : "error",
    "output" => $output
]);
