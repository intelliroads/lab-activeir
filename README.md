lab-activeir
============

Demo of an active IR sensor wired to an Arduino


calibrar_sensores_laboratorio and medir_velocidad are Arduino Sketches meant for setting up the Arduino for the proyect.
calibrar_sensores_laboratorio´s purpose is simply to calibrate the sensors by determinating the threshold that the
Arduino will interpret as the precense of an object, whereas medir_velocidad is the main sketch that should be running in the Arduino
to detect movement and measure its speed. 

persistirDatosSeriales.py is a simple Python program that takes the serial output of the Arduino as input to persist the 
speed measured, using the PySerial library and PostgreSQL. On the other hand, LaboratorioProyecto is a JavaEE proyect that
uses JSF and Primefaces to display the values obtained with a p:LinearChart.

