package com.example.hibsa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {
    private ArrayList<Car> cars = new ArrayList<>();
    private ControlDb dBHelper;
    private  SQLiteDatabase db;
    private MyAdapter myAdapter;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        setSupportActionBar(findViewById(R.id.toolbar));

        ListView listView = findViewById(R.id.listView);
        myAdapter = new MyAdapter(this, R.layout.list_item, cars);
        listView.setAdapter(myAdapter);

        dBHelper = new ControlDb(this, "DBTest1", null, 1);
        db = dBHelper.connect();
        create();
        update();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CatalogActivity.this, "Clicked: " + cars.get(i).getModel(), Toast.LENGTH_LONG).show();

                Car selectedCar = cars.get(i);
                Intent intent = new Intent(CatalogActivity.this, DescriptionActivity.class);
                intent.putExtra("brand", selectedCar.getBrand());
                intent.putExtra("model", selectedCar.getModel());
                intent.putExtra("year", selectedCar.getYear());
                intent.putExtra("description", selectedCar.getDescription());
                startActivity(intent);

            }
        });
    }

    private ArrayList<Car> getAllCars() {
        Cursor cursor = db.rawQuery("select * from Cars", null);
        ArrayList<Car> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                String brand = cursor.getString(/*cursor.getColumnIndex("brand")*/ 1);
                String model = cursor.getString(/*cursor.getColumnIndex("brand")*/ 2);
                int year = cursor.getInt(/*cursor.getColumnIndex("brand")*/ 3);
                String description = cursor.getString(/*cursor.getColumnIndex("brand")*/ 4);
                int price = cursor.getInt(/*cursor.getColumnIndex("brand")*/ 5);

                list.add(new Car(brand, model, year, description, price));
                cursor.moveToNext();
            }
        }

        return list;
    }

    private void createCars() {
        Car car = new Car("Cupra", "Leon", 2024,
                "Potencia hasta: 300 HP \n" +
                        "Aceleración 0-100 km/h: 5.7s\n" +
                        "Vel. máx: 250km/h \n" +
                        "Transmisión: 7 Ve\n", 674900);
        cars.add(car);

        car = new Car("Cupra", "Formentor", 2024,
                "Potencia hasta: 310 HP \n" +
                        "Aceleración 0-100 km/h: 4.9s\n" +
                        "Vel. máx: 250km/h \n" +
                        "Consumo combinado: 12.8 km/l\n", 720900);
        cars.add(car);

        car = new Car("Audi", "A5", 2024,
                "El A5 Sportback con un diseño más llamativo, deportivo y puede ser también un auto familiar gracias a sus cinco puertas. Equipado con motores con tecnología TFSI con tres versiones en el mercado mexicano: Select, S line y S line quattro. Cuenta con los servicios de Audi connect con Car2X Safety & Service, Audi phone box, Radio MMI plus con pantalla táctil de 10.1 pulgadas y Audi Virtual Cockpit",
                974900);
        cars.add(car);

        car = new Car("Mini Cooper", "Resolute", 2024,
                "Se trata de maximizar la MINIficación. Y, con más espacio para pasajeros y equipaje, puedes estar seguro de que el MINI 5 puertas Hatch te permitirá llevar mucho más en su interior y sacarle el máximo partido. Todo ello sin renunciar a la agilidad de rendimiento y manejo que ha hecho célebre a MINI.",
                812000);
        cars.add(car);

        car = new Car("Omoda", "C5 Unlimited", 2024,
                "En ambas versiones el OMODA O5 cuenta con capacidad de cinco pasajeros y cuatro puertas, mide 4.68 metros de largo y 1.8 metros de ancho, con neumáticos que le dan aspecto de auto deportivo, debido a su tamaño, con 205/50 R17.\n" +
                        "\n" +
                        "Es decir, 205 milímetros de ancho, 50% de relación entre la altura del lateral y el ancho del neumático. La R es igual al valor “Radial” y el 17, indica la dimensión de la llanta en pulgadas, a esto le añadimos rines de aluminio provocando que sea un vehículo moderno que no pasa desapercibido.",
                455900);
        cars.add(car);
    }

    private void update() {
        cars.clear();
        cars.addAll(getAllCars());
        myAdapter.notifyDataSetChanged();
    }

    private void create() {
        if (db != null) {
            ContentValues newInput = new ContentValues();
            newInput.put("id", 1);
            newInput.put("brand", "Cupra");
            newInput.put("name", "Leon");
            newInput.put("model", 2024);
            newInput.put("description","Potencia hasta: 300 HP");
            newInput.put("price", 67490);
            newInput.put("cylinders", 8);
            newInput.put("image", "ruta");
            db.insert("Cars", null, newInput);

            newInput.clear();
            newInput.put("brand", "Audi");
            newInput.put("name", "A5");
            newInput.put("model", 2024);
            newInput.put("description", "El A5 Sportback con un diseño más llamativo, deportivo y puede ser también un auto familiar gracias a sus cinco puertas. Equipado con motores con tecnología TFSI con tres versiones en el mercado mexicano: Select, S line y S line quattro. Cuenta con los servicios de Audi connect con Car2X Safety & Service, Audi phone box, Radio MMI plus con pantalla táctil de 10.1 pulgadas y Audi Virtual Cockpit");
            newInput.put("price", 974900);
            newInput.put("cylinders", 4);
            newInput.put("image", "ruta");
            db.insert("Cars", null, newInput);

            newInput.clear();
            newInput.put("brand", "Mini Cooper");
            newInput.put("name", "Resolute");
            newInput.put("model", 2024);
            newInput.put("description", "Se trata de maximizar la MINIficación. Y, con más espacio para pasajeros y equipaje, puedes estar seguro de que el MINI 5 puertas Hatch te permitirá llevar mucho más en su interior y sacarle el máximo partido. Todo ello sin renunciar a la agilidad de rendimiento y manejo que ha hecho célebre a MINI.");
            newInput.put("price", 812000);
            newInput.put("cylinders", 4);
            newInput.put("image", "ruta");
            db.insert("Cars", null, newInput);

            newInput.clear();
            newInput.put("brand", "Omoda");
            newInput.put("name", "C5 Unlimited");
            newInput.put("model", 2024);
            newInput.put("description", "En ambas versiones el OMODA O5 cuenta con capacidad de cinco pasajeros y cuatro puertas, mide 4.68 metros de largo y 1.8 metros de ancho, con neumáticos que le dan aspecto de auto deportivo, debido a su tamaño, con 205/50 R17. Es decir, 205 milímetros de ancho, 50% de relación entre la altura del lateral y el ancho del neumático. La R es igual al valor “Radial” y el 17, indica la dimensión de la llanta en pulgadas, a esto le añadimos rines de aluminio provocando que sea un vehículo moderno que no pasa desapercibido.");
            newInput.put("price", 455900);
            newInput.put("cylinders", 4);
            newInput.put("image", "ruta");
            db.insert("Cars", null, newInput);

            newInput.clear();
            newInput.put("brand", "Cupra");
            newInput.put("name", "Formentor");
            newInput.put("model", 2024);
            newInput.put("description", "Ahhhh chirrion");
            newInput.put("price", 974900);
            newInput.put("cylinders", 4);
            newInput.put("image", "ruta");
            db.insert("Cars", null, newInput);
        }
    }
    private void deleteAll() {
        db.delete("Cars", "", null);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_item) {
            Intent intent = new Intent(CatalogActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

