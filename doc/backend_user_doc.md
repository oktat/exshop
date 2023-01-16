# Shop backend felhasználói dokumentáció


## A szerver indítása

```cmd
php artisan serve
```

## Útvonalak

| Végpont | Metódus | Azonosítás |
|-|-|-|
|/products | get | nem |

### Felhasználó regisztrálása

| Végpont | Metódus |
|-|-|
|/singup | post | nem |

```json
{
    "name": "valaki",
    "email": "mari@zold.lan",
    "password": "titok",
    "password_confirmation": "titok"
}
```

Az e-mail cím nem létezhet az adatbázisban.

### Belépés


| Végpont | Metódus |
|-|-|
|/api/singin | post | nem |

Belépéshez felhasználónév és jelszó szükséges:

```json
{
    "name": "valaki",
    "password": "titok",
}
```

## Termékek kezelése

### Az összes termék lekérdezése

| Végpont | Metódus |
|-|-|
|/api/products | get | nem |

