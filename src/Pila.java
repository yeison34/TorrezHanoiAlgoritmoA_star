
import java.util.ArrayList;


public class Pila {
    private NodoPila pila;
    private String nombre;
    
    public Pila(String nombre){
        pila=null;
        this.nombre=nombre;
    }
    
    public Pila(){
        pila=null;
    }
    
    public void apilar(int n){
    
        NodoPila nuevo=new NodoPila();
        nuevo.valor=n;
        if(pila==null){
            pila=nuevo;
            nuevo.siguiente=null;
        }else{
            nuevo.siguiente=pila;
            pila=nuevo;
        }
    }
    
    public NodoPila valor(){
        return pila;
    }
    
    public NodoPila desapilar(){
        NodoPila aux=pila;
        pila=pila.siguiente;
        return aux;
    }
    
    public int cantidad(){
        int cant=0;
        NodoPila aux=pila;
        while(aux!=null){
            cant++;
            aux=aux.siguiente;
        }
        return cant;
    }
    
    public void inicializarPila(){
        pila=null;
    }
    
    public String getNombre(){
        return nombre;
    }
 
    public NodoPila getPila(){
        return pila;
    }
    
    public boolean pilaVacia(){
        return pila==null;
    }
    
    public void mostrarDatosPilas(){
        NodoPila aux=pila;
        ArrayList<Integer> pilas=new ArrayList<>();
        pilas.clear();
        while(aux!=null){
            pilas.add(aux.valor);
            aux=aux.siguiente;
        }
        System.out.println("Pila"+this.nombre);
        for(int i=0;i<pilas.size();i++){
            System.out.println(pilas.get(i));
        }
        System.out.println("---------------------");
    }
    
    public int cantidadValores(){
        NodoPila aux=pila;
        int contadorDatos=0;
        while(aux!=null){
            contadorDatos++;
            aux=aux.siguiente;
        }
        return contadorDatos;
    }
}
