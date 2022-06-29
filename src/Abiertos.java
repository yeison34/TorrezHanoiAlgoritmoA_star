
import java.util.ArrayList;


public class Abiertos {
    NodoAbiertos lista;
    Cerrados cerrado=new Cerrados();
    public Abiertos(){
        lista=null;
    }
  
    public void insertarAbiertos(NodoEstado estado){
        NodoAbiertos nuevo=new NodoAbiertos();
        NodoAbiertos aux;
         
        int menor=10000;
        NodoAbiertos aux2;
        aux2 = lista;
        nuevo.abiertos=estado;
        nuevo.siguiente=null;
        if(lista==null){
            lista=nuevo;
        }else{
            aux=lista;
            while(aux!=null && aux.abiertos.getFh()<=estado.getFh()){
                aux2=aux;
                aux=aux.siguiente;                
            }

            if(aux==lista){
                if(lista.abiertos.getFh()==estado.getFh()){
                    aux2.siguiente=nuevo;
                }else{
                    lista=nuevo;
                }
                
            }else{
                aux2.siguiente=nuevo;
            }
            nuevo.siguiente=aux;
        }

    }
    
    public void eliminarAbiertos(){
        
        lista=lista.siguiente;
        
    }
    
    public NodoAbiertos getAbiertos(){
        return lista;
    }
    
    public void mostrarAbiertos(){
        NodoAbiertos aux=lista;
        while(aux!=null){
            System.out.println(aux.abiertos.getNombre());
            aux=aux.siguiente;
        }
    }
    
    public boolean compararDiscos(NodoEstado estadoVerificar){
        
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
        
        
        
        NodoAbiertos auxiliar=lista;
        
        while(auxiliar!=null){
            
            torreauxA.clear();
            torreauxB.clear();
            torreauxC.clear();
            
            NodoEstado auxE=auxiliar.abiertos;
        
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
                
                estadoVerificar.setNombre(auxiliar.abiertos.getNombre());
                return true;

            }
            auxiliar=auxiliar.siguiente;
        }
       return false;
    }
    
    
}
