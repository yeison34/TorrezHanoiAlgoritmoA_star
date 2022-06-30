
public class Principal {
    private static int cont=0;
    private static boolean bandera=false;
    
    static Cerrados cerrado=new Cerrados();
    static Abiertos abiertos=new Abiertos();
    
    public static void main(String[] args) {
        ///estado inicial
        NodoEstado inicial=new NodoEstado(0,0,0,"Padre",null);
        
        //carga los discos a la pila A, de donde parten los discos, en este caso en la torre A
        inicial.getPilaA().apilar(4);
        inicial.getPilaA().apilar(3);
        inicial.getPilaA().apilar(2);
        inicial.getPilaA().apilar(1);
        
        //inicializa la torre B como null
        inicial.getPilaB().inicializarPila();
        
        //inicializa la torre C con null
        inicial.getPilaC().inicializarPila();
        
        //estado objetivo
        NodoEstado objetivo=new NodoEstado(0,0,5,"Objetivo",null);
        
        //el objetivo es que los discos queden en la torre c
        objetivo.getPilaC().apilar(4);
        objetivo.getPilaC().apilar(3);
        objetivo.getPilaC().apilar(2);
        objetivo.getPilaC().apilar(1);
        
        //la torre A se inicializa como null
        objetivo.getPilaA().inicializarPila();
        
        //la torre B se inicializa como null
        objetivo.getPilaB().inicializarPila();
       
        //en la lista de abiertos que es una lista de prioridades en donde siempre se ordena desde el primero que llegue y con el mejor valor heuristico
        abiertos.insertarAbiertos(inicial);
        
        //contador para saber la cantidad de iteraciones del algoritmo
        //la cantidad de iteraciones tambien incluye como iteracion el paso de un abierto que ya esta en cerrado 
        //pero no genera los sucersores sino que lo elimina de abiertos ya que esta en cerrados
        int cantidad=0;
        
        //bandera para determinar si ya se llego al estado objetivo
        boolean flag=false;                
                        while(abiertos.getAbiertos().abiertos.getFh()!=objetivo.getFh()){
                              NodoEstado nuevoEstado=abiertos.getAbiertos().abiertos;
                              
                              if(!cerrado.verificarCerrados(nuevoEstado)){
                                  generarGrafo(nuevoEstado);
                                  
                                  /*System.out.println("Lista sucesores: \t\t"+nuevoEstado.getNombre());
                                  nuevoEstado.sucesores.mostarSucesores();
                                  System.out.println("************************");
                                  */
                                  
                                  cerrado.insertarCerrados(nuevoEstado);
                                  abiertos.eliminarAbiertos();
                              }else{
                                  abiertos.eliminarAbiertos();
                              }
                              flag=true;
                         cantidad++;     
                        }
                        
                        
                        if(flag){
                            cerrado.insertarCerrados(abiertos.getAbiertos().abiertos);
                           
                            System.out.println("Exito");
                            System.out.println("********************* RUTA ****************************");
                            //System.out.println(abiertos.getAbiertos().abiertos.getNombre());
                            
                            NodoEstado aux=abiertos.getAbiertos().abiertos;
                            PilaRuta pilaRuta=new PilaRuta();
                            
                            while(!aux.getNombre().equalsIgnoreCase("Padre")){
                                pilaRuta.insertarRuta(aux);
                                aux=aux.getPadre();
                            }
                            pilaRuta.obtenerRuta();
                              /*System.out.println("Cantidad de Iteraciones: "+cantidad);
                        
                              System.out.println("Lista Abiertos");
                              
                              abiertos.mostrarAbiertos();
                              System.out.println("----------------");

                              System.out.println("Lista Cerrados");
                              cerrado.mostrarCerrados();
                              System.out.println("----------------");
                              */
                        }else{
                            System.out.println("Fracaso");
                        }
                              
                              
                              
    }
    //metodo para generar los sucesores de cada uno de los estados
    //el parametro apilar es el nombre de la torre que va a quitar un disco para desplazarlo a la otra torre
    //el parametro desapilar es nombre de la torre a donde se va adirigir el disco que va a desapilar la torre anterior 
    public static void generarSucesores(NodoEstado estado,String apilar,String desapilar){
        
        bandera=false;
        NodoEstado estadoPadre=estado;
        //se genera un sucesor y se le asigno todos sus parametros incluyendo a su padre
        NodoEstado sucesor=new NodoEstado(0,0,0,"hijo",estadoPadre);
        
        //inicializa las pilas en null
        sucesor.getPilaA().inicializarPila();
        sucesor.getPilaB().inicializarPila();
        sucesor.getPilaC().inicializarPila();
        
        //este metodo se utiliza para llenar las pilas o torrez de cada nodo sucesor
        llenarPilasSucesores(estadoPadre,sucesor);
        
        //esta es la operacion
        if(sucesor.getPilaA().getNombre().equalsIgnoreCase(apilar)&&sucesor.getPilaB().getNombre().equalsIgnoreCase(desapilar)&&sucesor.getPilaA().pilaVacia()==false){
            sucesor.getPilaB().apilar(sucesor.getPilaA().desapilar().valor);
            bandera=true;
        }
        //esta es la operacion
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
        //si se genero el estado sucesor la bandera se hace true y esto quiere decir que el estado sucesor se genero correctamente
        if(bandera==true){
            //este contador es para dar un nombre al hijo ej.1-2-3....
            cont++;
            sucesor.setNombre(sucesor.getNombre()+cont);
            //compara el sucesor si esta en abiertos para colocarle el mismo nombre y no se repita la ruta, ya que en un momento llegara a ser visitado
            abiertos.compararDiscos(sucesor);
            //System.out.println(sucesor.getNombre());
            //metodo asignamos la funcion heuristica al nodo sucesor
            funcionHeuristica(sucesor);
            
            //comparamos en la lista de cerrados el nodo sucesor para que de igual manera no lo tenga encuenta ya que ya ha sido visitado
            cerrado.compararDiscos(sucesor);
            
            //insertamos los nodos sucesores al padre
            estado.sucesores.insertarSucesor(sucesor);
            
            //insertamos los nodos sucesores a la lista de abiertos
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
        
        //llena las torrez de los nodos sucesores
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
    
    //metodo para calcular la funcion heuristica  del estado
    /*
        nuestra-> cuenta los discos que estan en la torrez C este valor lo representa la variable h y g siempre suma uno
        por tanto la funcion heuristica es fh=h+g
    
        valores a tener en cuenta:
        si un disco mayor esta sobre un disco menor la funcion heuristica toma un 1000, ya esta estaria mal,
        por lo cual el algoritmo siempre va a escoger 
        el valor heuristico menor
    */
    public static void funcionHeuristica(NodoEstado estado){
        
        NodoEstado nuevoEstado=estado;
        
        NodoPila pilaA=nuevoEstado.getPilaA().getPila();
        NodoPila pilaB=nuevoEstado.getPilaB().getPila();
        NodoPila pilaC=nuevoEstado.getPilaC().getPila();
        
        boolean bandera=false;
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
            estado.setH(1000);
        }else{
            estado.setH(cont);
        }
        
        estado.setG(1);
        estado.setFh(estado.getG()+estado.getH());
        
    }
    //metodo para generar el grafo
    public static void generarGrafo(NodoEstado Estado){
                NodoEstado nuevoEstado=Estado;
                generarSucesores(nuevoEstado,"A","B");
                generarSucesores(nuevoEstado,"A","C");
                generarSucesores(nuevoEstado,"B","A");
                generarSucesores(nuevoEstado,"B","C");
                generarSucesores(nuevoEstado,"C","A");
                generarSucesores(nuevoEstado,"C","B");
                
    }
}
