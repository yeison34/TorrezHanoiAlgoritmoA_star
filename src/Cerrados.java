
import java.util.ArrayList;
import java.util.Arrays;


public class Cerrados{
    NodoCerrados lista;
    
    public Cerrados(){
        lista=null;
    }
    
    public void insertarCerrados(NodoEstado estado){
        
        NodoCerrados nuevo=new NodoCerrados();
        nuevo.estados=estado;
        nuevo.siguiente=null;
        
        if(lista==null){
            lista=nuevo;
        }else{
            NodoCerrados aux=lista;
            while(aux.siguiente!=null){
                aux=aux.siguiente;
            }
            aux.siguiente=nuevo;
        }   
    }
    
    public boolean verificarCerrados(NodoEstado estadoVerificar){
        NodoEstado verificar=estadoVerificar;
        NodoCerrados aux=lista;
        while(aux!=null){
            if(verificar.getNombre().equalsIgnoreCase(aux.estados.getNombre())){
                return true;
            }
            aux=aux.siguiente;
        }
        return false;
    }
    
    public void compararDiscos(NodoEstado estadoVerificar){
        
        ArrayList<Integer> torrea,torreb,torrec;
        NodoEstado aux=estadoVerificar;

        torrea=new ArrayList<>();
        torreb=new ArrayList<>();
        torrec=new ArrayList<>();
        
        torrea.clear();
        torreb.clear();
        torreb.clear();
        
        NodoPila aux2=aux.getPilaA().valor();
        
        while(aux2!=null){
            torrea.add(aux2.valor);
            aux2=aux2.siguiente;
        }
        
        aux2=aux.getPilaB().valor();
        while(aux2!=null){
            torreb.add(aux2.valor);
            aux2=aux2.siguiente;
        }

        aux2=aux.getPilaC().valor();
        while(aux2!=null){
            torrec.add(aux2.valor);
            //iterador++;
            aux2=aux2.siguiente;
        }

        ArrayList<Integer> torreauxA,torreauxB,torreauxC;
        torreauxA=new ArrayList<>();
        torreauxB=new ArrayList<>();
        torreauxC=new ArrayList<>();
        
        
        
        NodoCerrados auxiliar=lista;
        
        while(auxiliar!=null){
            torreauxA.clear();
            torreauxB.clear();
            torreauxC.clear();
            
            NodoEstado auxE=auxiliar.estados;
        
            NodoPila aux2E=auxE.getPilaA().valor();
        
            while(aux2E!=null){
                torreauxA.add(aux2E.valor);
                aux2E=aux2E.siguiente;
            }
            
            aux2E=auxE.getPilaB().valor();
            while(aux2E!=null){
                torreauxB.add(aux2E.valor);
                //i++;
                aux2E=aux2E.siguiente;
            }
            
            aux2E=auxE.getPilaC().valor();
            while(aux2E!=null){
                torreauxC.add(aux2E.valor);
                //i++;
                aux2E=aux2E.siguiente;
            }
            
            if(torrea.equals(torreauxA)&&torreb.equals(torreauxB)&&torrec.equals(torreauxC)){
                aux.setNombre(auxiliar.estados.getNombre());
                //System.out.println(aux.getNombre());
                return;
                //break;
                //System.out.println("yes");
            }
            auxiliar=auxiliar.siguiente;
        }
       
    }
    
    public void mostrarCerrados(){
        NodoCerrados aux=lista;
        while(aux!=null){
            System.out.println(aux.estados.getNombre());
            aux=aux.siguiente;
        }
    }
    
    public NodoCerrados getCerrado(){
        lista=lista.siguiente;
        return lista;
    }
    
}
