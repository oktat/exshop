
## Adatbázis

### Modell létrehozása

```cmd
php artisan make:model Employee --migration
```

### A létrehozott products tábla

```php
$table->id();
$table->string('name');
$table->string('itemNumber');
$table->integer('count');
$table->double('price');
$table->timestamps();
```

