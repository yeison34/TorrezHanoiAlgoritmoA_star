
public class Principal {
    private static int cont=0;
    private static boolean bandera=false;
    
    static Cerrados cerrado=new Cerrados();
    static Abiertos abiertos=new Abiertos();
    public static void main(String[] args) {
        
        NodoEstado inicial=new NodoEstado(0,0,0,"Padre",null);
        
        inicial.getPilaA().apilar(4);
        inicial.getPilaA().apilar(3);
        inicial.getPilaA().apilar(2);
        inicial.getPilaA().apilar(1);
        inicial.getPilaB().inicializarPila();
        inicial.getPilaC().inicializarPila();
        
        NodoEstado objetivo=new NodoEstado(0,0,0,"Objetivo",null);
        objetivo.getPilaC().apilar(4);
        objetivo.getPilaC().apilar(3);
        objetivo.getPilaC().apilar(2);
        objetivo.getPilaC().apilar(1);
        objetivo.getPilaA().inicializarPila();
        objetivo.getPilaB().inicializarPila();
       
        
        abiertos.insertarAbiertos(inicial);
        int cantidad=0;
        boolean flag=false;                
                        while(abiertos.getAbiertos().abiertos.getFh()!=5){
                              NodoEstado nuevoEstado=abiertos.getAbiertos().abiertos;
                              
                              if(!cerrado.verificarCerrados(nuevoEstado)){
                                  generarGrafo(nuevoEstado);
                                 
                                  System.out.println("Lista sucesores: \t\t"+nuevoEstado.getNombre());
                                  nuevoEstado.sucesores.mostarSucesores();
                                  System.out.println("************************");
                                  cerrado.insertarCerrados(nuevoEstado);
                                 
                                  abiertos.eliminarAbiertos();
                              }else{
                                  
                                  abiertos.eliminarAbiertos();
                              }
                              flag=true;
                         cantidad++;     
                        }
                        
                        
                        if(flag){
                            System.out.println("Exito");
                              System.out.println("Cantidad de Iteraciones: "+cantidad);
                        
                              System.out.println("Lista Abiertos");
                              
                              abiertos.mostrarAbiertos();
                              System.out.println("----------------");

                              System.out.println("Lista Cerrados");
                              cerrado.mostrarCerrados();
                              System.out.println("----------------");
                        }else{
                            System.out.println("Fracaso");
                        }
                              
                              
                              
    }
    
    public static void generarSucesores(NodoEstado estado,String apilar,String desapilar){
        
        bandera=false;
        NodoEstado estadoPadre=estado;
        NodoEstado sucesor=new NodoEstado(0,0,0,"hijo",estadoPadre);
        
        sucesor.getPilaA().inicializarPila();
        sucesor.getPilaB().inicializarPila();
        sucesor.getPilaC().inicializarPila();
        
        llenarPilasSucesores(estadoPadre,sucesor);
        
        if(sucesor.getPilaA().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaB().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaA().pilaVacia()==false){
            sucesor.getPilaB().apilar(sucesor.getPilaA().desapilar().valor);
            bandera=true;
        }
        
        if(sucesor.getPilaA().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaC().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaA().pilaVacia()==false){
            sucesor.getPilaC().apilar(sucesor.getPilaA().desapilar().valor);
            bandera=true;
        }
        
        if(sucesor.getPilaB().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaA().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaB().pilaVacia()==false){
            sucesor.getPilaA().apilar(sucesor.getPilaB().desapilar().valor);
            bandera=true;
        }
        
        if(sucesor.getPilaB().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaC().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaB().pilaVacia()==false){
            sucesor.getPilaC().apilar(sucesor.getPilaB().desapilar().valor);
            bandera=true;
        }
        
        if(sucesor.getPilaC().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaB().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaC().pilaVacia()==false){
            sucesor.getPilaB().apilar(sucesor.getPilaC().desapilar().valor);
            bandera=true;
        }
        
        if(sucesor.getPilaC().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaA().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaC().pilaVacia()==false){
            sucesor.getPilaA().apilar(sucesor.getPilaC().desapilar().valor);
            bandera=true;
        }
        //System.out.println("++++++++++++++++");
        
        //System.out.println("++++++++++++++++");
        if(bandera==true){
            cont++;
            sucesor.setNombre(sucesor.getNombre()+cont);
            
            abiertos.compararDiscos(sucesor);
            //System.out.println(sucesor.getNombre());
            funcionHeuristica(sucesor);
            cerrado.compararDiscos(sucesor);
            estado.sucesores.insertarSucesor(sucesor);
            abiertos.insertarAbiertos(sucesor);
        }   
    }
    
    public static void llenarPilasSucesores(NodoEstado estadoPadre,NodoEstado sucesor){
        NodoEstado estado=estadoPadre;
        NodoPila pilaA=estado.getPilaA().getPila();
        NodoPila pilaB=estado.getPilaB().getPila();
        NodoPila pilaC=estado.getPilaC().getPila();
        
        sucesor.getPilaA().inicializarPila();
        sucesor.getPilaB().inicializarPila();
        sucesor.getPilaC().inicializarPila();
        
        while(pilaA!=null){
            sucesor.getPilaA().apilar(pilaA.valor);
            pilaA=pilaA.siguiente;
        }
        
        NodoPila auxA=sucesor.getPilaA().getPila();
        sucesor.getPilaA().inicializarPila();
        
        while(auxA!=null){
            sucesor.getPilaA().apilar(auxA.valor);
            auxA=auxA.siguiente;
        }
        
        while(pilaB!=null){
            sucesor.getPilaB().apilar(pilaB.valor);
            pilaB=pilaB.siguiente;
        }
        
        NodoPila auxB=sucesor.getPilaB().getPila();
        sucesor.getPilaB().inicializarPila();
        
        while(auxB!=null){
            sucesor.getPilaB().apilar(auxB.valor);
            auxB=auxB.siguiente;
        }
        
        while(pilaC!=null){
            sucesor.getPilaC().apilar(pilaC.valor);
            pilaC=pilaC.siguiente;
        }
        
        NodoPila auxC=sucesor.getPilaC().getPila();
        sucesor.getPilaC().inicializarPila();
        
        while(auxC!=null){
            sucesor.getPilaC().apilar(auxC.valor);
            auxC=auxC.siguiente;
        }
        //Cerrados cerrado=new Cerrados();
        //cerrado.compararDiscos(sucesor);
        
    }
    
    public static void funcionHeuristica(NodoEstado estado){
        NodoEstado nuevoEstado=estado;
        NodoPila pilaA=nuevoEstado.getPilaA().getPila();
        NodoPila pilaB=nuevoEstado.getPilaB().getPila();
        NodoPila pilaC=nuevoEstado.getPilaC().getPila();
        boolean bandera=false;
        int cont=0;
        int comparar=0;
        
        while(pilaA!=null){
            
            if(pilaA.siguiente==null){
                comparar=10;
            }else{
                comparar=pilaA.siguiente.valor;
            }
            
            if(pilaA.valor>comparar){
                bandera=true;
                break;
            }
            pilaA=pilaA.siguiente;
        }
        
        comparar=10;
        while(pilaB!=null){
            
            if(pilaB.siguiente==null){
                comparar=10;
            }else{
                comparar=pilaB.siguiente.valor;
            }
            
            if(pilaB.valor>comparar){
                bandera=true;
                break;
            }
            pilaB=pilaB.siguiente;
        }
        
        comparar=0;
        
        while(pilaC!=null){
            
            if(pilaC.siguiente==null){
                comparar=10;
            }else{
                comparar=pilaC.siguiente.valor;
            }
            
            if(pilaC.valor>comparar){
                bandera=true;
                break;
            }else{
                cont=cont+1;
            }
            pilaC=pilaC.siguiente;
        }
        
        if(bandera==true){
            estado.setH(-1000*-1);
        }else{
            estado.setH(cont);
        }
        
        estado.setG(1);
        estado.setFh(estado.getG()+estado.getH());
        

    }
    
    public static void generarGrafo(NodoEstado Estado){
                NodoEstado nuevoEstado=Estado;
                generarSucesores(nuevoEstado,"A","B");
                generarSucesores(nuevoEstado,"A","C");
                generarSucesores(nuevoEstado,"B","A");
                generarSucesores(nuevoEstado,"B","C");
                generarSucesores(nuevoEstado,"C","A");
                generarSucesores(nuevoEstado,"C","B");
                
                //nuevoEstado.sucesores.mostarSucesores();
                 
    }
}
