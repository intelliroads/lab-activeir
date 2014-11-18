int sens1;
int sens2;

void setup()
{
Serial.begin(9600);
  pinMode (A0, INPUT);
  pinMode (A1, INPUT);
}
void loop()
{
  sens1 = analogRead(A0);
  sens2 = analogRead(A1);
  Serial.println("Primer Sensor");
  Serial.println(sens1);
  Serial.println("Segundo Sensor");
  Serial.println(sens2);
  delay (1000);
}
