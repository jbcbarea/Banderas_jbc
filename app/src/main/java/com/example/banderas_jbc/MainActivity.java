package com.example.banderas_jbc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int aleatorio1;
    private int aleatorio2;
    private int Numero_aleatorio_TextView;
    private int puntuacion=0;
    private int puntuacionsumatorio=0;
    private ArrayList<Integer> TopScore;

    private int[] banderas = new int[]{
            R.drawable.ag, R.drawable.dm, R.drawable.ec, R.drawable.gt, R.drawable.eg, R.drawable.es, R.drawable.ar, R.drawable.ch,
            R.drawable.ci, R.drawable.cl, R.drawable.cm, R.drawable.cz, R.drawable.de, R.drawable.cy, R.drawable.gy, R.drawable.cu,
            R.drawable.pa, R.drawable.uy, R.drawable.vc, R.drawable.us
    };

    private String[] paises = new String[]{"ANTIGUA Y BARBUDA", "DOMINICA", "ECUADOR", "GUATEMALA", "EGIPTO", "ESPAÑA", "ARGENTINA", "SUIZA",
            "IRLANDA", "CHILE", "CAMERUN", "REPUBLICA CHECA", "ALEMANIA", "CHIPRE", "GUYANA", "CUBA", "PANAMA", "URUGUAY", "SAN VICENTE Y LAS GRANADINAS",
            "ESTADOS UNIDOS DE AMERICA"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopScore = new ArrayList<>();
        TopScore.add(puntuacion);
        Generar_2_Num_Aleatorios();

        TextView V;
        V = findViewById(R.id.textView);

        Numero_aleatorio_TextView=(int) aleatorio1_2(aleatorio1,aleatorio2);

        V.setText("Indica la bandera de:  "+paises[Numero_aleatorio_TextView]);


//ImageButton1. Al principio llevara el aleatorio 1 que correspondera a una bandera

        ImageButton b1;
        b1= findViewById(R.id.imageButton0);
        b1.setImageResource(banderas[aleatorio1]);


        //Referenciamos ImageButton 2
        ImageButton b2;
        b2= findViewById(R.id.imageButton1);
        b2.setImageResource(banderas[aleatorio2]);


        //Listener cuando pulsamos en ImageButto0
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si pulsamos el boton 0 y el numero aleatorio del TextView coincide con el aleatorio 1 que corresponderá con una bandera con número de posicion del Array aleatorio 1
                if ( Numero_aleatorio_TextView == aleatorio1){
                    //Hemos acertado por la tanto sumamos un punto a continuación generaremos nuevas banderas y un nuevo textview donde se pedirá descubrir otra bandera
                    puntuacion++;
                    Generar_2_Num_Aleatorios();

                    TextView V;
                    V = findViewById(R.id.textView);

                    Numero_aleatorio_TextView=(int) aleatorio1_2(aleatorio1,aleatorio2);
                    V.setText("Indica la bandera de:  "+paises[Numero_aleatorio_TextView]);

                    //Pintaremos nuevas banderas aleatorias para descubrir nuevamente
                    ImageButton b1;
                    b1=findViewById(R.id.imageButton0);
                    b1.setImageResource(banderas[aleatorio1]);

                    ImageButton b2;
                    b2= findViewById(R.id.imageButton1);
                    b2.setImageResource(banderas[aleatorio2]);

                    //Guardamos las puntuaciones en un ArrayList de enteros despues al fallar recorreremos dicha lista para que nos diga cual es el mayor
                    //Para asegurarnos que nos muestre la puntuación más alta solo guarda de nuevo en la lista  si la puntuacion max es menor en la ronda anterior
                    if (puntuacionsumatorio<puntuacion){
                        TopScore= new ArrayList<Integer>();
                        TopScore.add(puntuacion);
                    }
                } else {

                    //Si fallamos nos muestra un mensaje mediante un toast donde nos dice la puntuación obtenida en la ronda y la puntuación más alta para batir
                    Toast.makeText(getApplicationContext(),"Has perdido, tu puntuación es de:  "+String.valueOf(puntuacion)+"  La puntuación más alta a batir es de:  "+String.valueOf(PuntuacionMasAlta()),Toast.LENGTH_SHORT).show();
                    //Empezamos el juego de nuevo dibujando en los ImageButton nuevas banderas y estableciendo la puntuación de la nueva ronda a cero
                    puntuacionsumatorio = PuntuacionMasAlta();

                    EmpezarJuego();

                }
            }
        });
        //Listener cuando pulsamos en ImageButton1
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //Si pulsamos el boton 1 y el nombre del pais indicado en el TextView coincide con el aleatorio 2 que corresponderá con una bandera con número de posicion del Array aleatorio 2
                if ( Numero_aleatorio_TextView == aleatorio2){
                    //Hemos acertado por la tanto sumamos un punto a continuación generaremos nuevas banderas y un nuevo textview donde se pedirá descubrir otra bandera
                    puntuacion++;
                    Generar_2_Num_Aleatorios();

                    TextView V;
                    V = findViewById(R.id.textView);

                    Numero_aleatorio_TextView=(int) aleatorio1_2(aleatorio1,aleatorio2);

                    V.setText("Indica la bandera de:  "+paises[Numero_aleatorio_TextView]);

                    ImageButton b1;
                    b1=findViewById(R.id.imageButton0);
                    b1.setImageResource(banderas[aleatorio1]);

                    ImageButton b2;
                    b2= findViewById(R.id.imageButton1);
                    b2.setImageResource(banderas[aleatorio2]);

                    //Para asegurarnos que nos muestre la puntuación más alta solo guarda de nuevo en la lista  si la puntuacion max es menor en la ronda anterior
                    if (puntuacionsumatorio<puntuacion){
                        TopScore= new ArrayList<Integer>();
                        TopScore.add(puntuacion);
                    }
                } else {

                    //Si fallamos nos muestra un mensaje mediante un toast donde nos dice la puntuación obtenida en la ronda y la puntuación más alta para batir
                    Toast.makeText(getApplicationContext(),"Has perdido, tu puntuación es de:  "+String.valueOf(puntuacion)+"  La puntuación más alta a batir es de:  "+String.valueOf(PuntuacionMasAlta()),Toast.LENGTH_SHORT).show();
                    //Empezamos el juego de nuevo dibujando en los ImageButton nuevas banderas y estableciendo la puntuación de la nueva ronda a cero
                    puntuacionsumatorio=PuntuacionMasAlta();
                    EmpezarJuego();

                }

            }

        } );


        //Metodos-----------------------------------------
    }

    //De los dos numeros aleatorios obtenidos dos numeros aleatorios comprendidos entre 0 y 19 que serían las posiciones de los recursos en el array de banderas y paises
    //nos da solo uno que es el que aparecería en el textview para descubrir nuevas banderas
    private double aleatorio1_2(int aleatorio1, int aleatorio2 ){

        int numero_aleatorio = 0;
        double prueba;
        double azar= 0.5;
        prueba=Math.random();

        if ( prueba <= azar) {

            numero_aleatorio=aleatorio1;

        }  else{
            numero_aleatorio=aleatorio2;
        }

        return numero_aleatorio;
    }
    //Genera dos numeros aleatorios entre 0 y 19
    private void Generar_2_Num_Aleatorios (){

        do {
            aleatorio1 = (int)Math.floor(Math.random()*(19-0+1)+0);
            aleatorio2 = (int)Math.floor(Math.random()*(19-0+1)+0);
        } while (aleatorio1 == aleatorio2);

    }

    //Recorre lista con las puntuaciones obtenidas dandonos la mas alta
    private int PuntuacionMasAlta() {

        int topscore=0;
        topscore =TopScore.get(0);
        for (int i=0;i<TopScore.size();i++){

            if (TopScore.get(i)>topscore){

                topscore=TopScore.get(i) + i;
            }
        }

        return topscore;
    }
    //Pinta de nuevo banderas aleatorias establece a cero la puntuacion
    private void EmpezarJuego(){

        puntuacionsumatorio=PuntuacionMasAlta();
        puntuacion=0;
        Generar_2_Num_Aleatorios();

        TextView V;
        V = findViewById(R.id.textView);

        Numero_aleatorio_TextView=(int) aleatorio1_2(aleatorio1,aleatorio2);

        V.setText("Indica la bandera de :  "+paises[Numero_aleatorio_TextView]);

        ImageButton b1;
        b1= findViewById(R.id.imageButton0);
        b1.setImageResource(banderas[aleatorio1]);

        ImageButton b2;
        b2= findViewById(R.id.imageButton1);
        b2.setImageResource(banderas[aleatorio2]);
    }
}


