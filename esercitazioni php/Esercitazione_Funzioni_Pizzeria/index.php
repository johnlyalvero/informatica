<?php
include 'script/script.php'; // Include il file con le funzioni

$menu = [
    'ASPARAGI' => 6.5,
    'BOLOGNESE' => 8,
    'BRESAOLA' => 7.5,
    'DIAVOLA' => 6.5,
    'CALZONE_NAPOLETANO' => 6.5,
    'CAPRICCIOSA' => 7,
    'QUATTRO_FORMAGGI' => 6.5,
    'AI_PEPERONI' => 6.5,
    'MARINARA' => 4,
    'AL_TONNO' => 6.5,
    'AI_PORCINI' => 7.5,
    'AL_PROSCIUTTO' => 6.5,
    'PROSCIUTTO_E_FUNGHI' => 7,
    'PUGLIESE' => 6,
    'TIROLESE' => 7,
    'MARGHERITA' => 4.5
];

$ordine = [];
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    foreach ($menu as $pizza => $prezzo) {
        if (!empty($_POST[$pizza]) && is_numeric($_POST[$pizza]) && $_POST[$pizza] > 0) {
            $ordine[$pizza] = (int)$_POST[$pizza];
        }
    }
}
?>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizzeria Online</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Ordina la tua pizza</h1>
    <form method="post">
        <table>
            <tr>
                <th>Pizza</th>
                <th>Prezzo</th>
                <th>Quantità</th>
            </tr>
            <?php foreach ($menu as $pizza => $prezzo): ?>
                <tr>
                    <td><?= htmlspecialchars($pizza) ?></td>
                    <td><?= number_format($prezzo, 2) ?>€</td>
                    <td><input type="number" name="<?= htmlspecialchars($pizza) ?>" min="0" value="0"></td>
                </tr>
            <?php endforeach; ?>
        </table>
        <button type="submit">Invia Ordine</button>
    </form>
    
    <?php if (!empty($ordine)): ?>
        <h2>Dettaglio Ordine</h2>
        <ul>
            <?php foreach ($ordine as $pizza => $quantita): ?>
                <li><?= $quantita ?> x <?= htmlspecialchars($pizza) ?> - <?= number_format($menu[$pizza] * $quantita, 2) ?>€</li>
            <?php endforeach; ?>
        </ul>
        <?php visualizzaPrezzo($menu, $ordine); ?>
    <?php endif; ?>
</body>
</html>
