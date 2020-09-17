package com.coonfbeer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coonfbeer.R
import com.coonfbeer.model.Conference
import com.coonfbeer.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject

import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonArr = JSONArray("[\n" +
                "            {\n" +
                "                'biography' : 'Mariano Ledesma, farmacéutico, apasionado por los estilos IPA, con tonos ácidos.',\n" +
                "                'category' : 5,\n" +
                "                'image' : 'https://instagram.faep8-1.fna.fbcdn.net/v/t51.2885-15/e35/41793745_901841086677387_8516599375872769580_n.jpg',\n" +
                "                'jobtitle' : 'Content Creator',\n" +
                "                'name' : 'Mariano Ledesma',\n" +
                "                'twitter' : 'meledesma',\n" +
                "                'workplace' : 'CEO LAGASH'\n" +
                "            },\n" +
                "            {\n" +
                "                'biography' : 'Soy Win. Especialista en lupulo Hago fotografía los domingos.',\n" +
                "                    'category' : 12,\n" +
                "             'image' : 'https://instagram.faep8-2.fna.fbcdn.net/v/t51.2885-15/e35/85103358_502876907086248_5521988066124257315_n.jpg',\n" +
                "                'jobtitle' : 'Head of Production',\n" +
                "                'name' : 'Julio Lian',\n" +
                "                'twitter' : 'Julian',\n" +
                "                'workplace' : 'CEO DRAKAR'\n" +
                "            },\n" +
                "            {\n" +
                "                'biography' : 'CEO y Fundador de Capptu, cerveza  de México.',\n" +
                "                'category' : 10,\n" +
                "                'image' : 'https://pbs.twimg.com/profile_images/1042801434989879296/Dya62rEB_400x400.jpg',\n" +
                "                'jobtitle' : 'Founder & CEO',\n" +
                "                'name' : 'Manuel Villegas',\n" +
                "                'twitter' : 'manvillegasmx',\n" +
                "                'workplace' : 'Capptu'\n" +
                "            },\n" +
                "            {\n" +
                "                'biography' : 'Líder del equipo de Growth en DRAKAR especialista en cocción.',\n" +
                "                'category' : 9,\n" +
                "                'image' : 'https://pbs.twimg.com/profile_images/1128429578647736321/5ZwuI5_Q_400x400.jpg',\n" +
                "                'jobtitle' : 'Head of Growth',\n" +
                "                'name' : 'Juan Pablo Rojas',\n" +
                "                'twitter' : 'esto',\n" +
                "                'workplace' : 'DRAKAR'\n" +
                "            },\n" +
                "        ]")


        val jsonArr2 = JSONArray("[\n" +
                "            {\n" +
                "                \"datetime\" : 1564830000,\n" +
                "                \"description\" : \"Yo les voy a hablar el día de hoy de como mejorar las recetas con lúpulos alemanes.\",\n" +
                "                \"speaker\" : \"Mariano ledesma\",\n" +
                "                \"tag\" : \"Recetas\",\n" +
                "                \"title\" : \"Recetas magicas\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564862400,\n" +
                "                \"description\" : \"En esta charla Erika Oregel del equipo de la Berlina nos cuenta su experiencia en cocion. \",\n" +
                "                \"speaker\" : \"Erika Oregel\",\n" +
                "                \"tag\" : \"Procastinación\",\n" +
                "                \"title\" : \"Procrastinar puede ser el alimento de tus futuras pasiones\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564860000,\n" +
                "                \"description\" : \"Como poder gestionar buenas practicas.\",\n" +
                "                \"speaker\" : \"Veronica Calderón\",\n" +
                "                \"tag\" : \"Cheaf\",\n" +
                "                \"title\" : \"Periodismo en 2019: Constar historias no debe ser aburrido.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564861200,\n" +
                "                \"description\" : \"Tener una marca personal es fundamental para tu desarrollo profesional .\",\n" +
                "                \"speaker\" : \"Rubén Gómez\",\n" +
                "                \"tag\" : \"Diseño\",\n" +
                "                \"title\" : \"¿Tienes una marca personal o solo redes sociales?\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564837200,\n" +
                "                \"description\" : \"Hoy quiero hablarles de los dos tipos de personas que existen en el mundo, hay personas que consumen y hay personas que crean.\",\n" +
                "                \"speaker\" : \"Cesar Fajardo\",\n" +
                "                \"tag\" : \"Content\",\n" +
                "                \"title\" : \"Un producto de calidad marca la diferencia\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564858800,\n" +
                "                \"description\" : \"En esta charla Andrea Baba, CEO de FITco, nos explica  para complementar tu carrera profesional. \",\n" +
                "                \"speaker\" : \"Andrea Baba\",\n" +
                "                \"tag\" : \"Firness\",\n" +
                "                \"title\" : \"Una buen FIT te hace un mejor profesional\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564826400,\n" +
                "                \"description\" : \"Buenas practicas para crecer.   \",\n" +
                "                \"speaker\" : \"Christian Henst\",\n" +
                "                \"tag\" : \"Motivacional\",\n" +
                "                \"title\" : \" Creador y luego emprendedor\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564861800,\n" +
                "                \"description\" : \"En esta charla Nicolay Coral nos explica cómo construimos sentimientos a través del cúmulo de emociones repetitivas  y cómo al lograr identificar qué y quién despiertan nuestras emociones podemos desaprender a ser felices. \",\n" +
                "                \"speaker\" : \"Nicolay Coral\",\n" +
                "                \"tag\" : \"Felicidad\",\n" +
                "                \"title\" : \"Desaprender a ser Feliz | Diferencia entre emociones y sentimientos\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564840800,\n" +
                "                \"description\" : \"Les quiero comentar de cómo se pueden convertir líderes en tu negocio.\",\n" +
                "                \"speaker\" : \"Pablo Villanueva\",\n" +
                "                \"tag\" : \"Desarrollo\",\n" +
                "                \"title\" : \"Cómo convertirte en la persona más valiosa de una empresa\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564860600,\n" +
                "                \"description\" : \"Redefiniendo la competencia en el mercado financiero: Open Banking\",\n" +
                "                \"speaker\" : \"Elina Corona\",\n" +
                "                \"tag\" : \"Bank\",\n" +
                "                \"title\" : \"Redefiniendo la competencia en el mercado financiero: Open Banking\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564848000,\n" +
                "                \"description\" : \"Empresa de desarrollo de nuevas marcas.\",\n" +
                "                \"speaker\" : \"Jorge García\",\n" +
                "                \"tag\" : \"Git\",\n" +
                "                \"title\" : \"La base esta en el tiempo\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564855200,\n" +
                "                \"description\" : \"Entusiastas para crear una gran empresa.\",\n" +
                "                \"speaker\" : \"Manuel Villegas\",\n" +
                "                \"tag\" : \"Emprendimiento\",\n" +
                "                \"title\" : \"La fabrication es el negocio\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564859400,\n" +
                "                \"description\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\",\n" +
                "                \"speaker\" : \"Ludwin Cuevas\",\n" +
                "                \"tag\" : \"Creative\",\n" +
                "                \"title\" : \"5 consejos para liderar equipos grandes\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564844400,\n" +
                "                \"description\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua..\",\n" +
                "                \"speaker\" : \"Isis García\",\n" +
                "                \"tag\" : \"Voz\",\n" +
                "                \"title\" : \"Cómo comunicarte con tu equipo de produccion\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564833600,\n" +
                "                \"description\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua..\",\n" +
                "                \"speaker\" : \"Claudia Sosa\",\n" +
                "                \"tag\" : \"Negocios\",\n" +
                "                \"title\" : \"Crear workshops para comprender, idear y decidir\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564851600,\n" +
                "                \"description\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\",\n" +
                "                \"speaker\" : \"Juan Pablo Rojas\",\n" +
                "                \"tag\" : \"Estadística\",\n" +
                "                \"title\" : \"5 formas de producir calidad\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564822800,\n" +
                "                \"description\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\",\n" +
                "                \"speaker\" : \"Rodrigo Lostu\",\n" +
                "                \"tag\" : \"Motivacional\",\n" +
                "                \"title\" : \"Cómo desarrollar tu carrera profesional en cervesas\"\n" +
                "            }\n" +
                "        ]")


               val firebaseFirestore = FirebaseFirestore.getInstance()

               for (i in 0 until jsonArr.length()-1) {
                   val aux = jsonArr.get(i) as JSONObject
                   val speaker = Speaker()

                   speaker.name = aux.getString("name")
                   speaker.jobTitle = aux.getString("jobtitle")
                   speaker.workPlace = aux.getString("workplace")
                   speaker.biography = aux.getString("biography")
                   speaker.twitter = aux.getString("twitter")
                   speaker.image = aux.getString("image")
                   speaker.category = aux.getString("category")

                   firebaseFirestore.collection("speakers").document().set(speaker)
               }

               for (i in 0 until jsonArr2.length()-1) {
                   val aux = jsonArr2.get(i) as JSONObject
                   val conference = Conference()
                   val cal = Calendar.getInstance()
                   cal.timeInMillis = aux.getLong("datetime")

                   conference.title = aux.getString("title")
                   conference.description = aux.getString("description")
                   conference.tag = aux.getString("tag")
                   conference.dateTime = cal.time
                   conference.speaker = aux.getString("speaker")

                   firebaseFirestore.collection("conferences").document().set(conference)
               }
    }
}
