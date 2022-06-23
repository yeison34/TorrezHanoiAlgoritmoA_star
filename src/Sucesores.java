
public class Sucesores {
    
    private NodoSucesor listaSucesores;
    
    public Sucesores(){
        listaSucesores=null;
    }
    
    public void insertarSucesor(NodoEstado sucesor){
        NodoSucesor nuevo=new NodoSucesor();
        nuevo.sucesor=sucesor;
        
        if(listaSucesores==null){
            nuevo.siguiente=null;
        }else{
            nuevo.siguiente=listaSucesores;
            
        }
        listaSucesores=nuevo;
    }
    
    public void mostarSucesores(){
        NodoSucesor aux=listaSucesores;
        
        while(aux!=null){
            System.out.println(aux.sucesor.getNombre());
            NodoPila pilaA=aux.sucesor.getPilaA().getPila();
            NodoPila pilaB=aux.sucesor.getPilaB().getPila();
            NodoPila pilaC=aux.sucesor.getPilaC().getPila();

            System.out.println("Pila A");
            
            while(pilaA!=null){
                System.out.println(pilaA.valor);
                pilaA=pilaA.siguiente;
            }
            System.out.println("Pila B");
            while(pilaB!=null){
                System.out.println(pilaB.valor);
                pilaB=pilaB.siguiente;
            }
            System.out.println("Pila C");
            while(pilaC!=null){
                System.out.println(pilaC.valor);
                pilaC=pilaC.siguiente;
            }
            System.out.println("Funcion Heuristica: --->"+aux.sucesor.getFh());
            System.out.println("---------------------------------");
            
            aux=aux.siguiente;
        }
    }
        
    public NodoSucesor listaSucesores(){
        return listaSucesores;
    }
}
