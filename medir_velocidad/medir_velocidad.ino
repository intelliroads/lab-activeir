int sens1,sens2;
boolean pasoSensor1, pasoSensor2;
unsigned long tiempo1, tiempo2; 
double mps,distancia, elap;

void setup()
{
  Serial.begin(9600);
  pinMode (A0, INPUT);
  pinMode (A1, INPUT);
  pinMode (8, OUTPUT);
  pasoSensor1 = false;
  pasoSensor2 = false;
}
void loop()
{
  while(!pasoSensor1){
    sens1 = analogRead(A0);
    if(sens1>900){
      tiempo1 = micros();
      pasoSensor1 = true;
    }
  }
  
  while(!pasoSensor2){
    sens2 = analogRead(A1);
    if(sens2>900){
      tiempo2 = micros();
      pasoSensor2 = true;
    }
  }
 
  if((tiempo2 - tiempo1)>5000){
      elap = (tiempo2 - tiempo1)/1000000.0;
      distancia = 6.2/100;
      Serial.println(distancia/elap);
      digitalWrite(8,HIGH);
      delay(200);
      digitalWrite(8,LOW);
  }
  pasoSensor1 = false;
  pasoSensor2 = false;
}
