
 - Vad är Androids 'R' för något, vad innehåller den för information, hur genereras den?
 Hur används den i Android-kod, t.ex. i onCreate i en Activity?
 Det är en inbyggd klass som håller reda på alla id bland resources. Den skapas automatiskt. Den
 används i onCreate, exempelvis i följande rad:
 (EditText) findViewById(R.id.editTextText);. Här hittas rätt "view" genom att söka efter dess id
 som R-klassen håller reda på.

https://stackoverflow.com/questions/6804053/understand-the-r-class-in-android

 - Vad är Gradle? Vad innebär Gradle sync?
 Det är en kompilator som gör ens kod körbar. Den laddar även ned bibliotek som behövs. Gradle sync
 är det som kopplar koden till gradle kompilatorn. Alltså är det det som laddar ner rätt bibliotek,
 kopplar rätt filer osv.

 https://en.wikipedia.org/wiki/Gradle

 - Vad brukar man lägga med för inställningar i de build.gradle filer som finns i varje projekt,
 dvs. a) i build.gradle på projektnivå b) build.gradle på app-nivå

a) Här lägger man till vilken verision av Android man vill använda.
b) Här kan man t.ex. lägga till binding vilket gör att man kan koppla views till kod. Man kan även
lägga till kompilerings inställningar och annat.