from serial import Serial
import psycopg2
import datetime


def receiving():
    ser = Serial('COM3', 9600) #Establezco la conexión con el puerto
    while True:
        velocidad = ser.readline() #Leo el ultimo serial print que mando el arduino
        print velocidad 
        persistir(velocidad) #Persisto el valor obtenido



def persistir(velocidad):
    con = None
    try:
        con = psycopg2.connect(database='LaboratorioProyecto', user='postgres', password = 'root') 
        cur = con.cursor()
        now = datetime.datetime.now()
        cur.execute("Insert into velocidades (velocidad, fecha) values(%s,%s);", (velocidad, now.strftime("%Y-%m-%d %H:%M:%S")))
        con.commit()
        
    except psycopg2.DatabaseError, e:
        print 'Error %s' % e    
        sys.exit(1)
        
    finally:
        if con:
            con.close()
    
receiving()


