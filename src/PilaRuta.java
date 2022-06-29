
public class PilaRuta {
    
    private NodoPilaRuta pilaRuta;
    
    public PilaRuta(){
        pilaRuta=null;
    }
    
    public void insertarRuta(NodoEstado estado){
        NodoPilaRuta nuevo=new NodoPilaRuta();
        nuevo.estado=estado;
        nuevo.siguiente=null;
        
        if(pilaRuta==null){
            pilaRuta=nuevo;
        }else{
            nuevo.siguiente=pilaRuta;
            pilaRuta=nuevo;
        }
    }
    
    public void obtenerRuta(){
        NodoPilaRuta aux=pilaRuta;
        
        while(aux!=null){
            System.out.println("**************************************************");
            System.out.println("*"+"Nombre Nodo:\t\t\t\t"+aux.estado.getNombre()+"  *");
            System.out.println("**************************************************");
            aux.estado.getPilaA().mostrarDatosPilas();
            aux.estado.getPilaB().mostrarDatosPilas();
            aux.estado.getPilaC().mostrarDatosPilas();
            System.out.println("Valor Heuristico:\t\t\t\t"+aux.estado.getFh());
            System.out.println("**************************************************");
            System.out.println("\n");
            aux=aux.siguiente;
        }
    }
    
}
